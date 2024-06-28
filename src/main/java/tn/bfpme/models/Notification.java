package tn.bfpme.models;

public class Notification extends Utilisateur{
    private int idNotif;
    private String notification;
    public static Notification Current_Notif;

    public Notification(){super();}
    public Notification(int idNotif, int idUser, String nom, String prenom, String email, String mdp, Role role, String image,  int soldeAnnuel,int soldeMaladie, int soldeExceptionnel,int soldeMaternite, String notification) {
        super(idUser, nom, prenom, email, mdp, role, image, soldeAnnuel,soldeMaladie,soldeExceptionnel,soldeMaternite);
        this.idNotif = idNotif;
        this.notification = notification;
    }
    public Notification(int idNotif, String notification, Utilisateur utilisateur) {
        super(utilisateur.getIdUser(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMdp(), utilisateur.getRole(), utilisateur.getImage(), utilisateur.getSoldeAnnuel(), utilisateur.getSoldeMaladie(),utilisateur.getSoldeExceptionnel(),utilisateur.getSoldeMaternite());
        this.idNotif = idNotif;
        this.notification = notification;
    }
    public int getIdNotif() {
        return idNotif;
    }
    public void setIdNotif(int idNotif) {
        this.idNotif = idNotif;
    }

    public String getnotification() {
        return notification;
    }
    public void setNotification(String notification) {
        this.notification = notification;
    }

    public static Notification getCurrent_Notif() {return Current_Notif;}
    public static void setCurrent_Notif(Notification Current_Notif) {Notification.Current_Notif = Current_Notif;}
}
