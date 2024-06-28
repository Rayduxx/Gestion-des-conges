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
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Role;
import tn.bfpme.services.ServiceConge;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class HistoriqueCongeController implements Initializable {
    private final ServiceConge CongeS = new ServiceConge();
    private Connection cnx;
    private ContextMenu contextMenu;
    @FXML private TextField Recherche_conge;
    @FXML private Button settingsButton;
    @FXML private Button btnListe;
    @FXML private MenuItem Menu_profile;
    @FXML private GridPane congeContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        contextMenu = new ContextMenu();
        MenuItem boiteItem = new MenuItem("boîte de réception");
        MenuItem aideItem = new MenuItem("Aide et support");
        MenuItem logoutItem = new MenuItem("Déconnexion");
        contextMenu.getItems().addAll(boiteItem, aideItem, logoutItem);
        settingsButton.setOnAction(event -> {
            double screenX = settingsButton.localToScreen(settingsButton.getBoundsInLocal()).getMinX() - 70;
            double screenY = settingsButton.localToScreen(settingsButton.getBoundsInLocal()).getMaxY() + 10;
            contextMenu.show(settingsButton, screenX, screenY);
        });
        boiteItem.setOnAction(this::viewboite);
        aideItem.setOnAction(this::viewaide);
        logoutItem.setOnAction(this::viewdeconnection);
        if (SessionManager.getInstance().getUtilisateur().getRole().equals(Role.ChefDepartement)) {
            btnListe.setVisible(true);
        }
    }

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
    public void tri_statut(ActionEvent actionEvent) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        congeContainer.getColumnConstraints().add(columnConstraints);
        congeContainer.setVgap(4);
        congeContainer.setPadding(new Insets(4));
        int row = 0;
        try {
            congeContainer.getChildren().clear();
            for (Conge conge : CongeS.TriparStatut()) {
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
    void tri_datedeb(ActionEvent event) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        congeContainer.getColumnConstraints().add(columnConstraints);
        congeContainer.setVgap(4);
        congeContainer.setPadding(new Insets(4));
        int row = 0;
        try {
            congeContainer.getChildren().clear(); // Clear existing items

            for (Conge conge : CongeS.TriparDateD()) {
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
    void tri_datefin(ActionEvent event) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        congeContainer.getColumnConstraints().add(columnConstraints);
        congeContainer.setVgap(4);
        congeContainer.setPadding(new Insets(4));
        int row = 0;
        try {
            congeContainer.getChildren().clear(); // Clear existing items

            for (Conge conge : CongeS.TriparDateF()) {
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
    void tri_desc(ActionEvent event) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        congeContainer.getColumnConstraints().add(columnConstraints);
        congeContainer.setVgap(4);
        congeContainer.setPadding(new Insets(4));
        int row = 0;
        try {
            congeContainer.getChildren().clear(); // Clear existing items

            for (Conge conge : CongeS.TriparDesc()) {
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
    void tri_type(ActionEvent event) {
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
            StageManager.addStage("DemandeConge", stage);
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
            stage.setTitle("Historique des demandes");
            stage.show();
            StageManager.addStage("HistoriqueConge", stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewdeconnection(ActionEvent actionEvent) {
        SessionManager.getInstance().cleanUserSession();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();

            // Get the stage from the current context menu's owner window
            MenuItem menuItem = (MenuItem) actionEvent.getSource();
            Stage stage = (Stage) menuItem.getParentPopup().getOwnerWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Gestion de Congés - Connection");
            StageManager.addStage("Login", stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void viewaide(ActionEvent actionEvent) {
        // Implement viewaide functionality here
    }

    void viewboite(ActionEvent actionEvent) {
        // Implement viewboite functionality here
    }

    @FXML
    public void goto_profil(@NotNull ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Mon profil");
            stage.show();
            StageManager.addStage("Profile", stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ListeDesDemandes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeDepListe.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Liste des demandes - " + SessionManager.getInstance().getDepartement());
            stage.show();
            StageManager.addStage("DemandeDepListe", stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
