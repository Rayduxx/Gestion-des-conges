package tn.bfpme.models;

public class Employe extends Utilisateur {
    private int idEmploye;
    private String departement;

    public Employe() {
        super();
    }

    public Employe(int idEmploye, String departement, int idUser, String nom, String prenom, String email, String mdp, String role, String image, int soldeConge) {
        super(idUser, nom, prenom, email, mdp, role, image, soldeConge);
        this.idEmploye = idEmploye;
        this.departement = departement;
    }

    public Employe(int idEmploye, String departement, Utilisateur utilisateur) {
        super(utilisateur.getIdUser(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMdp(), utilisateur.getRole(), utilisateur.getImage(), utilisateur.getSoldeConge());
        this.idEmploye = idEmploye;
        this.departement = departement;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "idEmploye=" + idEmploye +
                ", departement='" + departement + '\'' +
                ", " + super.toString() +
                '}';
    }
}
