package be.condorcet.apiprojet2022.services;


import be.condorcet.apiprojet2022.entities.Disciplines;
import be.condorcet.apiprojet2022.entities.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

public class ProjetSeviceMock implements InterfProjetService {
    private List<Projet> lpro = new ArrayList<>();
    private int numact = 0;

    @Override
    public Projet create(Projet pro) throws Exception {
        for (Projet pro2 : lpro) {
            if (pro2.getTitre().equals(pro.getTitre())) throw new Exception("doublon");
        }
        numact++;
        pro.setID_APIPROJET(numact);
        lpro.add(pro);
        return pro;
    }

    @Override
    public Projet read(Integer id) throws Exception {
        for (Projet pro : lpro) {
            if (pro.getID_APIPROJET().equals(id)) return pro;
        }
        throw new Exception("code inconnu");
    }

    @Override
    public Projet update(Projet projet) throws Exception {
        Integer id = projet.getID_APIPROJET();
        Projet oldPro = read(id);
        oldPro.setTitre(projet.getTitre());
        oldPro.setDATEDEBUT(projet.getDATEDEBUT());
        oldPro.setDATEFIN(projet.getDATEFIN());
        oldPro.setCout(projet.getCout());

        return read(oldPro.getID_APIPROJET());
    }

    @Override
    public List<Projet> readTitre(String titre) {
        List<Projet> lprotitre = new ArrayList<>();
        lpro.stream().filter(cl -> cl.getTitre().equals(titre)).forEach(cl -> lprotitre.add(cl));
        return lprotitre;
    }

    @Override
    public List<Projet> readCout(Double cout) {
        return null;
    }

    @Override
    public List<Projet> readUnique(String cout) {
        List<Projet> lprocout = new ArrayList<>();
        lpro.stream().filter(cl -> Objects.equals(cl.getCout(), cout)).forEach(cl -> lprocout.add(cl));
        return lprocout;
    }

    @Override
    public List<Projet> getProjets(Disciplines dsc) {
        return null;
    }

    @Override
    public List<Projet> readDateDebut(Date d) {
        return null;
    }

    @Override
    public void delete(Projet prodel) throws Exception {
        Iterator<Projet> itc = lpro.iterator();
        while (itc.hasNext()) {
            Projet pro = itc.next();
            if (pro.getID_APIPROJET().equals(prodel.getID_APIPROJET())) {
                itc.remove();
            }
        }
    }

    @Override
    public List<Projet> all() throws Exception {
        return lpro;
    }

    @Override
    public Page<Projet> allp(Pageable pageable) throws Exception {
        return null;
    }
}
