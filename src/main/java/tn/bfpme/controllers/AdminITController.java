package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceUtilisateur;
import tn.bfpme.utils.MyDataBase;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminITController implements Initializable {
    @FXML
    private AnchorPane MainAnchorPane;
    @FXML
    private TextField ID_A;

    @FXML
    private TextField MDP_A;

    @FXML
    private TextField Prenom_A;

    @FXML
    private TextField S_Ann;

    @FXML
    private TextField S_exc;

    @FXML
    private TextField S_mal;

    @FXML
    private TextField S_mat;

    @FXML
    private TextField email_A;

    @FXML
    private TextField image_A;

    @FXML
    private TextField nom_A;
    @FXML
    private Label infolabel;
    ServiceUtilisateur UserS =new ServiceUtilisateur();
    Connection cnx = MyDataBase.getInstance().getCnx();

    @FXML
    void ajouter_user(ActionEvent actionEvent) {
            String nom = nom_A.getText();
            String prenom = Prenom_A.getText();
            String email = email_A.getText();
            String mdp = MDP_A.getText();
            String image = image_A.getText();
            int soldeAnnuel = (int) Double.parseDouble(S_Ann.getText());
            int soldeMaladie = (int) Double.parseDouble(S_mal.getText());
            int soldeExceptionnel = (int) Double.parseDouble(S_exc.getText());
            int soldeMaternite = (int) Double.parseDouble(S_mat.getText());

            if (email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(bfpme\\.tn|gmail\\.com)$")) {
                try {
                    if (!emailExists(email)) {
                        UserS.Add(new User(0, nom, prenom, email, mdp, image, soldeAnnuel, soldeMaladie, soldeExceptionnel, soldeMaternite, 0, 0));
                        infolabel.setText("Ajout Effectue");
                    } else {
                        infolabel.setText("Email déjà existe");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                infolabel.setText("Email est invalide");
            }
    }

    @FXML
    void goto_conge(ActionEvent event) {

    }

    @FXML
    void goto_user(ActionEvent event) {

    }

    @FXML
    void modifier_user(ActionEvent event) {

    }

    @FXML
    void supprimer_user(ActionEvent event) {

    }

    @FXML
    void upload_image(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/NavigationHeader.fxml"));
            Pane departementPane = loader.load();
            MainAnchorPane.getChildren().add(departementPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean emailExists(String email) throws SQLException {
        cnx = MyDataBase.getInstance().getCnx();
        String query = "SELECT * FROM `user` WHERE Email=?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }
}
