package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import tn.bfpme.models.SoldeConge;
import tn.bfpme.services.ServiceSoldeConge;

public class AttributionSoldeController {

    @FXML
    private Pane CongePane;

    @FXML
    private TextField soldeAnnuelTF;

    @FXML
    private TextField soldeExceptionnelTF;

    @FXML
    private TextField soldeMaladieTF;

    @FXML
    private TextField soldeMaterniteTF;

    private final ServiceSoldeConge soldeCongeService = new ServiceSoldeConge();
    private final int soldeId = 1; // Use the correct id based on your application logic

    @FXML
    public void initialize() {
        SoldeConge soldeConge = soldeCongeService.getSoldeConge(soldeId);
        if (soldeConge != null) {
            soldeAnnuelTF.setText(String.valueOf(soldeConge.getSoldeAnn()));
            soldeExceptionnelTF.setText(String.valueOf(soldeConge.getSoldeExc()));
            soldeMaladieTF.setText(String.valueOf(soldeConge.getSoldeMal()));
            soldeMaterniteTF.setText(String.valueOf(soldeConge.getSoldeMat()));
        }
    }

    @FXML
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
    }
}
