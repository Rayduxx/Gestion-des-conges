package tn.bfpme.utils;

import tn.bfpme.models.Departement;
import tn.bfpme.models.Role;

import java.sql.Date;

public class SessionManager {
    private static SessionManager instance;

    private static int id_user;
    private static String nom;
    private static String prenom;
    private static String email;
    private static Role role;
    private static String image;
    private static int soldeConge;
    private static Departement departement;

    private SessionManager(int id_user , String nom , String prenom , String email, Role role, String image, int soldeConge, Departement departement) {
        SessionManager.id_user=id_user;
        SessionManager.nom=nom;
        SessionManager.prenom=prenom;
        SessionManager.soldeConge=soldeConge;
        SessionManager.email=email;
        SessionManager.role=role;
        SessionManager.image=image;
        SessionManager.departement=departement;
    }
    public static SessionManager getInstace(int id_user , String nom , String prenom , String email , Role role, String image, int soldeConge, Departement departement) {
        if(instance == null) {
            instance = new SessionManager(id_user, nom , prenom, email, role, image, soldeConge, departement);
        }
        return instance;
    }

    public static SessionManager getInstance() {return instance;}
    public static void setInstance(SessionManager instance) {SessionManager.instance = instance;}

    public static int getId_user() {return id_user;}
    public static void setId_user(int id_user) {SessionManager.id_user = id_user;}

    public static String getNom() {return nom;}
    public static void setNom(String nom) {SessionManager.nom = nom;}

    public static String getPrenom() {return prenom;}
    public static void setPrenom(String prenom) {SessionManager.prenom = prenom;}

    public static String getEmail() {return email;}
    public static void setEmail(String email) {SessionManager.email = email;}

    public static Role getRole() {return role;}
    public static void setRole(Role role) {SessionManager.role = role;}

    public static Departement getDepartement() {return departement;}
    public static void setImage(Departement departement) {SessionManager.departement = departement;}

    public static String getImage() {return image;}
    public static void setImage(String image) {SessionManager.image = image;}


    public static int getSoldeConge() {return soldeConge;}
    public static void setsoldeConge(int soldeConge) {SessionManager.soldeConge  = soldeConge;}

    public static void cleanUserSession() {
        id_user=0;
        nom="";
        prenom="";
        soldeConge=0;
        email="";
        role= Role.valueOf("");
        image="";
    }

}