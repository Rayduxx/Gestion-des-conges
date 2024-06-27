package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Statut;
import tn.bfpme.models.TypeConge;
import tn.bfpme.models.Utilisateur;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;

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

    public void setData(Conge conge, Utilisateur user) {
        this.conge = conge;
        this.user = user;
        CongePerson.setText(user.getNom() + " " + user.getPrenom());
        labelDD.setText(String.valueOf(conge.getDateDebut()));
        labelDF.setText(String.valueOf(conge.getDateFin()));
        labelDesc.setText(conge.getDescription());
        labelType.setText(String.valueOf(conge.getTypeConge()));
        labelJours.setText(String.valueOf(ChronoUnit.DAYS.between(conge.getDateDebut(), conge.getDateFin()))+" Jours");
    }

    @FXML void AfficherCongFichier(ActionEvent event) {

    }

    @FXML void ApproverConge(ActionEvent event) {

    }

    @FXML void RefuserConge(ActionEvent event) {

    }

    @FXML void retour(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
