package tn.bfpme.models;

public class RoleHierarchie {
    private int idRoleH;
    private int idRoleP;
    private int idRoleC;

    public RoleHierarchie(int idRoleH, int idRoleP, int idRoleC) {
        this.idRoleH = idRoleH;
        this.idRoleP = idRoleP;
        this.idRoleC = idRoleC;
    }

    public int getIdRoleH() {
        return idRoleH;
    }

    public void setIdRoleH(int idRoleH) {
        this.idRoleH = idRoleH;
    }

    public int getIdRoleP() {
        return idRoleP;
    }

    public void setIdRoleP(int idRoleP) {
        this.idRoleP = idRoleP;
    }

    public int getIdRoleC() {
        return idRoleC;
    }

    public void setIdRoleC(int idRoleC) {
        this.idRoleC = idRoleC;
    }

    public Role getParentRole() {
        return null;
    }

    public Role getChildRole() {
        return null;
    }

    public void setParentRole(Role parentRole) {
        if (parentRole != null) {
            this.idRoleP = parentRole.getIdRole();
        } else {
            this.idRoleP = 0;
        }
    }

    public void setChildRole(Role childRole) {
        if (childRole != null) {
            this.idRoleC = childRole.getIdRole();
        } else {
            this.idRoleC = 0;
        }
    }
}
