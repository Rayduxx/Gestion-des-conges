package tn.bfpme.models;

public enum Role {
    Employé("Employé"),
    ChefDepartement("Chef Departement"),
    ChefAdministration("Chef Administration"),
    AdminIT("Admin IT");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

}
