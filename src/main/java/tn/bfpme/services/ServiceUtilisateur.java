package tn.bfpme.services;

import tn.bfpme.interfaces.IUtilisateur;
import tn.bfpme.models.*;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceUtilisateur implements IUtilisateur {
    private final Connection cnx;

    public ServiceUtilisateur() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    public UserConge afficherusers() {
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));


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
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.Approuvé));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
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
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.Rejeté));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
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
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ? ORDER BY conge.TypeConge";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
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
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ? ORDER BY user.Nom";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
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
            String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
            List<User> users = new ArrayList<>();
            List<Conge> conges = new ArrayList<>();
            String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                    "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                    "FROM user" +
                    "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                    " WHERE user.ID_Departement = ? AND conge.Statut = ? ORDER BY user.Prenom";
            try {
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.setString(1, departement);
                ps.setString(2, String.valueOf(Statut.En_Attente));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    user.setIdUser(rs.getInt("ID_User"));
                    user.setNom(rs.getString("Nom"));
                    user.setPrenom(rs.getString("Prenom"));
                    user.setEmail(rs.getString("Email"));
                    user.setImage(rs.getString("Image"));
                    user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                    user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                    user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                    user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                    user.setIdDepartement(rs.getInt("ID_Departement"));
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
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ? ORDER BY conge.DateDebut";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
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
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ? ORDER BY conge.DateFin";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
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
    public User getChef() {
        User chef = null;
        Connection cnx = MyDataBase.getInstance().getCnx(); // Assuming MyDataBase is your connection manager

        // Get the current user's department ID and role ID
        int currentDeptId = SessionManager.getInstance().getUser().getIdDepartement();
        int currentRoleId = SessionManager.getInstance().getUser().getIdRole();

        // Find parent roles of the current user's role
        List<Integer> parentRoleIds = ServiceRole.getParentRoleIds(currentRoleId);
        if (parentRoleIds.isEmpty()) {
            return null;
        }

        // Create SQL query to find a user with one of the parent roles in the same department
        String query = "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.Image, u.Solde_Annuel, u.Solde_Maladie, u.Solde_Exceptionnel, u.Solde_Maternite, u.ID_Departement " +
                "FROM user u " +
                "JOIN user_role ur ON u.ID_User = ur.ID_User " +
                "WHERE u.ID_Departement = ? AND ur.ID_Role IN (" +
                parentRoleIds.stream().map(String::valueOf).collect(Collectors.joining(",")) +
                ") LIMIT 1";

        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, currentDeptId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                chef = new User();
                chef.setIdUser(rs.getInt("ID_User"));
                chef.setNom(rs.getString("Nom"));
                chef.setPrenom(rs.getString("Prenom"));
                chef.setEmail(rs.getString("Email"));
                chef.setImage(rs.getString("Image"));
                chef.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                chef.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                chef.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                chef.setSoldeMaternite(rs.getInt("Solde_Maternite"));
                chef.setIdDepartement(rs.getInt("ID_Departement"));
                chef.setIdRole(rs.getInt("ID_Role"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return chef;
    }

    public List<User> getUsersByDepartment(String departement) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité " +
                "FROM user " +
                "WHERE user.Departement = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, departement);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             Statement stmt = cnx.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User(
                        rs.getInt("ID_User"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Email"),
                        rs.getString("MDP"),
                        rs.getString("Image"),
                        rs.getInt("Solde_Annuel"),
                        rs.getInt("Solde_Maladie"),
                        rs.getInt("Solde_Exceptionnel"),
                        rs.getInt("Solde_Maternité"),
                        rs.getInt("ID_DEPT")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addUser(String nom, String prenom, String email, String mdp, String image, int soldeAnnuel, int soldeMaladie, int soldeExceptionnel, int soldeMaternite, int idDepartement, int idRole) {
        String query = "INSERT INTO user (Nom, Prenom, Email, MDP, Image, Solde_Annuel, Solde_Maladie, Solde_Exceptionnel, Solde_Maternité, ID_DEPT, ID_Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, email);
            pstmt.setString(4, mdp);
            pstmt.setString(5, image);
            pstmt.setInt(6, soldeAnnuel);
            pstmt.setInt(7, soldeMaladie);
            pstmt.setInt(8, soldeExceptionnel);
            pstmt.setInt(9, soldeMaternite);
            pstmt.setInt(10, idDepartement);
            pstmt.setInt(11, idRole);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int idUser, String nom, String prenom, String email, String mdp, String image, int soldeAnnuel, int soldeMaladie, int soldeExceptionnel, int soldeMaternite, int idDepartement, int idRole) {
        String query = "UPDATE user SET Nom = ?, Prenom = ?, Email = ?, MDP = ?, Image = ?, Solde_Annuel = ?, Solde_Maladie = ?, Solde_Exceptionnel = ?, Solde_Maternité = ?, ID_DEPT = ?, ID_Role = ? WHERE ID_User = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, email);
            pstmt.setString(4, mdp);
            pstmt.setString(5, image);
            pstmt.setInt(6, soldeAnnuel);
            pstmt.setInt(7, soldeMaladie);
            pstmt.setInt(8, soldeExceptionnel);
            pstmt.setInt(9, soldeMaternite);
            pstmt.setInt(10, idDepartement);
            pstmt.setInt(11, idRole);
            pstmt.setInt(12, idUser);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int idUser) {
        String query = "DELETE FROM user WHERE ID_User = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setInt(1, idUser);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assignUserToDepartmentAndRole(int idUser, int idDepartement, int idRole) {
        String query = "UPDATE user SET ID_DEPT = ?, ID_Role = ? WHERE ID_User = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setInt(1, idDepartement);
            pstmt.setInt(2, idRole);
            pstmt.setInt(3, idUser);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
