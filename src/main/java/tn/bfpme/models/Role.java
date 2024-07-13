package tn.bfpme.models;

public class Role {
    private int idRole;
    private int RoleParent;
    private String nom;
    private String description;

    private String parentRoleName; // New field for parent role name
    private String childRoleName; // New field for child role name

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

    public int getRoleParent() {
        return RoleParent;
    }

    public void setRoleParent(int RoleParent) {
        this.RoleParent = RoleParent;
    }

    public String getParentRoleName() {
        return parentRoleName;
    }

    public void setParentRoleName(String parentRoleName) {
        this.parentRoleName = parentRoleName;
    }

    public String getChildRoleName() {
        return childRoleName;
    }

    public void setChildRoleName(String childRoleName) {
        this.childRoleName = childRoleName;
    }

    @Override
    public String toString() {
        return nom + " - " + description;
    }
}
