package tn.bfpme.services;
import tn.bfpme.models.Departement;
import tn.bfpme.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



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


}
