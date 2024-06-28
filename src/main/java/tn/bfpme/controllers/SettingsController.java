package tn.bfpme.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController {

    @FXML
    private Label User_name;

    @FXML
    private ImageView User_pdp;

    @FXML
    private Label User_role;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Load the user's profile picture from session
            String userImagePath = SessionManager.getInstance().getUtilisateur().getImage().replace("\\", "/");
            System.out.println("Image path: " + userImagePath); // Debugging statement
            Image image = new Image(userImagePath);
            User_pdp.setImage(image);

            // Make the ImageView round
            Circle clip = new Circle(User_pdp.getFitWidth() / 2, User_pdp.getFitHeight() / 2, Math.min(User_pdp.getFitWidth(), User_pdp.getFitHeight()) / 2);
            User_pdp.setClip(clip);

            System.out.println("Initialization complete"); // Debugging statement
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Contact_button(ActionEvent event) {

    }

    @FXML
    void Deconnection(ActionEvent event) {
        SessionManager.getInstance().cleanUserSession();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestion de Congés - Connection");
            stage.show();
            StageManager.closeAllStages();
            StageManager.addStage(stage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void Help_button(ActionEvent event) {
        // Implement your help button action here
    }
}
