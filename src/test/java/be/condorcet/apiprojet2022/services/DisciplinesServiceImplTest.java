package be.condorcet.apiprojet2022.services;

import be.condorcet.apiprojet2022.entities.Disciplines;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DisciplinesServiceImplTest {

    @Autowired
    private DisciplinesServiceImpl disciplinesServiceImpl;
    @Autowired
    private ProjetServiceImpl projetServiceImpl;


    Disciplines dsc;

    @BeforeEach //equivalent de populate, execution au lancement du test
    void setUp() {
        try{
            dsc = new Disciplines(null,"NomTest","DescriptionTest",new ArrayList<>());
            disciplinesServiceImpl.create(dsc);
            System.out.println("création de la discipline : "+dsc);
        }catch(Exception e){
            System.out.println("erreur lors de la création de la discipline : "+dsc+" erreur : "+e.getMessage());
        }
    }

    @AfterEach //pour effacer l'élément à chaque fin de test
    void tearDown() {
        try{
            disciplinesServiceImpl.delete(dsc);
            System.out.println("effacement de la discipline : "+dsc);
        }catch (Exception e){
            System.out.println("erreur d'effacement de la discipline : "+dsc+" erreur : "+e.getMessage());
        }
    }

    @Test
    void create() {
        assertNotEquals(0,dsc.getId_apidisciplines(), "id disciplines non incrémenté");
        assertEquals("NomTest",dsc.getNom(),"nom discipline non enregistré : "+dsc.getNom()+" au lieu de NomTest");
        assertEquals("DescriptionTest",dsc.getDescription(),"description discipline non enregistré : "+dsc.getDescription()+" au lieu de DescriptionTest");
    }

    @Test()
    void creationDoublon() {   //ajouté
        Disciplines dsc2 = new Disciplines(null,"NomTest","DescriptionTest2",null); //changer les valeurs des cp, localités,...car même si il y a une contrainte d'unicité sur les 7 champs, il peut y avoir plusieurs personnes dans ces loc
        Assertions.assertThrows(Exception.class, () -> { //on teste une méthode pour voir si elle renvoie une exception
            disciplinesServiceImpl.create(dsc2); //méthode à invoquer pour tester
        }, "création d'un doublon");
    }


    @Test
    void read() {
        try{
            int numdsc = dsc.getId_apidisciplines();
            Disciplines dsc2 = disciplinesServiceImpl.read(numdsc);
            assertEquals("NomTest",dsc2.getNom(),"noms différents "+ " NomTest"+" - "+dsc2.getNom());
            assertEquals("DescriptionTest",dsc2.getDescription(),"descriptions différentes "+ " DescriptionTest"+" - "+dsc2.getDescription());
        }catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            dsc.setNom("NomTest2");
            dsc.setDescription("DescriptionTest2");

            dsc = disciplinesServiceImpl.update(dsc);
            assertEquals("NomTest2",dsc.getNom(),"noms différents : "+ " NomTest2 - " + dsc.getNom());
            assertEquals("DescriptionTest2", dsc.getDescription(), "descriptions différentes " + " DescriptionTest2 - " + dsc.getDescription());
        }catch (Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            disciplinesServiceImpl.delete(dsc);
            Assertions.assertThrows(Exception.class, () -> {
                disciplinesServiceImpl.read(dsc.getId_apidisciplines());
            },"record non effacé");
        }catch (Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void rechNom() {
        List<Disciplines> ldsc = disciplinesServiceImpl.read("NomTest");
        boolean trouve = false;
        for (Disciplines d : ldsc) {
            if (d.getNom().equals("NomTest")) trouve = true;
            else fail("un record ne correspond pas , nom = " + d.getNom());
        }
        assertTrue(trouve, "record non trouvé dans la liste");
    }

    @Test
    void rechDescription() {
        List<Disciplines> ldsc = disciplinesServiceImpl.readUnique("DescriptionTest");
        boolean trouve = false;
        for (Disciplines d : ldsc) {
            if (d.getDescription().equals("DescriptionTest")) trouve = true;
            else fail("un record ne correspond pas , description = " + d.getDescription());
        }
        assertTrue(trouve, "record non trouvé dans la liste");
    }

    @Test
    void all() {
        try{
            List<Disciplines> ldsc = disciplinesServiceImpl.all();
            assertNotEquals(0,ldsc.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les clients "+e);
        }
    }
}