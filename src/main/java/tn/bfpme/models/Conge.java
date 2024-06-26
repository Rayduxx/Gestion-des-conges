package tn.bfpme.models;

import java.time.LocalDate;

public class Conge {
    private int idConge;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private TypeConge typeConge;
    private Statut statut;
    private int idUser;
    private String file;
    private String description;

    public Conge() {}

    public Conge(int idConge, LocalDate dateDebut, LocalDate dateFin, TypeConge typeConge, Statut statut, int idUser) {
        this.idConge = idConge;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeConge = typeConge;
        this.statut = statut;
        this.idUser = idUser;
        this.file = file;
        this.description = description;
    }

    public int getIdConge() {
        return idConge;
    }

    public void setIdConge(int idConge) {
        this.idConge = idConge;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public TypeConge getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(TypeConge typeConge) {
        this.typeConge = typeConge;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Conge{" +
                "idConge=" + idConge +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", typeConge=" + typeConge +
                ", statut=" + statut +
                ", idUser=" + idUser +
                ", file='" + file +
                ", description='" + description +
                '}';
    }
}
