package be.condorcet.apiprojet2022.services;


import be.condorcet.apiprojet2022.entities.Projet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjetSeviceMock implements InterfProjetService {
    private List<Projet> lpro = new ArrayList<>();
    private int numact = 0;

    @Override
    public Projet create(Projet pro) throws Exception {
        for (Projet pro2 : lpro) {
            if (pro2.getTitre().equals(pro.getTitre())) throw new Exception("doublon");
        }
        numact++;
        pro.setId_apiprojet(numact);
        lpro.add(pro);
        return pro;
    }

    @Override
    public Projet read(Integer id) throws Exception {
        for (Projet pro : lpro) {
            if (pro.getId_apiprojet().equals(id)) return pro;
        }
        throw new Exception("code inconnu");
    }

    @Override
    public Projet update(Projet projet) throws Exception {
        Integer id = projet.getId_apiprojet();
        Projet oldPro = read(id);
        oldPro.setTitre(projet.getTitre());
        oldPro.setDateDebut(projet.getDateDebut());
        oldPro.setDateFin(projet.getDateFin());
        oldPro.setCout(projet.getCout());

        return read(oldPro.getId_apiprojet());
    }

    @Override
    public List<Projet> read(String titre) {
        List<Projet> lprotitre = new ArrayList<>();
        lpro.stream().filter(cl -> cl.getTitre().equals(titre)).forEach(cl -> lprotitre.add(cl));
        return lprotitre;
    }

    @Override
    public List<Projet> readUnique(String cout) {
        List<Projet> lprocout = new ArrayList<>();
        lpro.stream().filter(cl -> cl.getTitre().equals(cout)).forEach(cl -> lprocout.add(cl));
        return lprocout;
    }

    @Override
    public void delete(Projet prodel) throws Exception {
        Iterator<Projet> itc = lpro.iterator();
        while (itc.hasNext()) {
            Projet pro = itc.next();
            if (pro.getId_apiprojet().equals(prodel.getId_apiprojet())) {
                itc.remove();
            }
        }
    }

    @Override
    public List<Projet> all() throws Exception {
        return lpro;
    }
}
