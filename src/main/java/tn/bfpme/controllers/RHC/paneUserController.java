package tn.bfpme.controllers.RHC;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    public ComboBox<Departement> departmentComboBox;
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
        List<Departement> departmentList = depService.getAllDepartments();
        Departement noParentDept = new Departement(0, "", "", 0);
        departmentList.add(0, noParentDept);
        List<Role> roleList = roleService.getAllRoles();
        Role noParentRole = new Role(0, "", "");
        roleList.add(0, noParentRole);
        ObservableList<Role> roles = FXCollections.observableArrayList(roleList);
        ObservableList<Departement> departments = FXCollections.observableArrayList(departmentList);
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
        departmentComboBox.setItems(departments);
        departmentComboBox.setCellFactory(param -> new ListCell<Departement>() {
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
        departmentComboBox.setButtonCell(new ListCell<Departement>() {
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
        roleComboBox.setItems(roles);
        roleComboBox.setCellFactory(param -> new ListCell<Role>() {
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
        roleComboBox.setButtonCell(new ListCell<Role>() {
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
        System.out.println("handleEditUser called");

        if (selectedUser != null) {
            Role selectedRole = roleComboBox.getSelectionModel().getSelectedItem();
            Departement selectedDepartement = departmentComboBox.getSelectionModel().getSelectedItem();

            boolean isUpdated = false;

            try {
                if (selectedRole != null && selectedDepartement != null) {
                    System.out.println("Updating role and department for user: " + selectedUser);
                    userService.updateUserRoleAndDepartment(selectedUser.getIdUser(), selectedRole.getIdRole(), selectedDepartement.getIdDepartement());
                    isUpdated = true;
                } else if (selectedRole != null) {
                    System.out.println("Updating role for user: " + selectedUser);
                    userService.updateUserRole(selectedUser.getIdUser(), selectedRole.getIdRole());
                    isUpdated = true;
                } else if (selectedDepartement != null) {
                    System.out.println("Updating department for user: " + selectedUser);
                    userService.updateUserDepartment(selectedUser.getIdUser(), selectedDepartement.getIdDepartement());
                    isUpdated = true;
                }

                if (isUpdated) {
                    loadUsers();
                    highlightSelectedUser(selectedUser);
                    System.out.println("User updated: " + selectedUser);
                } else {
                    showError("Please select a role and/or department to assign.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showError("An error occurred while updating the user: " + e.getMessage());
            }
        } else {
            System.out.println("No user selected for editing.");
            showError("Please select a user to edit.");
        }
    }



    private void highlightSelectedUser(User user) {
        Platform.runLater(() -> {
            userListView.getItems().forEach(u -> {
                if (u.equals(user)) {
                    userListView.getSelectionModel().select(u);
                    userListView.scrollTo(u);
                }
            });
            System.out.println("Highlighted User: " + user);
        });
    }

    private void handleUserSelection(User newValue) {
        selectedUser = newValue;
        if (selectedUser != null) {
            try {
                User_field.setText(selectedUser.getPrenom() + " " + selectedUser.getNom());

                Departement departement = depService.getDepartmentById(selectedUser.getIdDepartement());
                Role role = roleService.getRoleById(selectedUser.getIdRole());

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

                // Debugging to check the selected user
                System.out.println("Selected User in Listener: " + selectedUser);

            } catch (Exception e) {
                e.printStackTrace(); // Log the exception to the console
                showError("An error occurred while selecting the user: " + e.getMessage());
            }
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
            showError("Please select a user and a role to assign.");
        }
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
            showError("Failed to load users: " + e.getMessage());
        }
    }

    protected void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
