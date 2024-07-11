package tn.bfpme.models;

import tn.bfpme.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SoldeConge {
    private int idSolde;
    private double SoldeAnn;
    private double SoldeMal;
    private double SoldeMat;
    private double SoldeExc;



    public SoldeConge(){}

    public SoldeConge(double soldeAnn, double soldeMal, double soldeMat, double soldeExc) {
        SoldeAnn = soldeAnn;
        SoldeMal = soldeMal;
        SoldeMat = soldeMat;
        SoldeExc = soldeExc;
    }


    public SoldeConge getSoldeConge() {
        SoldeConge soldeConge = null;
        try (Connection connection = MyDataBase.getInstance().getCnx()) {
            String query = "SELECT SoldeAnn, SoldeMal, SoldeMat, SoldeExc FROM solde_conge WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1); // Assuming you are fetching data for id = 1, change as per your requirement
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                soldeConge = new SoldeConge();
                soldeConge.setSoldeAnn(resultSet.getDouble("SoldeAnn"));
                soldeConge.setSoldeMal(resultSet.getDouble("SoldeMal"));
                soldeConge.setSoldeMat(resultSet.getDouble("SoldeMat"));
                soldeConge.setSoldeExc(resultSet.getDouble("SoldeExc"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soldeConge;
    }

//Getters & Setters


    public int getIdSolde() {
        return idSolde;
    }

    public void setIdSolde(int idSolde) {
        this.idSolde = idSolde;
    }

    public double getSoldeAnn() {
        return SoldeAnn;
    }

    public void setSoldeAnn(double soldeAnn) {
        SoldeAnn = soldeAnn;
    }

    public double getSoldeMal() {
        return SoldeMal;
    }

    public void setSoldeMal(double soldeMal) {
        SoldeMal = soldeMal;
    }

    public double getSoldeMat() {
        return SoldeMat;
    }

    public void setSoldeMat(double soldeMat) {
        SoldeMat = soldeMat;
    }

    public double getSoldeExc() {
        return SoldeExc;
    }

    public void setSoldeExc(double soldeExc) {
        SoldeExc = soldeExc;
    }
}
