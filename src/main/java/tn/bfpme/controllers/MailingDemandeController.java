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
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import tn.bfpme.models.Conge;
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
    private Button btnListe;

    @FXML
    private Label mail_dest;

    @FXML
    private TextField mail_obj;

    @FXML
    private TextArea mail_text;

    @FXML
    private ComboBox<String> raison_mail;

    String employeeName, startDate, endDate, managerName, managerRole;
    private Popup settingsPopup;
    private Popup notifPopup;
    @FXML public Button NotifBtn;
    private Conge conge;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        raison_mail.setValue("Sélectionner une raison");
        raison_mail.setItems(RaisonsList);

        // Initialize variables from SessionManager
        Utilisateur manager = SessionManager.getInstance().getUtilisateur();
        if (manager != null) {
            managerName = manager.getPrenom() + " " + manager.getNom();
            managerRole = String.valueOf(manager.getRole());
        }
        settingsPopup = new Popup();
        settingsPopup.setAutoHide(true);

        try {
            Parent settingsContent = FXMLLoader.load(getClass().getResource("/Settings.fxml"));
            settingsPopup.getContent().add(settingsContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        notifPopup =new Popup();
        notifPopup.setAutoHide(true);
        try {
            Parent settingsContent = FXMLLoader.load(getClass().getResource("/paneNotif.fxml"));
            notifPopup.getContent().add(settingsContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setData(Conge conge, Utilisateur user) {
        this.conge = conge;
        this.user = user;
        employeeName = user.getPrenom() + " " + user.getNom();
        startDate = String.valueOf(conge.getDateDebut());
        endDate = String.valueOf(conge.getDateFin());
        mail_dest.setText(user.getEmail());
    }
    @FXML
    void settings_button(ActionEvent event) {
        if (settingsPopup.isShowing()) {
            settingsPopup.hide();
        } else {
            Window window = ((Node) event.getSource()).getScene().getWindow();
            double x = window.getX() + settingsButton.localToScene(0, 0).getX() + settingsButton.getScene().getX() - 150;
            double y = window.getY() + settingsButton.localToScene(0, 0).getY() + settingsButton.getScene().getY() + settingsButton.getHeight();
            settingsPopup.show(window, x, y);
        }
    }
    @FXML
    void OpenNotifPane(ActionEvent event) {
        if (notifPopup.isShowing()) {
            notifPopup.hide();
        } else {
            Window window = ((Node) event.getSource()).getScene().getWindow();
            double x = window.getX() + NotifBtn.localToScene(0, 0).getX() + NotifBtn.getScene().getX() - 250;
            double y = window.getY() + NotifBtn.localToScene(0, 0).getY() + NotifBtn.getScene().getY() + NotifBtn.getHeight();
            notifPopup.show(window, x, y);
        }

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

    @FXML
    private Button settingsButton;

    @FXML
    void Historique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HistoriqueConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Historique congé");
            stage.show();
            StageManager.addStage("DemandeDepListe", stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goto_profil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Mon profil");
            stage.show();
            StageManager.addStage("DemandeDepListe", stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ListeDesDemandes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeDepListe.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Liste des demandes - " + SessionManager.getInstance().getDepartement());
            stage.show();
            StageManager.addStage("DemandeDepListe", stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Demander(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Demande congé");
            stage.show();
            StageManager.addStage("DemandeDepListe", stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
