package tn.bfpme.services;

import tn.bfpme.controllers.SoldeLogicController;
import tn.bfpme.interfaces.IUtilisateur;
import tn.bfpme.models.*;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceUtilisateur implements IUtilisateur {
    private static Connection cnx;

    public ServiceUtilisateur(Connection cnx) {
        this.cnx = cnx;
    }

    private Map<Integer, Integer> userManagerMap = new HashMap<>();

    public ServiceUtilisateur() {
        this.cnx = MyDataBase.getInstance().getCnx();
        loadHierarchy();
    }

    private void loadHierarchy() {
    }

    public UserConge afficherusers() {
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "WITH RECURSIVE Subordinates AS (" +
                "SELECT ID_User, Nom, Prenom, Email, Image, ID_Departement " +
                "FROM user " +
                "WHERE ID_User = ? " +
                "UNION ALL " +
                "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.Image, u.ID_Departement " +
                "FROM user u " +
                "INNER JOIN Subordinates s ON u.ID_Manager = s.ID_User " +
                ") " +
                "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE user.ID_User IN (SELECT ID_User FROM Subordinates WHERE ID_User != ?) AND conge.Statut = ?";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            int currentUserId = SessionManager.getInstance().getUser().getIdUser();
            ps.setInt(1, currentUserId);
            ps.setInt(2, currentUserId);
            ps.setString(3, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                user.setIdSolde(rs.getInt("idSolde"));
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
        String query = "WITH RECURSIVE Subordinates AS (" +
                "SELECT ID_User, Nom, Prenom, Email, Image, ID_Departement " +
                "FROM user " +
                "WHERE ID_User = ? " +
                "UNION ALL " +
                "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.Image, u.ID_Departement " +
                "FROM user u " +
                "INNER JOIN Subordinates s ON u.ID_Manager = s.ID_User " +
                ") " +
                "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE user.ID_User IN (SELECT ID_User FROM Subordinates WHERE ID_User != ?) AND conge.Statut = ?";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            int currentUserId = SessionManager.getInstance().getUser().getIdUser();
            ps.setInt(1, currentUserId);
            ps.setInt(2, currentUserId);
            ps.setString(3, String.valueOf(Statut.Approuvé));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                user.setIdSolde(rs.getInt("idSolde"));
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
        String query = "WITH RECURSIVE Subordinates AS (" +
                "SELECT ID_User, Nom, Prenom, Email, Image, ID_Departement " +
                "FROM user " +
                "WHERE ID_User = ? " +
                "UNION ALL " +
                "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.Image, u.ID_Departement " +
                "FROM user u " +
                "INNER JOIN Subordinates s ON u.ID_Manager = s.ID_User " +
                ") " +
                "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE user.ID_User IN (SELECT ID_User FROM Subordinates WHERE ID_User != ?) AND conge.Statut = ?";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            int currentUserId = SessionManager.getInstance().getUser().getIdUser();
            ps.setInt(1, currentUserId);
            ps.setInt(2, currentUserId);
            ps.setString(3, String.valueOf(Statut.Rejeté));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                user.setIdSolde(rs.getInt("idSolde"));
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
        return null;
    }

    @Override
    public User getChef() {
        return null;
    }

    @Override
    public List<User> getUsersByDepartment(String departement) {
        return null;
    }

    public UserConge TriType() {
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "WITH RECURSIVE Subordinates AS (" +
                "SELECT ID_User, Nom, Prenom, Email, Image, ID_Departement " +
                "FROM user " +
                "WHERE ID_User = ? " +
                "UNION ALL " +
                "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.Image, u.ID_Departement " +
                "FROM user u " +
                "INNER JOIN Subordinates s ON u.ID_Manager = s.ID_User " +
                ") " +
                "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE user.ID_User IN (SELECT ID_User FROM Subordinates WHERE ID_User != ?) AND conge.Statut = ? ORDER BY conge.TypeConge";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            int currentUserId = SessionManager.getInstance().getUser().getIdUser();
            ps.setInt(1, currentUserId);
            ps.setInt(2, currentUserId);
            ps.setString(3, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                user.setIdSolde(rs.getInt("idSolde"));
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
        String query = "WITH RECURSIVE Subordinates AS (" +
                "SELECT ID_User, Nom, Prenom, Email, Image, ID_Departement " +
                "FROM user " +
                "WHERE ID_User = ? " +
                "UNION ALL " +
                "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.Image, u.ID_Departement " +
                "FROM user u " +
                "INNER JOIN Subordinates s ON u.ID_Manager = s.ID_User " +
                ") " +
                "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE user.ID_User IN (SELECT ID_User FROM Subordinates WHERE ID_User != ?) AND conge.Statut = ? ORDER BY user.Nom";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            int currentUserId = SessionManager.getInstance().getUser().getIdUser();
            ps.setInt(1, currentUserId);
            ps.setInt(2, currentUserId);
            ps.setString(3, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                user.setIdSolde(rs.getInt("idSolde"));
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
        String query = "WITH RECURSIVE Subordinates AS (" +
                "SELECT ID_User, Nom, Prenom, Email, Image, ID_Departement " +
                "FROM user " +
                "WHERE ID_User = ? " +
                "UNION ALL " +
                "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.Image, u.ID_Departement " +
                "FROM user u " +
                "INNER JOIN Subordinates s ON u.ID_Manager = s.ID_User " +
                ") " +
                "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.ID_Departement, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM user " +
                "JOIN conge ON user.ID_User = conge.ID_User " +
                "WHERE user.ID_User IN (SELECT ID_User FROM Subordinates WHERE ID_User != ?) AND conge.Statut = ? ORDER BY user.Prenom";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(query);
            int currentUserId = SessionManager.getInstance().getUser().getIdUser();
            ps.setInt(1, currentUserId);
            ps.setInt(2, currentUserId);
            ps.setString(3, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
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
    public UserConge TriDateDebut() {
        return null;
    }

    @Override
    public UserConge TriDateFin() {
        return null;
    }

    @Override
    public void DeleteByID(int id) throws SQLException {
        User user = getUserById3(id); // Fetch the user by ID
        if (user != null) {
            Delete(user); // Reuse the existing method to handle dependencies
        } else {
            System.out.println("User not found for ID: " + id);
        }
    }

    public User getUserById3(int userId) {
        User user = null;
        String query = "SELECT * FROM user WHERE ID_User = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("ID_User"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Email"),
                        rs.getString("MDP"),
                        rs.getString("Image"),
                        rs.getInt("ID_Departement"),
                        rs.getInt("ID_Manager")
                );
                user.setIdSolde(rs.getInt("idSolde"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean checkUserExists(String email) {
        String req = "SELECT count(1) FROM `user` WHERE `Email`=?";
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
        String req = "SELECT count(1) FROM `user` WHERE `NumTel`=?";
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
                        rs.getInt("ID_Departement"),
                        rs.getInt("ID_Manager")
                );
                user.setIdSolde(rs.getInt("idSolde"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public String getRoleNameById(int roleId) {
        String roleName = null;
        String query = "SELECT nom FROM role WHERE ID_Role = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                roleName = rs.getString("nom");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleName;
    }

    public String getDepartmentNameById(int departmentId) {
        String departmentName = null;
        String query = "SELECT nom FROM departement WHERE ID_Departement = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, departmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                departmentName = rs.getString("nom");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentName;
    }

    public int getUserIdCard() {
        return 0;
    }

    /*public void recalculateSolde(User user) {
        user.setSoldeAnnuel(SoldeLogicController.calculateSoldeAnnuelle(user.getCreationDate()));
        user.setSoldeMaladie(SoldeLogicController.calculateSoldeMaladie(user.getCreationDate()));
        user.setSoldeExceptionnel(SoldeLogicController.calculateSoldeExceptionnel(user.getCreationDate()));
        user.setSoldeMaternite(SoldeLogicController.calculateSoldeMaternite(user.getCreationDate()));

        String query = "UPDATE user SET Solde_Annuel = ?, Solde_Maladie = ?, Solde_Exceptionnel = ?, Solde_Maternité = ? WHERE ID_User = ?";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setDouble(1, user.getSoldeAnnuel());
            stm.setDouble(2, user.getSoldeMaladie());
            stm.setDouble(3, user.getSoldeExceptionnel());
            stm.setDouble(4, user.getSoldeMaternite());
            stm.setInt(5, user.getIdUser());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public List<User> SortDepart() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.*, d.nom AS DepartementNom, r.nom AS RoleNom, ur.ID_Role FROM `user` u " +
                "LEFT JOIN `departement` d ON u.ID_Departement = d.ID_Departement " +
                "LEFT JOIN `user_role` ur ON u.ID_User = ur.ID_User " +
                "LEFT JOIN `role` r ON ur.ID_Role = r.ID_Role " +
                "ORDER BY u.ID_Departement";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setMdp(rs.getString("MDP"));
                user.setImage(rs.getString("Image"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                user.setIdManager(rs.getInt("ID_Manager"));
                user.setIdRole(rs.getInt("ID_Role"));
                user.setDepartementNom(rs.getString("DepartementNom"));
                user.setRoleNom(rs.getString("RoleNom"));
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public List<User> SortRole() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.*, d.nom AS DepartementNom, r.nom AS RoleNom, ur.ID_Role FROM `user` u " +
                "LEFT JOIN `departement` d ON u.ID_Departement = d.ID_Departement " +
                "LEFT JOIN `user_role` ur ON u.ID_User = ur.ID_User " +
                "LEFT JOIN `role` r ON ur.ID_Role = r.ID_Role " +
                "ORDER BY ur.ID_Role";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setMdp(rs.getString("MDP"));
                user.setImage(rs.getString("Image"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                user.setIdManager(rs.getInt("ID_Manager"));
                user.setIdRole(rs.getInt("ID_Role"));
                user.setDepartementNom(rs.getString("DepartementNom"));
                user.setRoleNom(rs.getString("RoleNom"));
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public List<User> search(String query) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.*, d.nom AS DepartementNom, ur.ID_Role, r.nom AS RoleNom " +
                "FROM user u " +
                "LEFT JOIN departement d ON u.ID_Departement = d.ID_Departement " +
                "LEFT JOIN user_role ur ON u.ID_User = ur.ID_User " +
                "LEFT JOIN role r ON ur.ID_Role = r.ID_Role " +
                "WHERE u.Nom LIKE ? OR u.Prenom LIKE ? OR u.Email LIKE ? OR d.nom LIKE ? OR r.nom LIKE ?";
        Connection cnx = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            ps = cnx.prepareStatement(sql);
            String searchQuery = "%" + query + "%";
            ps.setString(1, searchQuery);
            ps.setString(2, searchQuery);
            ps.setString(3, searchQuery);
            ps.setString(4, searchQuery);
            ps.setString(5, searchQuery);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setMdp(rs.getString("MDP"));
                user.setImage(rs.getString("Image"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                user.setIdManager(rs.getInt("ID_Manager"));
                user.setIdRole(rs.getInt("ID_Role")); // Correctly reference ID_Role
                user.setDepartementNom(rs.getString("DepartementNom"));
                user.setRoleNom(rs.getString("RoleNom"));
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cnx != null && !cnx.isClosed()) cnx.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("Search results: " + users); // Debug statement
        return users;
    }

    @Override
    public List<User> ShowUnder() {
        List<User> users = new ArrayList<>();
        int currentUserId = SessionManager.getInstance().getUser().getIdUser(); // Assuming SessionManager manages the current user's session
        String sql = "WITH RECURSIVE Subordinates AS (" +
                "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.MDP, u.Image, u.ID_Departement, u.ID_Manager, ur.ID_Role " +
                "FROM user u " +
                "LEFT JOIN user_role ur ON u.ID_User = ur.ID_User " +
                "WHERE u.ID_Manager = ? " +
                "UNION ALL " +
                "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.MDP, u.Image, u.ID_Departement, u.ID_Manager, ur.ID_Role " +
                "FROM user u " +
                "INNER JOIN Subordinates s ON s.ID_User = u.ID_Manager " +
                "LEFT JOIN user_role ur ON u.ID_User = ur.ID_User" +
                ") " +
                "SELECT s.ID_User, s.Nom, s.Prenom, s.Email, s.MDP, s.Image, s.ID_Departement, s.ID_Manager, s.ID_Role, d.nom AS DepartementNom, r.nom AS RoleNom " +
                "FROM Subordinates s " +
                "LEFT JOIN departement d ON s.ID_Departement = d.ID_Departement " +
                "LEFT JOIN role r ON s.ID_Role = r.ID_Role " +
                "WHERE s.ID_User != ?";

        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, currentUserId);
            ps.setInt(2, currentUserId); // Exclude current user
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setMdp(rs.getString("MDP"));
                user.setImage(rs.getString("Image"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                user.setIdManager(rs.getInt("ID_Manager"));
                user.setIdRole(rs.getInt("ID_Role"));
                user.setDepartementNom(rs.getString("DepartementNom"));
                user.setRoleNom(rs.getString("RoleNom"));
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public List<User> RechercheUnder(String input) {
        List<User> users = new ArrayList<>();
        int currentUserId = SessionManager.getInstance().getUser().getIdUser(); // Assuming SessionManager manages the current user's session
        String sql = "WITH RECURSIVE Subordinates AS (" +
                "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.MDP, u.Image, u.ID_Departement, u.ID_Manager, ur.ID_Role " +
                "FROM user u " +
                "LEFT JOIN user_role ur ON u.ID_User = ur.ID_User " +
                "WHERE u.ID_Manager = ? " +
                "UNION ALL " +
                "SELECT u.ID_User, u.Nom, u.Prenom, u.Email, u.MDP, u.Image, u.ID_Departement, u.ID_Manager, ur.ID_Role " +
                "FROM user u " +
                "INNER JOIN Subordinates s ON s.ID_User = u.ID_Manager " +
                "LEFT JOIN user_role ur ON u.ID_User = ur.ID_User" +
                ") " +
                "SELECT s.ID_User, s.Nom, s.Prenom, s.Email, s.MDP, s.Image, s.ID_Departement, s.ID_Manager, s.ID_Role, d.nom AS DepartementNom, r.nom AS RoleNom " +
                "FROM Subordinates s " +
                "LEFT JOIN departement d ON s.ID_Departement = d.ID_Departement " +
                "LEFT JOIN role r ON s.ID_Role = r.ID_Role " +
                "WHERE s.ID_User != ? " +
                "AND (s.Nom LIKE ? OR s.Prenom LIKE ? OR s.Email LIKE ?)";

        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, currentUserId);
            ps.setInt(2, currentUserId); // Exclude current user
            String searchInput = "%" + input + "%";
            ps.setString(3, searchInput);
            ps.setString(4, searchInput);
            ps.setString(5, searchInput);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setMdp(rs.getString("MDP"));
                user.setImage(rs.getString("Image"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                user.setIdManager(rs.getInt("ID_Manager"));
                user.setIdRole(rs.getInt("ID_Role"));
                user.setDepartementNom(rs.getString("DepartementNom"));
                user.setRoleNom(rs.getString("RoleNom"));
                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.*, d.nom AS departmentName, r.nom AS roleName, m.Nom AS managerName, m.Prenom AS managerPrenom " +
                "FROM user u " +
                "LEFT JOIN departement d ON u.ID_Departement = d.ID_Departement " +
                "LEFT JOIN user_role ur ON u.ID_User = ur.ID_User " +
                "LEFT JOIN role r ON ur.ID_Role = r.ID_Role " +
                "LEFT JOIN user m ON u.ID_Manager = m.ID_User";
        try {
            if (cnx == null || cnx.isClosed()) {
                cnx = MyDataBase.getInstance().getCnx();
            }
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User(
                        rs.getInt("ID_User"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Email"),
                        rs.getString("MDP"),
                        rs.getString("Image"),
                        rs.getInt("ID_Departement"),
                        rs.getInt("ID_Manager")
                );
                user.setDepartementNom(rs.getString("departmentName"));
                user.setRoleNom(rs.getString("roleName"));
                user.setManagerName(rs.getString("managerName") + " " + rs.getString("managerPrenom"));
                user.setIdSolde(rs.getInt("idSolde"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public List<User> getAllUsersInfo() {
        return null;
    }

    @Override
    public void addUser(String nom, String prenom, String email, String mdp, String image, double soldeAnnuel, int soldeMaladie, int soldeExceptionnel, int soldeMaternite, int idDepartement, int idRole) {

    }

    @Override
    public void deleteUser(int idUser) {

    }

    @Override
    public void assignUserToDepartmentAndRole(int idUser, int idDepartement, int idRole) {

    }

    @Override
    public void assignRoleToUser(int userId, int roleId) {

    }

    private List<User> getAllUsersWithDetails() {
        List<User> users = new ArrayList<>();
        try {
            // Set up the connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bfpmeconge", "root", "password");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query
            ResultSet resultSet = statement.executeQuery(
                    "SELECT u.*, d.nom as departementNom, m.nom as managerName " +
                            "FROM user u " +
                            "LEFT JOIN departement d ON u.idDepartement = d.idDepartement " +
                            "LEFT JOIN user m ON u.idManager = m.idUser"
            );

            // Process the result set
            while (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt("idUser"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setEmail(resultSet.getString("email"));
                user.setMdp(resultSet.getString("mdp"));
                user.setImage(resultSet.getString("image"));
                user.setCreationDate(resultSet.getDate("creationDate").toLocalDate());
                user.setIdManager(resultSet.getInt("idManager"));
                user.setIdDepartement(resultSet.getInt("idDepartement"));
                user.setIdRole(resultSet.getInt("idRole"));
                user.setDepartementNom(resultSet.getString("departementNom"));
                user.setManagerName(resultSet.getString("managerName"));

                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
    public void updateUserRoleAndDepartment(int userId, int roleId, int departmentId) throws SQLException {
        if (cnx == null || cnx.isClosed()) {
            cnx = MyDataBase.getInstance().getCnx();
        }
        String updateDepartmentQuery = "UPDATE user SET ID_Departement = ? WHERE ID_User = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(updateDepartmentQuery)) {
            stmt.setInt(1, departmentId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }

        // Check if the user already has a role
        String checkRoleQuery = "SELECT COUNT(*) FROM user_role WHERE ID_User = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(checkRoleQuery)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Update the existing role
                String updateRoleQuery = "UPDATE user_role SET ID_Role = ? WHERE ID_User = ?";
                try (PreparedStatement updateStmt = cnx.prepareStatement(updateRoleQuery)) {
                    updateStmt.setInt(1, roleId);
                    updateStmt.setInt(2, userId);
                    updateStmt.executeUpdate();
                }
            } else {
                // Insert the new role
                String insertRoleQuery = "INSERT INTO user_role (ID_User, ID_Role) VALUES (?, ?)";
                try (PreparedStatement insertStmt = cnx.prepareStatement(insertRoleQuery)) {
                    insertStmt.setInt(1, userId);
                    insertStmt.setInt(2, roleId);
                    insertStmt.executeUpdate();
                }
            }
        }
    }

    @Override
    public void updateUserRole(int userId, int roleId) {

    }

    @Override
    public void updateUserDepartment(int userId, int departmentId) {

    }

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public void Add(User user) {

    }

    @Override
    public void Update(User user) {

    }

    @Override
    public List<User> Show() {
        return null;
    }

    @Override
    public void Delete(User user) throws SQLException {

    }

    private void showErrorToUser(String message) {
        // Implementation to show error message to the user
        // For example, in JavaFX you might use:
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Integer findManagerInDepartmentHierarchy(int userId, int deptId, List<Integer> parentRoleIds) throws SQLException {
        // Check if there's a manager in the same department
        Integer managerId = findManagerInDepartment(userId, deptId, parentRoleIds);
        if (managerId != null) {
            return managerId;
        }

        // If not found, check the parent department recursively
        String parentDeptQuery = "SELECT Parent_Dept FROM departement WHERE ID_Departement = ?";
        PreparedStatement parentDeptStmt = cnx.prepareStatement(parentDeptQuery);
        parentDeptStmt.setInt(1, deptId);
        ResultSet parentDeptRs = parentDeptStmt.executeQuery();

        if (parentDeptRs.next()) {
            int parentDeptId = parentDeptRs.getInt("Parent_Dept");
            if (parentDeptId != 0) {
                return findManagerInDepartmentHierarchy(userId, parentDeptId, parentRoleIds);
            }
        }

        return null;
    }

    private Integer findManagerInDepartment(int userId, int deptId, List<Integer> parentRoleIds) throws SQLException {
        for (int parentRoleId : parentRoleIds) {
            String managerQuery = "SELECT u.ID_User FROM user u JOIN user_role ur ON u.ID_User = ur.ID_User WHERE u.ID_Departement = ? AND ur.ID_Role = ? AND u.ID_User != ? LIMIT 1";
            PreparedStatement managerStmt = cnx.prepareStatement(managerQuery);
            managerStmt.setInt(1, deptId);
            managerStmt.setInt(2, parentRoleId);
            managerStmt.setInt(3, userId);
            ResultSet managerRs = managerStmt.executeQuery();

            if (managerRs.next()) {
                return managerRs.getInt("ID_User");
            }
        }
        return null;
    }

    public void checkRoleDepartmentUniqueness(int idUser, int idRole, int idDepartement) {

    }
}






