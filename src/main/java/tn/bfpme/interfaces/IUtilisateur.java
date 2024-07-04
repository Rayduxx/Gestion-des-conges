package tn.bfpme.interfaces;

import tn.bfpme.models.User;
import tn.bfpme.models.UserConge;

import java.util.List;

public interface IUtilisateur {

    UserConge afficherusers();
    //UserConge Rechercher(String search);
    UserConge TriType();
    UserConge TriNom();
    UserConge TriPrenom();
    UserConge TriDateDebut();
    UserConge TriDateFin();
    UserConge AfficherApprove();
    UserConge AfficherReject();

    List<User> RechrecheRH(String recherche);
}
