package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import tn.bfpme.services.ServiceConge;

public class CongeCarte {

    @FXML
    private Pane Card;

    @FXML
    private Label cardDatedeb;

    @FXML
    private Label cardDatefin;

    @FXML
    private Label cardDescription;

    @FXML
    private Label cardStatus;

    @FXML
    private Label cardType;

    private final ServiceConge CongeS = new ServiceConge();

    @FXML
    void modifConge(ActionEvent event) {

    }

    @FXML
    void suppConge(ActionEvent event) {

    }
}
