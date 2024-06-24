package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.bfpme.models.Departement;
import tn.bfpme.models.Utilisateur;
import tn.bfpme.utils.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeController implements Initializable {
    @FXML private Label CU_dep;
    @FXML private Label CU_email;
    @FXML private Label CU_nomprenom;
    @FXML private ImageView CU_pdp;
    @FXML private Label CU_solde;
    private ContextMenu contextMenu;
    @FXML
    private Button settingsButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CU_dep.setText(String.valueOf(SessionManager.getDepartement()));
        CU_email.setText(SessionManager.getEmail());
        CU_nomprenom.setText(SessionManager.getNom()+" "+SessionManager.getPrenom());
        CU_solde.setText(String.valueOf(SessionManager.getSoldeConge()));
        contextMenu = new ContextMenu();

        // Add menu items to the context menu
        MenuItem profileItem = new MenuItem("Profile");
        MenuItem infoItem = new MenuItem("Info");
        MenuItem suppressionItem = new MenuItem("Supprimer Compte");
        MenuItem logoutItem = new MenuItem("Déconnexion");

        contextMenu.getItems().addAll(profileItem, infoItem, suppressionItem, logoutItem);

        // Show the context menu directly under the settings button
        settingsButton.setOnAction(event -> {
            double screenX = settingsButton.localToScreen(settingsButton.getBoundsInLocal()).getMinX()-70;
            double screenY = settingsButton.localToScreen(settingsButton.getBoundsInLocal()).getMaxY()+10;
            contextMenu.show(settingsButton, screenX, screenY);
        });


    }
    @FXML
    public void DemanderConge(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Demande de Congé");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void HistoriqueConge(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HistoriqueConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Historique des Congés");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Demander(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Demande congé");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void Historique(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HistoriqueConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Historique congé");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
