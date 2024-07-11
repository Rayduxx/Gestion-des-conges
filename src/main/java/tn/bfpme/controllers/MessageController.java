package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Notification;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceConge;
import tn.bfpme.services.ServiceNotification;

public class MessageController {
    @FXML
    private Label labelCC;
    @FXML
    private Label labelMSG;

    private final ServiceNotification notifService = new ServiceNotification();
    private Notification notif;

    public void setData(Notification notif) {
        this.notif = notif;
        labelMSG.setText(notif.getNotifcontent());
        labelCC.setText(notif.getNotification());
    }

    @FXML
    void fermer(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
