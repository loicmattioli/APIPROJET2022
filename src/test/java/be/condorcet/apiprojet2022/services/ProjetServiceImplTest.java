package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.entities.Disciplines;
import be.condorcet.apiprojet2022.entities.Employe;
import be.condorcet.apiprojet2022.entities.Projet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjetServiceImplTest {

    @Autowired
    private DisciplinesServiceImpl disciplinesServiceImpl;
    @Autowired
    private ProjetServiceImpl projetServiceImpl;

    Disciplines dsc;
    Projet pro;

    @BeforeEach //equivalent de populate, execution au lancement du test
    void setUp() {
        Date datedebut = Date.valueOf("2022-11-02");
        Date datefin = Date.valueOf("2022-12-22");
        try{
            dsc = new Disciplines(null,"NomTest","DescriptionTest",new ArrayList<>());
            disciplinesServiceImpl.create(dsc);
            System.out.println("création de la discipline : "+dsc);

            pro = new Projet(null,"TitreTest", datedebut,datefin,10.0,dsc);
            projetServiceImpl.create(pro);
            System.out.println("création du projet : "+pro);
        }catch(Exception e){
            System.out.println("erreur lors de la création du projet : "+pro+" erreur : "+e.getMessage());
        }
    }

    @AfterEach //pour effacer l'élément à chaque fin de test
    void tearDown() {
        try{
            projetServiceImpl.delete(pro);
            System.out.println("effacement du projet : "+pro);
        }catch (Exception e){
            System.out.println("erreur d'effacement du projet : "+pro+" erreur : "+e.getMessage());
        }
    }

    @Test
    void create() {
       /* Date datedebut = Date.valueOf("2022-11-02");
        Date datefin = Date.valueOf("2022-12-22");*/
        assertNotEquals(0,pro.getID_APIPROJET(), "id projet non incrémenté");
       /* assertEquals("TitreTest",pro.getTitre(),"titre projet non enregistré : "+pro.getTitre()+" au lieu de TitreTest");
        assertEquals(datedebut,pro.getDateDebut(),"datedebut du projet non enregistré : "+pro.getDateDebut()+" au lieu de 2022-11-02");
        assertEquals(datefin,pro.getDateFin(),"datefin du projet non enregistré : "+pro.getDateFin()+" au lieu de 2022-12-22");
        assertEquals(10,pro.getCout(),"coût du projet non enregistré : "+pro.getCout()+" au lieu de 10");
        assertEquals(dsc.getNom(),pro.getDisciplines().getNom(),"nom de la discipline non enregistré "+pro.getDisciplines().getNom()+" au lieu de NomTest");*/
    }

    @Test()
    void creationDoublon() {   //ajouté
        Date datedebut = Date.valueOf("2022-11-02");
        Date datefin = Date.valueOf("2022-12-22");
        Projet pro2 = new Projet(null,"TitreTest", datedebut,datefin,10.0,null); //changer les valeurs des cp, localités,...car même si il y a une contrainte d'unicité sur les 7 champs, il peut y avoir plusieurs personnes dans ces loc
        Assertions.assertThrows(Exception.class, () -> { //on teste une méthode pour voir si elle renvoie une exception
            projetServiceImpl.create(pro2); //méthode à invoquer pour tester
        }, "création d'un doublon");
    }


    @Test
    void read() {
        try{
            int numpro = pro.getID_APIPROJET();
            Projet pro2 = projetServiceImpl.read(numpro);
            assertEquals("TitreTest",pro2.getTitre(),"titres différents "+ " TitreTest"+" - "+pro2.getTitre());
            assertEquals(10.0,pro2.getCout(),"coûts différents "+ "10.0"+" - "+pro2.getCout());
        }catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            pro.setTitre("TitreTest2");
            pro.setCout(20.0);

            pro = projetServiceImpl.update(pro);
            assertEquals("TitreTest2",pro.getTitre(),"titres différents "+ " TitreTest2"+" - "+pro.getTitre());
            assertEquals(20.0,pro.getCout(),"coûts différents "+ " 20.0"+" - "+pro.getCout());
        }catch (Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            projetServiceImpl.delete(pro);
            Assertions.assertThrows(Exception.class, () -> {
                projetServiceImpl.read(pro.getID_APIPROJET());
            },"record non effacé");
        }catch (Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void rechTitre() {
        List<Projet> lpro = projetServiceImpl.readUnique("TitreTest");
        boolean trouve = false;
        for (Projet p : lpro) {
            if (p.getTitre().equals("TitreTest")) trouve = true;
            else fail("un record ne correspond pas , titre = " + p.getTitre());
        }
        assertTrue(trouve, "record non trouvé dans la liste");
    }

    @Test
    void rechCout() {
        List<Projet> lpro = projetServiceImpl.read("10.0");
        boolean trouve = false;
        for (Projet p : lpro) {
            if (p.getCout() == (10.0)) trouve = true;
            else fail("un record ne correspond pas , cout = " + p.getCout());
        }
        assertTrue(trouve, "record non trouvé dans la liste");
    }

    @Test
    void all() {
        try{
            List<Projet> lpro = projetServiceImpl.all();
            assertNotEquals(0,lpro.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les projets "+e);
        }
    }
}