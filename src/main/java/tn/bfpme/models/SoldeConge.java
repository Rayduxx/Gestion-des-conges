package tn.bfpme.models;

import tn.bfpme.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SoldeConge {
    private int idSolde;

    private String Designation;
    private String Type;
    private int Pas;
    private int Periode;



    public SoldeConge(){}

    public SoldeConge(int idSolde, String designation, String type, int pas, int periode) {
        this.idSolde = idSolde;
        this.Designation = designation;
        this.Type = type;
        this.Pas = pas;
        this.Periode = periode;
    }

    //Getters & Setters
    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getPas() {
        return Pas;
    }

    public void setPas(int pas) {
        Pas = pas;
    }

    public int getPeriode() {
        return Periode;
    }

    public void setPeriode(int periode) {
        Periode = periode;
    }
    public int getIdSolde() {
        return idSolde;
    }

    public void setIdSolde(int idSolde) {
        this.idSolde = idSolde;
    }


}
