package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Statut;
import tn.bfpme.models.TypeConge;
import tn.bfpme.models.Utilisateur;
import tn.bfpme.services.ServiceConge;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import static tn.bfpme.models.Utilisateur.getCurrent_User;

public class CongeCarteController implements Initializable {
    @FXML private Pane Card;
    @FXML private Label cardDatedeb;
    @FXML private Label cardDatefin;
    @FXML private Label cardDescription;
    @FXML private Label cardStatus;
    @FXML private Button btnDelete;
    @FXML private Button btnEdit;
    @FXML private Label cardType;
    private int cUser, cid;
    private String cdesc, cfile;
    private LocalDate cdebut, cfin;
    private Statut cstatut;
    private TypeConge ctype;
    private Conge conge;
    private final ServiceConge CongeS = new ServiceConge();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setData(Conge conge) {
        this.conge = conge;
        cardType.setText(String.valueOf(conge.getTypeConge()));
        cardDatedeb.setText(String.valueOf(conge.getDateDebut()));
        cardDatefin.setText(String.valueOf(conge.getDateFin()));
        cardDescription.setText(String.valueOf(conge.getDescription()));
        cardStatus.setText(String.valueOf(conge.getStatut()));
        Card.setStyle("-fx-border-radius: 5px;-fx-border-color:#808080");
        Connection cnx = MyDataBase.getInstance().getCnx();
        String qry = "SELECT `Statut` FROM `conge` WHERE `ID_User`= ? AND `ID_Conge`=? ";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, SessionManager.getInstance().getUtilisateur().getIdUser());
            stm.setInt(2, conge.getIdConge());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getString("Statut").equals(String.valueOf(Statut.En_Attente))){
                    System.out.println(rs.getString("Statut"));
                    btnDelete.setDisable(false);
                    btnEdit.setDisable(false);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cUser =conge.getIdUser();
        cid = conge.getIdConge();
        ctype = conge.getTypeConge();
        cdesc = conge.getDescription();
        cdebut = conge.getDateDebut();
        cfin = conge.getDateFin();
        cfile = conge.getFile();
        cstatut = conge.getStatut();
    }
    public void refreshData(Conge conge) {
        setData(conge);
    }

    @FXML
    void modifConge(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierConge.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();

            // Set up the controller and initialize it with the current conge data
            ModifierCongeController modifierCongeController = loader.getController();
            modifierCongeController.setData(conge, this);

            newStage.setTitle("Modifier Congé");
            newStage.setScene(new Scene(root));
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());

            newStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void suppConge(ActionEvent event) {
        CongeS.deleteCongeByID(cid);
        // Remove the card from the parent GridPane
        ((GridPane) Card.getParent()).getChildren().remove(Card);

    }


}

