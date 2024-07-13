package tn.bfpme.controllers.RHC;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import javafx.stage.Stage;
import tn.bfpme.controllers.UserCardController;
import tn.bfpme.models.Departement;
import tn.bfpme.models.Role;
import tn.bfpme.models.User;
import tn.bfpme.models.SoldeConge;
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
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class paneUserController implements Initializable {
    @FXML
    private TextField Depart_field;

    @FXML
    private TreeTableColumn<Departement,String > DeptparColumn;

    @FXML
    private TreeTableColumn<Role,String> DescRoleColumn;

    @FXML
    private TreeTableColumn<Departement,String> DescriptionDeptColumn;
    @FXML
    private TreeTableColumn<Role, String> RoleFilsColumn;

    @FXML
    private TreeTableColumn<Role, String> RoleParColumn;

    @FXML
    private TextField Role_field;

    @FXML
    public ListView<User> userListView;
    @FXML
    public TextField User_field;

    @FXML
    public TextField ID_A;

    @FXML
    public TextField MDP_A;

    @FXML
    public ImageView PDPimageHolder;

    @FXML
    public TextField Prenom_A;

    private TreeTableView<Departement> deptTable;

    @FXML
    private TreeTableColumn<User, String> emailUserColumn;
    @FXML
    private TreeTableColumn<Departement, Integer> idDapartementColumn;

    @FXML
    private TreeTableColumn<Role, Integer> idRoleColumn;

    @FXML
    private TreeTableColumn<User, Integer> idUserColumn;

    @FXML
    private TreeTableColumn<User, String> managerUserColumn;

    @FXML
    private TreeTableColumn<Departement, String> nomDeptColumn;

    @FXML
    private TreeTableColumn<Role, String> nomRoleColumn;

    @FXML
    private TreeTableColumn<User,String> nomUserColumn;
    @FXML
    private TreeTableColumn<User, String> prenomUserColumn;
    @FXML
    private TextField searchFieldDept;

    @FXML
    private TextField searchFieldRole;

    @FXML
    private TextField searchFieldUser;

    @FXML
    private ComboBox<String> hierarCombo;



    @FXML
    private ListView<Role> roleListView;

    @FXML
    private TreeTableView<Role> roleTable;

    @FXML
    public TextField S_Ann;

    @FXML
    public TextField S_exc;

    @FXML
    public TextField S_mal;

    @FXML
    public TextField S_mat;

    @FXML
    public GridPane UserContainers;

    @FXML
    private Pane UtilisateursPane;

    @FXML
    public ListView<Departement> departListView;

    @FXML
    public TextField email_A;

    @FXML
    public TextField image_A;

    @FXML
    public Label infolabel;

    @FXML
    public TextField nom_A;

    @FXML
    private Pane DepartPane1;
    @FXML
    private Pane RolePane1;
    @FXML
    private Pane UserPane1;
    @FXML
    private TreeTableView<User> userTable;


    public User selectedUser;
    public FilteredList<User> filteredData;
    public FilteredList<Departement> filteredDepartments;
    public FilteredList<Role> filteredRoles;
    ServiceUtilisateur UserS = new ServiceUtilisateur();
    Connection cnx = MyDataBase.getInstance().getCnx();

    private final ServiceDepartement depService = new ServiceDepartement();
    private final ServiceUtilisateur userService = new ServiceUtilisateur();
    private final ServiceRole roleService = new ServiceRole();
    ObservableList<String> HierarchieList = FXCollections.observableArrayList("Utilisateurs", "Départements", "Roles");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUsers();
        loadDepartments();
        loadRoles();
        hierarCombo.setValue("Selectioner type");
        hierarCombo.setItems(HierarchieList);
    }
    @FXML
    void SelecHierar(ActionEvent event) {
        if (hierarCombo.getValue().equals("Utilisateurs")) {
            UserPane1.setVisible(true);
            RolePane1.setVisible(false);
            DepartPane1.setVisible(false);
        }
        if (hierarCombo.getValue().equals("Départements")) {
            UserPane1.setVisible(false);
            DepartPane1.setVisible(true);
            RolePane1.setVisible(false);
        }
        if (hierarCombo.getValue().equals("Roles")) {
            UserPane1.setVisible(false);
            DepartPane1.setVisible(false);
            RolePane1.setVisible(true);
        }

    }

    private void loadUsers() {
        UserContainers.getChildren().clear();
        List<User> userList = userService.getAllUsers();
        int column = 0;
        int row = 0;

        try {
            for (User user : userList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/UserCard.fxml"));
                Pane userBox = fxmlLoader.load();

                UserCardController cardController = fxmlLoader.getController();
                Departement department = depService.getDepartmentById(user.getIdDepartement());
                Role role = roleService.getRoleById(user.getIdRole());

                String departmentName = department != null ? department.getNom() : "N/A";
                String roleName = role != null ? role.getNom() : "N/A";

                cardController.setData(user, roleName, departmentName);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                UserContainers.add(userBox, column++, row);
                GridPane.setMargin(userBox, new javafx.geometry.Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDepartments() {
        List<Departement> departmentList = depService.getAllDepartments();
        ObservableList<Departement> departments = FXCollections.observableArrayList(departmentList);
        filteredDepartments = new FilteredList<>(departments, p -> true);
        departListView.setItems(filteredDepartments);
        departListView.setCellFactory(param -> new ListCell<Departement>() {
            @Override
            protected void updateItem(Departement item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNom());
                }
            }
        });

        departListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if (newValue != null) {
                    Depart_field.setText(newValue.getNom());
                }
            });
        });
    }

    private void loadRoles() {
        List<Role> roleList = roleService.getAllRoles();
        ObservableList<Role> roles = FXCollections.observableArrayList(roleList);
        filteredRoles = new FilteredList<>(roles, p -> true);
        roleListView.setItems(filteredRoles);
        roleListView.setCellFactory(param -> new ListCell<Role>() {
            @Override
            protected void updateItem(Role item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNom());
                }
            }
        });

        roleListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if (newValue != null) {
                    Role_field.setText(newValue.getNom());
                }
            });
        });
    }

    @FXML
    public void User_Recherche(ActionEvent actionEvent) {
        String searchText = User_field.getText().trim();
        filteredData.setPredicate(user -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchText.toLowerCase();
            return user.getNom().toLowerCase().contains(lowerCaseFilter) ||
                    user.getPrenom().toLowerCase().contains(lowerCaseFilter) ||
                    user.getEmail().toLowerCase().contains(lowerCaseFilter);
        });
    }

    @FXML
    void Depart_Recherche(ActionEvent event) {
        String searchText = Depart_field.getText().trim();
        filteredDepartments.setPredicate(departement -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchText.toLowerCase();
            return (departement.getNom() != null && departement.getNom().toLowerCase().contains(lowerCaseFilter)) ||
                    (departement.getDescription() != null && departement.getDescription().toLowerCase().contains(lowerCaseFilter));
        });
    }

    @FXML
    void Role_Recherche(ActionEvent event) {
        String searchText = Role_field.getText().trim();
        filteredRoles.setPredicate(role -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchText.toLowerCase();
            return role.getNom().toLowerCase().contains(lowerCaseFilter) ||
                    role.getDescription().toLowerCase().contains(lowerCaseFilter);
        });
    }

    @FXML
    void rechercheUser1(ActionEvent event) {
        String searchText = searchFieldUser.getText().trim();
        filteredData.setPredicate(user -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchText.toLowerCase();
            return user.getNom().toLowerCase().contains(lowerCaseFilter) ||
                    user.getPrenom().toLowerCase().contains(lowerCaseFilter) ||
                    user.getEmail().toLowerCase().contains(lowerCaseFilter);
        });
    }

    @FXML
    void rechercheDept1(ActionEvent event) {
        String searchText = searchFieldDept.getText().trim();
        filteredDepartments.setPredicate(departement -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchText.toLowerCase();
            return (departement.getNom() != null && departement.getNom().toLowerCase().contains(lowerCaseFilter)) ||
                    (departement.getDescription() != null && departement.getDescription().toLowerCase().contains(lowerCaseFilter));
        });
    }

    @FXML
    void rechercheRole1(ActionEvent event) {
        String searchText = searchFieldRole.getText().trim();
        filteredRoles.setPredicate(role -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchText.toLowerCase();
            return role.getNom().toLowerCase().contains(lowerCaseFilter) ||
                    role.getDescription().toLowerCase().contains(lowerCaseFilter);
        });
    }

    private void handleUserSelection(User newValue) {
        selectedUser = newValue;
        if (selectedUser != null) {
            try {
                ID_A.setText(String.valueOf(selectedUser.getIdUser()));
                Prenom_A.setText(selectedUser.getPrenom());
                nom_A.setText(selectedUser.getNom());
                email_A.setText(selectedUser.getEmail());
                MDP_A.setText(selectedUser.getMdp());
                image_A.setText(selectedUser.getImage());

                // Assuming you need to set the image as well
                if (selectedUser.getImage() != null) {
                    File file = new File(selectedUser.getImage());
                    if (file.exists()) {
                        Image image = new Image(new FileInputStream(file));
                        PDPimageHolder.setImage(image);
                    }
                }

                Departement departement = depService.getDepartmentById(selectedUser.getIdDepartement());
                Role role = roleService.getRoleById(selectedUser.getIdRole());

                if (departement != null) {
                    Depart_field.setText(departement.getNom());
                } else {
                    Depart_field.clear();
                }

                if (role != null) {
                    Role_field.setText(role.getNom());
                } else {
                    Role_field.clear();
                }

                SoldeConge soldeConge = getSoldeCongeByUserId(selectedUser.getIdUser());

                if (soldeConge != null) {
                    S_Ann.setText(String.valueOf(soldeConge.getSoldeAnn()));
                    S_exc.setText(String.valueOf(soldeConge.getSoldeExc()));
                    S_mal.setText(String.valueOf(soldeConge.getSoldeMal()));
                    S_mat.setText(String.valueOf(soldeConge.getSoldeMat()));
                } else {
                    S_Ann.clear();
                    S_exc.clear();
                    S_mal.clear();
                    S_mat.clear();
                }

                System.out.println("Selected User in Listener: " + selectedUser);

            } catch (Exception e) {
                e.printStackTrace();
                showError("An error occurred while selecting the user: " + e.getMessage());
            }
        }
    }
    private SoldeConge getSoldeCongeByUserId(int userId) {
        SoldeConge soldeConge = null;
        String query = "SELECT sc.* FROM soldeconge sc JOIN user u ON sc.idSolde = u.ID_Solde WHERE u.ID_User = ?";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                soldeConge = new SoldeConge(
                        rs.getInt("idSolde"),
                        rs.getDouble("SoldeAnn"),
                        rs.getDouble("SoldeMat"),
                        rs.getDouble("SoldeExc"),
                        rs.getDouble("SoldeMal")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soldeConge;
    }

    @FXML
    private void handleEditUser() {
        System.out.println("handleEditUser called");

        if (selectedUser != null) {
            Departement selectedDepartement = departListView.getSelectionModel().getSelectedItem();
            Role selectedRole = roleListView.getSelectionModel().getSelectedItem();

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

    @FXML
    private void handleAssignUser() {
        Integer userId = getSelectedUserId();
        Departement selectedDepartement = departListView.getSelectionModel().getSelectedItem();
        Role selectedRole = roleListView.getSelectionModel().getSelectedItem();

        if (userId != null && selectedRole != null && selectedDepartement != null) {
            userService.updateUserRoleAndDepartment(userId, selectedRole.getIdRole(), selectedDepartement.getIdDepartement());
            loadUsers();
            highlightSelectedUser(userService.getUserById(userId));
        } else {
            showError("Please select a user, role, and department to assign.");
        }
    }

    public Integer getSelectedUserId() {
        return selectedUser != null ? selectedUser.getIdUser() : null;
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

        SoldeConge defaultSolde = getDefaultSolde();

        double soldeAnnuel = defaultSolde.getSoldeAnn();
        double soldeMaladie = defaultSolde.getSoldeMal();
        double soldeExceptionnel = defaultSolde.getSoldeExc();
        double soldeMaternite = defaultSolde.getSoldeMat();

        if (email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(bfpme\\.tn|gmail\\.com)$")) {
            try {
                if (!emailExists(email)) {
                    UserS.Add(new User(0, nom, prenom, email, mdp, image, soldeAnnuel, soldeMaladie, soldeExceptionnel, soldeMaternite, LocalDate.now(), 0, 0));
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
        return UserS != null && user.getEmail().equals(email);
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
        String query = "SELECT * FROM `user` WHERE Email=?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }


    private boolean emailExistss(String email, int excludeUserId) throws SQLException {
        String query = "SELECT * FROM `user` WHERE Email=?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private SoldeConge getDefaultSolde() {
        String query = "SELECT SoldeAnn, SoldeMal, SoldeExc, SoldeMat FROM soldeconge LIMIT 1";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stm = cnx.prepareStatement(query);
             ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return new SoldeConge(
                        rs.getDouble("SoldeAnn"),
                        rs.getDouble("SoldeMal"),
                        rs.getDouble("SoldeExc"),
                        rs.getDouble("SoldeMat")
                );
            } else {
                throw new SQLException("No default solde values found in soldeconge table.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new SoldeConge(0, 0, 0, 0);
        }
    }

}
