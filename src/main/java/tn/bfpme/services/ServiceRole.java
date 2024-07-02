package tn.bfpme.services;
import tn.bfpme.models.Role;
import tn.bfpme.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
