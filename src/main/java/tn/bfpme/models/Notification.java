package tn.bfpme.models;

public class Notification {
    private int idNotif;
    private int idUser;
    private String notification;
    private Statut statut;

    public Notification() {}

    public Notification(int idUser, int idNotif, String notification, Statut statut) {
        this.idUser = idUser;
        this.idNotif = idNotif;
        this.notification = notification;
        this.statut = statut;
    }

    public Notification(int idUser, int idNotif, String notification) {
        this.idUser = idUser;
        this.idNotif = idNotif;
        this.notification = notification;
    }

    public Notification(int idUser, String notification) {
        this.idUser = idUser;
        this.notification = notification;
    }

    public Notification(int idUser, String notification, Statut statut) {
        this.idUser = idUser;
        this.notification = notification;
        this.statut = statut;
    }

    public int getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(int idNotif) {
        this.idNotif = idNotif;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "idNotif=" + idNotif +
                ", idUser=" + idUser +
                ", notification='" + notification + '\'' +
                ", statut=" + statut +
                '}';
    }
}
