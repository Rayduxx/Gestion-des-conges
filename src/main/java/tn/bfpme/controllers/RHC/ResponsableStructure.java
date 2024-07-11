package tn.bfpme.controllers.RHC;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ResponsableStructure implements Initializable {

    @FXML
    private TreeTableView<User> userTable;
    @FXML
    private TreeTableColumn<User, Integer> idUserColumn;
    @FXML
    private TreeTableColumn<User, String> nomUserColumn;
    @FXML
    private TreeTableColumn<User, String> prenomUserColumn;
    @FXML
    private TreeTableColumn<User, String> emailUserColumn;
    @FXML
    private TreeTableColumn<User, String> managerUserColumn;
    @FXML
    private ComboBox<User> managerComboBox;
    @FXML
    private ComboBox<User> userComboBox;
    @FXML
    private AnchorPane MainAnchorPane;
    @FXML
    private TextField searchField; // Add a TextField for search input

    private ServiceUtilisateur serviceUtilisateur;
    private boolean isFirstSelection = true;

    private void loadUsers() {
        List<User> users = serviceUtilisateur.getAllUsers();
        if (users == null || users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        populateTable(users);
    }

    private void populateTable(List<User> users) {
        ObservableList<User> userList = FXCollections.observableArrayList(users);
        managerComboBox.setItems(userList);
        userComboBox.setItems(userList);

        managerComboBox.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getNom() + " " + user.getPrenom());
                }
            }
        });

        userComboBox.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getNom() + " " + user.getPrenom());
                }
            }
        });

        Map<Integer, TreeItem<User>> userItems = new HashMap<>();
        TreeItem<User> root = new TreeItem<>(new User(0, "Root", "", "", "", "", 0, 0, 0, 0, 0, 0, 0));

        // First pass to create TreeItems
        for (User user : users) {
            TreeItem<User> userItem = new TreeItem<>(user);
            userItems.put(user.getIdUser(), userItem);
        }

        // Second pass to set up the hierarchy
        for (User user : users) {
            TreeItem<User> userItem = userItems.get(user.getIdUser());
            Integer managerId = user.getIdManager();

            // Check for cyclic references
            if (managerId.equals(user.getIdUser())) {
                System.err.println("User " + user.getNom() + " " + user.getPrenom() + " is their own manager. Skipping to avoid cycle.");
                continue;
            }

            if (managerId == 0 || !userItems.containsKey(managerId)) {
                root.getChildren().add(userItem);
            } else {
                TreeItem<User> managerItem = userItems.get(managerId);
                // Check for potential cycle
                if (isAncestor(managerItem, userItem)) {
                    System.err.println("Cycle detected: User " + user.getNom() + " " + user.getPrenom() + " cannot be added under manager " + managerItem.getValue().getNom() + " " + managerItem.getValue().getPrenom());
                    continue;
                }
                managerItem.getChildren().add(userItem);
            }
        }

        // Add logging to trace the tree structure
        printTree(root, " ");

        userTable.setRoot(root);
        userTable.setShowRoot(false);
        userTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                User selectedUser = newValue.getValue();
                if (isFirstSelection) {
                    userComboBox.getSelectionModel().select(selectedUser);
                    isFirstSelection = false;
                } else {
                    managerComboBox.getSelectionModel().select(selectedUser);
                    isFirstSelection = true; // Reset for the next pair of selections
                }
            }
        });
    }

    private boolean isAncestor(TreeItem<User> ancestor, TreeItem<User> node) {
        TreeItem<User> parent = node.getParent();
        while (parent != null) {
            if (parent == ancestor) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    private void printTree(TreeItem<User> root, String indent) {
        System.out.println(indent + root.getValue().getNom() + " " + root.getValue().getPrenom());
        for (TreeItem<User> child : root.getChildren()) {
            printTree(child, indent + "  ");
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

        idUserColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<User, Integer> param) {
                return new SimpleIntegerProperty(param.getValue().getValue().getIdUser()).asObject();
            }
        });

        nomUserColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getNom());
            }
        });

        prenomUserColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getPrenom());
            }
        });

        emailUserColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getEmail());
            }
        });

        managerUserColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                Integer managerId = param.getValue().getValue().getIdManager();
                String managerName = managerId != 0 ? serviceUtilisateur.getManagerNameByUserId(managerId) : "Pas de Manager";
                return new SimpleStringProperty(managerName);
            }
        });

        loadUsers();
    }

    @FXML
    void recherche_hierarchie(ActionEvent event) {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            List<User> searchResults = serviceUtilisateur.searchUsers(query);
            populateTable(searchResults);
        }
    }

    public void Affecter_manager(ActionEvent actionEvent) {
        User selectedUser = userComboBox.getSelectionModel().getSelectedItem();
        User selectedManager = managerComboBox.getSelectionModel().getSelectedItem();

        if (selectedUser != null && selectedManager != null) {
            serviceUtilisateur.setManagerForUser(selectedUser.getIdUser(), selectedManager.getIdUser());
            loadUsers();
        }
    }
}
