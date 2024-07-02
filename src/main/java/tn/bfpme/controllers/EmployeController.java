package tn.bfpme.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import tn.bfpme.models.*;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeController implements Initializable {
    @FXML private Label CU_dep;
    @FXML private Label CU_email;
    @FXML private Label CU_nomprenom;
    @FXML private Label CU_role;
    @FXML private ImageView CU_pdp;
    @FXML private Button settingsButton;
    @FXML public Button NotifBtn;

    @FXML private Label CU_ANL;
    @FXML private Label CU_EXP;
    @FXML private Label CU_MAL;
    @FXML private Label CU_MAT;
    @FXML public Button btnListe;
    @FXML private Button btnRH;
    @FXML private TableView<Conge> TableHistorique;
    @FXML private TableColumn<Conge, LocalDate> TableDD;
    @FXML private TableColumn<Conge, LocalDate> TableDF;
    @FXML private TableColumn<Conge, TypeConge> TableType;
    @FXML private TableColumn<Conge, Integer> indexColumn;
    private Popup settingsPopup;
    private Popup notifPopup;
    private Connection cnx;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        indexColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(TableHistorique.getItems().indexOf(cellData.getValue()) + 1));
        indexColumn.setSortable(false);
        fetchUserConges();
        reloadUserData();

        String userRole = SessionManager.getInstance().getUserRoleName();
        String userDep = SessionManager.getInstance().getUserDepartmentName();
        btnListe.setVisible(!userRole.equals("Employe"));
        btnRH.setVisible(userDep.equals("RH"));
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

    @FXML
    public void Demander(ActionEvent actionEvent) {
        navigateToScene(actionEvent, "/DemandeConge.fxml", "Demande congé");
    }

    @FXML
    public void Historique(ActionEvent actionEvent) {
        navigateToScene(actionEvent, "/HistoriqueConge.fxml", "Historique congé");
    }

    private void fetchUserConges() {
        ObservableList<Conge> HisUserList = FXCollections.observableArrayList();
        String sql = "SELECT DateDebut, DateFin, TypeConge FROM conge WHERE ID_User = ? AND Statut = ?";
        cnx = MyDataBase.getInstance().getCnx();
        try {
            PreparedStatement stm = cnx.prepareStatement(sql);
            stm.setInt(1, SessionManager.getInstance().getUser().getIdUser());
            stm.setString(2, String.valueOf(Statut.Approuvé));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                HisUserList.add(new Conge(rs.getDate("DateDebut").toLocalDate(), rs.getDate("DateFin").toLocalDate(), TypeConge.valueOf(rs.getString("TypeConge"))));
            }
            indexColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(TableHistorique.getItems().indexOf(cellData.getValue()) + 1));
            TableType.setCellValueFactory(new PropertyValueFactory<>("typeConge"));
            TableDD.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            TableDF.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            TableHistorique.setItems(HisUserList);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void reloadUserData() {
        User currentUser = SessionManager.getInstance().getUser();
        String departement = SessionManager.getInstance().getUserDepartmentName();
        String role = SessionManager.getInstance().getUserRoleName();
        CU_dep.setText(departement);
        CU_email.setText(currentUser.getEmail());
        CU_nomprenom.setText(currentUser.getNom() + " " + currentUser.getPrenom());
        CU_role.setText(role);
        String imagePath = currentUser.getImage();

        if (imagePath != null && !imagePath.isEmpty()) {
            File file = new File(imagePath);
            if (file.exists()) {
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    Image image = new Image(inputStream);
                    CU_pdp.setImage(image);
                } catch (FileNotFoundException e) {
                    System.err.println("Image file not found: " + imagePath);
                }
            } else {
                System.err.println("Image file does not exist: " + imagePath);
            }
        } else {
            System.err.println("Image path is null or empty for user: " + currentUser);
        }
        CU_ANL.setText(String.valueOf(currentUser.getSoldeAnnuel()));
        CU_EXP.setText(String.valueOf(currentUser.getSoldeExceptionnel()));
        CU_MAL.setText(String.valueOf(currentUser.getSoldeMaladie()));
        CU_MAT.setText(String.valueOf(currentUser.getSoldeMaternite()));
    }

    @FXML
    public void goto_profil(ActionEvent actionEvent) {
        navigateToScene(actionEvent, "/profile.fxml", "Mon profil");
    }

    @FXML
    void ListeDesDemandes(ActionEvent event) {
        navigateToScene(event, "/DemandeDepListe.fxml", "Liste des demandes - " + SessionManager.getInstance().getUserDepartmentName());
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
    @FXML
    void RH_Interface(ActionEvent event) {
        navigateToScene(event, "/RH_Interface.fxml", "Ressource Humaine");

    }
}
