package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.entities.Disciplines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DisciplinesSeviceMock implements InterfDisciplinesService {
    private List<Disciplines> ldsc = new ArrayList<>();
    private int numact = 0;

    @Override
    public Disciplines create(Disciplines dsc) throws Exception {
        for (Disciplines dsc2 : ldsc) {
            if (dsc2.getNom().equals(dsc.getNom()) &&
                    (dsc2.getDescription().equals(dsc.getDescription()))) throw new Exception("doublon");
        }
        numact++;
        dsc.setId_apidisciplines(numact);
        ldsc.add(dsc);
        return dsc;
    }

    @Override
    public Disciplines read(Integer id) throws Exception {
        for (Disciplines dsc : ldsc) {
            if (dsc.getId_apidisciplines().equals(id)) return dsc;
        }
        throw new Exception("code inconnu");
    }

    @Override
    public Disciplines update(Disciplines disciplines) throws Exception {
        Integer id = disciplines.getId_apidisciplines();
        Disciplines oldDsc = read(id);
        oldDsc.setNom(disciplines.getNom());
        oldDsc.setDescription(disciplines.getDescription());

        return read(oldDsc.getId_apidisciplines());
    }

    @Override
    public List<Disciplines> read(String nom) {
        List<Disciplines> ldscnom = new ArrayList<>();
        ldsc.stream().filter(cl -> cl.getNom().equals(nom)).forEach(cl -> ldscnom.add(cl));
        return ldscnom;
    }

    @Override
    public List<Disciplines> readUnique(String description) {
        List<Disciplines> ldscdescr = new ArrayList<>();
        ldsc.stream().filter(cl -> cl.getDescription().equals(description)).forEach(cl -> ldscdescr.add(cl));
        return ldscdescr;
    }

    @Override
    public void delete(Disciplines dscdel) throws Exception {
        Iterator<Disciplines> itc = ldsc.iterator();
        while (itc.hasNext()) {
            Disciplines dsc = itc.next();
            if (dsc.getId_apidisciplines().equals(dscdel.getId_apidisciplines())) {
                itc.remove();
            }
        }
    }

    @Override
    public List<Disciplines> all() throws Exception {
        return ldsc;
    }
}
