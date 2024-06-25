package tn.bfpme.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.bfpme.models.*;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeController implements Initializable {
    @FXML private Label CU_dep;
    @FXML private Label CU_email;
    @FXML private Label CU_nomprenom;
    @FXML private Label CU_role;
    @FXML private ImageView CU_pdp;
    @FXML private Label CU_solde;
    @FXML private Button settingsButton;
    @FXML private TableView<Conge> TableHistorique;
    @FXML private TableColumn<Conge, LocalDate> TableDD;
    @FXML private TableColumn<Conge, LocalDate> TableDF;
    @FXML private TableColumn<Conge, TypeConge> TableType;
    private ContextMenu contextMenu;
    private Connection cnx;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contextMenu = new ContextMenu();

        // Add menu items to the context menu
        MenuItem boiteItem = new MenuItem("boîte de réception");
        MenuItem aideItem = new MenuItem("Aide et support");
        MenuItem logoutItem = new MenuItem("Déconnexion");


        contextMenu.getItems().addAll(boiteItem, aideItem, logoutItem);

        // Show the context menu directly under the settings button
        settingsButton.setOnAction(event -> {
            double screenX = settingsButton.localToScreen(settingsButton.getBoundsInLocal()).getMinX() - 70;
            double screenY = settingsButton.localToScreen(settingsButton.getBoundsInLocal()).getMaxY() + 10;
            contextMenu.show(settingsButton, screenX, screenY);
        });
        boiteItem.setOnAction(this::viewboite);
        aideItem.setOnAction(this::viewaide);
        logoutItem.setOnAction(this::viewdeconnection);
        fetchUserCongés();
        ReloadUserDATA();
    }
    @FXML public void DemanderConge(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Demande de Congé");
            stage.show();
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML public void HistoriqueConge(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HistoriqueConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Historique des Congés");
            stage.show();
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML public void Demander(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Demande congé");
            stage.show();
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML public void Historique(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HistoriqueConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Historique congé");
            stage.show();
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML void viewaide(ActionEvent actionEvent) {

    }
    @FXML private void viewboite(ActionEvent actionEvent) {

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
            StageManager.addStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchUserCongés() {
        ObservableList<Conge> HisUserList = FXCollections.observableArrayList();
        String sql = "SELECT `DateDebut`, `DateFin`, `TypeConge` FROM `conge` WHERE `ID_User`=? AND `Statut`=?";
        Connection cnx = MyDataBase.getInstance().getCnx();
        try {
            PreparedStatement stm = cnx.prepareStatement(sql);
            //stm.setInt(1, SessionManager.getInstance().getId_user());
            stm.setInt(1, SessionManager.getInstance().getUtilisateur().getIdUser());
            stm.setString(2, String.valueOf(Statut.Approuvé));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                HisUserList.add(new Conge(rs.getDate("DateDebut").toLocalDate(), rs.getDate("DateFin").toLocalDate(), TypeConge.valueOf(rs.getString("TypeConge"))));
                TableType.setCellValueFactory(new PropertyValueFactory<>("TypeConge"));
                TableDD.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
                TableDF.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
                TableHistorique.setItems(HisUserList);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void ReloadUserDATA(){
       /* CU_dep.setText(String.valueOf(SessionManager.getInstance().getDepartement()));
        CU_email.setText(SessionManager.getInstance().getEmail());
        CU_nomprenom.setText(SessionManager.getInstance().getNom()+" "+SessionManager.getInstance().getPrenom());
        CU_role.setText(String.valueOf(SessionManager.getInstance().getRole()));
        CU_solde.setText(String.valueOf(SessionManager.getInstance().getSoldeConge()));*/
        CU_dep.setText(String.valueOf(SessionManager.getInstance().getDepartement()));
        CU_email.setText(SessionManager.getInstance().getUtilisateur().getEmail());
        CU_nomprenom.setText(SessionManager.getInstance().getUtilisateur().getNom()+" "+SessionManager.getInstance().getUtilisateur().getPrenom());
        CU_role.setText(String.valueOf(SessionManager.getInstance().getUtilisateur().getRole()));
        CU_solde.setText(String.valueOf(SessionManager.getInstance().getUtilisateur().getSoldeConge()));
    }
    @FXML
    public void goto_profil(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
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
}
