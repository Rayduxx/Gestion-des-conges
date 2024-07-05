package tn.bfpme.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import tn.bfpme.models.Conge;
import tn.bfpme.models.User;
import tn.bfpme.utils.Mails;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MailingDemandeController implements Initializable {
    ObservableList<String> RaisonsList = FXCollections.observableArrayList(
            "Alternative Proposée",
            "Équité et Équilibre",
            "Politique de Rotation des Congés",
            "Congés Cumulés Non Autorisés",
            "Remplacement Non Disponible",
            "Évaluation de Performance ou Audit"
    );

    @FXML
    private Label mail_dest;
    @FXML
    private TextField mail_obj;
    @FXML
    private TextArea mail_text;
    @FXML
    private AnchorPane MainAnchorPane;
    @FXML
    private ComboBox<String> raison_mail;

    String employeeName, startDate, endDate, managerName, managerRole;
    private Conge conge;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        raison_mail.setValue("Sélectionner une raison");
        raison_mail.setItems(RaisonsList);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/NavigationHeader.fxml"));
            Pane departementPane = loader.load();
            MainAnchorPane.getChildren().add(departementPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User manager = SessionManager.getInstance().getUser();
        String role = SessionManager.getInstance().getUserRoleName();
        if (manager != null) {
            managerName = manager.getPrenom() + " " + manager.getNom();
            managerRole = String.valueOf(role);
        }
        }

    public void setData(Conge conge, User user) {
        this.conge = conge;
        this.user = user;
        employeeName = user.getPrenom() + " " + user.getNom();
        startDate = String.valueOf(conge.getDateDebut());
        endDate = String.valueOf(conge.getDateFin());
        mail_dest.setText(user.getEmail());
    }

    @FXML
    void selectRaison(ActionEvent event) {
        String selectedReason = raison_mail.getValue();
        if (selectedReason != null) {
            switch (selectedReason) {
                case "Alternative Proposée":
                    mail_obj.setText("Refus de Demande de Congé - Proposition d'Alternative");
                    mail_text.setText(Mails.generateAlternativeProposee(employeeName, startDate, endDate, managerName, managerRole));
                    break;
                case "Équité et Équilibre":
                    mail_obj.setText("Refus de Demande de Congé - Équité et Équilibre");
                    mail_text.setText(Mails.generateEquiteEtEquilibre(employeeName, startDate, endDate, managerName, managerRole));
                    break;
                case "Politique de Rotation des Congés":
                    mail_obj.setText("Refus de Demande de Congé - Politique de Rotation");
                    mail_text.setText(Mails.generatePolitiqueDeRotationDesConges(employeeName, startDate, endDate, managerName, managerRole));
                    break;
                case "Congés Cumulés Non Autorisés":
                    mail_obj.setText("Refus de Demande de Congé - Congés Cumulés Non Autorisés");
                    mail_text.setText(Mails.generateCongesCumulesNonAutorises(employeeName, startDate, endDate, managerName, managerRole, 10)); // Adjust the 10 to the actual limit
                    break;
                case "Remplacement Non Disponible":
                    mail_obj.setText("Refus de Demande de Congé - Remplacement Non Disponible");
                    mail_text.setText(Mails.generateRemplacementNonDisponible(employeeName, startDate, endDate, managerName, managerRole));
                    break;
                case "Évaluation de Performance ou Audit":
                    mail_obj.setText("Refus de Demande de Congé - Évaluation de Performance ou Audit");
                    mail_text.setText(Mails.generateEvaluationDePerformanceOuAudit(employeeName, startDate, endDate, managerName, managerRole));
                    break;
                default:
                    mail_text.setText("");
                    break;
            }
        }
    }

    @FXML
    void Annuler_mail(ActionEvent event) {
        mail_text.setText("");
        mail_obj.setText("");

    }

    @FXML
    void Envoyer_mail(ActionEvent event) {
        String to = mail_dest.getText();
        String subject = mail_obj.getText();
        String messageText = mail_text.getText();
        Mails.sendEmail(to,subject,messageText);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeDepListe.fxml"));
            Parent root = loader.load();
            DemandeDepListeController controller = loader.getController();
            StageManager.closeAllStages();
            Stage demandeDepListeStage = new Stage();
            Scene scene = new Scene(root);
            demandeDepListeStage.setScene(scene);
            demandeDepListeStage.setTitle("Mailing de Demande");
            demandeDepListeStage.show();
            StageManager.addStage("DemandeDepListe", demandeDepListeStage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
