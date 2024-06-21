package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import tn.bfpme.models.Conge;
import tn.bfpme.services.ServiceConge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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


    int cid,unumtel;
    String unom, uprenom, uemail, umdp, urole;

    @FXML
    void modifConge(ActionEvent event) {

    }

    @FXML
    void suppConge(ActionEvent event) {

    }
}
