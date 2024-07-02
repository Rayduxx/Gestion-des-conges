package tn.bfpme.models;

public class Departement {
    private int idDepartement;
    private String nom;
    private String description;
    private int parentDept;

    public Departement() {}

    public Departement(int idDepartement, String nom, String description, int parentDept) {
        this.idDepartement = idDepartement;
        this.nom = nom;
        this.description = description;
        this.parentDept = parentDept;
    }

    public int getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
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

    public int getParentDept() {
        return parentDept;
    }

    public void setParentDept(int parentDept) {
        this.parentDept = parentDept;
    }
}
