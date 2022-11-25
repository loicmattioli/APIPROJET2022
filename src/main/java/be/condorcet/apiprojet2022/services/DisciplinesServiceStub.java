package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.entities.Disciplines;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DisciplinesServiceStub implements InterfDisciplinesService{

    @Override
    public Disciplines create(Disciplines disciplines) throws Exception {
        return null;
    }

    @Override
    public Disciplines read(Integer id) throws Exception {
        Disciplines dsc = new Disciplines(id,"NomTest","DescriptionTest",new ArrayList<>());
        return dsc;
    }
    @Override
    public Disciplines update(Disciplines disciplines) throws Exception{
        return disciplines;
    }
    @Override
    public void delete(Disciplines disciplines) throws Exception {
    }
    @Override
    public List<Disciplines> read(String nom) {
        List<Disciplines>ldsc = new ArrayList<>();
        ldsc.add(new Disciplines(1,"NomTest","DescriptionTest",null));
        ldsc.add(new Disciplines(2,"NomTest2","DescriptionTest2",null));
        return ldsc;
    }

    @Override
    public List<Disciplines> readUnique(String description) {
        List<Disciplines>ldsc = new ArrayList<>();
        ldsc.add(new Disciplines(1,"NomTest","DescriptionTest",null));
        ldsc.add(new Disciplines(2,"NomTest2","DescriptionTest2",null));
        return ldsc;
    }

    @Override
    public List<Disciplines> all() throws Exception {
        List<Disciplines> ldsc = new ArrayList<>();
        ldsc.add(new Disciplines(1,"ABC","DescriptionDiscipline",null));
        ldsc.add(new Disciplines(2,"123","DescriptionDiscipline2",null));
        return ldsc;
    }

    @Override
    public Page<Disciplines> allp(Pageable pageable) throws Exception {
        return null;
    }
}

