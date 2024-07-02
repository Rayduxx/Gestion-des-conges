package tn.bfpme.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import tn.bfpme.models.Departement;
import tn.bfpme.models.Role;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceDepartement;
import tn.bfpme.services.ServiceRole;
import tn.bfpme.services.ServiceUtilisateur;

public class RHController {
    @FXML
    private Pane DepartementPane, RolesPane, UtilisateursPane;
    @FXML
    private ListView<Departement> departementListView;
    @FXML
    private TextField deptNameField, deptDescriptionField;
    @FXML
    private ComboBox<Departement> parentDeptComboBox;
    @FXML
    private ListView<Role> roleListView;
    @FXML
    private TextField roleNameField, roleDescriptionField;
    @FXML
    private ComboBox<Role> parentRoleComboBox;
    @FXML
    private ListView<User> userListView;
    @FXML
    private TextField userNameField, userEmailField;
    @FXML
    private ComboBox<Departement> departmentComboBox;
    @FXML
    private ComboBox<Role> roleComboBox;

    private ServiceDepartement depService;
    private ServiceRole roleService;
    private ServiceUtilisateur userService;

    public void initialize() {
        depService = new ServiceDepartement();
        roleService = new ServiceRole();
        userService = new ServiceUtilisateur();
        loadDepartments();
        loadRoles();
        loadUsers();
    }

    private void loadDepartments() {
        ObservableList<Departement> departments = FXCollections.observableArrayList(depService.getAllDepartments());
        departementListView.setItems(departments);
        parentDeptComboBox.setItems(departments);
        departmentComboBox.setItems(departments);
    }

    private void loadRoles() {
        ObservableList<Role> roles = FXCollections.observableArrayList(roleService.getAllRoles());
        roleListView.setItems(roles);
        parentRoleComboBox.setItems(roles);
        roleComboBox.setItems(roles);
    }

    private void loadUsers() {
        ObservableList<User> users = FXCollections.observableArrayList(userService.getAllUsers());
        userListView.setItems(users);
    }

    @FXML
    private void handleAddDepartment() {
        String name = deptNameField.getText();
        String description = deptDescriptionField.getText();
        Departement parent = parentDeptComboBox.getSelectionModel().getSelectedItem();
        depService.addDepartement(name, description, parent != null ? parent.getIdDepartement() : null);
        loadDepartments();
    }

    @FXML
    private void handleEditDepartment() {
        Departement selectedDept = departementListView.getSelectionModel().getSelectedItem();
        if (selectedDept != null) {
            String name = deptNameField.getText();
            String description = deptDescriptionField.getText();
            Departement parent = parentDeptComboBox.getSelectionModel().getSelectedItem();
            depService.updateDepartment(selectedDept.getIdDepartement(), name, description, parent != null ? parent.getIdDepartement() : null);
            loadDepartments();
        }
    }

    @FXML
    private void handleDeleteDepartment() {
        Departement selectedDept = departementListView.getSelectionModel().getSelectedItem();
        if (selectedDept != null) {
            depService.deleteDepartment(selectedDept.getIdDepartement());
            loadDepartments();
        }
    }

    @FXML
    private void handleAddRole() {
        String name = roleNameField.getText();
        String description = roleDescriptionField.getText();
        Role parent = parentRoleComboBox.getSelectionModel().getSelectedItem();
        roleService.addRole(name, description);
        loadRoles();
    }

    @FXML
    private void handleEditRole() {
        Role selectedRole = roleListView.getSelectionModel().getSelectedItem();
        if (selectedRole != null) {
            String name = roleNameField.getText();
            String description = roleDescriptionField.getText();
            Role parent = parentRoleComboBox.getSelectionModel().getSelectedItem();
            roleService.updateRole(selectedRole.getIdRole(), name, description);
            loadRoles();
        }
    }

    @FXML
    private void handleDeleteRole() {
        Role selectedRole = roleListView.getSelectionModel().getSelectedItem();
        if (selectedRole != null) {
            roleService.deleteRole(selectedRole.getIdRole());
            loadRoles();
        }
    }

    @FXML
    private void handleAddUser() {
        String name = userNameField.getText();
        String email = userEmailField.getText();
        Departement department = departmentComboBox.getSelectionModel().getSelectedItem();
        Role role = roleComboBox.getSelectionModel().getSelectedItem();
        //userService.addUser(name, email, department != null ? department.getIdDepartement() : null, role != null ? role.getIdRole() : null);
        loadUsers();
    }

    @FXML
    private void handleEditUser() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            String name = userNameField.getText();
            String email = userEmailField.getText();
            Departement department = departmentComboBox.getSelectionModel().getSelectedItem();
            Role role = roleComboBox.getSelectionModel().getSelectedItem();
            //userService.updateUser(selectedUser.getIdUser(), name, email, department != null ? department.getIdDepartement() : null, role != null ? role.getIdRole() : null);
            loadUsers();
        }
    }

    @FXML
    private void handleDeleteUser() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.deleteUser(selectedUser.getIdUser());
            loadUsers();
        }
    }

    @FXML
    private void handleAssignUser() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        Departement department = departmentComboBox.getSelectionModel().getSelectedItem();
        Role role = roleComboBox.getSelectionModel().getSelectedItem();
        if (selectedUser != null && department != null && role != null) {
            userService.assignUserToDepartmentAndRole(selectedUser.getIdUser(), department.getIdDepartement(), role.getIdRole());
            loadUsers();
        }
    }

    @FXML
    private void showDepartementPane() {
        DepartementPane.setVisible(true);
        RolesPane.setVisible(false);
        UtilisateursPane.setVisible(false);
    }

    @FXML
    private void showRolesPane() {
        DepartementPane.setVisible(false);
        RolesPane.setVisible(true);
        UtilisateursPane.setVisible(false);
    }

    @FXML
    private void showUtilisateursPane() {
        DepartementPane.setVisible(false);
        RolesPane.setVisible(false);
        UtilisateursPane.setVisible(true);
    }
}
