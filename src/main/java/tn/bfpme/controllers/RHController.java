package tn.bfpme.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import tn.bfpme.models.Departement;
import tn.bfpme.models.Role;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceDepartement;
import tn.bfpme.services.ServiceRole;
import tn.bfpme.services.ServiceUtilisateur;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

import java.io.IOException;
import java.util.List;

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
    @FXML
    private Button settingsButton;
    @FXML
    public Button NotifBtn;

    private ServiceDepartement depService;
    private ServiceRole roleService;
    private ServiceUtilisateur userService;
    private Popup settingsPopup;
    private Popup notifPopup;

    public void initialize() {
        depService = new ServiceDepartement();
        roleService = new ServiceRole();
        userService = new ServiceUtilisateur();
        loadDepartments();
        loadRoles();
        loadUsers();
        departementListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deptNameField.setText(newValue.getNom());
                deptDescriptionField.setText(newValue.getDescription());
                parentDeptComboBox.getSelectionModel().select(newValue.getParentDept() != 0 ? depService.getDepartmentById(newValue.getParentDept()) : null);
            }
        });
        roleListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                roleNameField.setText(newValue.getNom());
                roleDescriptionField.setText(newValue.getDescription());
                //parentRoleComboBox.getSelectionModel().select(roleService.getRoleParents(newValue.getIdRole()));
                parentDeptComboBox.getSelectionModel().select(roleService.getRoleParents(newValue.getIdRole()) != 0 ? roleService.getRoleParents(newValue.getIdRole()) : null);

            }
        });

        settingsPopup = new Popup();
        settingsPopup.setAutoHide(true);

        try {
            Parent settingsContent = FXMLLoader.load(getClass().getResource("/Settings.fxml"));
            settingsPopup.getContent().add(settingsContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        notifPopup = new Popup();
        notifPopup.setAutoHide(true);
        try {
            Parent settingsContent = FXMLLoader.load(getClass().getResource("/paneNotif.fxml"));
            notifPopup.getContent().add(settingsContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDepartments() {
        List<Departement> departmentList = depService.getAllDepartments();
        Departement noParentDept = new Departement(0, "", "", 0); // Create a "No Parent" option
        departmentList.add(0, noParentDept); // Add the "No Parent" option at the beginning of the list

        ObservableList<Departement> departments = FXCollections.observableArrayList(departmentList);
        departementListView.setItems(departments);
        departementListView.setCellFactory(param -> new ListCell<Departement>() {
            @Override
            protected void updateItem(Departement item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNom() == null) {
                    setText(null);
                } else {
                    setText(item.getNom());
                }
            }
        });
        parentDeptComboBox.setItems(departments);
        parentDeptComboBox.setCellFactory(param -> new ListCell<Departement>() {
            @Override
            protected void updateItem(Departement item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNom() == null) {
                    setText(null);
                } else {
                    setText(item.getNom());
                }
            }
        });
        parentDeptComboBox.setButtonCell(new ListCell<Departement>() {
            @Override
            protected void updateItem(Departement item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNom() == null) {
                    setText(null);
                } else {
                    setText(item.getNom());
                }
            }
        });
    }

    private void loadRoles() {
        List<Role> roleList = roleService.getAllRoles();
        Role noParentRole = new Role(0, "", "");
        roleList.add(0, noParentRole);
        ObservableList<Role> roles = FXCollections.observableArrayList(roleList);
        roleListView.setItems(roles);
        roleListView.setCellFactory(param -> new ListCell<Role>() {
            @Override
            protected void updateItem(Role item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNom() == null) {
                    setText(null);
                } else {
                    setText(item.getNom());
                }
            }
        });
        parentRoleComboBox.setItems(roles);
        parentRoleComboBox.setCellFactory(param -> new ListCell<Role>() {
            @Override
            protected void updateItem(Role item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNom() == null) {
                    setText(null);
                } else {
                    setText(item.getNom());
                }
            }
        });
        parentRoleComboBox.setButtonCell(new ListCell<Role>() {
            @Override
            protected void updateItem(Role item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNom() == null) {
                    setText(null);
                } else {
                    setText(item.getNom());
                }
            }
        });
        roleComboBox.setItems(roles);
    }

    /*private void loadRoles() {
        ObservableList<Role> roles = FXCollections.observableArrayList(roleService.getAllRoles());
        roleListView.setItems(roles);
        parentRoleComboBox.setItems(roles);
        roleComboBox.setItems(roles);
    }*/

    private void loadUsers() {
        ObservableList<User> users = FXCollections.observableArrayList(userService.getAllUsers());
        userListView.setItems(users);
    }

    @FXML
    private void handleAddDepartment() {
        String name = deptNameField.getText();
        String description = deptDescriptionField.getText();
        Departement parent = parentDeptComboBox.getSelectionModel().getSelectedItem();

        if(parent.getNom() ==  "") {
            depService.addDepartement2(name, description);
        }else{
        depService.addDepartement(name, description, parent.getIdDepartement() != 0 ? parent.getIdDepartement() : 0);
        }
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

    @FXML
    void ListeDesDemandes(ActionEvent event) {
        navigateToScene(event, "/DemandeDepListe.fxml", "Liste des demandes - " + SessionManager.getInstance().getUserDepartmentName());
    }

    @FXML
    public void goto_profil(ActionEvent actionEvent) {
        navigateToScene(actionEvent, "/profile.fxml", "Mon profil");
    }

    @FXML
    public void Demander(ActionEvent actionEvent) {
        navigateToScene(actionEvent, "/DemandeConge.fxml", "Demande congé");
    }

    @FXML
    public void Historique(ActionEvent actionEvent) {
        navigateToScene(actionEvent, "/HistoriqueConge.fxml", "Historique congé");
    }

    @FXML
    void settings_button(ActionEvent event) {
        if (settingsPopup.isShowing()) {
            settingsPopup.hide();
        } else {
            Window window = ((Node) event.getSource()).getScene().getWindow();
            double x = window.getX() + settingsButton.localToScene(0, 0).getX() + settingsButton.getScene().getX() - 150;
            double y = window.getY() + settingsButton.localToScene(0, 0).getY() + settingsButton.getScene().getY() + settingsButton.getHeight();
            settingsPopup.show(window, x, y);
        }
    }

    @FXML
    void OpenNotifPane(ActionEvent event) {
        if (notifPopup.isShowing()) {
            notifPopup.hide();
        } else {
            Window window = ((Node) event.getSource()).getScene().getWindow();
            double x = window.getX() + NotifBtn.localToScene(0, 0).getX() + NotifBtn.getScene().getX() - 250;
            double y = window.getY() + NotifBtn.localToScene(0, 0).getY() + NotifBtn.getScene().getY() + NotifBtn.getHeight();
            notifPopup.show(window, x, y);
        }
    }

    private void navigateToScene(ActionEvent actionEvent, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
            StageManager.addStage(title, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
