package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceUtilisateur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class UserCardController {

    @FXML
    private Pane Card;

    @FXML
    private Label carddepart;

    @FXML
    private Label cardemail;

    @FXML
    private ImageView cardimage;

    @FXML
    private Label cardnameprename;

    @FXML
    private Label cardrole;

    private final ServiceUtilisateur UserS = new ServiceUtilisateur();

    int uid,unumtel;
    String unom, uprenom, uemail, umdp, urole;

    /*public void setData(User user) {
        String imagePath = user.getImage();
        if (imagePath != null) {
            try {
                File file = new File(imagePath);
                FileInputStream inputStream = new FileInputStream(file);
                Image image = new Image(inputStream);
                cardimage.setImage(image);
            } catch (FileNotFoundException e) {
                System.err.println("Image file not found: " + imagePath);
            }
        } else {
            System.err.println("Image path is null for user: " + user);
        }
        cardnameprename.setText(user.getNom() + " " + user.getPrenom());
        cardemail.setText(user.getEmail());
        cardrole.setText(user.getIdRole());
        carddepart
        Card.setStyle("-fx-border-radius: 5px;-fx-border-color:#808080");

        uprenom = user.getPrenom();
        uid = user.getId();
        unom = user.getNom();
        uemail = user.getEmail();
        umdp = user.getPassword();
        urole = user.getRole();
        unumtel = user.getNumtel();
    }

    @FXML
    void ModifierUser(ActionEvent event) {

    }

    @FXML
    void SupprimerUser(ActionEvent event) {

    }*/

}
