package tn.bfpme.utils;

import tn.bfpme.models.Departement;
import tn.bfpme.models.Role;
import tn.bfpme.models.User;
import tn.bfpme.services.ServiceDepartement;
import tn.bfpme.services.ServiceRole;

import java.util.List;

public class SessionManager {
    private static SessionManager instance;
    private User user;

    private SessionManager(User user) {
        this.user = user;
    }

    public static SessionManager getInstance(User user) {
        if (instance == null) {
            instance = new SessionManager(user);
        }
        return instance;
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("SessionManager is not initialized. Call getInstance(User) first.");
        }
        return instance;
    }

    public void updateSession(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public static void cleanUserSession() {
        instance = null;
    }

    // Methods to retrieve department and role information
    public Departement getUserDepartment() {
        return ServiceDepartement.getDepartmentById(user.getIdDepartement());
    }

    public String getUserDepartmentName() {
        Departement departement = getUserDepartment();
        return departement != null ? departement.getNom() : null;
    }

    public Departement getParentDepartment() {
        return ServiceDepartement.getParentDepartment(user.getIdDepartement());
    }

    public Role getUserRole() {
        return ServiceRole.getRoleById(user.getIdRole());
    }

    public String getUserRoleName() {
        Role role = getUserRole();
        return role != null ? role.getNom() : null;
    }

    public List<Role> getParentRoles() {
        return ServiceRole.getParentRoles(user.getIdRole());
    }

    public List<Role> getChildRoles() {
        return ServiceRole.getChildRoles(user.getIdRole());
    }
}
