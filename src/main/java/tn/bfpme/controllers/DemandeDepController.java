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
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DemandeDepController {
    @FXML private Label CongerPerson;
    @FXML private Label labelDD;
    @FXML private Label labelDF;
    @FXML private Label labelDesc;
    @FXML private Label labelJours;
    @FXML private Label labelType;
    Connection cnx = MyDataBase.getInstance().getCnx();

    public void setData(Conge conge) {
        /*String qry = "SELECT `ID_User` FROM `conge` WHERE `ID_Conge`=? ";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, conge.getIdConge());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String qry1 = "SELECT * FROM `utilisateur` WHERE `ID_User`=?";
                PreparedStatement stm1 = cnx.prepareStatement(qry1);
                stm1.setInt(1, conge.getIdConge());
                ResultSet rs1 = stm.executeQuery();
                while (rs1.next()) {
                    CongerPerson.setText(rs1.getString("Nom")+""+rs1.getString("Prenom"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
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
