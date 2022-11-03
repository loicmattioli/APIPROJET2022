package be.condorcet.apiprojet2022.repositories;

import be.condorcet.apiprojet2022.entities.Disciplines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinesRepository extends JpaRepository<Disciplines,Integer> {
    List<Disciplines> findDisciplinesByNomLike(String s);
    List<Disciplines> findDisciplinesByDescriptionLike(String s);
}
