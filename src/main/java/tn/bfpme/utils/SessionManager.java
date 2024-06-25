package tn.bfpme.utils;

import tn.bfpme.models.Departement;
import tn.bfpme.models.Role;

public class SessionManager {
    private static SessionManager instance;

    private int id_user;
    private String nom;
    private String prenom;
    private String email;
    private Role role;
    private String image;
    private String password;
    private int soldeConge;
    private Departement departement;

    private SessionManager(int id_user, String nom, String prenom, String email, Role role, String image, int soldeConge, String password, Departement departement) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = role;
        this.image = image;
        this.soldeConge = soldeConge;
        this.departement = departement;
        this.password = password;
    }

    public static SessionManager getInstance(int id_user, String nom, String prenom, String email, Role role, String image, int soldeConge, String password, Departement departement) {
        if (instance == null) {
            instance = new SessionManager(id_user, nom, prenom, email, role, image, soldeConge, password, departement);
        }
        return instance;
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SessionManager is not initialized. Call getInstance(int, String, String, String, Role, String, int, Departement) first.");
        }
        return instance;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSoldeConge() {
        return soldeConge;
    }

    public void setSoldeConge(int soldeConge) {
        this.soldeConge = soldeConge;
    }

    public static void cleanUserSession() {
        instance=null;
    }
}
