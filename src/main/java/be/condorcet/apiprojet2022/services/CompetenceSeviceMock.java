/*package be.condorcet.apiprojet2022.services;


import be.condorcet.apiprojet2022.entities.Competence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompetenceSeviceMock implements InterfCompetenceService {
    private List<Competence> lcomp = new ArrayList<>();
    private int numact = 0;

    @Override
    public Competence create(Competence comp) throws Exception {
        for (Competence comp2 : lcomp) {
            if (comp2.getNiveau().equals(comp.getNiveau()) && comp2.getEmploye().getId_apiemploye().equals(comp.getEmploye().getId_apiemploye())
            && comp2.getDisciplines().getId_apidisciplines().equals(comp.getDisciplines().getId_apidisciplines())) throw new Exception("doublon");
        }
        numact++;
        comp.setNiveau(numact);
        lcomp.add(comp);
        return comp;
    }

    @Override
    public Competence read(Integer id) throws Exception {
        for (Competence comp : lcomp) {
            if (comp.getNiveau().equals(id)) return comp;
        }
        throw new Exception("code inconnu");
    }

    @Override
    public Competence update(Competence competence) throws Exception {
        Integer id = competence.getNiveau();
        Competence oldComp = read(id);
        oldComp.setNiveau(competence.getNiveau());

        return read(oldComp.getNiveau());
    }

    @Override
    public List<Competence> read(int niveau) {
        List<Competence> lcompniv = new ArrayList<>();
        lcomp.stream().filter(cl -> cl.getNiveau().equals(niveau)).forEach(cl -> lcompniv.add(cl));
        return lcompniv;
    }

    @Override
    public void delete(Competence compdel) throws Exception {
        Iterator<Competence> itc = lcomp.iterator();
        while (itc.hasNext()) {
            Competence comp = itc.next();
            if (comp.getNiveau().equals(compdel.getNiveau())) {
                itc.remove();
            }
        }
    }

    @Override
    public List<Competence> all() throws Exception {
        return lcomp;
    }
}
*/