package tn.bfpme.controllers.RHC;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import tn.bfpme.models.Departement;
import tn.bfpme.models.Role;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceDepartement;
import tn.bfpme.services.ServiceRole;
import tn.bfpme.services.ServiceUtilisateur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class paneUserController implements Initializable {
    @FXML
    private ListView<User> userListView;
    @FXML
    private TextField User_field;
    @FXML
    protected ComboBox<Departement> departmentComboBox;
    @FXML
    protected ComboBox<Role> roleComboBox;

    private final ServiceDepartement depService = new ServiceDepartement();
    private final ServiceUtilisateur userService = new ServiceUtilisateur();
    private final ServiceRole roleService = new ServiceRole();
    private RHController RHC;
    private User selectedUser;
    private FilteredList<User> filteredData;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUsers();
        List<User> userList = userService.getAllUsers();
        ObservableList<User> users = FXCollections.observableArrayList(userList);
        filteredData = new FilteredList<>(users, p -> true);
        userListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if (newValue != null) {
                    handleUserSelection(newValue);
                }
            });
        });
        userListView.setItems(filteredData);
        userListView.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    Departement departement = depService.getDepartmentById(user.getIdDepartement());
                    Role role = roleService.getRoleById(user.getIdRole());
                    setText(user.getPrenom() + " " + user.getNom() + " _ " + user.getEmail() + " _ " + (role != null ? role.getNom() : "N/A") + " _ " + (departement != null ? departement.getNom() : "N/A"));
                }
            }
        });

        User_field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return user.getNom().toLowerCase().contains(lowerCaseFilter) || user.getPrenom().toLowerCase().contains(lowerCaseFilter) || user.getEmail().toLowerCase().contains(lowerCaseFilter);
            });
        });
        userListView.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    Departement departement = depService.getDepartmentById(user.getIdDepartement());
                    Role role = roleService.getRoleById(user.getIdRole());
                    setText(user.getPrenom() + " " + user.getNom() + " _ " + user.getEmail() + " _ " + (role != null ? role.getNom() : "N/A") + " _ " + (departement != null ? departement.getNom() : "N/A"));
                }
            }
        });
    }

    @FXML
    public void User_Recherche(ActionEvent actionEvent) {
        String searchText = User_field.getText().trim();
        for (User user : userService.getAllUsers()) {
            if ((user.getNom() + " " + user.getPrenom()).equalsIgnoreCase(searchText) || user.getEmail().equalsIgnoreCase(searchText) || ((user.getPrenom() + " " + user.getNom()).equalsIgnoreCase(searchText))) {
                User_field.setText(user.getEmail());
                break;
            }
        }

    }

    @FXML
    private void handleEditUser() {
        // Debug statement to check if a user is selected
        System.out.println("Selected User in handleEditUser: " + selectedUser);

        if (selectedUser != null) {
            Role selectedRole = roleComboBox.getSelectionModel().getSelectedItem();
            Departement selectedDepartement = departmentComboBox.getSelectionModel().getSelectedItem();

            // Debug statements to check selected role and department
            System.out.println("Selected Role: " + selectedRole);
            System.out.println("Selected Department: " + selectedDepartement);

            boolean isUpdated = false;

            try {
                if (selectedRole != null && selectedDepartement != null) {
                    userService.updateUserRoleAndDepartment(selectedUser.getIdUser(), selectedRole.getIdRole(), selectedDepartement.getIdDepartement());
                    isUpdated = true;
                } else if (selectedRole != null) {
                    userService.updateUserRole(selectedUser.getIdUser(), selectedRole.getIdRole());
                    isUpdated = true;
                } else if (selectedDepartement != null) {
                    userService.updateUserDepartment(selectedUser.getIdUser(), selectedDepartement.getIdDepartement());
                    isUpdated = true;
                }

                if (isUpdated) {
                    loadUsers();
                    highlightSelectedUser(selectedUser);
                } else {
                    RHC.showError("Please select a role and/or department to assign.");
                }
            } catch (Exception e) {
                // Log any exception that occurs during the update process
                e.printStackTrace();
                RHC.showError("An error occurred while updating the user: " + e.getMessage());
            }
        } else {
            RHC.showError("Please select a user to edit.");
        }
    }

    @FXML
    private void handleAssignUser() {
        Integer userId = getSelectedUserId();
        Role selectedRole = roleComboBox.getSelectionModel().getSelectedItem();

        if (userId != null && selectedRole != null) {
            int roleId = selectedRole.getIdRole();
            userService.assignRoleToUser(userId, roleId);
            loadUsers();
            highlightSelectedUser(userService.getUserById(userId));
        } else {
            RHC.showError("Please select a user and a role to assign.");
        }
    }

    private void handleUserSelection(User newValue) {
        selectedUser = newValue;
        try {
            User_field.setText(newValue.getPrenom() + " " + newValue.getNom());

            Departement departement = depService.getDepartmentById(newValue.getIdDepartement());
            Role role = roleService.getRoleById(newValue.getIdRole());

            if (departement != null) {
                departmentComboBox.getSelectionModel().select(departement);
            } else {
                departmentComboBox.getSelectionModel().clearSelection();
            }

            if (role != null) {
                roleComboBox.getSelectionModel().select(role);
            } else {
                roleComboBox.getSelectionModel().clearSelection();
            }
            System.out.println("Selected User in Listener: " + newValue);
        } catch (Exception e) {
            e.printStackTrace();
            RHC.showError("An error occurred while selecting the user: " + e.getMessage());
        }
    }

    public void highlightSelectedUser(User user) {
        Platform.runLater(() -> {
            userListView.getItems().forEach(u -> {
                if (u.equals(user)) {
                    userListView.getSelectionModel().select(u);
                    userListView.scrollTo(u);
                }
            });
        });
    }

    public Integer getSelectedUserId() {
        return selectedUser != null ? selectedUser.getIdUser() : null;
    }

    private void loadUsers() {
        try {
            List<User> userList = userService.getAllUsers();
            ObservableList<User> users = FXCollections.observableArrayList(userList);
            filteredData = new FilteredList<>(users, p -> true);
            userListView.setItems(filteredData);
            userListView.setCellFactory(param -> new ListCell<User>() {
                @Override
                protected void updateItem(User user, boolean empty) {
                    super.updateItem(user, empty);
                    if (empty || user == null) {
                        setText(null);
                    } else {
                        Departement departement = depService.getDepartmentById(user.getIdDepartement());
                        Role role = roleService.getRoleById(user.getIdRole());
                        setText(user.getPrenom() + " " + user.getNom() + " _ " + user.getEmail() + " _ " + (role != null ? role.getNom() : "N/A") + " _ " + (departement != null ? departement.getNom() : "N/A"));
                    }
                }
            });
        } catch (Exception e) {
            RHC.showError("Failed to load users: " + e.getMessage());
        }
    }
}
