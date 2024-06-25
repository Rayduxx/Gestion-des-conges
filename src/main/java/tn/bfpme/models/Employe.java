package tn.bfpme.models;

public class Employe extends Utilisateur {
    private int idEmploye;
    private Departement departement;
    public static Employe Current_Emp;

    public Employe() {
        super();
    }

    public Employe(int idEmploye, int idUser, String nom, String prenom, String email, String mdp, Role role, String image, Solde_congé soldeConge, Departement departement) {
        super(idUser, nom, prenom, email, mdp, role, image, soldeConge);
        this.idEmploye = idEmploye;
        this.departement = departement;
    }

    public Employe(int idEmploye, Departement departement, Utilisateur utilisateur) {
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

    public Departement getDepartement() {
        return departement;
    }
    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public static Employe getCurrent_Emp() {return Current_Emp;}
    public static void setCurrent_Emp(Employe Current_Emp) {Employe.Current_Emp = Current_Emp;}

    @Override
    public String toString() {
        return "Employe{" +
                "idEmploye=" + idEmploye +
                ", departement=" + departement +
                ", " + super.toString() +
                '}';
    }
}
