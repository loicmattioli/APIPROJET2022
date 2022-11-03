package be.condorcet.apiprojet2022.services;


import be.condorcet.apiprojet2022.entities.Projet;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjetServiceStub implements InterfProjetService{

    @Override
    public Projet create(Projet projet) throws Exception {
        return null;
    }

    @Override
    public Projet read(Integer id) throws Exception {
        Projet pro = new Projet(id,"TitreTest", Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.of(2022,12,12)),10,null);
        return pro;
    }
    @Override
    public Projet update(Projet projet) throws Exception{
        return projet;
    }
    @Override
    public void delete(Projet projet) throws Exception {
    }
    @Override
    public List<Projet> read(String titre) {
        List<Projet>lpro = new ArrayList<>();
        lpro.add(new Projet(1,"TitreTest", Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.of(2022,12,12)),10,null));
        lpro.add(new Projet(2,"TitreTest2", Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.of(2022,11,11)),20,null));
        return lpro;
    }

    @Override
    public List<Projet> readUnique(String cout) {
        List<Projet>lpro = new ArrayList<>();
        lpro.add(new Projet(1,"TitreTest", Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.of(2022,12,12)),10,null));
        lpro.add(new Projet(2,"TitreTest2", Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.of(2022,11,11)),20,null));
        return lpro;
    }

    @Override
    public List<Projet> all() throws Exception {
        List<Projet> lpro = new ArrayList<>();
        lpro.add(new Projet(1,"TitreProjet",Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.of(2022,12,12)),10,null));
        lpro.add(new Projet(2,"TitreProjet2", Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.of(2022,11,11)),20,null));
        return lpro;
    }
}

