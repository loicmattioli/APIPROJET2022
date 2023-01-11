package be.condorcet.apiprojet2022.repositories;

import be.condorcet.apiprojet2022.entities.Disciplines;
import be.condorcet.apiprojet2022.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Integer> {
    List<Projet> findProjetByTitreLike(String s);
    public List<Projet> findProjetByDisciplines(Disciplines dsc);
    public List<Projet> findProjetByDATEDEBUT(Date d);

    List<Projet> findProjetByCout(Double cout);
}
