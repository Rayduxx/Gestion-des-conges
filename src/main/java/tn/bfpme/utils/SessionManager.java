package tn.bfpme.utils;

import tn.bfpme.models.Departement;
import tn.bfpme.models.Role;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceDepartement;
import tn.bfpme.services.ServiceRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SessionManager {
    private static SessionManager instance;
    private User user;

    private SessionManager(User user) {
        this.user = user;
    }

    public static SessionManager getInstance(User user) {
        if (instance == null) {
            instance = new SessionManager(user);
        }
        return instance;
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SessionManager is not initialized. Call getInstance(User) first.");
        }
        return instance;
    }

    public void updateSession(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public static void cleanUserSession() {
        instance = null;
    }

    // Methods to retrieve department and role information
    public Departement getUserDepartment() {
        return ServiceDepartement.getDepartmentById(user.getIdDepartement());
    }

    public String getUserDepartmentName() {
        Departement departement = getUserDepartment();
        return departement != null ? departement.getNom() : null;
    }

    public Departement getParentDepartment() {
        return ServiceDepartement.getParentDepartment(user.getIdDepartement());
    }

    public Role getUserRole() {
        return ServiceRole.getRoleById(user.getIdRole());
    }

    public String getUserRoleName() {
        Role role = getUserRole();
        return role != null ? role.getNom() : null;
    }

    public List<Role> getParentRoles() {
        return ServiceRole.getParentRoles(user.getIdRole());
    }

    public List<Role> getChildRoles() {
        return ServiceRole.getChildRoles(user.getIdRole());
    }

    // New method to get the user's chef by role name
    public User getUserChef() {
        List<Integer> parentRoleIds = ServiceRole.getParentRoleIds(user.getIdRole());
        if (parentRoleIds.isEmpty()) {
            return null;
        }

        String sql = "SELECT * FROM user u JOIN user_role ur ON u.ID_User = ur.ID_User WHERE u.ID_Departement = ? AND ur.ID_Role IN (";
        for (int i = 0; i < parentRoleIds.size(); i++) {
            sql += parentRoleIds.get(i);
            if (i < parentRoleIds.size() - 1) {
                sql += ", ";
            }
        }
        sql += ") LIMIT 1";

        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, user.getIdDepartement());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User chef = new User();
                chef.setIdUser(rs.getInt("ID_User"));
                chef.setNom(rs.getString("Nom"));
                chef.setPrenom(rs.getString("Prenom"));
                chef.setEmail(rs.getString("Email"));
                chef.setMdp(rs.getString("MDP"));
                chef.setImage(rs.getString("Image"));
                chef.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                chef.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                chef.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                chef.setSoldeMaternite(rs.getInt("Solde_Maternite"));
                chef.setIdDepartement(rs.getInt("ID_Departement"));
                chef.setIdRole(rs.getInt("ID_Role"));
                return chef;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // New method to get the user's chef by department name and role name
    public User getUserChefByDeptAndRole(String deptName, String roleName) {
        Departement departement = ServiceDepartement.getDepartmentByName(deptName);
        Role role = ServiceRole.getRoleByName(roleName);
        if (departement == null || role == null) {
            return null;
        }

        List<Integer> parentRoleIds = ServiceRole.getParentRoleIds(role.getIdRole());
        if (parentRoleIds.isEmpty()) {
            return null;
        }

        String sql = "SELECT * FROM user u JOIN user_role ur ON u.ID_User = ur.ID_User WHERE u.ID_Departement = ? AND ur.ID_Role IN (";
        for (int i = 0; i < parentRoleIds.size(); i++) {
            sql += parentRoleIds.get(i);
            if (i < parentRoleIds.size() - 1) {
                sql += ", ";
            }
        }
        sql += ") LIMIT 1";

        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, departement.getIdDepartement());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User chef = new User();
                chef.setIdUser(rs.getInt("ID_User"));
                chef.setNom(rs.getString("Nom"));
                chef.setPrenom(rs.getString("Prenom"));
                chef.setEmail(rs.getString("Email"));
                chef.setMdp(rs.getString("MDP"));
                chef.setImage(rs.getString("Image"));
                chef.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                chef.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                chef.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                chef.setSoldeMaternite(rs.getInt("Solde_Maternite"));
                chef.setIdDepartement(rs.getInt("ID_Departement"));
                chef.setIdRole(rs.getInt("ID_Role"));
                return chef;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
