package be.condorcet.apiprojet2022.webservices;

import be.condorcet.apiprojet2022.entities.Disciplines;
import be.condorcet.apiprojet2022.entities.Employe;
import be.condorcet.apiprojet2022.services.DisciplinesServiceImpl;
import be.condorcet.apiprojet2022.services.InterfDisciplinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class RestDisciplines {
    @Autowired
    private InterfDisciplinesService disciplinesServiceImpl;


    //-------------------Retrouver la discipline correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Disciplines> getDiscipline(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche de la discipline d' id " + id);
        Disciplines discipline = disciplinesServiceImpl.read(id);
        return new ResponseEntity<>(discipline, HttpStatus.OK);
    }

    //-------------------Retrouver les disciplines portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Disciplines>> listDisciplinesNom(@PathVariable(value="nom") String nom) throws Exception{
        System.out.println("recherche de "+nom);
        List<Disciplines> disciplines;
        disciplines = disciplinesServiceImpl.read(nom);
        return new ResponseEntity<>(disciplines, HttpStatus.OK);
    }
    //-------------------Créer une discipline--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Disciplines> createDisciplines(@RequestBody Disciplines discipline) throws Exception {
        System.out.println("Création de la discipline " + discipline.getNom());
        disciplinesServiceImpl.create(discipline);
        return new ResponseEntity<>(discipline, HttpStatus.OK);
    }

    //-------------------Mettre à jour une discipline d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Disciplines> majDisciplines(@PathVariable(value = "id") int id,@RequestBody Disciplines nouvdsc) throws Exception{
        System.out.println("maj de la discipline id =  " + id);
        nouvdsc.setId_apidisciplines(id);
        Disciplines dscact = disciplinesServiceImpl.update(nouvdsc);
        return new ResponseEntity<>(dscact, HttpStatus.OK);
    }

    //-------------------Effacer une discipline d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDisciplines(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de la discipline d'id " + id);
        Disciplines discipline = disciplinesServiceImpl.read(id);
        disciplinesServiceImpl.delete(discipline);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver toutes les disciplines --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Disciplines>> listDisciplines() throws Exception{
        System.out.println("recherche de toutes les disciplines");
        return new ResponseEntity<>(disciplinesServiceImpl.all(), HttpStatus.OK);
    }

    //-------------------Retrouver toutes les disciplines triées et par page--------------------------------------------------------
    @RequestMapping(value =  "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Disciplines>> listDiscipline(Pageable pageable) throws Exception{
        System.out.println("recherche de toutes les disciplines");
        return new ResponseEntity<>(disciplinesServiceImpl.allp(pageable), HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
