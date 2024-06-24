package tn.bfpme.models;

public enum Departement {
    IT("Information Technology"),
    RH("Human Resources"),
    Finance("Finance"),
    Sécurité("Sécurité"),
    Datascience("Data Science"),
    Administration("Administration"),
    Marketing("Marketing");

    private final String displayName;

    Departement(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

