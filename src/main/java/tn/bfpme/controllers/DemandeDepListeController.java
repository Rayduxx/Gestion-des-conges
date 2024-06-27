package tn.bfpme.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import tn.bfpme.models.*;
import tn.bfpme.services.ServiceConge;
import tn.bfpme.services.ServiceUtilisateur;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

public class DemandeDepListeController implements Initializable {
    @FXML private GridPane DemandesContainer;
    @FXML private TextField Recherche_demande;
    @FXML private ComboBox<String> comboTri;
    @FXML private Button settingsButton;
    private ContextMenu contextMenu;


    private Conge conge;
    private final ServiceConge CongeS = new ServiceConge();
    private final ServiceUtilisateur UserS = new ServiceUtilisateur();
    public void load() {

        System.out.println("Loading demandes...");
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        DemandesContainer.getColumnConstraints().add(columnConstraints);
        DemandesContainer.setVgap(4);
        DemandesContainer.setPadding(new Insets(4));
        int row = 0;
        try {
            UserConge userConge = UserS.afficherusers();
            System.out.println("UserConge object: " + userConge);
            List<Utilisateur> users = userConge.getUsers();
            List<Conge> conges = userConge.getConges();
            System.out.println("Number of users: " + users.size());
            System.out.println("Number of conges: " + conges.size());

            for (Conge conge : conges) {
                for (Utilisateur user : users) {
                    if (conge.getIdUser() == user.getIdUser()) {
                        System.out.println("Processing conge for user: " + user.getNom() + " " + user.getPrenom());
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/UserCarte.fxml"));
                        try {
                            Pane cardBox = fxmlLoader.load();
                            UserCarteController cardu = fxmlLoader.getController();
                            cardu.setData(conge, user);

                            DemandesContainer.add(cardBox, 0, row++);
                            GridPane.setMargin(cardBox, new Insets(4, 4, 4, 4));
                            cardBox.setMaxWidth(Double.MAX_VALUE);
                            DemandesContainer.setColumnSpan(cardBox, GridPane.REMAINING);
                            GridPane.setHalignment(cardBox, javafx.geometry.HPos.CENTER);

                            System.out.println("Added card for user: " + user.getNom() + " " + user.getPrenom());
                        } catch (IOException e) {
                            System.err.println("Error loading UserCarte.fxml: " + e.getMessage());
                        }
                        break; // Exit the inner loop once the correct user is found
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in load method: " + e.getMessage());
            e.printStackTrace();
        }

    }





    ObservableList<String> TriListe = FXCollections.observableArrayList("Statut", "Type", "Nom", "Prenom", "Date Debut", "Date Fin");
    @Override public void initialize(URL url, ResourceBundle rb) {
        load();
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
