package tn.bfpme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceUtilisateur;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class UserCardController {
    @FXML
    private Pane Card;
    @FXML
    private Label carddepart;
    @FXML
    private Label cardemail;
    @FXML
    private ImageView cardimage;
    @FXML
    private Label cardnameprename;
    @FXML
    public HBox HBoxBtns;
    @FXML
    private Label cardrole;

    private final ServiceUtilisateur UserS = new ServiceUtilisateur();

    int uid;
    String unom, uprenom, uemail, umdp, urole, udepart, updp;
    double SAnn, SExp, SMala, SMater;
    public void setData(User user) {
        String imagePath = user.getImage();
        if (imagePath != null) {
            try {
                File file = new File(imagePath);
                FileInputStream inputStream = new FileInputStream(file);
                Image image = new Image(inputStream);
                cardimage.setImage(image);
            } catch (FileNotFoundException e) {
                System.err.println("Image file not found: " + imagePath);
            }
        } else {
            System.err.println("Image path is null for user: " + user);
        }
        cardnameprename.setText(user.getNom() + " " + user.getPrenom());
        cardemail.setText(user.getEmail());
        String roleName = UserS.getRoleNameById(user.getIdRole());
        String departmentName = UserS.getDepartmentNameById(user.getIdDepartement());

        cardrole.setText(roleName);
        carddepart.setText(departmentName);
        Card.setStyle("-fx-border-radius: 5px;-fx-border-color:#808080");

        uprenom = user.getPrenom();
        uid = user.getIdUser();
        unom = user.getNom();
        uemail = user.getEmail();
        umdp = user.getMdp();
        urole = roleName;
        udepart = departmentName;
        updp = user.getImage();
        SAnn = user.getSoldeAnnuel();
        SMala = user.getSoldeMaladie();
        SMater = user.getSoldeMaternite();
        SExp = user.getSoldeExceptionnel();
    }


    @FXML
    void ModifierUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminIT.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            AdminITController AITC = loader.getController();
            AITC.ID_A.setText(String.valueOf(uid));
            AITC.nom_A.setText(unom);
            AITC.Prenom_A.setText(uprenom);
            AITC.email_A.setText(uemail);
            AITC.MDP_A.setText(umdp);
            AITC.image_A.setText(updp);
            AITC.S_Ann.setText(String.valueOf(SAnn));
            AITC.S_exc.setText(String.valueOf(SExp));
            AITC.S_mal.setText(String.valueOf(SMala));
            AITC.S_mat.setText(String.valueOf(SMater));
            String imagePath = updp;
            if (imagePath != null) {
                try {
                    File file = new File(imagePath);
                    FileInputStream inputStream = new FileInputStream(file);
                    Image image = new Image(inputStream);
                    AITC.PDPimageHolder.setImage(image);
                } catch (FileNotFoundException e) {
                    System.err.println("Image file not found: " + imagePath);
                }
            }
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SupprimerUser(ActionEvent event) {
        UserS.DeleteByID(uid);
        ((GridPane) Card.getParent()).getChildren().remove(Card);
    }
}

