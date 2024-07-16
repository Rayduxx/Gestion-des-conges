package tn.bfpme.controllers.RHC;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import tn.bfpme.models.SoldeConge;
import tn.bfpme.services.ServiceSoldeConge;

public class AttributionSoldeController {

    @FXML
    private Button Ajout_Solde;

    @FXML
    private Pane CongePane;

    @FXML
    private TextField Designation_Solde;

    @FXML
    private TextField ID_Solde;

    @FXML
    private ListView<?> Liste_Solde;

    @FXML
    private Button Modifier_Solde;

    @FXML
    private TextField Pas_Solde;

    @FXML
    private TextField Periode_Solde;

    @FXML
    private TextField RechercheSol;

    @FXML
    private Button Supprimer_Solde;

    @FXML
    private TextField Type_Solde;

    @FXML
    private Label labelSolde;


    private final ServiceSoldeConge soldeCongeService = new ServiceSoldeConge();
    private final int soldeId = 1; // Use the correct id based on your application logic


    @FXML
    public void initialize() {
        /*SoldeConge soldeConge = soldeCongeService.getSoldeConge(soldeId);
        if (soldeConge != null) {
            soldeAnnuelTF.setText(String.valueOf(soldeConge.getSoldeAnn()));
            soldeExceptionnelTF.setText(String.valueOf(soldeConge.getSoldeExc()));
            soldeMaladieTF.setText(String.valueOf(soldeConge.getSoldeMal()));
            soldeMaterniteTF.setText(String.valueOf(soldeConge.getSoldeMat()));
        }*/
    }

    @FXML
    void Recherche_Solde(ActionEvent event) {

    }

    /*@FXML
    void ConfirmerSolde(ActionEvent event) {
        try {
            double soldeAnnuel = Double.parseDouble(soldeAnnuelTF.getText());
            double soldeExceptionnel = Double.parseDouble(soldeExceptionnelTF.getText());
            double soldeMaladie = Double.parseDouble(soldeMaladieTF.getText());
            double soldeMaternite = Double.parseDouble(soldeMaterniteTF.getText());

            SoldeConge soldeConge = new SoldeConge(soldeAnnuel, soldeMaladie, soldeMaternite, soldeExceptionnel);
            soldeCongeService.updateSoldeConge(soldeConge, soldeId);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Show an error message to the user
        }
    }*/
}

