package tn.bfpme.models;

public class Utilisateur {
    private int idUser;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private Role role;
    private String image;
    private int soldeAnnuel;
    private int soldeMaladie;
    private int soldeExceptionnel;
    private int soldeMaternite;

    public static Utilisateur Current_User;

    public Utilisateur() {}

    public Utilisateur(int idUser, String nom, String prenom, String email, String mdp, Role role, String image, int soldeAnnuel,int soldeMaladie, int soldeExceptionnel,int soldeMaternite) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.image = image;
        this.soldeAnnuel = soldeAnnuel;
        this.soldeMaladie=soldeMaladie;
        this.soldeExceptionnel=soldeExceptionnel;
        this.soldeMaternite=soldeMaternite;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public static Utilisateur getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(Utilisateur Current_User) {
        Utilisateur.Current_User = Current_User;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUser=" + idUser +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", role=" + role +
                ", image='" + image + '\'' +
                ", soldeAnnuel=" + soldeAnnuel + '\'' +
                ", soldeMaladie=" + soldeMaladie + '\'' +
                ", soldeExceptionnel=" + soldeExceptionnel + '\'' +
                ", soldeMaternite=" + soldeMaternite + '\'' +
                '}';
    }
}
