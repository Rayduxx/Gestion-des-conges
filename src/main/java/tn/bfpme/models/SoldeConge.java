package tn.bfpme.models;

import tn.bfpme.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SoldeConge {
    private int idSolde;
   private String Type;
   private double Pas;
   private int PeriodeJ;
   private int PeriodeM;
   private int PeriodeA;
   private boolean File;




    public SoldeConge(){}

    public SoldeConge(int idSolde, String type, double pas, int periodeJ, int periodeM, int periodeA, boolean file) {
        this.idSolde = idSolde;
        Type = type;
        Pas = pas;
        PeriodeJ = periodeJ;
        PeriodeM = periodeM;
        PeriodeA = periodeA;
        this.File = file;
    }

//Getters & Setters


    public int getIdSolde() {
        return idSolde;
    }

    public void setIdSolde(int idSolde) {
        this.idSolde = idSolde;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public double getPas() {
        return Pas;
    }

    public void setPas(double pas) {
        Pas = pas;
    }

    public int getPeriodeJ() {
        return PeriodeJ;
    }

    public void setPeriodeJ(int periodeJ) {
        PeriodeJ = periodeJ;
    }

    public int getPeriodeM() {
        return PeriodeM;
    }

    public void setPeriodeM(int periodeM) {
        PeriodeM = periodeM;
    }

    public int getPeriodeA() {
        return PeriodeA;
    }

    public void setPeriodeA(int periodeA) {
        PeriodeA = periodeA;
    }

    public boolean isFile() {
        return File;
    }

    public void setFile(boolean file) {
        File = file;
    }
}
