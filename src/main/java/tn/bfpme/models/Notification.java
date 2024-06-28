package tn.bfpme.models;

public class Notification extends Utilisateur{
    private int idNotif;
    private int idUser;
    private String notification;
    public Notification(int idNotif, int idUser, String notification) {
        this.idNotif = idNotif;
        this.idUser = idUser;
        this.notification = notification;
    }
}
