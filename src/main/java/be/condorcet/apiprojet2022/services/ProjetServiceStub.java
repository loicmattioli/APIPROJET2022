package be.condorcet.apiprojet2022.services;


import be.condorcet.apiprojet2022.entities.Disciplines;
import be.condorcet.apiprojet2022.entities.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
        Date datedebut = Date.valueOf("2022-11-02");
        Date datefin = Date.valueOf("2022-12-22");
        Projet pro = new Projet(id,"TitreTest", datedebut,datefin,10.0,null);
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
        Date datedebut = Date.valueOf("2022-11-02");
        Date datefin = Date.valueOf("2022-12-22");
        Date datedebut2 = Date.valueOf("2022-11-03");
        Date datefin2 = Date.valueOf("2022-12-23");
        List<Projet>lpro = new ArrayList<>();
        lpro.add(new Projet(1,"TitreTest", datedebut,datefin,10.0,null));
        lpro.add(new Projet(2,"TitreTest2", datedebut2,datefin2,20.0,null));
        return lpro;
    }

    @Override
    public List<Projet> readUnique(String cout) {
        Date datedebut = Date.valueOf("2022-11-02");
        Date datefin = Date.valueOf("2022-12-22");
        Date datedebut2 = Date.valueOf("2022-11-03");
        Date datefin2 = Date.valueOf("2022-12-23");
        List<Projet>lpro = new ArrayList<>();
        lpro.add(new Projet(1,"TitreTest", datedebut,datefin,10.0,null));
        lpro.add(new Projet(2,"TitreTest2", datedebut2,datefin2,20.0,null));
        return lpro;
    }

    @Override
    public List<Projet> getProjets(Disciplines dsc) {
        return null;
    }

    @Override
    public List<Projet> all() throws Exception {
        Date datedebut = Date.valueOf("2022-11-02");
        Date datefin = Date.valueOf("2022-12-22");
        Date datedebut2 = Date.valueOf("2022-11-03");
        Date datefin2 = Date.valueOf("2022-12-23");
        List<Projet> lpro = new ArrayList<>();
        lpro.add(new Projet(1,"TitreProjet",datedebut,datefin,10.0,null));
            lpro.add(new Projet(2,"TitreProjet2", datedebut2,datefin2,20.0,null));
        return lpro;
    }

    @Override
    public Page<Projet> allp(Pageable pageable) throws Exception {
        return null;
    }
}

