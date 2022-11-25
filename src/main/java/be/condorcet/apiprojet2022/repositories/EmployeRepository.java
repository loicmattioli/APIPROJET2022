package be.condorcet.apiprojet2022.repositories;
import be.condorcet.apiprojet2022.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe,Integer> {
    public List<Employe> findEmployeByNomLike(String nom);
    public Employe findEmployeByMatriculeAndTelAndMail(String matricule, String tel, String mail);
}



