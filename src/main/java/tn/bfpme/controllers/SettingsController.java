package tn.bfpme.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController  implements Initializable {

    @FXML
    private Label User_name;

    @FXML
    private ImageView User_pdp;

    @FXML
    private Label User_role;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            User_name.setText(SessionManager.getInstance().getUser().getNom()+" "+SessionManager.getInstance().getUser().getPrenom());
            User_role.setText(String.valueOf(SessionManager.getInstance().getUserRoleName()));
            String imagePath = SessionManager.getInstance().getUser().getImage();

            if (imagePath != null && !imagePath.isEmpty()) {
                File file = new File(imagePath);
                if (file.exists()) {
                    try {
                        FileInputStream inputStream = new FileInputStream(file);
                        Image image = new Image(inputStream);
                        User_pdp.setImage(image);
                    } catch (FileNotFoundException e) {
                        System.err.println("Image file not found: " + imagePath);
                    }
                } else {
                    System.err.println("Image file does not exist: " + imagePath);
                }
            } else {
                System.err.println("Image path is null or empty for user: " + SessionManager.getInstance().getUser());
            }
            /*String userImagePath = SessionManager.getInstance().getUtilisateur().getImage();
            System.out.println("Image path: " + userImagePath); // Debugging statement
            Image image = new Image(userImagePath);
            User_pdp.setImage(image);*/

            // Make the ImageView round
            Circle clip = new Circle(User_pdp.getFitWidth() / 2, User_pdp.getFitHeight() / 2, Math.min(User_pdp.getFitWidth(), User_pdp.getFitHeight()) / 2);
            User_pdp.setClip(clip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Contact_button(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/MailingContact.fxml"));
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
