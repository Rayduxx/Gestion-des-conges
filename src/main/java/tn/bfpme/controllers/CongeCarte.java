package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Statut;
import tn.bfpme.models.TypeConge;
import tn.bfpme.services.ServiceConge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;

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
    private int cUser, cid;
    private String cdesc, cfile;
    private LocalDate cdebut, cfin;
    private Statut cstatut;
    private TypeConge ctype;
    private final ServiceConge CongeS = new ServiceConge();

    public void setData(Conge conge) {
        cardType.setText(String.valueOf(conge.getTypeConge()));
        cardDatedeb.setText(String.valueOf(conge.getDateDebut()));
        cardDatefin.setText(String.valueOf(conge.getDateFin()));
        cardDescription.setText(String.valueOf(conge.getDescription()));
        Card.setStyle("-fx-border-radius: 5px;-fx-border-color:#808080");

        cUser =conge.getIdUser();
        cid = conge.getIdConge();
        ctype = conge.getTypeConge();
        cdesc = conge.getDescription();
        cdebut = conge.getDateDebut();
        cfin = conge.getDateFin();
        cfile = conge.getFile();
        cstatut = conge.getStatut();
    }

    @FXML
    void modifConge(ActionEvent event) {

    }

    @FXML
    void suppConge(ActionEvent event) {

    }
}
