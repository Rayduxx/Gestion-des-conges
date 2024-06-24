package tn.bfpme.controllers;

import javafx.fxml.FXML;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.bfpme.models.*;
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
        Connection cnx = MyDataBase.getInstance().getCnx();
        String qry = "SELECT * FROM `utilisateur` WHERE `Email`=? AND `MDP`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, LoginEmail.getText());
            stm.setString(2, LoginMDP.getText());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Utilisateur ConnectedUser = new Utilisateur(rs.getInt("ID_User"), rs.getString("Nom"), rs.getString("Prenom"), rs.getString("Email"), rs.getString("MDP"), Role.valueOf(rs.getString("Role")), rs.getString("Image"), rs.getInt("Solde_congé"));
                SessionManager.getInstance(rs.getInt("ID_User"), rs.getString("Nom"), rs.getString("Prenom"), rs.getString("Email"), Role.valueOf(rs.getString("Role")), rs.getString("Image"), rs.getInt("Solde_congé"),rs.getString("MDP"), null);

                // Check if user is an employee
                String qry2 = "SELECT `ID_Employé`, `Departement`, `ID_User` FROM `employe` WHERE `ID_User`=?";
                PreparedStatement stm2 = cnx.prepareStatement(qry2);
                stm2.setInt(1, rs.getInt("ID_User"));
                ResultSet rs2 = stm2.executeQuery();
                if (rs2.next()) {
                    Employe curEmp = new Employe(
                            rs2.getInt("ID_Employé"),
                            Departement.valueOf(rs2.getString("Departement")),
                            ConnectedUser
                    );
                    Employe.setCurrent_Emp(curEmp);
                    SessionManager.getInstance().setDepartement(Departement.valueOf(rs2.getString("Departement")));
                    System.out.println(Departement.valueOf(rs2.getString("Departement")));
                }

                // Check if user is a department head
                String qry3 = "SELECT `ID_ChefDep`, `Departement`, `ID_User` FROM `chef_departement` WHERE `ID_User`=?";
                PreparedStatement stm3 = cnx.prepareStatement(qry3);
                stm3.setInt(1, rs.getInt("ID_User"));
                ResultSet rs3 = stm3.executeQuery();
                if (rs3.next()) {
                    ChefDepartement curChefDep = new ChefDepartement(
                            rs3.getInt("ID_ChefDep"),
                            Departement.valueOf(rs3.getString("Departement")),
                            ConnectedUser
                    );
                    ChefDepartement.setCurrent_ChefDep(curChefDep);
                    SessionManager.getInstance().setDepartement(Departement.valueOf(rs3.getString("Departement")));
                }

                // Check if user is a chief of administration
                String qry4 = "SELECT * FROM `chef_administration` WHERE `ID_User`=?";
                PreparedStatement stm4 = cnx.prepareStatement(qry4);
                stm4.setInt(1, rs.getInt("ID_User"));
                ResultSet rs4 = stm4.executeQuery();
                if (rs4.next()) {
                    ChefAdministration curChefAdm = new ChefAdministration(
                            rs4.getInt("ID_ChefAdmin"),
                            ConnectedUser
                    );
                    ChefAdministration.setCurrent_ChefAdm(curChefAdm);
                    SessionManager.getInstance().setDepartement(Departement.Administration);
                }

                // Check if user is an IT admin
                String qry5 = "SELECT * FROM `admin` WHERE `ID_User`=?";
                PreparedStatement stm5 = cnx.prepareStatement(qry5);
                stm5.setInt(1, rs.getInt("ID_User"));
                ResultSet rs5 = stm5.executeQuery();
                if (rs5.next()) {
                    AdminIT curAdm = new AdminIT(
                            rs5.getInt("ID_Admin"),
                            ConnectedUser
                    );
                    AdminIT.setCurrent_Adm(curAdm);
                    SessionManager.getInstance().setDepartement(Departement.IT);
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Profile");
                stage.show();
            } else {
                System.out.println("Login failed: Invalid email or password.");
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

}