package tn.bfpme.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;
import tn.bfpme.models.*;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;
import tn.bfpme.services.ServiceUtilisateur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField LoginEmail;
    @FXML
    private TextField LoginMDP;
    @FXML
    private Connection cnx;
    private final ServiceUtilisateur userS = new ServiceUtilisateur();


    @FXML
    void Login(ActionEvent event) {
        Connection cnx = MyDataBase.getInstance().getCnx();
        String qry = "SELECT * FROM `utilisateur` WHERE `Email`=? AND `MDP`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, LoginEmail.getText());
            stm.setString(2, LoginMDP.getText());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Utilisateur ConnectedUser = new Utilisateur(rs.getInt("ID_User"), rs.getString("Nom"), rs.getString("Prenom"), rs.getString("Email"), rs.getString("MDP"), Role.valueOf(rs.getString("Role")), rs.getString("Image"), Solde_congé.valueOf(rs.getString("Solde_congé")));

                // Check if user is an employee
                userS.checkEmployee(rs.getInt("ID_User"), ConnectedUser);
                // Check if user is a department head
                userS.checkDepartmentHead(rs.getInt("ID_User"), ConnectedUser);
                // Check if user is a chief of administration
                userS.checkChiefAdministration(rs.getInt("ID_User"), ConnectedUser);
                // Check if user is an IT admin
                userS.checkAdminIT(rs.getInt("ID_User"), ConnectedUser);

                navigateToProfile(event);
            } else {
                System.out.println("Login failed: Invalid email or password.");
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private void navigateToProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
        StageManager.addStage(stage);
    }
}
