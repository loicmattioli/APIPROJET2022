/*package be.condorcet.apiprojet2022.services;


import be.condorcet.apiprojet2022.entities.Competence;
import be.condorcet.apiprojet2022.entities.Employe;
import be.condorcet.apiprojet2022.entities.Disciplines;
import be.condorcet.apiprojet2022.repositories.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(rollbackOn = Exception.class)
public class CompetenceServiceImpl implements InterfCompetenceService{
    @Autowired
    private CompetenceRepository competenceRepository;


    @Override
    public List<Competence> read(int niveau) {
        return competenceRepository.findCompetenceByNiveauLike(Integer.parseInt(niveau+"%"));
    }

    @Override
    public Competence create(Competence competence) throws Exception {
        competenceRepository.save(competence);
        return competence;
    }

    @Override
    public Competence read(Integer id) throws Exception {
        Optional<Competence> ocomp= competenceRepository.findById(id);
        return ocomp.get();
    }

    @Override
    public Competence update(Competence competence) throws Exception {
        read(competence.getNiveau());
        competenceRepository.save(competence);
        return competence;
    }

    @Override
    public void delete(Competence competence) throws Exception {
        competenceRepository.deleteById(competence.getNiveau());
    }

    @Override
    public List<Competence> all() throws Exception {
        return competenceRepository.findAll();
    }
}*/
