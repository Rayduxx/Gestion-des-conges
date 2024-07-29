package tn.bfpme.models;

public class UserSolde {
    private int idUserSolde;
    private int idTypeConge;
    private int idUser;
    private Double TotalSolde;

    public UserSolde() {
    }

    public UserSolde(int idUserSolde, int idTypeConge, int idUser, Double totalSolde) {
        this.idUserSolde = idUserSolde;
        this.idTypeConge = idTypeConge;
        this.idUser = idUser;
        this.TotalSolde = totalSolde;
    }

    public int getIdUserSolde() {
        return idUserSolde;
    }

    public void setIdUserSolde(int idUserSolde) {
        this.idUserSolde = idUserSolde;
    }

    public int getIdTypeConge() {
        return idTypeConge;
    }

    public void setIdTypeConge(int idTypeConge) {
        this.idTypeConge = idTypeConge;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Double getTotalSolde() {
        return TotalSolde;
    }

    public void setTotalSolde(Double totalSolde) {
        TotalSolde = totalSolde;
    }

    @Override
    public String toString() {
        return "UserSolde{" +
                "idUserSolde=" + idUserSolde +
                ", idTypeConge=" + idTypeConge +
                ", idUser=" + idUser +
                ", TotalSolde=" + TotalSolde +
                '}';
    }
}
