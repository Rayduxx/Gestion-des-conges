package tn.bfpme.services;

import tn.bfpme.models.*;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceUtilisateur {
    private final Connection cnx;

    public ServiceUtilisateur() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    public void checkEmployee(int userId, Utilisateur user) throws SQLException {
        String qry = "SELECT `ID_Employé`, `Departement`, `ID_User` FROM `employe` WHERE `ID_User`=?";
        try (PreparedStatement stm = cnx.prepareStatement(qry)) {
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Employe curEmp = new Employe(rs.getInt("ID_Employé"), Departement.valueOf(rs.getString("Departement")), user);
                SessionManager sessionManager = SessionManager.getInstance(curEmp);
                sessionManager.setDepartementEmp(Departement.valueOf(rs.getString("Departement")));
            }
        }
    }

    public void checkDepartmentHead(int userId, Utilisateur user) throws SQLException {
        String qry = "SELECT `ID_ChefDep`, `Departement`, `ID_User` FROM `chef_departement` WHERE `ID_User`=?";
        try (PreparedStatement stm = cnx.prepareStatement(qry)) {
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                ChefDepartement curChefDep = new ChefDepartement(rs.getInt("ID_ChefDep"), Departement.valueOf(rs.getString("Departement")), user);
                SessionManager sessionManager = SessionManager.getInstance(curChefDep);
                sessionManager.setDepartementChfDep(Departement.valueOf(rs.getString("Departement")));
            }
        }
    }

    public void checkChiefAdministration(int userId, Utilisateur user) throws SQLException {
        String qry = "SELECT * FROM `chef_administration` WHERE `ID_User`=?";
        try (PreparedStatement stm = cnx.prepareStatement(qry)) {
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                ChefAdministration curChefAdm = new ChefAdministration(rs.getInt("ID_ChefAdmin"), user);
                SessionManager.getInstance(curChefAdm);
            }
        }
    }

    public void checkAdminIT(int userId, Utilisateur user) throws SQLException {
        String qry = "SELECT * FROM `admin` WHERE `ID_User`=?";
        try (PreparedStatement stm = cnx.prepareStatement(qry)) {
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                AdminIT curAdm = new AdminIT(rs.getInt("ID_Admin"), user);
                SessionManager.getInstance(curAdm);
            }
        }
    }
}
