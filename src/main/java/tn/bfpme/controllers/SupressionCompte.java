package tn.bfpme.controllers;

import com.twilio.rest.chat.v1.service.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.bfpme.models.Utilisateur;
import tn.bfpme.services.ServiceEmploye;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;



import static tn.bfpme.models.Utilisateur.getCurrent_User;

public class SupressionCompte {

    private final ServiceEmploye UserS = new ServiceEmploye();
    @FXML private Label labelSupp;
    @FXML private TextField mdpSUpp;

    @FXML
    void annuler_supression(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void supprimer_user(ActionEvent actionEvent) {
        try {

            if (mdpSUpp.getText().equals(SessionManager.getInstance().getUtilisateur().getMdp())) {
                UserS.deleteByID(SessionManager.getInstance().getUtilisateur().getIdUser());
                labelSupp.setText("Suppression Effectue");
                SessionManager.getInstance().cleanUserSession();
                StageManager.closeAllStages();
                restartApplication();
            } else {
                labelSupp.setText("Suppression Non Effectue: Incorrect Password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred during deletion.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    private void restartApplication() {
        Platform.runLater(() -> {
            try {
                final Stage primaryStage = new Stage();
                new tn.bfpme.test.MainFX().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
