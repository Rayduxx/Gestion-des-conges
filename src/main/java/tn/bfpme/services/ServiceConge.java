package tn.bfpme.services;

import tn.bfpme.models.Conge;
import tn.bfpme.models.Statut;
import tn.bfpme.models.TypeConge;
import tn.bfpme.utils.MyDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ServiceConge {
    private final Connection cnx;

    public ServiceConge() {
        cnx = MyDataBase.getInstance().getCnx();
    }
    public List<Conge> afficher() {
        List<Conge> conges = new ArrayList<>();
        String sql = "SELECT `ID_Conge`, `DateDebut`, `DateFin`, `TypeConge`, `Statut`, `ID_User`, `file`, `description` FROM `conge`";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge").toUpperCase())); // Ensure the enum value is in the correct format
                conge.setStatut(Statut.valueOf(rs.getString("Statut").toUpperCase())); // Ensure the enum value is in the correct format
                conge.setIdUser(rs.getInt("ID_User"));
                conge.setFile(rs.getString("file"));
                conge.setDescription(rs.getString("description"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conges;
    }
}
