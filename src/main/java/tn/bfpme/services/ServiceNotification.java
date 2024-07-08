package tn.bfpme.services;

import tn.bfpme.interfaces.INotification;
import tn.bfpme.models.Notification;
import tn.bfpme.models.Statut;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceNotification implements INotification {
    private final Connection cnx;

    public ServiceNotification() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    public List<Notification> AfficherNotifUser() {
        List<Notification> notifs = new ArrayList<>();
        String query = "SELECT ID_Notif, ID_User, NotfiMessage, Statut FROM `notification` WHERE `ID_User` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, SessionManager.getInstance().getUser().getIdUser());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notification notif = new Notification(
                        rs.getInt("ID_User"),
                        rs.getInt("ID_Notif"),
                        rs.getString("NotfiMessage"),
                        Statut.valueOf(rs.getString("Statut"))
                );
                notifs.add(notif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifs;
    }


    public void DeleteAllUserNotif() {
        try {
            String qry = "DELETE FROM `notification` WHERE `ID_User`= ?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, SessionManager.getInstance().getUser().getIdUser());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void DeleteNotif(int id) {
        try {
            String qry = "DELETE FROM `notification` WHERE `ID_Notif`= ?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
