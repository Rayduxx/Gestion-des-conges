package tn.bfpme.services;

import tn.bfpme.interfaces.IConge;
import tn.bfpme.models.Conge;
import tn.bfpme.models.Statut;
import tn.bfpme.models.TypeConge;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;


public class ServiceConge implements IConge<Conge> {
    private final Connection cnx;

    public ServiceConge() {
        cnx = MyDataBase.getInstance().getCnx();
    }
    @Override
    public List<Conge> afficher() {
        List<Conge> conges = new ArrayList<>();
        String sql = "SELECT ID_Conge, DateDebut, DateFin, TypeConge, Statut, ID_User, file, description FROM conge WHERE ID_User LIKE '%" + SessionManager.getInstance().getUser().getIdUser() + "%'";
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
        String qry = "INSERT INTO `conge`(`DateDebut`, `DateFin`, `TypeConge`, `Statut`, `ID_User`, `file`, `description`) VALUES (?,?,?,?,?,?,?)";
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
    public void updateStatutConge(int id, Statut statut) {
        try {
            String qry = "UPDATE `conge` SET `Statut`=? WHERE `ID_Conge`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, String.valueOf(statut));
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void updateSoldeAnnuel(int id, int solde) {
        try {
            String qry = "UPDATE `utilisateur` SET `Solde_Annuel`=? WHERE `ID_User`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, solde);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void updateSoldeMaladie(int id, int solde) {
        try {
            String qry = "UPDATE `utilisateur` SET `Solde_Maladie`=? WHERE `ID_User`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, solde);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void updateSoldeExceptionnel(int id, int solde) {
        try {
            String qry = "UPDATE `utilisateur` SET `Solde_Exceptionnel`=? WHERE `ID_User`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, solde);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void updateSoldeMaternité(int id, int solde) {
        try {
            String qry = "UPDATE `utilisateur` SET `Solde_Maternité`=? WHERE `ID_User`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, solde);
            stm.setInt(2, id);
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
    @Override
    public List<Conge> TriparStatut() {
        List<Conge> conges = new ArrayList<>();
        String sql = "SELECT `ID_Conge`, `DateDebut`, `DateFin`, `TypeConge`, `Statut`, `ID_User`, `file`, `description` FROM `conge` WHERE `ID_User` LIKE '%" + SessionManager.getInstance().getUser().getIdUser() + "%' ORDER BY `Statut`";
        try {

            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
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
    public List<Conge> TriparType() {
        List<Conge> conges = new ArrayList<>();
        String sql = "SELECT `ID_Conge`, `DateDebut`, `DateFin`, `TypeConge`, `Statut`, `ID_User`, `file`, `description` FROM `conge` WHERE `ID_User` LIKE '%" + SessionManager.getInstance().getUser().getIdUser() + "%' ORDER BY `TypeConge`";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
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
    public List<Conge> TriparDateD() {
        List<Conge> conges = new ArrayList<>();
        String sql = "SELECT `ID_Conge`, `DateDebut`, `DateFin`, `TypeConge`, `Statut`, `ID_User`, `file`, `description` FROM `conge` WHERE `ID_User` LIKE '%" + SessionManager.getInstance().getUser().getIdUser() + "%'ORDER BY `DateDebut`";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
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
    public List<Conge> TriparDateF() {
        List<Conge> conges = new ArrayList<>();
        String sql = "SELECT `ID_Conge`, `DateDebut`, `DateFin`, `TypeConge`, `Statut`, `ID_User`, `file`, `description` FROM `conge` WHERE `ID_User` LIKE '%" + SessionManager.getInstance().getUser().getIdUser() + "%' ORDER BY `DateFin`";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
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
    public List<Conge> TriparDesc() {
        List<Conge> conges = new ArrayList<>();
        String sql = "SELECT `ID_Conge`, `DateDebut`, `DateFin`, `TypeConge`, `Statut`, `ID_User`, `file`, `description` FROM `conge` WHERE `ID_User` LIKE '%" + SessionManager.getInstance().getUser().getIdUser() + "%' ORDER BY `description`";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
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
    public List<Conge> Rechreche(String recherche) {
        List<Conge> conges = new ArrayList<>();
        String sql = "SELECT `ID_Conge`, `DateDebut`, `DateFin`, `TypeConge`, `Statut`, `ID_User`, `file`, `description` " +
                "FROM `conge` " +
                "WHERE `ID_User` LIKE ? " +
                "AND (`TypeConge` LIKE ? " +
                "OR `Statut` LIKE ? " +
                "OR `DateDebut` LIKE ? " +
                "OR `DateFin` LIKE ? " +
                "OR `description` LIKE ?)";


        try (PreparedStatement ste = cnx.prepareStatement(sql)) {
            String searchPattern = "%" + recherche + "%";
            ste.setString(1, "%" + SessionManager.getInstance().getUser().getIdUser() + "%");
            ste.setString(2, searchPattern);
            ste.setString(3, searchPattern);
            ste.setString(4, searchPattern);
            ste.setString(5, searchPattern);
            ste.setString(6, searchPattern);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
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
    public void updateNotificationText(int id, String text) {
        try {
            String qry = "UPDATE `conge` SET `Notification`=? WHERE `ID_Conge`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, text);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Conge> AfficherNotifications() {
        List<Conge> conges = new ArrayList<>();
        String sql = "SELECT ID_Conge, DateDebut, DateFin, TypeConge, Statut, ID_User, file, description, Notification " +
                "FROM conge " +
                "WHERE ID_User LIKE '%" + SessionManager.getInstance().getUser().getIdUser() + "%' " +
                "AND Notification IS NOT NULL " +
                "AND Notification <> '' " +
                "AND Statut <> 'En_Attente'";
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
                conge.setNotification(rs.getString("Notification"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conges;
    }
    public void DeleteAllUserNotif() {
        try {
            String qry = "UPDATE `conge` SET `Notification`=? WHERE `ID_User`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, "");
            stm.setInt(2, SessionManager.getInstance().getUser().getIdUser());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void NewNotification(String NotfiText, int idUser,int idConge) {
        try {
            String qry = "UPDATE `conge` SET `Notification`=? WHERE `ID_User`=? AND `ID_Conge`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, NotfiText);
            stm.setInt(2, idUser);
            stm.setInt(3, idConge);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
