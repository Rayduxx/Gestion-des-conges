package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceUtilisateur;
import tn.bfpme.utils.MyDataBase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;

public class AdminITController implements Initializable {
    @FXML
    private AnchorPane MainAnchorPane;
    @FXML
    private TextField ID_A;
    @FXML
    private ImageView PDPimageHolder;
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
        int solde_annuel= (int) Double.parseDouble(S_Ann.getText());
        int solde_maladie = (int) Double.parseDouble(S_mal.getText());
        int solde_exceptionnel = (int) Double.parseDouble(S_exc.getText());
        int solde_maternite = (int) Double.parseDouble(S_mat.getText());

        if (Email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(bfpme\\.tn|gmail\\.com)$")) {
            try {
                int userId = UserS.getUserIdCard();
                if (!emailExists(Email) || isCurrentUser(userId, Email)) {
                    UserS.Update(new User(userId, Nom, Prenom, Email, Mdp, Image, solde_annuel, solde_maladie, solde_exceptionnel, solde_maternite, 0, 0));
                    infolabel.setText("Modification Effectuée");
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

    private boolean isCurrentUser(int userId, String email) {
        User user = UserS.getUserById(userId);
        return user != null && user.getEmail().equals(email);
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
        String imagePath = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        Stage stage = (Stage) nom_A.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                Path destinationFolder = Paths.get("src/main/resources/assets/imgs");
                if (!Files.exists(destinationFolder)) {
                    Files.createDirectories(destinationFolder);
                }
                String fileName = UUID.randomUUID().toString() + "_" + selectedFile.getName();
                Path destinationPath = destinationFolder.resolve(fileName);
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                imagePath = destinationPath.toString();
                System.out.println("Image uploaded successfully: " + imagePath);
                image_A.setText(fileName);
                if (imagePath != null) {
                    try {
                        File file = new File(imagePath);
                        FileInputStream inputStream = new FileInputStream(file);
                        Image image = new Image(inputStream);
                        PDPimageHolder.setImage(image);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
