package tn.bfpme.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.bfpme.services.ServiceUtilisateur;

import java.sql.DriverManager;

import tn.bfpme.utils.MyDataBase;

public class ResponsableStructure {


private ServiceUtilisateur serviceUtilisateur;
    @FXML
    public void Initialize(){
        serviceUtilisateur = new ServiceUtilisateur();
        //userIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        }

}
