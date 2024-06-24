package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import tn.bfpme.models.Conge;
import tn.bfpme.services.ServiceConge;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import java.awt.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
public class HistoriqueCongeController implements Initializable  {

    private final ServiceConge CongeS = new ServiceConge();
    private Connection cnx;
    private ContextMenu contextMenu;

    @FXML
    private TextField Recherche_conge;
    @FXML
    private Button settingsButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
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
    private GridPane congeContainer;
    public void load() {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        congeContainer.getColumnConstraints().add(columnConstraints);
        congeContainer.setVgap(4);
        congeContainer.setPadding(new Insets(4));
        int row = 0;
        try {
            for (Conge conge : CongeS.afficher()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CongeCarte.fxml"));
                Pane CardBox = fxmlLoader.load();
                CongeCarteController cardC = fxmlLoader.getController();
                cardC.setData(conge);

                congeContainer.add(CardBox, 0 , row++);
                GridPane.setMargin(CardBox, new Insets(4, 4, 4, 4));
                CardBox.setMaxWidth(Double.MAX_VALUE);
                congeContainer.setColumnSpan(CardBox, GridPane.REMAINING);
                GridPane.setHalignment(CardBox, javafx.geometry.HPos.CENTER);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void Recherche(KeyEvent event) {
        String recherche = Recherche_conge.getText();
        int row = 0;
        System.out.println(recherche);
        try {
            congeContainer.getChildren().clear(); // Clear existing items
            for (Conge conge : CongeS.Rechreche(recherche)) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CongeCarte.fxml"));
                Pane CardBox = fxmlLoader.load();
                CongeCarteController cardC = fxmlLoader.getController();
                cardC.setData(conge);
                congeContainer.add(CardBox, 0 , row++);
                GridPane.setMargin(CardBox, new Insets(4, 4, 4, 4));
                CardBox.setMaxWidth(Double.MAX_VALUE);
                congeContainer.setColumnSpan(CardBox, GridPane.REMAINING);
                GridPane.setHalignment(CardBox, javafx.geometry.HPos.CENTER);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void tri_statut(ActionEvent actionEvent) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        congeContainer.getColumnConstraints().add(columnConstraints);
        congeContainer.setVgap(4);
        congeContainer.setPadding(new Insets(4));
        int row = 0;
        try {
            congeContainer.getChildren().clear(); // Clear existing items

            for (Conge conge : CongeS.TriparStatut()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CongeCarte.fxml"));
                Pane CardBox = fxmlLoader.load();
                CongeCarteController cardC = fxmlLoader.getController();
                cardC.setData(conge);

                congeContainer.add(CardBox, 0 , row++);
                GridPane.setMargin(CardBox, new Insets(4, 4, 4, 4));
                CardBox.setMaxWidth(Double.MAX_VALUE);
                congeContainer.setColumnSpan(CardBox, GridPane.REMAINING);
                GridPane.setHalignment(CardBox, javafx.geometry.HPos.CENTER);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void tri_type(ActionEvent actionEvent) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        congeContainer.getColumnConstraints().add(columnConstraints);
        congeContainer.setVgap(4);
        congeContainer.setPadding(new Insets(4));
        int row = 0;
        try {
            congeContainer.getChildren().clear(); // Clear existing items
            for (Conge conge : CongeS.TriparType()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CongeCarte.fxml"));
                Pane CardBox = fxmlLoader.load();
                CongeCarteController cardC = fxmlLoader.getController();
                cardC.setData(conge);

                congeContainer.add(CardBox, 0, row++);
                GridPane.setMargin(CardBox, new Insets(4, 4, 4, 4));
                CardBox.setMaxWidth(Double.MAX_VALUE);
                congeContainer.setColumnSpan(CardBox, GridPane.REMAINING);
                GridPane.setHalignment(CardBox, javafx.geometry.HPos.CENTER);

            }
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



