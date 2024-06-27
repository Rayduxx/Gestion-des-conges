package tn.bfpme.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
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
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

public class DemandeDepListeController implements Initializable {
    @FXML
    private GridPane DemandesContainer;
    @FXML
    private TextField Recherche_demande;
    @FXML
    private ComboBox<String> comboTri;
    @FXML
    private Button settingsButton;
    @FXML
    private Button btnListe;
    private ContextMenu contextMenu;
    private Conge conge;
    private final ServiceConge CongeS = new ServiceConge();
    private final ServiceUtilisateur UserS = new ServiceUtilisateur();

    public void load() {
        DemandesContainer.getColumnConstraints().clear();
        for (int i = 0; i < 3; i++) { // Three columns
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setHgrow(Priority.ALWAYS);
            DemandesContainer.getColumnConstraints().add(columnConstraints);
        }
        DemandesContainer.setVgap(10);
        DemandesContainer.setHgap(10);
        DemandesContainer.setPadding(new Insets(10));

        int row = 0;
        int column = 0;
        try {
            UserConge userConge = UserS.afficherusers();
            List<Utilisateur> users = userConge.getUsers();
            List<Conge> conges = userConge.getConges();
            for (Conge conge : conges) {
                for (Utilisateur user : users) {
                    if (conge.getIdUser() == user.getIdUser()) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/UserCarte.fxml"));
                        try {
                            Pane cardBox = fxmlLoader.load();
                            UserCarteController cardu = fxmlLoader.getController();
                            cardu.setData(conge, user);
                            DemandesContainer.add(cardBox, column, row);
                            GridPane.setMargin(cardBox, new Insets(10));
                            cardBox.setMaxWidth(Double.MAX_VALUE);
                            column++;
                            if (column == 3) {
                                column = 0;
                                row++;
                            }
                        } catch (IOException e) {
                            System.err.println("Error loading UserCarte.fxml: " + e.getMessage());
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in load method: " + e.getMessage());
            e.printStackTrace();
        }
    }

    ObservableList<String> TriListe = FXCollections.observableArrayList("Type", "Nom", "Prenom", "Date Debut", "Date Fin");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        comboTri.setValue("Selectioner");
        comboTri.setItems(TriListe);
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

    @FXML void Recherche(KeyEvent event) {
    }

    @FXML void TriPar(ActionEvent event) {
        String TYPE = comboTri.getValue();
        if (TYPE != null) {
            switch (TYPE) {
                case "Type":
                    triGenerique(UserS.TriType());
                    break;
                case "Nom":
                    triGenerique(UserS.TriNom());
                    break;
                case "Prenom":
                    triGenerique(UserS.TriPrenom());
                    break;
                case "Date Debut":
                    triGenerique(UserS.TriDateDebut());
                    break;
                case "Date Fin":
                    triGenerique(UserS.TriDateFin());
                    break;
                default:
                    break;
            }
        }
    }

    private void triGenerique(UserConge userConge) {
        int row = 0;
        int column = 0;
        try {
            DemandesContainer.getChildren().clear();
            List<Utilisateur> users = userConge.getUsers();
            List<Conge> conges = userConge.getConges();
            for (Conge conge : conges) {
                for (Utilisateur user : users) {
                    if (conge.getIdUser() == user.getIdUser()) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/UserCarte.fxml"));
                        try {
                            Pane cardBox = fxmlLoader.load();
                            UserCarteController cardu = fxmlLoader.getController();
                            cardu.setData(conge, user);
                            DemandesContainer.add(cardBox, column, row);
                            GridPane.setMargin(cardBox, new Insets(10));
                            cardBox.setMaxWidth(Double.MAX_VALUE);
                            column++;
                            if (column == 3) {
                                column = 0;
                                row++;
                            }
                        } catch (IOException e) {
                            System.err.println("Error loading UserCarte.fxml: " + e.getMessage());
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void viewaide(ActionEvent actionEvent) {
        // Implement viewaide functionality here
    }

    void viewboite(ActionEvent actionEvent) {
        // Implement viewboite functionality here
    }

    @FXML void viewdeconnection(ActionEvent actionEvent) {
        SessionManager.getInstance().cleanUserSession();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
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

    @FXML void Demander(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Demande congé");
            stage.show();
            StageManager.addStage("DemandeConge", stage);
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
            StageManager.addStage("HistoriqueConge", stage);
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
            StageManager.addStage("Profile", stage);
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
            stage.setTitle("Liste des demandes - " + SessionManager.getInstance().getDepartement());
            stage.show();
            StageManager.addStage("DemandeDepListe", stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void LesApprouves(ActionEvent event) {
        int row = 0;
        int column = 0;
        try {
            DemandesContainer.getChildren().clear();
            UserConge userConge = UserS.AfficherApprove();
            List<Utilisateur> users = userConge.getUsers();
            List<Conge> conges = userConge.getConges();
            for (Conge conge : conges) {
                for (Utilisateur user : users) {
                    if (conge.getIdUser() == user.getIdUser()) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/UserCarte.fxml"));
                        try {
                            Pane cardBox = fxmlLoader.load();
                            UserCarteController cardu = fxmlLoader.getController();
                            cardu.setData(conge, user);
                            DemandesContainer.add(cardBox, column, row);
                            GridPane.setMargin(cardBox, new Insets(10));
                            cardBox.setMaxWidth(Double.MAX_VALUE);
                            column++;
                            if (column == 3) {
                                column = 0;
                                row++;
                            }
                        } catch (IOException e) {
                            System.err.println("Error loading UserCarte.fxml: " + e.getMessage());
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void LesEnAttente(ActionEvent event) {
        int row = 0;
        int column = 0;
        try {
            DemandesContainer.getChildren().clear();
            UserConge userConge = UserS.afficherusers();
            List<Utilisateur> users = userConge.getUsers();
            List<Conge> conges = userConge.getConges();
            for (Conge conge : conges) {
                for (Utilisateur user : users) {
                    if (conge.getIdUser() == user.getIdUser()) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/UserCarte.fxml"));
                        try {
                            Pane cardBox = fxmlLoader.load();
                            UserCarteController cardu = fxmlLoader.getController();
                            cardu.setData(conge, user);
                            DemandesContainer.add(cardBox, column, row);
                            GridPane.setMargin(cardBox, new Insets(10));
                            cardBox.setMaxWidth(Double.MAX_VALUE);
                            column++;
                            if (column == 3) {
                                column = 0;
                                row++;
                            }
                        } catch (IOException e) {
                            System.err.println("Error loading UserCarte.fxml: " + e.getMessage());
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void LesRejetes(ActionEvent event) {
        int row = 0;
        int column = 0;
        try {
            DemandesContainer.getChildren().clear();
            UserConge userConge = UserS.AfficherReject();
            List<Utilisateur> users = userConge.getUsers();
            List<Conge> conges = userConge.getConges();
            for (Conge conge : conges) {
                for (Utilisateur user : users) {
                    if (conge.getIdUser() == user.getIdUser()) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/UserCarte.fxml"));
                        try {
                            Pane cardBox = fxmlLoader.load();
                            UserCarteController cardu = fxmlLoader.getController();
                            cardu.setData(conge, user);
                            DemandesContainer.add(cardBox, column, row);
                            GridPane.setMargin(cardBox, new Insets(10));
                            cardBox.setMaxWidth(Double.MAX_VALUE);
                            column++;
                            if (column == 3) {
                                column = 0;
                                row++;
                            }
                        } catch (IOException e) {
                            System.err.println("Error loading UserCarte.fxml: " + e.getMessage());
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
