package tn.bfpme.services;

import tn.bfpme.interfaces.IUtilisateur;
import tn.bfpme.models.*;
import tn.bfpme.utils.MyDataBase;
import tn.bfpme.utils.SessionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUtilisateur implements IUtilisateur {
    private final Connection cnx;

    public ServiceUtilisateur() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    public UserConge afficherusers() {
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));


                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }

    public UserConge AfficherApprove() {
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.Approuvé));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }

    public UserConge AfficherReject() {
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.Rejeté));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }

    public UserConge TriType() {
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ? ORDER BY conge.TypeConge";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }

    public UserConge TriNom() {
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ? ORDER BY user.Nom";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }

    public UserConge TriPrenom() {
            String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
            List<User> users = new ArrayList<>();
            List<Conge> conges = new ArrayList<>();
            String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                    "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                    "FROM user" +
                    "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                    " WHERE user.ID_Departement = ? AND conge.Statut = ? ORDER BY user.Prenom";
            try {
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.setString(1, departement);
                ps.setString(2, String.valueOf(Statut.En_Attente));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    user.setIdUser(rs.getInt("ID_User"));
                    user.setNom(rs.getString("Nom"));
                    user.setPrenom(rs.getString("Prenom"));
                    user.setEmail(rs.getString("Email"));
                    user.setImage(rs.getString("Image"));
                    user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                    user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                    user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                    user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                    user.setIdDepartement(rs.getInt("ID_Departement"));
                    if (!users.contains(user)) {
                        users.add(user);
                    }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }

    public UserConge TriDateDebut() {
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ? ORDER BY conge.DateDebut";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }

    public UserConge TriDateFin() {
        String departement =String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        List<User> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité, user.ID_Departement " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file, conge.notification" +
                "FROM user" +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User" +
                " WHERE user.ID_Departement = ? AND conge.Statut = ? ORDER BY conge.DateFin";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }
    /*public UserConge Recherche() {
        Departement departementEnum = SessionManager.getInstance().getDepartement();
        String departement = departementEnum.name();
        List<Utilisateur> users = new ArrayList<>();
        List<Conge> conges = new ArrayList<>();
        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, utilisateur.Solde_Annuel, utilisateur.Solde_Maladie, utilisateur.Solde_Exceptionnel, utilisateur.Solde_Maternité, " +
                "conge.ID_Conge, conge.TypeConge, conge.Statut, conge.DateFin, conge.DateDebut, conge.description, conge.file " +
                "FROM utilisateur " +
                "JOIN employe ON utilisateur.ID_User = employe.ID_User " +
                "JOIN conge ON utilisateur.ID_User = conge.ID_User"+
                " WHERE employe.Departement = ? AND conge.Statut = ? AND utilisateur.Nom ";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ps.setString(2, String.valueOf(Statut.En_Attente));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                if (!users.contains(user)) {
                    users.add(user);
                }
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("ID_Conge"));
                conge.setDateDebut(rs.getDate("DateDebut").toLocalDate());
                conge.setDateFin(rs.getDate("DateFin").toLocalDate());
                conge.setTypeConge(TypeConge.valueOf(rs.getString("TypeConge")));
                conge.setStatut(Statut.valueOf(rs.getString("Statut")));
                conge.setDescription(rs.getString("description"));
                conge.setFile(rs.getString("file"));
                conge.setIdUser(rs.getInt("ID_User"));
                conges.add(conge);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new UserConge(users, conges);
    }*/




    /*public User GetChef() {
        String departement = String.valueOf(SessionManager.getInstance().getUser().getIdDepartement());
        User chef = null;

        String query = "SELECT utilisateur.ID_User, utilisateur.Nom, utilisateur.Prenom, utilisateur.Email, utilisateur.Image, utilisateur.Solde_Annuel, utilisateur.Solde_Maladie, utilisateur.Solde_Exceptionnel, utilisateur.Solde_Maternité " +
                "FROM utilisateur " +
                "JOIN chef_departement ON utilisateur.ID_User = chef_departement.ID_User " +
                "WHERE chef_departement.Departement = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, departement);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                chef = new User();
                chef.setIdUser(rs.getInt("ID_User"));
                chef.setNom(rs.getString("Nom"));
                chef.setPrenom(rs.getString("Prenom"));
                chef.setEmail(rs.getString("Email"));
                chef.setImage(rs.getString("Image"));
                chef.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                chef.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                chef.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                chef.setSoldeMaternite(rs.getInt("Solde_Maternité"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return chef;
    }*/

    public List<User> getUsersByDepartment(String departement) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user.ID_User, user.Nom, user.Prenom, user.Email, user.Image, user.Solde_Annuel, user.Solde_Maladie, user.Solde_Exceptionnel, user.Solde_Maternité " +
                "FROM user " +
                "WHERE user.Departement = ?";

        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, departement);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("ID_User"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setImage(rs.getString("Image"));
                user.setSoldeAnnuel(rs.getInt("Solde_Annuel"));
                user.setSoldeExceptionnel(rs.getInt("Solde_Exceptionnel"));
                user.setSoldeMaladie(rs.getInt("Solde_Maladie"));
                user.setSoldeMaternite(rs.getInt("Solde_Maternité"));
                user.setIdDepartement(rs.getInt("ID_Departement"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

}
