package tn.bfpme.interfaces;

import tn.bfpme.models.UserConge;

public interface IUtilisateur {

    UserConge afficherusers();
    UserConge TriStatut();
    UserConge TriType();
    UserConge TriNom();
    UserConge TriPrenom();
    UserConge TriDateDebut();
    UserConge TriDateFin();
}
