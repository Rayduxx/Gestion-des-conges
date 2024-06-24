package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.bfpme.models.Departement;
import tn.bfpme.utils.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeController implements Initializable {
    @FXML private Label CU_dep;
    @FXML private Label CU_email;
    @FXML private Label CU_nomprenom;
    @FXML private ImageView CU_pdp;
    @FXML private Label CU_solde;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CU_dep.setText(String.valueOf(SessionManager.getDepartement()));
        CU_email.setText(SessionManager.getEmail());
        CU_nomprenom.setText(SessionManager.getNom()+" "+SessionManager.getPrenom());
        CU_solde.setText(String.valueOf(SessionManager.getSoldeConge()));
    }

    @FXML
    public void DemanderConge(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DemandeConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Demande de Congé");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void HistoriqueConge(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/HistoriqueConge.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Historique des Congés");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
