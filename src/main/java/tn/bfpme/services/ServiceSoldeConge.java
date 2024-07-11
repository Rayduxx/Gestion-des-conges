package tn.bfpme.services;

import tn.bfpme.models.SoldeConge;
import tn.bfpme.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceSoldeConge {

    private final Connection cnx = MyDataBase.getInstance().getCnx();

    public void updateSoldeConge(SoldeConge soldeConge, int id) {
        try (Connection connection = MyDataBase.getInstance().getCnx()) {
            String query = "UPDATE soldeConge SET SoldeAnn = ?, SoldeMal = ?, SoldeMat = ?, SoldeExc = ? WHERE idSolde = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, soldeConge.getSoldeAnn());
            preparedStatement.setDouble(2, soldeConge.getSoldeMal());
            preparedStatement.setDouble(3, soldeConge.getSoldeMat());
            preparedStatement.setDouble(4, soldeConge.getSoldeExc());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SoldeConge getSoldeConge(int id) {
        SoldeConge soldeConge = null;
        try (Connection connection = MyDataBase.getInstance().getCnx()) {
            String query = "SELECT SoldeAnn, SoldeMal, SoldeMat, SoldeExc FROM soldeConge WHERE idSolde = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                soldeConge = new SoldeConge();
                soldeConge.setSoldeAnn(resultSet.getDouble("SoldeAnn"));
                soldeConge.setSoldeMal(resultSet.getDouble("SoldeMal"));
                soldeConge.setSoldeMat(resultSet.getDouble("SoldeMat"));
                soldeConge.setSoldeExc(resultSet.getDouble("SoldeExc"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soldeConge;
    }
}
