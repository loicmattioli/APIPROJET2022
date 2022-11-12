package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.entities.Employe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeServiceImplTest {

    @Autowired
    private EmployeServiceImpl employeServiceImpl;

    Employe emp;

    @BeforeEach //equivalent de populate, execution au lancement du test
    void setUp() {
        try{
            emp = new Employe(null,"MatriculeTest","NomTest","PrenomTest","0001","MailTest");
            employeServiceImpl.create(emp);
            System.out.println("création de l'employé : "+emp);
        }catch(Exception e){
            System.out.println("erreur lors de la création de l'employé : "+emp+" erreur : "+e.getMessage());
        }
    }

    @AfterEach //pour effacer l'élément à chaque fin de test
    void tearDown() {
        try{
            employeServiceImpl.delete(emp);
            System.out.println("effacement de l'employé : "+emp);
        }catch (Exception e){
            System.out.println("erreur d'effacement de l'employé : "+emp+" erreur : "+e.getMessage());
        }
    }

    @Test
    void create() {
        assertNotEquals(0,emp.getId_apiemploye(), "id employé non incrémenté");
        assertEquals("NomTest",emp.getNom(),"non employé non enregistré : "+emp.getNom()+" au lieu de NomTest");
        assertEquals("PrenomTest",emp.getPrenom(),"prenom employé non enregistré : "+emp.getPrenom()+" au lieu de PrenomTest");
    }

    @Test()
    void creationDoublon() {   //ajouté
        Employe emp2 = new Employe(null,"MatriculeTest","NomTest2","PrenomTest2","0001","MailTest"); //changer les valeurs des cp, localités,...car même si il y a une contrainte d'unicité sur les 7 champs, il peut y avoir plusieurs personnes dans ces loc
        Assertions.assertThrows(Exception.class, () -> { //on teste une méthode pour voir si elle renvoie une exception
            employeServiceImpl.create(emp2); //méthode à invoquer pour tester
        }, "création d'un doublon");
    }


    @Test
    void read() {
        try{
            int numemp = emp.getId_apiemploye();
            Employe emp2 = employeServiceImpl.read(numemp);
            assertEquals("NomTest",emp2.getNom(),"noms différents  "+" NomTest"+"-"+emp2.getNom());
            assertEquals("PrenomTest",emp2.getPrenom(),"prenoms différents  "+" PrenomTest"+"-"+emp2.getPrenom());
        }catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            emp.setNom("NomTest2");
            emp.setPrenom("PrenomTest2");

            emp = employeServiceImpl.update(emp);
            assertEquals("NomTest2",emp.getNom(),"noms différents  "+" NomTest2"+"-"+emp.getNom());
            assertEquals("PrenomTest2",emp.getPrenom(),"prenoms différents  "+" PrenomTest2"+"-"+emp.getPrenom());
        }catch (Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try {
            employeServiceImpl.delete(emp);
            Assertions.assertThrows(Exception.class, () -> {
                employeServiceImpl.read(emp.getId_apiemploye());
            }, "record non effacé");
        } catch (Exception e) {
            fail("erreur d'effacement " + e);
        }
    }

    @Test
    void rechNom() {
        List<Employe> lemp = employeServiceImpl.read("NomTest");
        boolean trouve = false;
        for (Employe e : lemp) {
            if (e.getNom().equals("NomTest")) trouve = true;
            else fail("un record ne correspond pas , nom = " + e.getNom());
        }
        assertTrue(trouve, "record non trouvé dans la liste");
    }

    @Test
    void all() {
        try{
            List<Employe> lemp = employeServiceImpl.all();
            assertNotEquals(0,lemp.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les clients "+e);
        }
    }
}