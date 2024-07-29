package tn.bfpme.models;

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
    private int idManager;
    private int idDepartement;
    private int idRole;
    private int idUserSolde; // Add this field

    private String departementNom; // New field for department name
    private String roleNom; // New field for role name
    private String managerName; // New field for manager name

    public User() {}

    public User(int idUser, String nom, String prenom, String email, String mdp, String image, int idDepartement, int idManager, int idRole, int idUserSolde) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.image = image;
        this.idDepartement = idDepartement;
        this.idManager = idManager;
        this.idRole = idRole;
        this.idUserSolde = idUserSolde;
        this.creationDate = LocalDate.now(); // Default creation date
    }

    public User(int idUser, String nom, String prenom, String email, String mdp, String image, int idDepartement, int idRole) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.image = image;
        this.idDepartement = idDepartement;
        this.idRole = idRole;
        this.creationDate = LocalDate.now(); // Default creation date
    }

    public User(int idUser, String nom, String prenom, String email, String mdp, String image, int idDepartement, int idManager, int idRole, LocalDate creationDate, int idUserSolde) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.image = image;
        this.idDepartement = idDepartement;
        this.idManager = idManager;
        this.idRole = idRole;
        this.creationDate = (creationDate != null) ? creationDate : LocalDate.now();
        this.idUserSolde = idUserSolde;
    }

    public User(int idUser, String nom, String prenom, String email, String mdp, String image, LocalDate creationDate, int idDepartement, int idRole) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.image = image;
        this.creationDate = (creationDate != null) ? creationDate : LocalDate.now();
        this.idDepartement = idDepartement;
        this.idRole = idRole;
    }

    public User(String string, String s, String nom, String prenom, String email, LocalDate now, int idManager, int idDepartement) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.idManager = idManager;
        this.idDepartement = idDepartement;
        this.idRole = idRole;
    }

    public User(int idUser, String nom, String prenom, String email, String mdp, String image, LocalDate creationDate, int idDepartement, int idRole, int idUserSolde) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.image = image;
        this.creationDate = (creationDate != null) ? creationDate : LocalDate.now();
        this.idDepartement = idDepartement;
        this.idRole = idRole;
        this.idUserSolde = idUserSolde;
    }

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

    public int getIdSolde() {
        return idUserSolde;
    }

    public void setIdSolde(int idUserSolde) {
        this.idUserSolde = idUserSolde;
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

    public String getManagerName() {
        return managerName == null || managerName.isEmpty() ? "Il n'y a pas de manager" : managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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
                ", idManager=" + idManager +
                ", idDepartement=" + idDepartement +
                ", idRole=" + idRole +
                ", idUserSolde=" + idUserSolde +
                ", departementNom='" + departementNom + '\'' +
                ", roleNom='" + roleNom + '\'' +
                ", managerName='" + managerName + '\'' +
                ", creationDate=" + creationDate +
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
