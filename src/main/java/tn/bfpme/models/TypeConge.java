package tn.bfpme.models;

public class TypeConge {
    private int idTypeConge;
    private String Designation;
    private double Pas;
    private int PeriodeJ;
    private int PeriodeM;
    private int PeriodeA;
    private boolean File;

    public TypeConge() {
    }

    public TypeConge(int idTypeConge, String Designation, double pas, int periodeJ, int periodeM, int periodeA, boolean file) {
        this.idTypeConge = idTypeConge;
        this.Designation = Designation;
        this.Pas = pas;
        this.PeriodeJ = periodeJ;
        this.PeriodeM = periodeM;
        this.PeriodeA = periodeA;
        this.File = file;
    }

    public int getIdTypeConge() {
        return idTypeConge;
    }

    public void setIdTypeConge(int idTypeConge) {
        this.idTypeConge = idTypeConge;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        Designation = Designation;
    }

    public double getPas() {
        return Pas;
    }

    public void setPas(double pas) {
        Pas = pas;
    }

    public int getPeriodeJ() {
        return PeriodeJ;
    }

    public void setPeriodeJ(int periodeJ) {
        PeriodeJ = periodeJ;
    }

    public int getPeriodeM() {
        return PeriodeM;
    }

    public void setPeriodeM(int periodeM) {
        PeriodeM = periodeM;
    }

    public int getPeriodeA() {
        return PeriodeA;
    }

    public void setPeriodeA(int periodeA) {
        PeriodeA = periodeA;
    }

    public boolean isFile() {
        return File;
    }

    public void setFile(boolean file) {
        File = file;
    }

    @Override
    public String toString() {
        return "TypeConge{" +
                "idTypeConge=" + idTypeConge +
                ", Designation='" + Designation + '\'' +
                ", Pas=" + Pas +
                ", PeriodeJ=" + PeriodeJ +
                ", PeriodeM=" + PeriodeM +
                ", PeriodeA=" + PeriodeA +
                ", File=" + File +
                '}';
    }
}
