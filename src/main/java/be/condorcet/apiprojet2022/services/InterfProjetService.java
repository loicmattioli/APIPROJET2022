package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.entities.Disciplines;
import be.condorcet.apiprojet2022.entities.Projet;

import java.util.List;

public interface InterfProjetService extends InterfService<Projet>{
    public List<Projet> read(String titre);

    List<Projet> readUnique(String cout);
    public List<Projet> getProjets(Disciplines dsc);
}
