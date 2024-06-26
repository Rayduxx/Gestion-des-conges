package tn.bfpme.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ContextMenu;
import javafx.stage.Stage;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

public class DemandeDepListeController implements Initializable {
    @FXML private GridPane DemandesContainer;
    @FXML private TextField Recherche_demande;
    @FXML private ComboBox<String> comboTri;
    @FXML private Button settingsButton;
    private ContextMenu contextMenu;
    ObservableList<String> TriListe = FXCollections.observableArrayList("Statut", "Type", "Nom", "Prenom", "Date Debut", "Date Fin");
    @Override public void initialize(URL url, ResourceBundle rb) {
        comboTri.setValue("Selectioner");
        comboTri.setItems(TriListe);
        contextMenu = new ContextMenu();
        MenuItem boiteItem = new MenuItem("boîte de réception");
        MenuItem aideItem = new MenuItem("Aide et support");
        MenuItem logoutItem = new MenuItem("Déconnexion");
        contextMenu.getItems().addAll(boiteItem, aideItem, logoutItem);
        settingsButton.setOnAction(event -> {
            double screenX = settingsButton.localToScreen(settingsButton.getBoundsInLocal()).getMinX()-70;
            double screenY = settingsButton.localToScreen(settingsButton.getBoundsInLocal()).getMaxY()+10;
            contextMenu.show(settingsButton, screenX, screenY);
        });
        boiteItem.setOnAction(this::viewboite);
        aideItem.setOnAction(this::viewaide);
        logoutItem.setOnAction(this::viewdeconnection);
    }

    @FXML void Recherche(KeyEvent event) {

    }

    @FXML void TriPar(ActionEvent event) {
        String TYPE = comboTri.getValue();
        if (TYPE.equals("Statut")) {

        } else if (TYPE.equals("Type")) {

        } else if (TYPE.equals("Nom")) {

        } else if (TYPE.equals("Prenom")) {

        } else if (TYPE.equals("Date Debut")) {

        } else if (TYPE.equals("Date Fin")) {

        }
    }
    public void LoadCredits() {
       /* int column = 0;
        int row = 1;
        try {
            for (Credit credit : afficher()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CardCredit.fxml"));
                Pane userBox = fxmlLoader.load();
                CardCreditController CCC = fxmlLoader.getController();
                CCC.setData(credit);
                if (column == 2) {
                    column = 0;
                    ++row;
                }
                CredContainer.add(userBox, column++, row);
                GridPane.setMargin(userBox, new Insets(20));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    void viewaide(ActionEvent actionEvent){}
    void viewboite(ActionEvent actionEvent){}
    @FXML void viewdeconnection(ActionEvent actionEvent) {
        SessionManager.getInstance().cleanUserSession();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();

            // Get the stage from the current context menu's owner window
            MenuItem menuItem = (MenuItem) actionEvent.getSource();
            Stage stage = (Stage) menuItem.getParentPopup().getOwnerWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Gestion de Congés - Connection");
            StageManager.addStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML void Demander(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Demande congé");
            stage.show();
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML void Historique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HistoriqueConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Historique congé");
            stage.show();
            StageManager.addStage(stage);
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML void goto_profil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Mon profil");
            stage.show();
            StageManager.addStage(stage);
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML void ListeDesDemandes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeDepListe.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Liste des demandes - "+SessionManager.getInstance().getDepartement());
            stage.show();
            StageManager.addStage(stage);
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}