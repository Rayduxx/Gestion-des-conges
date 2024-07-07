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

        int soldeAnnuel = parseIntOrZero(S_Ann.getText());
        int soldeMaladie = parseIntOrZero(S_mal.getText());
        int soldeExceptionnel = parseIntOrZero(S_exc.getText());
        int soldeMaternite = parseIntOrZero(S_mat.getText());

        if (email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(bfpme\\.tn|gmail\\.com)$")) {
            try {
                if (!emailExists(email)) {
                    UserS.Add(new User(0, nom, prenom, email, mdp, image, soldeAnnuel, soldeMaladie, soldeExceptionnel, soldeMaternite, 0, 0));
                    infolabel.setText("Ajout Effectué");
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

    private int parseIntOrZero(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        try {
            return (int) Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0;
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
        String Nom = nom_A.getText();
        String Prenom = Prenom_A.getText();
        String Email = email_A.getText();
        String Mdp = MDP_A.getText();
        String Image = image_A.getText();
        int solde_annuel = parseIntOrZero(S_Ann.getText());
        int solde_maladie = parseIntOrZero(S_mal.getText());
        int solde_exceptionnel = parseIntOrZero(S_exc.getText());
        int solde_maternite = parseIntOrZero(S_mat.getText());

        if (Email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(bfpme\\.tn|gmail\\.com)$")) {
            int IdUser = Integer.parseInt(ID_A.getText());
            try {
                if (!emailExistss(Email, IdUser) || isCurrentUser(IdUser, Email)) {
                    User user = new User(IdUser, Nom, Prenom, Email, Mdp, Image, solde_annuel, solde_maladie, solde_exceptionnel, solde_maternite, 0, 0);
                    UserS.Update(user);
                    infolabel.setText("Modification Effectuée");
                    System.out.println("User updated: " + user);
                } else {
                    infolabel.setText("Email déjà existe");
                }
            } catch (SQLException e) {
                infolabel.setText("Erreur de base de données: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            infolabel.setText("Email est invalide");
        }
    }

    private boolean isCurrentUser(int userId, String email) {
       User user = UserS.getUserById(userId);

    return UserS != null  && user.getEmail().equals(email);
    }

    @FXML
    void supprimer_user(ActionEvent event) {
        int userId = UserS.getUserIdCard(); // Implement this method to get the current user ID to delete

        if (userId > 0) {
            User user = UserS.getUserById(userId);
            if (user != null) {
                UserS.Delete(user);
                infolabel.setText("Suppression Effectuée");
            } else {
                infolabel.setText("Utilisateur non trouvé");
            }
        } else {
            infolabel.setText("Sélectionnez un utilisateur valide à supprimer");
        }
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

    private boolean emailExistss(String email, int excludeUserId) throws SQLException {
        String query = "SELECT COUNT(*) FROM user WHERE Email = ? AND ID_User != ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setString(1, email);
            stm.setInt(2, excludeUserId);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
