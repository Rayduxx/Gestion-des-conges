package tn.bfpme.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UserCarteController {
    @FXML
    private Pane UserCard;
    @FXML
    private Label email_card;
    @FXML
    private Label nomprenom_card;
    @FXML
    private ImageView pdp_card;
    @FXML
    private Label periode_card;
    @FXML
    private Label statut_card;
    @FXML
    private Label type_card;

    private Conge conge;
    private Utilisateur user;

    public void setData(Conge conge, Utilisateur user) {
        System.out.println("Setting data for user: " + user.getNom() + " " + user.getPrenom());
        this.conge = conge;
        this.user = user;

        String imagePath = user.getImage();
        if (imagePath != null) {
            try {
                File file = new File(imagePath);
                FileInputStream inputStream = new FileInputStream(file);
                Image image = new Image(inputStream);
                pdp_card.setImage(image);
            } catch (FileNotFoundException e) {
                System.err.println("Image file not found: " + imagePath);
            }
        } else {
            System.err.println("Image path is null for user: " + user);
        }
        nomprenom_card.setText(user.getNom() + " " + user.getPrenom());
        email_card.setText(user.getEmail());
        periode_card.setText("De " + conge.getDateDebut() + " à " + conge.getDateFin());
        statut_card.setText(String.valueOf(conge.getStatut()));
        type_card.setText(String.valueOf(conge.getTypeConge()));
        UserCard.setStyle("-fx-border-radius: 5px; -fx-border-color: #808080;");

        System.out.println("Card data set for user: " + user.getNom() + " " + user.getPrenom());
    }
}

