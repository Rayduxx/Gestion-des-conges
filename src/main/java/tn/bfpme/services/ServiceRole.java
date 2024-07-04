package tn.bfpme.services;

import tn.bfpme.models.Departement;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Role;
import tn.bfpme.models.RoleHierarchie;
import tn.bfpme.models.UserRole;
import tn.bfpme.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRole {

    public static Role getRoleById(int id) {
        Role role = null;
        String query = "SELECT * FROM role WHERE ID_Role = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
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

    public static Role getRoleByUserId(int idUser) {
        Role role = null;
        String query = "SELECT r.* FROM role r JOIN user_role ur ON r.ID_Role = ur.ID_Role WHERE ur.ID_User = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
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
        String query = "SELECT r.* FROM rolehierarchie rh JOIN role r ON rh.ID_RoleP = r.ID_Role WHERE rh.ID_RoleC = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, idRole);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                parentRoles.add(new Role(
                        rs.getInt("ID_Role"),
                        rs.getString("nom"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parentRoles;
    }

    public static List<Role> getChildRoles(int idRole) {
        List<Role> childRoles = new ArrayList<>();
        String query = "SELECT r.* FROM rolehierarchie rh JOIN role r ON rh.ID_RoleC = r.ID_Role WHERE rh.ID_RoleP = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, idRole);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                childRoles.add(new Role(
                        rs.getInt("ID_Role"),
                        rs.getString("nom"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return childRoles;
    }

    public static List<Integer> getParentRoleIds(int idRole) {
        List<Integer> parentRoleIds = new ArrayList<>();
        String query = "SELECT ID_RoleP FROM rolehierarchie WHERE ID_RoleC = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, idRole);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                parentRoleIds.add(rs.getInt("ID_RoleP"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parentRoleIds;
    }

    public static Role getRoleByName(String name) {
        Role role = null;
        String query = "SELECT * FROM role WHERE nom = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
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

    public static Role getRoleParents(int idRole) {
        Role parentRole = null;
        String sql = "SELECT r2.* FROM role r1 " +
                "JOIN rolehierarchie rh ON r1.ID_Role = rh.ID_RoleC " +
                "JOIN role r2 ON rh.ID_RoleP = r2.ID_Role " +
                "WHERE r1.ID_Role = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, idRole);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                parentRole = new Role(
                        rs.getInt("ID_Role"),
                        rs.getString("nom"),
                        rs.getString("description")
                        // Add other fields if necessary
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parentRole;
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

    public List<RoleHierarchie> getAllRoleHierarchies() {
        List<RoleHierarchie> roleHierarchies = new ArrayList<>();
        String query = "SELECT rh.ID_RoleH, rh.ID_RoleP, rh.ID_RoleC, rp.nom AS parentRoleName, rc.nom AS childRoleName " +
                "FROM rolehierarchie rh " +
                "JOIN role rp ON rh.ID_RoleP = rp.ID_Role " +
                "JOIN role rc ON rh.ID_RoleC = rc.ID_Role";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             Statement stmt = cnx.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                RoleHierarchie roleHierarchie = new RoleHierarchie(
                        rs.getInt("ID_RoleH"),
                        rs.getInt("ID_RoleP"),
                        rs.getInt("ID_RoleC")
                );
                roleHierarchie.setParentRoleName(rs.getString("parentRoleName"));
                roleHierarchie.setChildRoleName(rs.getString("childRoleName"));
                roleHierarchies.add(roleHierarchie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleHierarchies;
    }
    public void addRoleHierarchy(Role idP, Role idC) {
        String query = "INSERT INTO `rolehierarchie`(`ID_RoleP`, `ID_RoleC`) VALUES (?,?)";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setInt(1, idP.getIdRole());
            pstmt.setInt(2, idC.getIdRole());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateRoleHierarchy(int idRole, Role parent, Role child) {
        String query = "UPDATE `rolehierarchie` SET `ID_RoleP`=?,`ID_RoleC`=? WHERE `ID_RoleH`=?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setInt(1, parent.getIdRole());
            pstmt.setInt(2, child.getIdRole());
            pstmt.setInt(3, idRole);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteRoleHierarchy(int idRole) {
        String query = "DELETE FROM rolehierarchie WHERE ID_RoleH = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setInt(1, idRole);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
