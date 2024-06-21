package tn.bfpme.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DemandeConge implements Initializable {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ComboBox<String> cb_typeconge;
    @FXML
    private Pane paneAnnuel;
    @FXML
    private Pane paneExeptionnel;
    @FXML
    private Pane paneMaladie;
    @FXML
    private Pane paneSousSolde;
    @FXML
    private Pane paneMaternite;
    @FXML
    private Pane paneNaissance;
    @FXML
    private Pane paneGrossesse;

    ObservableList<String> CongeList = FXCollections.observableArrayList("Annuel", "Exeptionnel", "Maladie", "Sous-Solde", "Maternité");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_typeconge.setValue("Selectioner type");
        cb_typeconge.setItems(CongeList);
    }
    @FXML
    void TypeSelec(ActionEvent event) {
        if (cb_typeconge.getValue().equals("Annuel")) {
            paneAnnuel.setVisible(true);
            paneExeptionnel.setVisible(false);
            paneMaladie.setVisible(false);
            paneSousSolde.setVisible(false);
            paneMaternite.setVisible(false);
        }
        if (cb_typeconge.getValue().equals("Exeptionnel")) {
            paneAnnuel.setVisible(false);
            paneExeptionnel.setVisible(true);
            paneMaladie.setVisible(false);
            paneSousSolde.setVisible(false);
            paneMaternite.setVisible(false);
        }
        if (cb_typeconge.getValue().equals("Maladie")) {
            paneAnnuel.setVisible(false);
            paneExeptionnel.setVisible(false);
            paneMaladie.setVisible(true);
            paneSousSolde.setVisible(false);
            paneMaternite.setVisible(false);
        }
        if (cb_typeconge.getValue().equals("Sous-Solde")) {
            paneAnnuel.setVisible(false);
            paneExeptionnel.setVisible(false);
            paneMaladie.setVisible(false);
            paneSousSolde.setVisible(true);
            paneMaternite.setVisible(false);
        }
        if (cb_typeconge.getValue().equals("Maternité")) {
            paneAnnuel.setVisible(false);
            paneExeptionnel.setVisible(false);
            paneMaladie.setVisible(false);
            paneSousSolde.setVisible(false);
            paneMaternite.setVisible(true);
        }
    }
    @FXML
    void switchGrossesse(ActionEvent event) {
     paneGrossesse.setVisible(true);
     paneNaissance.setVisible(false);
    }

    @FXML
    void switchNaissance(ActionEvent event) {
        paneNaissance.setVisible(true);
        paneGrossesse.setVisible(false);
    }
}
