package tn.bfpme.services;

import tn.bfpme.utils.MyDataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tn.bfpme.interfaces.IEmploye;

public class ServiceEmploye implements IEmploye{
    private final Connection cnx;

    public ServiceEmploye() {
        cnx = MyDataBase.getInstance().getCnx();
    }
    @Override
    public void deleteByID(int id) {
        try {
            String qry = "DELETE FROM `utilisateur` WHERE `ID_User`=?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            stm.executeUpdate();
            System.out.println("Suppression Effectuée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
