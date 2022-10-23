package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.repositories.EmployeRepository;
import be.condorcet.apiprojet2022.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service
@Transactional(rollbackOn = Exception.class)
public class EmployeServiceImpl implements InterfEmployeService{
    @Autowired
    private EmployeRepository employeRepository;


    @Override
    public List<Employe> read(String nom) {
        return employeRepository.findEmployeByNomLike(nom+"%");
    }

    @Override
    public Employe create(Employe employe) throws Exception {
        employeRepository.save(employe);
        return employe;
    }

    @Override
    public Employe read(Integer id) throws Exception {
        Optional<Employe> oemp= employeRepository.findById(id);
        return oemp.get();
    }

    @Override
    public Employe update(Employe employe) throws Exception {
        employeRepository.save(employe);
        return employe;
    }

    @Override
    public void delete(Employe employe) throws Exception {
        employeRepository.deleteById(employe.getId_apiemploye());
    }

    @Override
    public List<Employe> all() throws Exception {
        return employeRepository.findAll();
    }
}