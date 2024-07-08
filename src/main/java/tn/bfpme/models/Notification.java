package tn.bfpme.models;

public class Notification {
    private int idNotif;
    private int idUser;
    private String notification;

    public Notification(int idUser, int idNotif, String notification) {
        this.idUser = idUser;
        this.idNotif = idNotif;
        this.notification = notification;
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

    @Override
    public String toString() {
        return "Notification{" +
                "idNotif=" + idNotif +
                ", idUser=" + idUser +
                ", notification='" + notification + '\'' +
                '}';
    }
}
