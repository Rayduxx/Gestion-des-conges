package tn.bfpme.controllers.RHC;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.bfpme.controllers.UserCardController;
import tn.bfpme.models.Departement;
import tn.bfpme.models.Role;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceDepartement;
import tn.bfpme.services.ServiceRole;
import tn.bfpme.services.ServiceUtilisateur;
import tn.bfpme.utils.MyDataBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

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
    @FXML
    private TextField ID_A;

    @FXML
    private TextField MDP_A;

    @FXML
    private ImageView PDPimageHolder;

    @FXML
    private TextField Prenom_A;

    @FXML
    private TextField S_Ann;

    @FXML
    private TextField S_exc;

    @FXML
    private TextField S_mal;

    @FXML
    private TextField S_mat;

    @FXML
    private Pane UtilisateursPane;

    @FXML
    private ComboBox<?> departementComboBox;

    @FXML
    private TextField email_A;

    @FXML
    private TextField image_A;

    @FXML
    private Label infolabel;

    @FXML
    private TextField nom_A;

    @FXML
    private VBox userContainer;
    @FXML
    private GridPane UserContainers;

    private User selectedUser;
    private FilteredList<User> filteredData;
    ServiceUtilisateur UserS =new ServiceUtilisateur();
    Connection cnx = MyDataBase.getInstance().getCnx();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load();
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

    @FXML
    void ajouter_user(ActionEvent actionEvent) {
        String nom = nom_A.getText();
        String prenom = Prenom_A.getText();
        String email = email_A.getText();
        String mdp = MDP_A.getText();
        String image = image_A.getText();

        int soldeAnnuel = parseIntOrZero(S_Ann.getText());
        int soldeMaladie = parseIntOrZero(S_mal.getText());
        int soldeExceptionnel = parseIntOrZero(S_exc.getText());
        int soldeMaternite = parseIntOrZero(S_mat.getText());

        if (email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(bfpme\\.tn|gmail\\.com)$")) {
            try {
                if (!emailExists(email)) {
                    UserS.Add(new User(0, nom, prenom, email, mdp, image, soldeAnnuel, soldeMaladie, soldeExceptionnel, soldeMaternite, 0, 0));
                    infolabel.setText("Ajout Effectué");
                } else {
                    infolabel.setText("Email déjà existe");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            infolabel.setText("Email est invalide");
        }
    }

    private int parseIntOrZero(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        try {
            return (int) Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @FXML
    void goto_conge(ActionEvent event) {

    }

    @FXML
    void goto_user(ActionEvent event) {

    }

    @FXML
    void modifier_user(ActionEvent event) {
        String Nom = nom_A.getText();
        String Prenom = Prenom_A.getText();
        String Email = email_A.getText();
        String Mdp = MDP_A.getText();
        String Image = image_A.getText();
        int solde_annuel = parseIntOrZero(S_Ann.getText());
        int solde_maladie = parseIntOrZero(S_mal.getText());
        int solde_exceptionnel = parseIntOrZero(S_exc.getText());
        int solde_maternite = parseIntOrZero(S_mat.getText());

        if (Email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(bfpme\\.tn|gmail\\.com)$")) {
            int IdUser = Integer.parseInt(ID_A.getText());
            try {
                if (!emailExistss(Email, IdUser) || isCurrentUser(IdUser, Email)) {
                    User user = new User(IdUser, Nom, Prenom, Email, Mdp, Image, solde_annuel, solde_maladie, solde_exceptionnel, solde_maternite, 0, 0);
                    UserS.Update(user);
                    infolabel.setText("Modification Effectuée");
                    System.out.println("User updated: " + user);
                } else {
                    infolabel.setText("Email déjà existe");
                }
            } catch (SQLException e) {
                infolabel.setText("Erreur de base de données: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            infolabel.setText("Email est invalide");
        }
    }

    private boolean isCurrentUser(int userId, String email) {
        User user = UserS.getUserById(userId);

        return UserS != null  && user.getEmail().equals(email);
    }

    @FXML
    void supprimer_user(ActionEvent event) {
        try {
            int userId = Integer.parseInt(ID_A.getText());

            User user = UserS.getUserById(userId);
            if (user != null) {
                UserS.Delete(user);
                infolabel.setText("Suppression Effectuée");
                System.out.println("User deleted: " + user);
            } else {
                infolabel.setText("Utilisateur non trouvé");
            }
        } catch (NumberFormatException e) {
            infolabel.setText("L'ID de l'utilisateur doit être un nombre.");
        }
    }

    @FXML
    void upload_image(ActionEvent event) {
        String imagePath = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        Stage stage = (Stage) nom_A.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                Path destinationFolder = Paths.get("src/main/resources/assets/imgs");
                if (!Files.exists(destinationFolder)) {
                    Files.createDirectories(destinationFolder);
                }
                String fileName = UUID.randomUUID().toString() + "_" + selectedFile.getName();
                Path destinationPath = destinationFolder.resolve(fileName);
                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                imagePath = destinationPath.toString();
                System.out.println("Image uploaded successfully: " + imagePath);
                image_A.setText(fileName);
                if (imagePath != null) {
                    try {
                        File file = new File(imagePath);
                        FileInputStream inputStream = new FileInputStream(file);
                        Image image = new Image(inputStream);
                        PDPimageHolder.setImage(image);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean emailExists(String email) throws SQLException {
        cnx = MyDataBase.getInstance().getCnx();
        String query = "SELECT * FROM `user` WHERE Email=?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    private boolean emailExistss(String email, int excludeUserId) throws SQLException {
        String query = "SELECT COUNT(*) FROM user WHERE Email = ? AND ID_User != ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setString(1, email);
            stm.setInt(2, excludeUserId);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void load(List<User> users) {
        UserContainers.getChildren().clear(); // Clear existing items
        int column = 0;
        int row = 0; // Start row from 0
        try {

            for (User user : users) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/UserCard.fxml"));
                Pane userBox = fxmlLoader.load();
                UserCardController cardC = fxmlLoader.getController();
                cardC.setData(user);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                UserContainers.add(userBox, column++, row); // Ensure correct row and column
                GridPane.setMargin(userBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {

        List<User> users = UserS.Show();
        load(users);
    }

    @FXML
    void Tri_Departement(ActionEvent actionEvent) {
        UserContainers.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (User user : UserS.SortDepart()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/UserCard.fxml"));
                Pane userBox = fxmlLoader.load();
                UserCardController cardC = fxmlLoader.getController();
                cardC.setData(user);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                UserContainers.add(userBox, column++, row);
                GridPane.setMargin(userBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Tri_Role(ActionEvent actionEvent) {
        UserContainers.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (User user : UserS.SortRole()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/UserCard.fxml"));
                Pane userBox = fxmlLoader.load();
                UserCardController cardC = fxmlLoader.getController();
                cardC.setData(user);
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                UserContainers.add(userBox, column++, row);
                GridPane.setMargin(userBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
