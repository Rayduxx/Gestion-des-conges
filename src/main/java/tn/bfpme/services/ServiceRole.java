package tn.bfpme.services;

import tn.bfpme.models.Role;
import tn.bfpme.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRole {

    public static Role getRoleById(int idRole) {
        Role role = null;
        String sql = "SELECT * FROM role WHERE ID_Role = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, idRole);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                role = new Role(
                        rs.getInt("ID_Role"),
                        rs.getString("nom"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public static Role getRoleByName(String name) {
        Role role = null;
        String sql = "SELECT * FROM role WHERE nom = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                role = new Role(
                        rs.getInt("ID_Role"),
                        rs.getString("nom"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public static List<Role> getParentRoles(int idRole) {
        List<Role> parentRoles = new ArrayList<>();
        String sql = "SELECT * FROM rolehierarchie WHERE ID_RoleC = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, idRole);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Role parentRole = getRoleById(rs.getInt("ID_RoleP"));
                if (parentRole != null) {
                    parentRoles.add(parentRole);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parentRoles;
    }

    public static List<Role> getChildRoles(int idRole) {
        List<Role> childRoles = new ArrayList<>();
        String sql = "SELECT * FROM rolehierarchie WHERE ID_RoleP = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, idRole);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Role childRole = getRoleById(rs.getInt("ID_RoleC"));
                if (childRole != null) {
                    childRoles.add(childRole);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return childRoles;
    }

    public static List<Integer> getParentRoleIds(int idRole) {
        List<Integer> parentRoleIds = new ArrayList<>();
        String sql = "SELECT ID_RoleP FROM rolehierarchie WHERE ID_RoleC = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, idRole);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                parentRoleIds.add(rs.getInt("ID_RoleP"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parentRoleIds;
    }

    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM role";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             Statement stmt = cnx.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Role role = new Role(
                        rs.getInt("ID_Role"),
                        rs.getString("nom"),
                        rs.getString("description")
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public void addRole(String nom, String description) {
        String query = "INSERT INTO role (nom, description) VALUES (?, ?)";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRole(int idRole, String nom, String description) {
        String query = "UPDATE role SET nom = ?, description = ? WHERE ID_Role = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, description);
            pstmt.setInt(3, idRole);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRole(int idRole) {
        String query = "DELETE FROM role WHERE ID_Role = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setInt(1, idRole);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
