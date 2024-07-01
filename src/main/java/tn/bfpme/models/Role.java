package tn.bfpme.models;

public class Role {
    private int idRole;
    private String nom;
    private String description;

    public Role(int idRole, String nom, String description) {
        this.idRole = idRole;
        this.nom = nom;
        this.description = description;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
