package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.entities.Employe;

import java.util.List;

public interface InterfEmployeService extends InterfService<Employe> {

    public List<Employe> read(String nom);
}
