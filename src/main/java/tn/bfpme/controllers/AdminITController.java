package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.bfpme.models.User;
import javafx.scene.image.*;

import tn.bfpme.services.ServiceUtilisateur;
import tn.bfpme.utils.MyDataBase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.scene.layout.AnchorPane;


public class AdminITController implements Initializable {
    @FXML
    private TextField ID_A;

    @FXML
    private TextField MDP_A;

    @FXML
    private AnchorPane MainAnchorPane;

    @FXML
    private ImageView PDPimageHolder;

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
    private GridPane UserContainers;

    @FXML
    private TextField email_A;

    @FXML
    private TextField image_A;

    @FXML
    private Label infolabel;

    @FXML
    private TextField nom_A;
    @FXML
    private TextField Recherche;


    ServiceUtilisateur UserS =new ServiceUtilisateur();
    Connection cnx = MyDataBase.getInstance().getCnx();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/NavigationHeader.fxml"));
            Pane departementPane = loader.load();
            MainAnchorPane.getChildren().add(departementPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



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
        try {
            int userId = Integer.parseInt(ID_A.getText());

            User user = UserS.getUserById(userId);
            if (user != null) {
                UserS.Delete(user);
                infolabel.setText("Suppression Effectuée");
                System.out.println("User deleted: " + user);
            } else {
                infolabel.setText("Utilisateur non trouvé");
            }
        } catch (NumberFormatException e) {
            infolabel.setText("L'ID de l'utilisateur doit être un nombre.");
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


    public void load(List<User> users) {
        UserContainers.getChildren().clear(); // Clear existing items
        int column = 0;
        int row = 0; // Start row from 0
        try {

            for (User user : users) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/UserCard.fxml"));
                Pane userBox = fxmlLoader.load();
                UserCardController cardC = fxmlLoader.getController();
                cardC.setData(user);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                UserContainers.add(userBox, column++, row); // Ensure correct row and column
                GridPane.setMargin(userBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {

        List<User> users = UserS.Show();
        load(users);
    }


    @FXML
    void Tri_Departement(ActionEvent actionEvent) {
        UserContainers.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (User user : UserS.SortDepart()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/UserCard.fxml"));
                Pane userBox = fxmlLoader.load();
                UserCardController cardC = fxmlLoader.getController();
                cardC.setData(user);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                UserContainers.add(userBox, column++, row);
                GridPane.setMargin(userBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Tri_Role(ActionEvent actionEvent) {
        UserContainers.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (User user : UserS.SortRole()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/UserCard.fxml"));
                Pane userBox = fxmlLoader.load();
                UserCardController cardC = fxmlLoader.getController();
                cardC.setData(user);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                UserContainers.add(userBox, column++, row);
                GridPane.setMargin(userBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Recherche(ActionEvent event) {
        String searchQuery = Recherche.getText().trim();
        if (!searchQuery.isEmpty()) {

            List<User> users = UserS.search(searchQuery);
            load(users);
        } else {
            load();
        }
    }



}
