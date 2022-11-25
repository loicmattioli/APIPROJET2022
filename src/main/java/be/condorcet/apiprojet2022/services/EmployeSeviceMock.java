package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeSeviceMock implements InterfEmployeService {
    private List<Employe> lemp = new ArrayList<>();
    private int numact = 0;

    @Override
    public Employe create(Employe emp) throws Exception {
        for (Employe emp2 : lemp) {
            if (emp2.getNom().equals(emp.getNom()) &&
                    (emp2.getPrenom().equals(emp.getPrenom()) &&
                            emp2.getTel().equals(emp.getTel()))) throw new Exception("doublon");
        }
        numact++;
        emp.setId_apiemploye(numact);
        lemp.add(emp);
        return emp;
    }

    @Override
    public Employe read(Integer id) throws Exception {
        for (Employe emp : lemp) {
            if (emp.getId_apiemploye().equals(id)) return emp;
        }
        throw new Exception("code inconnu");
    }

    @Override
    public Employe update(Employe employe) throws Exception {
        Integer id = employe.getId_apiemploye();
        Employe oldEmp = read(id);
        oldEmp.setMatricule(employe.getMatricule());
        oldEmp.setNom(employe.getNom());
        oldEmp.setPrenom(employe.getPrenom());
        oldEmp.setTel(employe.getTel());
        oldEmp.setMail(employe.getMail());

        return read(oldEmp.getId_apiemploye());
    }

    @Override
    public List<Employe> read(String nom) {
        List<Employe> lempnom = new ArrayList<>();
        lemp.stream().filter(cl -> cl.getNom().equals(nom)).forEach(cl -> lempnom.add(cl));
        return lempnom;
    }

    @Override
    public Employe readTriplet(String matricule, String tel, String mail) {
        return null;
    }

    @Override
    public void delete(Employe empdel) throws Exception {
        Iterator<Employe> itc = lemp.iterator();
        while (itc.hasNext()) {
            Employe emp = itc.next();
            if (emp.getId_apiemploye().equals(empdel.getId_apiemploye())) {
                    itc.remove();
            }
        }
    }

    @Override
    public List<Employe> all() throws Exception {
        return lemp;
    }

    @Override
    public Page<Employe> allp(Pageable pageable) throws Exception {
        return null;
    }

}


