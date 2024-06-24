package tn.bfpme.interfaces;

import tn.bfpme.models.Conge;

import java.util.List;

public interface IConge<C> {
    List<Conge> afficher();
    void Add (C c);
    void updateConge(Conge conge);
    void deleteConge(Conge conge);
    void deleteCongeByID(int id);
}
