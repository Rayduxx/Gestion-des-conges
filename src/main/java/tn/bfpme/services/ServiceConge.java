package tn.bfpme.services;

import tn.bfpme.models.SoldeConge;
import tn.bfpme.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceConge {
    private Connection conn;

    public ServiceConge() {
        this.conn = MyDataBase.getInstance().getCnx();
    }

    public List<SoldeConge> getAllSoldeConges() {
        List<SoldeConge> soldeConges = new ArrayList<>();
        String query = "SELECT * FROM soldeconge";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SoldeConge soldeConge = new SoldeConge(
                        rs.getInt("idSolde"),
                        rs.getString("Designation"),
                        rs.getString("Type"),
                        rs.getDouble("Pas"),
                        rs.getInt("Periode")
                );
                soldeConges.add(soldeConge);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soldeConges;
    }

    public List<SoldeConge> searchSoldeConges(String searchText) {
        List<SoldeConge> soldeConges = new ArrayList<>();
        String query = "SELECT * FROM soldeconge WHERE Designation LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + searchText + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SoldeConge soldeConge = new SoldeConge(
                            rs.getInt("idSolde"),
                            rs.getString("Designation"),
                            rs.getString("Type"),
                            rs.getDouble("Pas"),
                            rs.getInt("Periode")
                    );
                    soldeConges.add(soldeConge);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soldeConges;
    }

    public void addSoldeConge(String designation, String type, double pas, int periode) {
        String query = "INSERT INTO soldeconge (Designation, Type, Pas, Periode) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, designation);
            stmt.setString(2, type);
            stmt.setDouble(3, pas);
            stmt.setInt(4, periode);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSoldeConge(int idSolde, String designation, String type, double pas, int periode) {
        String query = "UPDATE soldeconge SET Designation = ?, Type = ?, Pas = ?, Periode = ? WHERE idSolde = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, designation);
            stmt.setString(2, type);
            stmt.setDouble(3, pas);
            stmt.setInt(4, periode);
            stmt.setInt(5, idSolde);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSoldeConge(int idSolde) {
        String query = "DELETE FROM soldeconge WHERE idSolde = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idSolde);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSoldeCongeIdByDesignation(String designation) {
        String query = "SELECT idSolde FROM soldeconge WHERE Designation = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, designation);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idSolde");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public double getPasBySoldeId(int idSolde) {
        String query = "SELECT Pas FROM soldeconge WHERE idSolde = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idSolde);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("Pas");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
