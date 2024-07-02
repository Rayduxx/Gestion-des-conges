package tn.bfpme.services;

import tn.bfpme.models.Departement;
import tn.bfpme.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDepartement {

    public static Departement getDepartmentById(int idDepartement) {
        Departement departement = null;
        String sql = "SELECT * FROM departement WHERE ID_Departement = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setInt(1, idDepartement);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                departement = new Departement(
                        rs.getInt("ID_Departement"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getInt("Parent_Dept")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departement;
    }
    public static Departement getDepartmentByName(String name) {
        Departement departement = null;
        String sql = "SELECT * FROM departement WHERE nom = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement stmt = cnx.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                departement = new Departement(
                        rs.getInt("ID_Departement"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getInt("Parent_Dept")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departement;
    }
    public static Departement getParentDepartment(int idDepartement) {
        Departement departement = getDepartmentById(idDepartement);
        if (departement != null && departement.getParentDept() != 0) {
            return getDepartmentById(departement.getParentDept());
        }
        return null;
    }

    public List<Departement> getAllDepartments() {
        List<Departement> departments = new ArrayList<>();
        String query = "SELECT * FROM departement";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             Statement stmt = cnx.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Departement dept = new Departement(
                        rs.getInt("ID_Departement"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getInt("Parent_Dept")
                );
                departments.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public void addDepartement(String name, String description, Integer parentDeptId) {
        String query = "INSERT INTO departement (nom, description, Parent_Dept) VALUES (?, ?, ?)";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            if (parentDeptId != null) {
                pstmt.setInt(3, parentDeptId);
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDepartment(int id, String name, String description, Integer parentDeptId) {
        String query = "UPDATE departement SET nom = ?, description = ?, Parent_Dept = ? WHERE ID_Departement = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            if (parentDeptId != null) {
                pstmt.setInt(3, parentDeptId);
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(int id) {
        String query = "DELETE FROM departement WHERE ID_Departement = ?";
        try (Connection cnx = MyDataBase.getInstance().getCnx();
             PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
