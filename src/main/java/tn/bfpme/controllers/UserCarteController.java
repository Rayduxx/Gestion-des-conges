package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserCarteController {
    @FXML private Pane UserCard;
    @FXML private Label email_card;
    @FXML private Label nomprenom_card;
    @FXML private ImageView pdp_card;
    @FXML private Label periode_card;
    @FXML private Label statut_card;
    @FXML private Label type_card;

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
    @FXML
    void AfficherDemande(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeDep.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            DemandeDepController DemDepC = loader.getController();
            DemDepC.setData(conge, user);
            newStage.setTitle("Demande de congé");
            newStage.setScene(new Scene(root));
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            newStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

