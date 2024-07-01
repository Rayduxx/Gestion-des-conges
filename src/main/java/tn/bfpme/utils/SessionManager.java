package tn.bfpme.utils;

import tn.bfpme.models.*;

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


}
