package tn.bfpme.controllers.RHC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import tn.bfpme.models.Role;
import tn.bfpme.models.RoleHierarchie;
import tn.bfpme.services.ServiceRole;

public class paneHieController {
    @FXML
    private ComboBox<Role> parentRoleComboBox;
    @FXML
    private ComboBox<Role> RoleHComboBox;
    @FXML
    private ListView<RoleHierarchie> roleHListView;
    private final ServiceRole roleService = new ServiceRole();


    private void loadRoleHierarchie() {
        try {
            ObservableList<RoleHierarchie> roleHierarchies = FXCollections.observableArrayList(roleService.getAllRoleHierarchies());
            roleHListView.setItems(roleHierarchies);
        } catch (Exception e) {
            showError("Failed to load role hierarchies: " + e.getMessage());
        }
    }
    @FXML
    void handleAddRoleToH(ActionEvent event) {
        Role parent = parentRoleComboBox.getSelectionModel().getSelectedItem();
        Role child = RoleHComboBox.getSelectionModel().getSelectedItem();
        roleService.addRoleHierarchy(parent, child);
        loadRoleHierarchie();
    }

    @FXML
    void handleEditRoleH(ActionEvent event) {
        RoleHierarchie selectedRole = roleHListView.getSelectionModel().getSelectedItem();
        Role parent = parentRoleComboBox.getSelectionModel().getSelectedItem();
        Role child = RoleHComboBox.getSelectionModel().getSelectedItem();
        int id = selectedRole.getIdRoleH();
        if (selectedRole != null) {
            roleService.updateRoleHierarchy(id, parent, child);
            loadRoleHierarchie();
        }
    }
    @FXML
    void handleDeleteRoleH(ActionEvent event) {
       /* Role selectedRole = roleListView.getSelectionModel().getSelectedItem();
        if (selectedRole != null) {
            roleService.deleteRoleHierarchy(selectedRole.getIdRole());
            loadRoleHierarchie();
        }*/
    }
    protected void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
