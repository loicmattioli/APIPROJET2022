package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.entities.Employe;

import java.util.ArrayList;
import java.util.List;

public class EmployeServiceStub implements InterfEmployeService{

    @Override
    public Employe create(Employe employe) throws Exception {
        return null;
    }

    @Override
    public Employe read(Integer id) throws Exception {
        Employe emp = new Employe(id,"MatriculeTest","NomTest","PrenomTest","0001","MailTest");
        return emp;
    }
    @Override
    public Employe update(Employe employe) throws Exception{
        return employe;
    }
    @Override
    public void delete(Employe employe) throws Exception {
    }
    @Override
    public List<Employe> read(String nom) {
        List<Employe>lemp = new ArrayList<>();
        lemp.add(new Employe(1,"MatriculeTest",nom,"PrenomTest","0001",null));
        lemp.add(new Employe(2,"MatriculeTest2",nom,"PrenomTest2","0002",null));
        return lemp;
    }
    @Override
    public List<Employe> all() throws Exception {
        List<Employe> lemp = new ArrayList<>();
        lemp.add(new Employe(1,"ABC","NomEmploye","PrenomEmploye","0001",null));
        lemp.add(new Employe(2,"123","NomEmploye2","PrenomEmploye2","0002",null));
        return lemp;
    }
}

