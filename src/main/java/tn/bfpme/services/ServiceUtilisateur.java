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
        Departement departementEnum = SessionManager.getInstance().getDepartement();
        String departement = departementEnum.name();
        List<Utilisateur> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, utilisateur.Solde_Annuel, utilisateur.Solde_Maladie, utilisateur.Solde_Exceptionnel, utilisateur.Solde_Maternité, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM utilisateur " +
                "JOIN employe ON utilisateur.ID_User = employe.ID_User " +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User"+
                " WHERE employe.Departement = ? AND conge.Statut = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }
    public UserConge AfficherApprove() {
        Departement departementEnum = SessionManager.getInstance().getDepartement();
        String departement = departementEnum.name();
        List<Utilisateur> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, utilisateur.Solde_Annuel, utilisateur.Solde_Maladie, utilisateur.Solde_Exceptionnel, utilisateur.Solde_Maternité, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM utilisateur " +
                "JOIN employe ON utilisateur.ID_User = employe.ID_User " +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User"+
                " WHERE employe.Departement = ? AND conge.Statut = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.Approuvé));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }
    public UserConge AfficherReject() {
        Departement departementEnum = SessionManager.getInstance().getDepartement();
        String departement = departementEnum.name();
        List<Utilisateur> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, utilisateur.Solde_Annuel, utilisateur.Solde_Maladie, utilisateur.Solde_Exceptionnel, utilisateur.Solde_Maternité, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM utilisateur " +
                "JOIN employe ON utilisateur.ID_User = employe.ID_User " +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User"+
                " WHERE employe.Departement = ? AND conge.Statut = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.Rejeté));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }

    public UserConge TriType() {
        Departement departementEnum = SessionManager.getInstance().getDepartement();
        String departement = departementEnum.name();
        List<Utilisateur> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, utilisateur.Solde_Annuel, utilisateur.Solde_Maladie, utilisateur.Solde_Exceptionnel, utilisateur.Solde_Maternité, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM utilisateur " +
                "JOIN employe ON utilisateur.ID_User = employe.ID_User " +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User"+
                " WHERE employe.Departement = ? AND conge.Statut = ? ORDER BY conge.TypeConge";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }
    public UserConge TriNom() {
        Departement departementEnum = SessionManager.getInstance().getDepartement();
        String departement = departementEnum.name();
        List<Utilisateur> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, utilisateur.Solde_Annuel, utilisateur.Solde_Maladie, utilisateur.Solde_Exceptionnel, utilisateur.Solde_Maternité, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM utilisateur " +
                "JOIN employe ON utilisateur.ID_User = employe.ID_User " +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User"+
                " WHERE employe.Departement = ? AND conge.Statut = ? ORDER BY utilisateur.Nom";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }
    public UserConge TriPrenom() {
        Departement departementEnum = SessionManager.getInstance().getDepartement();
        String departement = departementEnum.name();
        List<Utilisateur> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, utilisateur.Solde_Annuel, utilisateur.Solde_Maladie, utilisateur.Solde_Exceptionnel, utilisateur.Solde_Maternité, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM utilisateur " +
                "JOIN employe ON utilisateur.ID_User = employe.ID_User " +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User"+
                " WHERE employe.Departement = ? AND conge.Statut = ? ORDER BY utilisateur.Prenom";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }
    public UserConge TriDateDebut() {
        Departement departementEnum = SessionManager.getInstance().getDepartement();
        String departement = departementEnum.name();
        List<Utilisateur> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, utilisateur.Solde_Annuel, utilisateur.Solde_Maladie, utilisateur.Solde_Exceptionnel, utilisateur.Solde_Maternité, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM utilisateur " +
                "JOIN employe ON utilisateur.ID_User = employe.ID_User " +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User"+
                " WHERE employe.Departement = ? AND conge.Statut = ? ORDER BY conge.DateDebut";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }
    public UserConge TriDateFin() {
        Departement departementEnum = SessionManager.getInstance().getDepartement();
        String departement = departementEnum.name();
        List<Utilisateur> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, utilisateur.Solde_Annuel, utilisateur.Solde_Maladie, utilisateur.Solde_Exceptionnel, utilisateur.Solde_Maternité, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM utilisateur " +
                "JOIN employe ON utilisateur.ID_User = employe.ID_User " +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User"+
                " WHERE employe.Departement = ? AND conge.Statut = ? ORDER BY conge.DateFin";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }
    /*public UserConge Recherche() {
        Departement departementEnum = SessionManager.getInstance().getDepartement();
        String departement = departementEnum.name();
        List<Utilisateur> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, utilisateur.Solde_Annuel, utilisateur.Solde_Maladie, utilisateur.Solde_Exceptionnel, utilisateur.Solde_Maternité, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM utilisateur " +
                "JOIN employe ON utilisateur.ID_User = employe.ID_User " +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User"+
                " WHERE employe.Departement = ? AND conge.Statut = ? AND utilisateur.Nom ";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }*/


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

    public void SetDestinataire(int userId,Departement departement,Utilisateur user) throws SQLException{
        String qry = "SELECT * FROM `user` WHERE `ID_User`=?l+";
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
