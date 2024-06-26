package tn.bfpme.models;

public class ChefAdministration extends Utilisateur {
    private int idChefAdmin;
    private int idUser;
    public static ChefAdministration Current_ChefAdm;

    public ChefAdministration() {super();}

    public ChefAdministration(int idChefAdmin, int idUser, String nom, String prenom, String email, String mdp, Role role, String image, int soldeAnnuel,int soldeMaladie, int soldeExceptionnel,int soldeMaternite) {
        super(idUser, nom, prenom, email, mdp, role, image,soldeAnnuel,soldeMaladie,soldeExceptionnel,soldeMaternite);
        this.idChefAdmin = idChefAdmin;
    }

    public ChefAdministration(int idChefAdmin, Utilisateur utilisateur) {
        super(utilisateur.getIdUser(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMdp(), utilisateur.getRole(), utilisateur.getImage(),  utilisateur.getSoldeAnnuel(), utilisateur.getSoldeMaladie(),utilisateur.getSoldeExceptionnel(),utilisateur.getSoldeMaternite());
        this.idChefAdmin = idChefAdmin;
    }

    public int getIdChefAdmin() {return idChefAdmin;}
    public void setIdChefAdmin(int idChefAdmin) {this.idChefAdmin = idChefAdmin;}

    public static ChefAdministration getCurrent_ChefAdm() {return Current_ChefAdm;}
    public static void setCurrent_ChefAdm(ChefAdministration Current_ChefAdm) {ChefAdministration.Current_ChefAdm = Current_ChefAdm;}

    @Override
    public String toString() {
        return "ChefAdministration{" +
                "idChefDep=" + idChefAdmin +
                ", " + super.toString() +
                '}';
    }
}
