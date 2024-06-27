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
import javafx.stage.Stage;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Role;
import tn.bfpme.services.ServiceConge;
import tn.bfpme.utils.Mails;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        raison_mail.setValue("Selectioner une raison");
        raison_mail.setItems(RaisonsList);

    }
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
    Conge conge =new Conge();
    @FXML
    void selectRaison(ActionEvent event) {
        String employeeName = SessionManager.getInstance().getUtilisateur().getPrenom();
        String startDate = String.valueOf(conge.getDateDebut());
        String endDate = String.valueOf(conge.getDateFin());
        String  managerName = SessionManager.getInstance().getUtilisateur().getNom();
        String  managerRole= String.valueOf(SessionManager.getInstance().getUtilisateur().getRole());


        if (raison_mail.getValue().equals("Alternative Proposée")) {
            mail_text.setText(Mails.generateAlternativeProposee(employeeName,startDate,endDate,managerName,managerRole));
        }
        if (raison_mail.getValue().equals("ExeptionnelÉquité et Équilibre")) {

        }
        if (raison_mail.getValue().equals("Politique de Rotation des Congés")) {

        }
        if (raison_mail.getValue().equals("Congés Cumulés Non Autorisés")) {

        }
        if (raison_mail.getValue().equals("Remplacement Non Disponible")) {

        }
        if (raison_mail.getValue().equals("Évaluation de Performance ou Audit")) {

        }



    }
    @FXML
    void Annuler_mail(ActionEvent event) {

    }

    @FXML
    void Envoyer_mail(ActionEvent event) {


    }


    @FXML
    private Button settingsButton;
    @FXML void Historique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HistoriqueConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Historique congé");
            stage.show();
            StageManager.addStage(stage);
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML void goto_profil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Mon profil");
            stage.show();
            StageManager.addStage(stage);
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML void ListeDesDemandes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeDepListe.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Liste des demandes - "+ SessionManager.getInstance().getDepartement());
            stage.show();
            StageManager.addStage(stage);
            StageManager.addStage(stage);
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
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
