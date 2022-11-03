package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.repositories.DisciplinesRepository;
import be.condorcet.apiprojet2022.repositories.ProjetRepository;
import be.condorcet.apiprojet2022.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class ProjetServiceImpl implements InterfProjetService{
    @Autowired
    private DisciplinesRepository disciplinesRepository;
    @Autowired
    private ProjetRepository projetRepository;


    @Override
    public List<Projet> readUnique(String titre) {
        return projetRepository.findProjetByTitreLike(titre+"%");
    }

    @Override
    public List<Projet> read(String cout) {
        return projetRepository.findProjetByCoutLike(cout+"%");
    }

    @Override
    public Projet create(Projet projet) throws Exception {
        projetRepository.save(projet);
        return projet;
    }

    @Override
    public Projet read(Integer id) throws Exception {
        Optional<Projet> opro= projetRepository.findById(id);
        return opro.get();
    }

    @Override
    public Projet update(Projet projet) throws Exception {
        //read(projet.getId_apiprojet()); //permet de ne pas créer un client qui n'existe pas
        projetRepository.save(projet);
        return projet;
    }

    @Override
    public void delete(Projet projet) throws Exception {
        projetRepository.deleteById(projet.getId_apiprojet());
    }

    @Override
    public List<Projet> all() throws Exception {
        return projetRepository.findAll();
    }
}