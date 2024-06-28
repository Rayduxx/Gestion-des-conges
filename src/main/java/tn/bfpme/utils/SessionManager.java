package tn.bfpme.utils;

import tn.bfpme.models.*;

public class SessionManager {
    private static SessionManager instance;

    private Utilisateur utilisateur;

    private SessionManager(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public static SessionManager getInstance(Utilisateur utilisateur) {
        if (instance == null) {
            instance = new SessionManager(utilisateur);
        }
        return instance;
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SessionManager is not initialized. Call getInstance(Utilisateur) first.");
        }
        return instance;
    }

    public void updateSession(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public static void cleanUserSession() {

        instance = null;
    }

    public void setDepartementEmp(Departement departement) {
        if (utilisateur instanceof Employe) {
            ((Employe) utilisateur).setDepartement(departement);
        }
    }
    public void setDepartementChfDep(Departement departement) {
        if (utilisateur instanceof ChefDepartement) {
            ((ChefDepartement) utilisateur).setDepartement(departement);
        }
    }
    public Departement getDepartement() {
        if (utilisateur instanceof Employe) {
            return ((Employe) utilisateur).getDepartement();
        }
        if (utilisateur instanceof ChefDepartement) {
            return ((ChefDepartement) utilisateur).getDepartement();
        }
        throw new IllegalStateException("This user type does not have a department.");
    }
}

//package tn.bfpme.utils;
//
//import tn.bfpme.models.Departement;
//import tn.bfpme.models.Employe;
//import tn.bfpme.models.Role;
//
//public class SessionManager {
//    private static SessionManager instance;
//
//    private int id_user;
//    private int idEmploye;
//    private int ID_ChefDep;
//    private String nom;
//    private String prenom;
//    private String email;
//    private Role role;
//    private String image;
//    private String password;
//    private int soldeConge;
//    private Departement departement;
//
//    private SessionManager(int id_user, String nom, String prenom, String email, Role role, String image, int soldeConge, String password) {
//        this.id_user = id_user;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.email = email;
//        this.role = role;
//        this.image = image;
//        this.soldeConge = soldeConge;
//        this.password = password;
//    }
//
//    public static SessionManager getInstance(int id_user, String nom, String prenom, String email, Role role, String image, int soldeConge, String password) {
//        if (instance == null) {
//            instance = new SessionManager(id_user, nom, prenom, email, role, image, soldeConge, password);
//        }
//        return instance;
//    }
//
//    public static SessionManager getInstance() {
//        if (instance == null) {
//            throw new IllegalStateException("SessionManager is not initialized. Call getInstance(int, String, String, String, Role, String, int, Departement) first.");
//        }
//        return instance;
//    }
//
//    public void updateSession(int id_user, String nom, String prenom, String email, Role role, String image, int soldeConge, String password, Departement departement) {
//        this.id_user = id_user;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.email = email;
//        this.role = role;
//        this.image = image;
//        this.soldeConge = soldeConge;
//        this.departement = departement;
//        this.password = password;
//    }
//
//    public int getId_user() {
//        return id_user;
//    }
//
//    public void setId_user(int id_user) {
//        this.id_user = id_user;
//    }
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPrenom() {
//        return prenom;
//    }
//
//    public void setPrenom(String prenom) {
//        this.prenom = prenom;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public Departement getDepartement() {
//        return departement;
//    }
//
//    public void setDepartement(Departement departement) {
//        this.departement = departement;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public int getSoldeConge() {
//        return soldeConge;
//    }
//
//    public void setSoldeConge(int soldeConge) {
//        this.soldeConge = soldeConge;
//    }
