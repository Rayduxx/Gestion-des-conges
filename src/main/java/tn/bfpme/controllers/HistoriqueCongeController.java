package tn.bfpme.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import tn.bfpme.models.Conge;
import tn.bfpme.services.ServiceConge;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class HistoriqueCongeController implements Initializable  {

        private final ServiceConge CongeS = new ServiceConge();
        private Connection cnx;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            load();
        }

        @FXML
        private GridPane congeContainer;
        public void load() {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.ALWAYS);
            congeContainer.getColumnConstraints().add(columnConstraints);
            congeContainer.setVgap(4);
            congeContainer.setPadding(new Insets(4));
            int row = 0;
            try {
                for (Conge conge : CongeS.afficher()) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/CongeCarte.fxml"));
                    Pane CardBox = fxmlLoader.load();
                    CongeCarteController cardC = fxmlLoader.getController();
                    cardC.setData(conge);

                    congeContainer.add(CardBox, 0 , row++);
                    GridPane.setMargin(CardBox, new Insets(4, 4, 4, 4));
                    CardBox.setMaxWidth(Double.MAX_VALUE);
                    congeContainer.setColumnSpan(CardBox, GridPane.REMAINING);
                    GridPane.setHalignment(CardBox, javafx.geometry.HPos.CENTER);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}
