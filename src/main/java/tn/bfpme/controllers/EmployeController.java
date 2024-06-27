package tn.bfpme.controllers;

import javafx.application.Platform;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.bfpme.models.*;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;
import tn.bfpme.utils.StageManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    @FXML private Label CU_ANL;
    @FXML private Label CU_EXP;
    @FXML private Label CU_MAL;
    @FXML private Label CU_MAT;
    @FXML private Button settingsButton;
    @FXML public Button btnListe;
    @FXML private TableView<Conge> TableHistorique;
    @FXML private TableColumn<Conge, LocalDate> TableDD;
    @FXML private TableColumn<Conge, LocalDate> TableDF;
    @FXML private TableColumn<Conge, TypeConge> TableType;
    @FXML private TableColumn<Conge, Integer> indexColumn;
    private ContextMenu contextMenu;
    private Connection cnx;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contextMenu = new ContextMenu();
        MenuItem boiteItem = new MenuItem("boîte de réception");
        MenuItem aideItem = new MenuItem("Aide et support");
        MenuItem logoutItem = new MenuItem("Déconnexion");
        contextMenu.getItems().addAll(boiteItem, aideItem, logoutItem);
        settingsButton.setOnAction(event -> {
            double screenX = settingsButton.localToScreen(settingsButton.getBoundsInLocal()).getMinX() - 70;
            double screenY = settingsButton.localToScreen(settingsButton.getBoundsInLocal()).getMaxY() + 10;
            contextMenu.show(settingsButton, screenX, screenY);
        });
        boiteItem.setOnAction(this::viewboite);
        aideItem.setOnAction(this::viewaide);
        logoutItem.setOnAction(this::viewdeconnection);
        indexColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(TableHistorique.getItems().indexOf(cellData.getValue()) + 1));
        indexColumn.setSortable(false);
        fetchUserCongés();
        ReloadUserDATA();
        if (SessionManager.getInstance().getUtilisateur().getRole().equals(Role.ChefDepartement)){
            btnListe.setVisible(true);
        }
    }

    @FXML public void Demander(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Demande congé");
            stage.show();
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML public void Historique(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HistoriqueConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Historique congé");
            stage.show();
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML void viewaide(ActionEvent actionEvent) {

    }
    @FXML private void viewboite(ActionEvent actionEvent) {

    }
    @FXML
    void viewdeconnection(ActionEvent actionEvent) {
        SessionManager.getInstance().cleanUserSession();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            MenuItem menuItem = (MenuItem) actionEvent.getSource();
            Stage stage = (Stage) menuItem.getParentPopup().getOwnerWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestion de Congés - Connection");
            StageManager.addStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchUserCongés() {
        ObservableList<Conge> HisUserList = FXCollections.observableArrayList();
        String sql = "SELECT `DateDebut`, `DateFin`, `TypeConge` FROM `conge` WHERE `ID_User`=? AND `Statut`=?";
        Connection cnx = MyDataBase.getInstance().getCnx();
        try {
            PreparedStatement stm = cnx.prepareStatement(sql);
            stm.setInt(1, SessionManager.getInstance().getUtilisateur().getIdUser());
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
    public void ReloadUserDATA(){
        CU_dep.setText(String.valueOf(SessionManager.getInstance().getDepartement()));
        CU_email.setText(SessionManager.getInstance().getUtilisateur().getEmail());
        CU_nomprenom.setText(SessionManager.getInstance().getUtilisateur().getNom()+" "+SessionManager.getInstance().getUtilisateur().getPrenom());
        CU_role.setText(String.valueOf(SessionManager.getInstance().getUtilisateur().getRole()));
        String imagePath = SessionManager.getInstance().getUtilisateur().getImage();
        if (imagePath != null) {
            try {
                File file = new File(imagePath);
                FileInputStream inputStream = new FileInputStream(file);
                Image image = new Image(inputStream);
                CU_pdp.setImage(image);
            } catch (FileNotFoundException e) {
                System.err.println("Image file not found: " + imagePath);
            }
        } else {
            System.err.println("Image path is null for user: " + SessionManager.getInstance().getUtilisateur());
        }
        CU_ANL.setText(String.valueOf(SessionManager.getInstance().getUtilisateur().getSoldeAnnuel()));
        CU_EXP.setText(String.valueOf(SessionManager.getInstance().getUtilisateur().getSoldeExceptionnel()));
        CU_MAL.setText(String.valueOf(SessionManager.getInstance().getUtilisateur().getSoldeMaladie()));
        CU_MAT.setText(String.valueOf(SessionManager.getInstance().getUtilisateur().getSoldeMaternite()));
    }
    @FXML public void goto_profil(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/profile.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Mon profil");
            stage.show();
            StageManager.addStage(stage);
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML void ListeDesDemandes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeDepListe.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Mon profil");
            stage.show();
            StageManager.addStage(stage);
            StageManager.addStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
