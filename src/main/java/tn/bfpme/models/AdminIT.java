package tn.bfpme.models;

public class AdminIT extends Utilisateur {
    private int idAdmin;
    private int idUser;
    public static AdminIT Current_Adm;

    public AdminIT() {super();}

    public AdminIT(int idAdmin, int idUser, String nom, String prenom, String email, String mdp, Role role, String image,  int soldeAnnuel,int soldeMaladie, int soldeExceptionnel,int soldeMaternite) {
        super(idUser, nom, prenom, email, mdp, role, image,soldeAnnuel,soldeMaladie,soldeExceptionnel,soldeMaternite);
        this.idAdmin = idAdmin;
    }

    public AdminIT(int idAdmin, Utilisateur utilisateur) {
        super(utilisateur.getIdUser(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMdp(), utilisateur.getRole(), utilisateur.getImage(), utilisateur.getSoldeAnnuel(), utilisateur.getSoldeMaladie(),utilisateur.getSoldeExceptionnel(),utilisateur.getSoldeMaternite());
        this.idAdmin = idAdmin;
    }

    public int getIdAdmin() {return idAdmin;}
    public void setIdAdmin(int idAdmin) {this.idAdmin = idAdmin;}

    public static AdminIT getCurrent_Adm() {return Current_Adm;}
    public static void setCurrent_Adm(AdminIT Current_Adm) {AdminIT.Current_Adm = Current_Adm;}

    @Override
    public String toString() {
        return "AdminIT{" +
                "idAdmin=" + idAdmin +
                ", " + super.toString() +
                '}';
    }
}
