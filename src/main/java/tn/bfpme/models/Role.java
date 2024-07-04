package tn.bfpme.models;

public class Role {
    private int idRole;
    private int RoleParent;
    private String nom;
    private String description;

    public Role(int idRole, String nom, String description) {
        this.idRole = idRole;
        this.nom = nom;
        this.description = description;
    }
    public Role(int idRole, String nom, String description, int RoleParent) {
        this.idRole = idRole;
        this.nom = nom;
        this.description = description;
        this.RoleParent = RoleParent;
    }

    // Getters and Setters
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

    public void setRoleParent(int RoleParent) {
        this.RoleParent = RoleParent;
    }

    public int getRoleParent() {
        return RoleParent;
    }

    @Override
    public String toString() {
        return nom + " - " + description;
    }
}
