package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.entities.Disciplines;
import be.condorcet.apiprojet2022.entities.Projet;

import java.util.Date;
import java.util.List;

public interface InterfProjetService extends InterfService<Projet>{

    public List<Projet> readTitre(String titre);

    List<Projet> readCout(Double cout);

    List<Projet> readUnique(String cout);

    public List<Projet> getProjets(Disciplines dsc);
    public List<Projet> readDateDebut(Date d);

    Projet read(Integer id) throws Exception;

}
