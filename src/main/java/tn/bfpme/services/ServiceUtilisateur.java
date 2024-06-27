package tn.bfpme.services;

import tn.bfpme.controllers.EmployeController;
import tn.bfpme.interfaces.IUtilisateur;
import tn.bfpme.models.*;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUtilisateur implements IUtilisateur {
    private final Connection cnx;
    public ServiceUtilisateur() {
        cnx = MyDataBase.getInstance().getCnx();
    }
    public UserConge afficherusers() {
        List<Utilisateur> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut " +
                "FROM utilisateur " +
                "JOIN employe ON utilisateur.ID_User = employe.ID_User " +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User";
        try {
            System.out.println("Executing query: " + query);
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                if (!users.contains(user)) {
                    users.add(user);
                }

                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
            System.out.println("Loaded users: " + users);
            System.out.println("Loaded conges: " + conges);
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
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
