package tn.bfpme.models;

import tn.bfpme.controllers.SoldeLogicController;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private int idUser;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String image;
    private LocalDate creationDate;
    private int soldeMaternite;
    private int soldeAnnuel;
    private double soldeAnnuelle;
    private int soldeExceptionnel;
    private int soldeMaladie;
    private int idManager;
    private int idDepartement;
    private int idRole;
    private String departementNom; // New field for department name
    private String roleNom; // New field for role name

    public User() {}

    public User(int idUser, String nom, String prenom, String email, String mdp, String image, int soldeAnnuel, int soldeMaladie, int soldeExceptionnel, int soldeMaternite, int idDepartement, int idRole) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.image = image;
        this.soldeAnnuel = soldeAnnuel;
        this.soldeMaladie = soldeMaladie;
        this.soldeExceptionnel = soldeExceptionnel;
        this.soldeMaternite = soldeMaternite;
        this.idDepartement = idDepartement;
        this.idRole = idRole;
    }

    public User(int idUser, String nom, String prenom, String email, String mdp, String image, int soldeAnnuel, int soldeMaladie, int soldeExceptionnel, int soldeMaternite, int idDepartement, int idManager, int idRole) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.image = image;
        this.soldeAnnuel = soldeAnnuel;
        this.soldeMaladie = soldeMaladie;
        this.soldeExceptionnel = soldeExceptionnel;
        this.soldeMaternite = soldeMaternite;
        this.idDepartement = idDepartement;
        this.idManager = idManager;
        this.idRole = idRole;
    }
    public User(int idUser, String nom, String prenom, String email, String mdp, String image, LocalDate creationDate, int idDepartement, int idRole) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.image = image;
        this.creationDate = (creationDate != null) ? creationDate : LocalDate.now();
        this.soldeMaternite = SoldeLogicController.calculateSoldeMaternite(this.creationDate);
        this.soldeAnnuelle = SoldeLogicController.calculateSoldeAnnuelle(this.creationDate);
        this.soldeExceptionnel = SoldeLogicController.calculateSoldeExceptionnel(this.creationDate);
        this.soldeMaladie = SoldeLogicController.calculateSoldeMaladie(this.creationDate);
        this.idDepartement = idDepartement;
        this.idRole = idRole;
    }

    public User(String nom, String prenom, String email, int idManager, int idDepartement, int idRole) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.idManager = idManager;
        this.idDepartement = idDepartement;
        this.idRole = idRole;
    }

    // Getters and Setters
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSoldeAnnuel() {
        return soldeAnnuel;
    }

    public void setSoldeAnnuel(int soldeAnnuel) {
        this.soldeAnnuel = soldeAnnuel;
    }

    public int getSoldeMaladie() {
        return soldeMaladie;
    }

    public void setSoldeMaladie(int soldeMaladie) {
        this.soldeMaladie = soldeMaladie;
    }

    public int getSoldeExceptionnel() {
        return soldeExceptionnel;
    }

    public void setSoldeExceptionnel(int soldeExceptionnel) {
        this.soldeExceptionnel = soldeExceptionnel;
    }

    public int getSoldeMaternite() {
        return soldeMaternite;
    }

    public void setSoldeMaternite(int soldeMaternite) {
        this.soldeMaternite = soldeMaternite;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public int getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getDepartementNom() {
        return departementNom;
    }

    public void setDepartementNom(String departementNom) {
        this.departementNom = departementNom;
    }

    public String getRoleNom() {
        return roleNom;
    }

    public void setRoleNom(String roleNom) {
        this.roleNom = roleNom;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", image='" + image + '\'' +
                ", soldeAnnuel=" + soldeAnnuel +
                ", soldeMaladie=" + soldeMaladie +
                ", soldeExceptionnel=" + soldeExceptionnel +
                ", soldeMaternite=" + soldeMaternite +
                ", idManager=" + idManager +
                ", idDepartement=" + idDepartement +
                ", idRole=" + idRole +
                ", departementNom='" + departementNom + '\'' +
                ", roleNom='" + roleNom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser);
    }
}
