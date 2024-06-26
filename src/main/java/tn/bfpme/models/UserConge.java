package tn.bfpme.models;

import java.util.List;

public class UserConge {

        private List<Utilisateur> users;
        private List<Conge> conges;

        public UserConge(List<Utilisateur> users, List<Conge> conges) {
            this.users = users;
            this.conges = conges;
        }

        public List<Utilisateur> getUsers() {
            return users;
        }

        public List<Conge> getConges() {
            return conges;
        }


}
