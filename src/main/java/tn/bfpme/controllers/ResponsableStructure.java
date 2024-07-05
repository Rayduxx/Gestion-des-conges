package tn.bfpme.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceUtilisateur;

import java.util.List;

public class ResponsableStructure {

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
    private ComboBox<User> managerComboBox;
    @FXML
    private ComboBox<User> userComboBox;

    private ServiceUtilisateur serviceUtilisateur;

    @FXML
    public void initialize() {
        serviceUtilisateur = new ServiceUtilisateur();

        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        userManagerColumn.setCellValueFactory(cellData -> {
            User manager = cellData.getValue();
            return new SimpleStringProperty(manager != null ? manager.getNom() : "No Manager");
        });

        loadUsers();
    }

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
}
