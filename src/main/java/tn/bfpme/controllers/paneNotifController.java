package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import tn.bfpme.models.Conge;
import tn.bfpme.services.ServiceConge;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class paneNotifController implements Initializable {
    @FXML
    private GridPane NotifContainer;


    private final ServiceConge CongeS = new ServiceConge();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load();
    }

    public void load() {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        NotifContainer.getColumnConstraints().add(columnConstraints);
        NotifContainer.setVgap(4);
        NotifContainer.setPadding(new Insets(4));
        int row = 0;
        try {
            NotifContainer.getChildren().clear();
            for (Conge conge : CongeS.AfficherNotifications()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CardNotif.fxml"));
                Pane CardBox = fxmlLoader.load();
                CardNotifController NotifCardC = fxmlLoader.getController();
                NotifCardC.setData(conge);
                NotifCardC.setPaneNotifController(this);
                NotifContainer.add(CardBox, 0, row++);
                GridPane.setMargin(CardBox, new Insets(4, 4, 4, 4));
                CardBox.setMaxWidth(Double.MAX_VALUE);
                NotifContainer.setColumnSpan(CardBox, GridPane.REMAINING);
                GridPane.setHalignment(CardBox, javafx.geometry.HPos.CENTER);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void EffacerTout(ActionEvent event) {
        CongeS.DeleteAllUserNotif();
        load();
    }
}
