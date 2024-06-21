package tn.bfpme.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tn.bfpme.models.Conge;
import tn.bfpme.services.ServiceConge;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class HistoriqueConge implements Initializable {
    private final ServiceConge CongeS = new ServiceConge();
    private Connection cnx;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
    }

    @FXML
    private GridPane congeContainer;
    public void load() {
        int column =1;
        int row = 1;
        try {
            for (Conge conge : CongeS.afficher()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/CongeCarte.fxml"));
                Pane CardBox = fxmlLoader.load();
                CongeCarte cardC = fxmlLoader.getController();
                cardC.setData(conge);
                if (row == 1) {

                    ++row;
                }
                congeContainer.add(CardBox, column , row);
                GridPane.setMargin(CardBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
