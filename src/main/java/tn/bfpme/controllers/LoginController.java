package tn.bfpme.controllers;

import javafx.fxml.FXML;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.bfpme.models.Role;
import tn.bfpme.models.Utilisateur;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML
    private TextField LoginEmail;
    @FXML
    private TextField LoginMDP;
    @FXML
    private Connection cnx;

    @FXML
    void Login(ActionEvent event) {
        String qry = "SELECT * FROM `utilisateur` WHERE `Email`=? AND `MDP`=?";
        cnx = MyDataBase.getInstance().getCnx();
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, LoginEmail.getText());
            stm.setString(2, LoginMDP.getText());
            ResultSet rs = stm.executeQuery();
            Utilisateur CurUser;
            if (rs.next()) {
                        CurUser = new Utilisateur(rs.getInt("ID_User"), rs.getString("Nom"), rs.getString("Prenom"), rs.getString("Email"), rs.getString("MDP"), Role.valueOf(rs.getString("Role")), rs.getString("Image"),rs.getInt("Solde_congé"));
                Utilisateur.setCurrent_User(CurUser);
                /*SessionManager.getInstace(rs.getInt("ID_User"), rs.getString("Nom"), rs.getString("Prenom"), rs.getString("Email"), Role.valueOf(rs.getString("Role")), rs.getString("Image"),rs.getInt("Solde_congé"));*/
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Profile");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
