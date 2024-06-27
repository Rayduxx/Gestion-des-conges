package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Statut;
import tn.bfpme.models.TypeConge;
import tn.bfpme.models.Utilisateur;
import tn.bfpme.services.ServiceConge;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DemandeDepController {
    @FXML private Label CongePerson;
    @FXML private Label labelDD;
    @FXML private Label labelDF;
    @FXML private Label labelDesc;
    @FXML private Label labelJours;
    @FXML private Label labelType;
    Connection cnx = MyDataBase.getInstance().getCnx();
    private Conge conge;
    private Utilisateur user;
    private int CongeDays;
    private final ServiceConge serviceConge = new ServiceConge();
    public void setData(Conge conge, Utilisateur user) {
        this.conge = conge;
        this.user = user;
        CongePerson.setText(user.getNom() + " " + user.getPrenom());
        labelDD.setText(String.valueOf(conge.getDateDebut()));
        labelDF.setText(String.valueOf(conge.getDateFin()));
        labelDesc.setText(conge.getDescription());
        labelType.setText(String.valueOf(conge.getTypeConge()));
        CongeDays = (int) ChronoUnit.DAYS.between(conge.getDateDebut(), conge.getDateFin());
        labelJours.setText(String.valueOf(CongeDays)+" Jours");
    }

    @FXML void AfficherCongFichier(ActionEvent event) {
        String filePath = "src/main/resources/assets/files/"+conge.getFile();
        File file = new File(filePath);
        if (file.exists()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
            } catch (IOException e) {
                Logger.getLogger(CongeCarteController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            System.out.println("File not found: " + filePath);
        }
    }

    @FXML void ApproverConge(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Êtes vous sûrs?");
        alert.setHeaderText("Êtes-vous certain de vouloir approver cette demande ?");
        ButtonType Oui = new ButtonType("Oui");
        ButtonType Non = new ButtonType("Non");
        alert.getButtonTypes().setAll(Oui, Non);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == Oui) {
            serviceConge.updateStatutConge(this.conge.getIdConge(), Statut.Approuvé);
            if (conge.getTypeConge().equals(TypeConge.Annuel)){
                serviceConge.updateSoldeAnnuel(this.user.getIdUser(), this.user.getSoldeAnnuel()-CongeDays);
            }
            if (conge.getTypeConge().equals(TypeConge.Exceptionnel)){
                serviceConge.updateSoldeExceptionnel(this.user.getIdUser(), this.user.getSoldeExceptionnel()-CongeDays);
            }
            if (conge.getTypeConge().equals(TypeConge.Maladie)){
                serviceConge.updateSoldeMaladie(this.user.getIdUser(), this.user.getSoldeMaladie()-CongeDays);
            }
            if (conge.getTypeConge().equals(TypeConge.Maternité)){
                serviceConge.updateSoldeMaternité(this.user.getIdUser(), this.user.getSoldeMaternite()-CongeDays);
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Alert cbon = new Alert(Alert.AlertType.INFORMATION);
            cbon.setTitle("Demande approvée");
            cbon.setHeaderText("La demande de congé "+this.conge.getTypeConge()+" de "+this.user.getNom()+" "+this.user.getPrenom()+" est apprové");
        }
    }

    @FXML void RefuserConge(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Êtes vous sûrs?");
        alert.setHeaderText("Êtes-vous certain de vouloir rejeter cette demande ?");
        ButtonType Oui = new ButtonType("Oui");
        ButtonType Non = new ButtonType("Non");
        alert.getButtonTypes().setAll(Oui, Non);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == Oui) {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Confirmation de l'envoi?");
            alert2.setHeaderText("Voulez-vous envoyer un email ?");
            ButtonType Oui2 = new ButtonType("Oui");
            ButtonType Non2 = new ButtonType("Non");
            alert2.getButtonTypes().setAll(Oui2, Non2);
            Optional<ButtonType> result2 = alert2.showAndWait();
            if (result2.isPresent() && result2.get() == Oui2) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/MailingDemande.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Mailing de Demande");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML void retour(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
