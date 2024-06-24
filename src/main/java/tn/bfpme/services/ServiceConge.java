package tn.bfpme.services;

import tn.bfpme.interfaces.IConge;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Statut;
import tn.bfpme.models.TypeConge;
import tn.bfpme.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceConge implements IConge<Conge> {
    private final Connection cnx;

    public ServiceConge() {
        cnx = MyDataBase.getInstance().getCnx();
    }
    @Override
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
                try {
                    conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                } catch (IllegalArgumentException e) {
                    System.out.println("Unknown TypeConge value: " + rs.getString("TypeConge"));
                    continue;
                }
                try {
                    conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                } catch (IllegalArgumentException e) {
                    System.out.println("Unknown Statut value: " + rs.getString("Statut"));
                    continue;
                }
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

    @Override
    public void Add(Conge conge) {
        String qry = "INSERT INTO `conge`(`DateDebut`, `DateFin`, `TypeCongé`, `Statut`, `ID_User`, `file`, `description`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setDate(1, Date.valueOf(conge.getDateDebut()));
            stm.setDate(2, Date.valueOf(conge.getDateFin()));
            stm.setString(3, String.valueOf(conge.getTypeConge()));
            stm.setString(4, String.valueOf(conge.getStatut()));
            stm.setInt(5, conge.getIdUser());
            stm.setString(6, conge.getFile());
            stm.setString(7, conge.getDescription());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateConge(Conge conge) {
        try {
            String qry = "UPDATE `conge` SET `DateDebut`=?, `DateFin`=?, `TypeConge`=?, `Statut`=?, `ID_User`=?, `file`=?, `description`=? WHERE `ID_Conge`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setDate(1, java.sql.Date.valueOf(conge.getDateDebut()));
            stm.setDate(2, java.sql.Date.valueOf(conge.getDateFin()));
            stm.setString(3, conge.getTypeConge().toString());
            stm.setString(4, conge.getStatut().toString());
            stm.setInt(5, conge.getIdUser());
            stm.setString(6, conge.getFile());
            stm.setString(7, conge.getDescription());
            stm.setInt(8, conge.getIdConge());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void deleteConge(Conge conge) {
        try {
            String qry = "DELETE FROM `conge` WHERE `ID_Conge`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, conge.getIdConge());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void deleteCongeByID(int id) {
        try {
            String qry = "DELETE FROM `conge` WHERE `ID_Conge`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            stm.executeUpdate();
            System.out.println("Suppression Effectuée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
