package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.entities.Disciplines;

import java.util.List;

public interface InterfDisciplinesService extends InterfService<Disciplines>{
    public List<Disciplines> read(String nom);

    List<Disciplines> readUnique(String description);
}
