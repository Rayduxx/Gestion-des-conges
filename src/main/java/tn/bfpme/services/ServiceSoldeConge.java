package tn.bfpme.services;
/*
import tn.bfpme.models.TypeConge;
import tn.bfpme.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceSoldeConge {

    private final Connection cnx = MyDataBase.getInstance().getCnx();

    public void updateSoldeConge(TypeConge typeConge, int id) {
        try (Connection connection = MyDataBase.getInstance().getCnx()) {
            String query = "UPDATE soldeconge SET Designation = ?, Type = ?, Pas = ?, Periode = ? WHERE idSolde = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, typeConge.getDesignation());
            preparedStatement.setString(2, typeConge.getType());
            preparedStatement.setDouble(3, typeConge.getPas());
            preparedStatement.setInt(4, typeConge.getPeriode());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TypeConge getSoldeConge(int id) {
        TypeConge typeConge = null;
        try (Connection connection = MyDataBase.getInstance().getCnx()) {
            String query = "SELECT Designation, Type, Pas, Periode FROM soldeconge WHERE idSolde = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                typeConge = new TypeConge();
                typeConge.setDesignation(resultSet.getString("Designation"));
                typeConge.setType(resultSet.getString("Type"));
                typeConge.setPas(resultSet.getInt("Pas"));
                typeConge.setPeriode(resultSet.getInt("Periode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeConge;
    }

    public List<TypeConge> getAllSoldeConges() {
        List<TypeConge> typeConges = new ArrayList<>();
        String query = "SELECT idSolde, Designation, Type, Pas, Periode FROM soldeconge";
        try (Connection connection = MyDataBase.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                TypeConge typeConge = new TypeConge();
                typeConge.setIdSolde(resultSet.getInt("idSolde"));
                typeConge.setDesignation(resultSet.getString("Designation"));
                typeConge.setType(resultSet.getString("Type"));
                typeConge.setPas(resultSet.getInt("Pas"));
                typeConge.setPeriode(resultSet.getInt("Periode"));
                typeConges.add(typeConge);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeConges;
    }

    public List<TypeConge> searchSoldeConges(String searchText) {
        List<TypeConge> typeConges = new ArrayList<>();
        String query = "SELECT idSolde, Designation, Type, Pas, Periode FROM soldeconge WHERE LOWER(Designation) LIKE ? OR LOWER(Type) LIKE ?";
        try (Connection connection = MyDataBase.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            String searchPattern = "%" + searchText + "%";
            preparedStatement.setString(1, searchPattern);
            preparedStatement.setString(2, searchPattern);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TypeConge typeConge = new TypeConge();
                typeConge.setIdSolde(resultSet.getInt("idSolde"));
                typeConge.setDesignation(resultSet.getString("Designation"));
                typeConge.setType(resultSet.getString("Type"));
                typeConge.setPas(resultSet.getInt("Pas"));
                typeConge.setPeriode(resultSet.getInt("Periode"));
                typeConges.add(typeConge);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeConges;
    }

    public void addSoldeConge(String designation, String type, double pas, int periode) {
        String query = "INSERT INTO soldeconge (Designation, Type, Pas, Periode) VALUES (?, ?, ?, ?)";
        try (Connection connection = MyDataBase.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, designation);
            preparedStatement.setString(2, type);
            preparedStatement.setDouble(3, pas);
            preparedStatement.setInt(4, periode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSoldeConge(int idSolde, String designation, String type, double pas, int periode) {
        String query = "UPDATE soldeconge SET Designation = ?, Type = ?, Pas = ?, Periode = ? WHERE idSolde = ?";
        try (Connection connection = MyDataBase.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, designation);
            preparedStatement.setString(2, type);
            preparedStatement.setDouble(3, pas);
            preparedStatement.setInt(4, periode);
            preparedStatement.setInt(5, idSolde);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSoldeConge(int idSolde) {
        String query = "DELETE FROM soldeconge WHERE idSolde = ?";
        try (Connection connection = MyDataBase.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idSolde);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSoldeCongeIdByDesignation(String designation) {
        int idSolde = -1;
        String query = "SELECT idSolde FROM soldeconge WHERE Designation = ?";
        try (Connection connection = MyDataBase.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, designation);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idSolde = resultSet.getInt("idSolde");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idSolde;
    }

    public double getPasBySoldeId(int idSolde) {
        double pas = 0;
        String query = "SELECT Pas FROM soldeconge WHERE idSolde = ?";
        try (Connection connection = MyDataBase.getInstance().getCnx();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idSolde);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                pas = resultSet.getDouble("Pas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pas;
    }
}*/
