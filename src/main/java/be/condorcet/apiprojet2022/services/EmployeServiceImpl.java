package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.repositories.EmployeRepository;
import be.condorcet.apiprojet2022.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Employe readTriplet(String matricule, String tel, String mail) {
        return employeRepository.findEmployeByMatriculeAndTelAndMail(matricule+"%",tel+"%",mail+"%");
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
       // read(employe.getId_apiemploye()); //permet de ne pas créer un client qui n'existe pas
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

    @Override
    public Page<Employe> allp(Pageable pageable) throws Exception {
        return employeRepository.findAll(pageable);
    }

}