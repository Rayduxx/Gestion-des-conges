package tn.bfpme.models;

public class RoleHierarchie {
    private int idRoleH;
    private int idRoleP;
    private int idRoleC;
    private String HierarchyName;
    private String parentRoleName;
    private String childRoleName;

    public RoleHierarchie(int idRoleH, int idRoleP, int idRoleC,String HierarchyName) {
        this.idRoleH = idRoleH;
        this.idRoleP = idRoleP;
        this.idRoleC = idRoleC;
        this.HierarchyName = HierarchyName;
    }

    public RoleHierarchie(int idRoleH, int idRoleP, int idRoleC, String parentRoleName, String childRoleName) {
        this.idRoleH = idRoleH;
        this.idRoleP = idRoleP;
        this.idRoleC = idRoleC;
        this.parentRoleName = parentRoleName;
        this.childRoleName = childRoleName;
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

    public String getParentRoleName() {
        return parentRoleName;
    }

    public void setParentRoleName(String parentRoleName) {
        this.parentRoleName = parentRoleName;
    }

    public String getChildRoleName() {
        return childRoleName;
    }

    public void setChildRoleName(String childRoleName) {
        this.childRoleName = childRoleName;
    }

    public String getHierarchyName() {
        return HierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        HierarchyName = hierarchyName;
    }

    @Override
    public String toString() {
        return parentRoleName + " - " + childRoleName;
    }
}
