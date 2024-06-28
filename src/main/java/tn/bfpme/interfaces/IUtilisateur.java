package tn.bfpme.interfaces;

import tn.bfpme.models.UserConge;

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

}
