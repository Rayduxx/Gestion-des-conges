package tn.bfpme.models;

public class ChefDepartement extends Utilisateur {
    private int idChefDep;
    private Departement departement;
    private int idUser;
    public static ChefDepartement Current_ChefDep;

    public ChefDepartement() {super();}

    public ChefDepartement(int idChefDep, int idUser, String nom, String prenom, String email, String mdp, Role role, String image, Solde_congé soldeConge, Departement departement) {
        super(idUser, nom, prenom, email, mdp, role, image, soldeConge);
        this.idChefDep = idChefDep;
        this.departement = departement;
    }

    public ChefDepartement(int idChefDep, Departement departement, Utilisateur utilisateur) {
        super(utilisateur.getIdUser(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMdp(), utilisateur.getRole(), utilisateur.getImage(), utilisateur.getSoldeConge());
        this.idChefDep = idChefDep;
        this.departement = departement;
    }

    public int getIdChefDep() {return idChefDep;}
    public void setIdChefDep(int idChefDep) {this.idChefDep = idChefDep;}

    public Departement getDepartement() {return departement;}
    public void setDepartement(Departement departement) {this.departement = departement;}

    public static ChefDepartement getCurrent_ChefDep() {return Current_ChefDep;}
    public static void setCurrent_ChefDep(ChefDepartement Current_ChefDep) {ChefDepartement.Current_ChefDep = Current_ChefDep;}

    @Override
    public String toString() {
        return "ChefDepartement{" +
                "idChefDep=" + idChefDep +
                ", departement=" + departement +
                ", " + super.toString() +
                '}';
    }
}
