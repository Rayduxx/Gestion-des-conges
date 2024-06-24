package tn.bfpme.controllers;

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
import javafx.stage.Stage;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Departement;
import tn.bfpme.models.TypeConge;
import tn.bfpme.models.Utilisateur;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

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
        CU_dep.setText(String.valueOf(SessionManager.getInstance().getDepartement()));
        CU_email.setText(SessionManager.getInstance().getEmail());
        CU_nomprenom.setText(SessionManager.getInstance().getNom()+" "+SessionManager.getInstance().getPrenom());
        CU_role.setText(String.valueOf(SessionManager.getInstance().getRole()));
        CU_solde.setText(String.valueOf(SessionManager.getInstance().getSoldeConge()));
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
        fetchUserCongés();
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
    @FXML public void Demander(ActionEvent actionEvent) {
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
    @FXML public void Historique(ActionEvent actionEvent) {
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

    private void fetchUserCongés() {
        ObservableList<Conge> HisUserList = FXCollections.observableArrayList();
        String sql = "SELECT `DateDebut`, `DateFin`, `TypeConge` FROM `conge` WHERE `ID_User`=?";
        Connection cnx = MyDataBase.getInstance().getCnx();
        try {
            PreparedStatement stm = cnx.prepareStatement(sql);
            stm.setInt(1, SessionManager.getInstance().getId_user());
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
}
