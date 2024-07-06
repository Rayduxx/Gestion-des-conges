package tn.bfpme.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ResponsableStructure implements Initializable {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> userIdColumn;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, String> userEmailColumn;
    @FXML
    private TableColumn<User, String> userManagerColumn;
    @FXML
    private TableColumn<User, String> userPrenomColumn;
    @FXML
    private ComboBox<User> managerComboBox;
    @FXML
    private ComboBox<User> userComboBox;
    @FXML
    private AnchorPane MainAnchorPane;
    @FXML
    private TreeView<?> treeview;

    private ServiceUtilisateur serviceUtilisateur;

    private void loadUsers() {
        List<User> users = serviceUtilisateur.getAllUsersWithManagers();
        userTable.setItems(FXCollections.observableArrayList(users));
        managerComboBox.setItems(FXCollections.observableArrayList(users));
        userComboBox.setItems(FXCollections.observableArrayList(users));
    }

    @FXML
    public void setManager(ActionEvent event) {
        User selectedUser = userComboBox.getSelectionModel().getSelectedItem();
        User selectedManager = managerComboBox.getSelectionModel().getSelectedItem();

        if (selectedUser != null && selectedManager != null) {
            serviceUtilisateur.setUserManager(selectedUser.getIdUser(), selectedManager.getIdUser());
            loadUsers(); // Refresh the table view
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/NavigationHeader.fxml"));
            Pane departementPane = loader.load();
            MainAnchorPane.getChildren().add(departementPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        serviceUtilisateur = new ServiceUtilisateur();

        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("ID_User"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        userPrenomColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        userManagerColumn.setCellValueFactory(cellData -> {
            User manager = serviceUtilisateur.getUserManager(cellData.getValue().getIdUser());
            return new SimpleStringProperty(manager != null ? manager.getNom() + " " + manager.getPrenom() : "No Manager");
        });

        loadUsers();
    }
}
