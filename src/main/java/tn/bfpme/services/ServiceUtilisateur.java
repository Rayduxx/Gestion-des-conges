package tn.bfpme.services;

import tn.bfpme.interfaces.IUtilisateur;
import tn.bfpme.models.*;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceUtilisateur implements IUtilisateur {
    Connection cnx = MyDataBase.getInstance().getCnx();
    private Map<Integer, Integer> userManagerMap = new HashMap<>();

    public ServiceUtilisateur() {
        loadHierarchy();
    }

    public UserConge afficherusers() {
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE user.ID_Manager = ? AND conge.Statut = ?";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, SessionManager.getInstance().getUser().getIdUser());
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
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();

        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE conge.Statut = ? " +
                "AND user.ID_Manager = ?";

        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, String.valueOf(Statut.Approuvé));
            ps.setInt(2, SessionManager.getInstance().getUser().getIdUser());
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
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE conge.Statut = ? " +
                "AND user.ID_Manager = ?";

        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx(); // Re-establish the connection if necessary
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, String.valueOf(Statut.Rejeté));
            ps.setInt(2, SessionManager.getInstance().getUser().getIdUser());
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
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE conge.Statut = ? " +
                "AND user.ID_Manager = ? ORDER BY conge.TypeConge";

        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, String.valueOf(Statut.En_Attente));
            ps.setInt(2, SessionManager.getInstance().getUser().getIdUser());
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
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE conge.Statut = ? " +
                "AND user.ID_Manager = ? ORDER BY user.Nom";

        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, String.valueOf(Statut.En_Attente));
            ps.setInt(2, SessionManager.getInstance().getUser().getIdUser());
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
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE conge.Statut = ? " +
                "AND user.ID_Manager = ? ORDER BY user.Prenom";

        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, String.valueOf(Statut.En_Attente));
            ps.setInt(2, SessionManager.getInstance().getUser().getIdUser());
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
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE conge.Statut = ? " +
                "AND user.ID_Manager = ? ORDER BY conge.DateDebut";

        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, String.valueOf(Statut.En_Attente));
            ps.setInt(2, SessionManager.getInstance().getUser().getIdUser());
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
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE conge.Statut = ? " +
                "AND user.ID_Manager = ? ORDER BY conge.DateFin";

        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, String.valueOf(Statut.En_Attente));
            ps.setInt(2, SessionManager.getInstance().getUser().getIdUser());
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

    @Override
    public List<User> RechrecheRH(String recherche) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT ID_User, Nom, Prenom, Email, Image, Solde_Annuel, Solde_Maladie, Solde_Exceptionnel, Solde_Maternité, ID_Departement " +
                "FROM `user` " +
                "WHERE `ID_User` LIKE ? " +
                "AND (`Nom` LIKE ? " +
                "OR `Prenom` LIKE ? " +
                "OR `Email` LIKE ? )";
        try (PreparedStatement ste = cnx.prepareStatement(sql)) {
            String searchPattern = "%" + recherche + "%";
            ste.setString(1, "%" + SessionManager.getInstance().getUser().getIdUser() + "%");
            ste.setString(2, searchPattern);
            ste.setString(3, searchPattern);
            ste.setString(4, searchPattern);
            ResultSet rs = ste.executeQuery();
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
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public User getChef() {
        User chef = null;
        Connection cnx = MyDataBase.getInstance().getCnx(); // Assuming MyDataBase is your connection manager

        // Get the current user's department ID and role ID
        int currentDeptId = SessionManager.getInstance().getUser().getIdDepartement();
        int currentRoleId = SessionManager.getInstance().getUserRole().getIdRole();

        // Find parent roles of the current user's role
        List<Integer> parentRoleIds = ServiceRole.getParentRoleIds(currentRoleId);
        if (parentRoleIds.isEmpty()) {
            return null;
        }

        // Create SQL query to find a user with one of the parent roles in the same department
        String query = "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.Image, u.Solde_Annuel, u.Solde_Maladie, u.Solde_Exceptionnel, u.Solde_Maternité, u.ID_Departement " +
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
                chef.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                chef.setIdDepartement(rs.getInt("ID_Departement"));
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
                "WHERE user.ID_Departement = ?";

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

    public List<User> getAllUsersInfo() {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.*, ur.ID_Role FROM user u LEFT JOIN user_role ur ON u.ID_User = ur.ID_User";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             Statement stmt = cnx.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Email"),
                        rs.getInt("ID_Manager"),
                        rs.getInt("ID_Departement"),
                        rs.getInt("ID_Role")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addUser(String nom, String prenom, String email, String mdp, String image, int soldeAnnuel, int soldeMaladie, int soldeExceptionnel, int soldeMaternite, int idDepartement, int idRole) {
        String query = "INSERT INTO user (Nom, Prenom, Email, MDP, Image, Solde_Annuel, Solde_Maladie, Solde_Exceptionnel, Solde_Maternité, ID_Departement, ID_Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        String query = "UPDATE user SET Nom = ?, Prenom = ?, Email = ?, MDP = ?, Image = ?, Solde_Annuel = ?, Solde_Maladie = ?, Solde_Exceptionnel = ?, Solde_Maternité = ?, ID_Departement = ?, ID_Role = ? WHERE ID_User = ?";
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
        String query = "UPDATE user SET ID_Departement = ?, ID_Role = ? WHERE ID_User = ?";
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

    public void assignRoleToUser(int userId, int roleId) {
        String sql = "INSERT INTO user_role (ID_User, ID_Role) VALUES (?, ?)";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stm = cnx.prepareStatement(sql)) {
            stm.setInt(1, userId);
            stm.setInt(2, roleId);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserRoleAndDepartment(int userId, int roleId, int departmentId) {
        updateUserDepartment(userId, departmentId);
        updateUserRole(userId, roleId);
    }

    public void updateUserRole(int userId, int roleId) {
        String deleteSql = "DELETE FROM user_role WHERE ID_User = ?";
        String insertSql = "INSERT INTO user_role (ID_User, ID_Role) VALUES (?, ?)";

        try (Connection cnx = MyDataBase.getInstance().getCnx()) {
            cnx.setAutoCommit(false);

            try (PreparedStatement deleteStmt = cnx.prepareStatement(deleteSql);
                 PreparedStatement insertStmt = cnx.prepareStatement(insertSql)) {

                deleteStmt.setInt(1, userId);
                deleteStmt.executeUpdate();

                insertStmt.setInt(1, userId);
                insertStmt.setInt(2, roleId);
                insertStmt.executeUpdate();

                cnx.commit();
            } catch (SQLException e) {
                cnx.rollback();
                e.printStackTrace();
            } finally {
                cnx.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserDepartment(int userId, int departmentId) {
        String sql = "UPDATE user SET ID_Departement = ? WHERE ID_User = ?";

        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stm = cnx.prepareStatement(sql)) {

            stm.setInt(1, departmentId);
            stm.setInt(2, userId);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT u.*, ur.ID_Role FROM user u LEFT JOIN user_role ur ON u.ID_User = ur.ID_User WHERE u.ID_User = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, userId);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    user = new User(
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
                            rs.getInt("ID_Departement"),
                            rs.getInt("ID_Role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int getManagerIdByUserId2(int userId) {
        String query = "SELECT ID_Manager FROM user WHERE ID_User = ?";
        int managerId = 0;
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                managerId = rs.getInt("ID_Manager");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managerId;
    }
    public Integer getManagerIdByUserId(int userId) {
        Integer managerId = null;
        String query = "SELECT ID_Manager FROM user WHERE ID_User = ?";

        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                managerId = rs.getInt("ID_Manager");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return managerId;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.*, ur.ID_Role FROM user u LEFT JOIN user_role ur ON u.ID_User = ur.ID_User";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx(); // Re-establish the connection if necessary
            }
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                // Handle potential null values for ID_Role
                int idRole = rs.getObject("ID_Role") != null ? rs.getInt("ID_Role") : -1; // -1 or any default value if ID_Role is null

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
                        rs.getInt("ID_Departement"),
                        rs.getInt("ID_Manager"),
                        idRole
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void setManagerForUser(int userId, int managerId) {
        String query = "UPDATE user SET ID_Manager = ? WHERE ID_User = ?";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx(); // Re-establish the connection if necessary
            }
            PreparedStatement pstmt = cnx.prepareStatement(query);
            pstmt.setInt(1, managerId);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getManagerNameByUserId(int userId) {
        String query = "SELECT Nom, Prenom FROM user WHERE ID_User = ?";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx(); // Re-establish the connection if necessary
            }
            PreparedStatement pstmt = cnx.prepareStatement(query);
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Nom") + " " + rs.getString("Prenom");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> loadHierarchy() {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.MDP, u.Image, u.Solde_Annuel, u.Solde_Maladie, u.Solde_Exceptionnel, u.Solde_Maternité, u.ID_Departement, u.ID_Manager, " +
                "m.Nom as ManagerNom, m.Prenom as ManagerPrenom " +
                "FROM user u " +
                "LEFT JOIN user m ON u.ID_Manager = m.ID_User";

        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement ps = cnx.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

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
                        rs.getInt("ID_Departement"),
                        rs.getInt("ID_Manager")
                );

                String managerName = rs.getString("ManagerNom") != null ? rs.getString("ManagerNom") + " " + rs.getString("ManagerPrenom") : "No Manager";
                user.setNom(user.getNom() + " (Manager: " + managerName + ")");
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<User> getAllUsersWithManagers() {
        List<User> users = getAllUsers();
        for (User user : users) {
            Integer managerId = getManagerIdByUserId(user.getIdUser());
            String managerName = managerId != null ? getManagerNameByUserId(managerId) : null;
            user.setNom(user.getNom() + (managerName != null ? " (Manager: " + managerName + ")" : " (No Manager)"));
        }
        return users;
    }

    @Override
    public void Add(User user) {
        String qry = "INSERT INTO `user`(`Nom`, `Prenom`, `Email`, `MDP`, `Image`, `Solde_Annuel`, `Solde_Maladie`, `Solde_Exceptionnel`, `Solde_Maternité`) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement stm = cnx.prepareStatement(qry);

            stm.setString(1, user.getNom());
            stm.setString(2, user.getPrenom());
            stm.setString(3, user.getEmail());
            stm.setString(4, user.getMdp());
            stm.setString(5, user.getImage());
            stm.setInt(6, user.getSoldeAnnuel());
            stm.setInt(7, user.getSoldeMaladie());
            stm.setInt(8, user.getSoldeExceptionnel());
            stm.setInt(9, user.getSoldeMaternite());

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Update(User user) {
        String query = "UPDATE user SET Nom = ?, Prenom = ?, Email = ?, MDP = ?, Image = ?, Solde_Annuel = ?, Solde_Maladie = ?, Solde_Exceptionnel = ?, Solde_Maternité = ? WHERE ID_User = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setString(1, user.getNom());
            stm.setString(2, user.getPrenom());
            stm.setString(3, user.getEmail());
            stm.setString(4, user.getMdp());
            stm.setString(5, user.getImage());
            stm.setInt(6, user.getSoldeAnnuel());
            stm.setInt(7, user.getSoldeMaladie());
            stm.setInt(8, user.getSoldeExceptionnel());
            stm.setInt(9, user.getSoldeMaternite());
            stm.setInt(10, user.getIdUser());
            stm.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> Show() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT `ID_User`, `Nom`,`Prenom`,`Email`,`MDP`,`Image`,`Solde_Annuel`,`Solde_Maladie`,`Solde_Exceptionnel`,`Solde_Maternité` FROM `user`";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
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
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public void Delete(User user) {

        String updateManagerQuery = "UPDATE `user` SET `ID_Manager` = NULL WHERE `ID_Manager` = ?";
        String deleteUserQuery = "DELETE FROM `user` WHERE `ID_User` = ?";

        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement updateStmt = cnx.prepareStatement(updateManagerQuery);
             PreparedStatement deleteStmt = cnx.prepareStatement(deleteUserQuery)) {

            updateStmt.setInt(1, user.getIdUser());
            updateStmt.executeUpdate();

            deleteStmt.setInt(1, user.getIdUser());
            deleteStmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void DeleteByID(int id) {
        String qry = "DELETE FROM `user` WHERE ID_User=?";

        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement smt = cnx.prepareStatement(qry)) {
            smt.setInt(1, id);
            smt.executeUpdate();
            System.out.println("Suppression Effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean checkUserExists(String email) {
        String req = "SELECT count(1) FROM `usertable` WHERE `Email`=?";
        boolean exists = false;
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                exists = res.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking if user exists: " + e.getMessage());
        }
        return exists;
    }

    public boolean checkPhoneExists(String phoneNumber) {
        String req = "SELECT count(1) FROM `usertable` WHERE `NumTel`=?";
        boolean exists = false;
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, phoneNumber);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                exists = res.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking if phone number exists: " + e.getMessage());
        }
        return exists;
    }


    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*\\.?[a-zA-Z0-9_+&*-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    public List<User> searchUsers(String query) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE ID_User LIKE ? OR Nom LIKE ? OR Prenom LIKE ? OR Email LIKE ?";
        try (PreparedStatement ps = cnx.prepareStatement(sql)) {
            String searchQuery = "%" + query + "%";
            ps.setString(1, searchQuery);
            ps.setString(2, searchQuery);
            ps.setString(3, searchQuery);
            ps.setString(4, searchQuery);
            ResultSet rs = ps.executeQuery();
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
                        rs.getInt("ID_Departement"),
                        rs.getInt("ID_Manager"),
                        rs.getInt("ID_Role")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public int getUserIdCard() {
        return 0;
    }
}
