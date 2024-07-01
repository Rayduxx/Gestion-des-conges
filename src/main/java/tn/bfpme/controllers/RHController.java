package tn.bfpme.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import tn.bfpme.dao.DepartmentDAO;
import tn.bfpme.dao.RoleDAO;
import tn.bfpme.dao.UserDAO;
import tn.bfpme.models.Department;
import tn.bfpme.models.User;

public class RHController {

    @FXML
    private Pane DepartementPane, RolesPane, UtilisateursPane;
    @FXML
    private ListView<Department> departementListView;
    @FXML
    private TextField deptNameField, deptDescriptionField;
    @FXML
    private ComboBox<Department> parentDeptComboBox;
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
    private ComboBox<Department> departmentComboBox;
    @FXML
    private ComboBox<Role> roleComboBox;

    private DepartmentDAO departmentDAO;
    private RoleDAO roleDAO;
    private UserDAO userDAO;

    public void initialize() {
        departmentDAO = new DepartmentDAO();
        roleDAO = new RoleDAO();
        userDAO = new UserDAO();

        loadDepartments();
        loadRoles();
        loadUsers();
    }

    private void loadDepartments() {
        ObservableList<Department> departments = FXCollections.observableArrayList(departmentDAO.getAllDepartments());
        departementListView.setItems(departments);
        parentDeptComboBox.setItems(departments);
        departmentComboBox.setItems(departments);
    }

    private void loadRoles() {
        ObservableList<Role> roles = FXCollections.observableArrayList(roleDAO.getAllRoles());
        roleListView.setItems(roles);
        parentRoleComboBox.setItems(roles);
        roleComboBox.setItems(roles);
    }

    private void loadUsers() {
        ObservableList<User> users = FXCollections.observableArrayList(userDAO.getAllUsers());
        userListView.setItems(users);
    }

    @FXML
    private void handleAddDepartment() {
        String name = deptNameField.getText();
        String description = deptDescriptionField.getText();
        Department parent = parentDeptComboBox.getSelectionModel().getSelectedItem();
        departmentDAO.addDepartment(name, description, parent != null ? parent.getId() : null);
        loadDepartments();
    }

    @FXML
    private void handleEditDepartment() {
        Department selectedDept = departementListView.getSelectionModel().getSelectedItem();
        if (selectedDept != null) {
            String name = deptNameField.getText();
            String description = deptDescriptionField.getText();
            Department parent = parentDeptComboBox.getSelectionModel().getSelectedItem();
            departmentDAO.updateDepartment(selectedDept.getId(), name, description, parent != null ? parent.getId() : null);
            loadDepartments();
        }
    }

    @FXML
    private void handleDeleteDepartment() {
        Department selectedDept = departementListView.getSelectionModel().getSelectedItem();
        if (selectedDept != null) {
            departmentDAO.deleteDepartment(selectedDept.getId());
            loadDepartments();
        }
    }

    @FXML
    private void handleAddRole() {
        String name = roleNameField.getText();
        String description = roleDescriptionField.getText();
        Role parent = parentRoleComboBox.getSelectionModel().getSelectedItem();
        roleDAO.addRole(name, description, parent != null ? parent.getId() : null);
        loadRoles();
    }

    @FXML
    private void handleEditRole() {
        Role selectedRole = roleListView.getSelectionModel().getSelectedItem();
        if (selectedRole != null) {
            String name = roleNameField.getText();
            String description = roleDescriptionField.getText();
            Role parent = parentRoleComboBox.getSelectionModel().getSelectedItem();
            roleDAO.updateRole(selectedRole.getId(), name, description, parent != null ? parent.getId() : null);
            loadRoles();
        }
    }

    @FXML
    private void handleDeleteRole() {
        Role selectedRole = roleListView.getSelectionModel().getSelectedItem();
        if (selectedRole != null) {
            roleDAO.deleteRole(selectedRole.getId());
            loadRoles();
        }
    }

    @FXML
    private void handleAddUser() {
        String name = userNameField.getText();
        String email = userEmailField.getText();
        Department department = departmentComboBox.getSelectionModel().getSelectedItem();
        Role role = roleComboBox.getSelectionModel().getSelectedItem();
        userDAO.addUser(name, email, department != null ? department.getId() : null, role != null ? role.getId() : null);
        loadUsers();
    }

    @FXML
    private void handleEditUser() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            String name = userNameField.getText();
            String email = userEmailField.getText();
            Department department = departmentComboBox.getSelectionModel().getSelectedItem();
            Role role = roleComboBox.getSelectionModel().getSelectedItem();
            userDAO.updateUser(selectedUser.getId(), name, email, department != null ? department.getId() : null, role != null ? role.getId() : null);
            loadUsers();
        }
    }

    @FXML
    private void handleDeleteUser() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userDAO.deleteUser(selectedUser.getId());
            loadUsers();
        }
    }

    @FXML
    private void handleAssignUser() {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        Department department = departmentComboBox.getSelectionModel().getSelectedItem();
        Role role = roleComboBox.getSelectionModel().getSelectedItem();
        if (selectedUser != null && department != null && role != null) {
            userDAO.assignUserToDepartmentAndRole(selectedUser.getId(), department.getId(), role.getId());
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
