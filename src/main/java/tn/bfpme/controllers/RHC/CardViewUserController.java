package tn.bfpme.controllers.RHC;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tn.bfpme.models.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.*;


public class CardViewUserController implements Initializable {

    @FXML
    public HBox card;

    @FXML
    private Label emailLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label soldeAnnuelLabel;

    @FXML
    private Label soldeExceptionnelLabel;

    @FXML
    private Label soldeMaladieLabel;

    @FXML
    private Label soldeMaterniteLabel;





    public void setData(User user) {
        nameLabel.setText(user.getNom() + " " + user.getPrenom());
        emailLabel.setText(user.getEmail());
        soldeAnnuelLabel.setText("Solde Annuel: " + user.getSoldeAnnuel());
        soldeMaladieLabel.setText("Solde Maladie: " + user.getSoldeMaladie());
        soldeExceptionnelLabel.setText("Solde Exceptionnel: " + user.getSoldeExceptionnel());
        soldeMaterniteLabel.setText("Solde Maternité: " + user.getSoldeMaternite());

        String imagePath = user.getImage();
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                File file = new File(imagePath);
                FileInputStream inputStream = new FileInputStream(file);
                Image image = new Image(inputStream);
                imageView.setImage(image);
            } catch (FileNotFoundException e) {
                System.err.println("Image file not found: " + imagePath);
            }
        } else {
            imageView.setImage(null); // Or set a default image
        }

        card.setStyle("-fx-border-radius: 5px; -fx-border-color: #808080;");
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
