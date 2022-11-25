package be.condorcet.apiprojet2022.services;


import be.condorcet.apiprojet2022.repositories.DisciplinesRepository;
import be.condorcet.apiprojet2022.entities.Disciplines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(rollbackOn = Exception.class)
public class DisciplinesServiceImpl implements InterfDisciplinesService{
    @Autowired
    private DisciplinesRepository disciplinesRepository;


    @Override
    public List<Disciplines> read(String nom) {
        return disciplinesRepository.findDisciplinesByNomLike(nom+"%");
    }

    @Override
    public List<Disciplines> readUnique(String description) {
        return disciplinesRepository.findDisciplinesByDescriptionLike(description+"%");
    }

    @Override
    public Disciplines create(Disciplines disciplines) throws Exception {
        disciplinesRepository.save(disciplines);
        return disciplines;
    }

    @Override
    public Disciplines read(Integer id) throws Exception {
        Optional<Disciplines> odsc= disciplinesRepository.findById(id);
        return odsc.get();
    }

    @Override
    public Disciplines update(Disciplines disciplines) throws Exception {
        read(disciplines.getId_apidisciplines()); //permet de ne pas créer un client qui n'existe pas
        disciplinesRepository.save(disciplines);
        return disciplines;
    }

    @Override
    public void delete(Disciplines disciplines) throws Exception {
        disciplinesRepository.deleteById(disciplines.getId_apidisciplines());
    }

    @Override
    public List<Disciplines> all() throws Exception {
        return disciplinesRepository.findAll();
    }

    @Override
    public Page<Disciplines> allp(Pageable pageable) throws Exception {
        return disciplinesRepository.findAll(pageable);
    }
}
