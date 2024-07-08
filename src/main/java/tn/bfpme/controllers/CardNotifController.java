package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Notification;
import tn.bfpme.models.Statut;
import tn.bfpme.services.ServiceConge;
import tn.bfpme.services.ServiceNotification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardNotifController implements Initializable {
    @FXML private ImageView IconNotif;
    @FXML private Label NotifContent;
    @FXML private Pane Card;

    private Notification notification;
    private final ServiceNotification ServiceNotif = new ServiceNotification();
    private paneNotifController paneNotifController;

    public void setPaneNotifController(paneNotifController paneNotifController) {
        this.paneNotifController = paneNotifController;
    }

    public void setData(Notification notification) {
        this.notification = notification;
        String iconN = "";
        if (notification.getStatut().equals(Statut.Approuvé)) {
            iconN = "src/main/resources/assets/imgs/approved.png";
        }
        if (notification.getStatut().equals(Statut.Rejeté)) {
            iconN = "src/main/resources/assets/imgs/declined.png";
        }
        if (notification.getStatut() == null){
            iconN = "1";
        }
        try {
            File file = new File(iconN);
            FileInputStream inputStream = new FileInputStream(file);
            Image image = new Image(inputStream);
            IconNotif.setImage(image);
        } catch (FileNotFoundException e) {
            System.err.println("Image file not found: " + iconN);
        }
        Card.setStyle("-fx-border-radius: 5px;-fx-border-color:#808080");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void DeleteNotif(ActionEvent event) {
        ServiceNotif.DeleteNotif(this.notification.getIdNotif());
        if (paneNotifController != null) {
            paneNotifController.load();
        }
    }

}
