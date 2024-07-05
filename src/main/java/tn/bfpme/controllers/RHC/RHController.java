package tn.bfpme.controllers.RHC;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import tn.bfpme.models.RoleHierarchie;
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
    private Pane PaneCont;
    @FXML
    private ComboBox<Role> parentRoleComboBox;

    @FXML
    private Button settingsButton;
    @FXML
    public Button NotifBtn;
    @FXML
    private ComboBox<Role> RoleHComboBox;
    @FXML
    private ListView<RoleHierarchie> roleHListView;

    private ServiceRole roleService;
    private Popup settingsPopup;
    private Popup notifPopup;
    private paneRoleController PRC;
    private paneDepController PDC;
    public void initialize() {
        roleService = new ServiceRole();
        PDC.loadDepartments();
        PRC.loadRoles();
        //loadUsers();
        loadRoleHierarchie();
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
            Parent notifContent = FXMLLoader.load(getClass().getResource("/paneNotif.fxml"));
            notifPopup.getContent().add(notifContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRoleHierarchie() {
        try {
            ObservableList<RoleHierarchie> roleHierarchies = FXCollections.observableArrayList(roleService.getAllRoleHierarchies());
            roleHListView.setItems(roleHierarchies);
        } catch (Exception e) {
            showError("Failed to load role hierarchies: " + e.getMessage());
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
    void handleAddRoleToH(ActionEvent event) {
        Role parent = parentRoleComboBox.getSelectionModel().getSelectedItem();
        Role child = RoleHComboBox.getSelectionModel().getSelectedItem();
        roleService.addRoleHierarchy(parent, child);
        loadRoleHierarchie();
    }

    @FXML
    void handleDeleteRoleH(ActionEvent event) {
       /* Role selectedRole = roleListView.getSelectionModel().getSelectedItem();
        if (selectedRole != null) {
            roleService.deleteRoleHierarchy(selectedRole.getIdRole());
            loadRoleHierarchie();
        }*/
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
    private void showDepartementPane() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/paneDepartement.fxml"));
            Pane departementPane = loader.load();
            PaneCont.getChildren().clear();
            PaneCont.getChildren().add(departementPane);
            centerPane(PaneCont, departementPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showRolesPane() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/paneRole.fxml"));
            Pane rolePane = loader.load();
            PaneCont.getChildren().clear();
            PaneCont.getChildren().add(rolePane);
            centerPane(PaneCont, rolePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showUtilisateursPane() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/paneUsers.fxml"));
            Pane userPane = loader.load();
            PaneCont.getChildren().clear();
            PaneCont.getChildren().add(userPane);
            centerPane(PaneCont, userPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void centerPane(Pane container, Pane pane) {
        pane.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            double containerWidth = container.getWidth();
            double containerHeight = container.getHeight();
            double paneWidth = newValue.getWidth();
            double paneHeight = newValue.getHeight();
            double x = (containerWidth - paneWidth) / 2;
            double y = (containerHeight - paneHeight) / 2;
            pane.setLayoutX(x);
            pane.setLayoutY(y);
        });
    }

    @FXML
    void showHierarchyPane(ActionEvent event) {

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
