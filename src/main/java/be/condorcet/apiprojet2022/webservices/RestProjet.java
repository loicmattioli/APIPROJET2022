package be.condorcet.apiprojet2022.webservices;

import be.condorcet.apiprojet2022.entities.Disciplines;
import be.condorcet.apiprojet2022.entities.Employe;
import be.condorcet.apiprojet2022.entities.Projet;
import be.condorcet.apiprojet2022.services.InterfProjetService;
import be.condorcet.apiprojet2022.services.InterfDisciplinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/projets")
public class RestProjet {

    @Autowired
    private InterfProjetService projetServiceImpl;
    @Autowired
    private InterfDisciplinesService disciplinesServiceImpl;

    //-------------------Retrouver le projet correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Projet> getProjet(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche du projet n° " + id);
        Projet pro = projetServiceImpl.read(id);
        return new ResponseEntity<>(pro, HttpStatus.OK);
    }

    //-------------------Retrouver les projets par titre--------------------------------------------------------
    @RequestMapping(value = "/titre={titre}", method = RequestMethod.GET)
    public ResponseEntity<List<Projet>> listProjetsTitre(@PathVariable(value="titre") String titre) throws Exception{
        System.out.println("recherche de "+titre);
        List<Projet> projets;
        projets = projetServiceImpl.readTitre(titre);
        return new ResponseEntity<>(projets, HttpStatus.OK);
    }

    //-------------------Retrouver les projets par date de debut--------------------------------------------------------
    @RequestMapping(value = "/datedebut={datedebut}", method = RequestMethod.GET)
    public ResponseEntity<List<Projet>> listProjetsDatedebut(@PathVariable(value = "datedebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datedebut) throws Exception{
        System.out.println("recherche de "+datedebut);
        List<Projet> projets;
        projets = projetServiceImpl.readDateDebut(datedebut);
        return new ResponseEntity<>(projets, HttpStatus.OK);
    }
    //-------------------Retrouver les projets par coûts--------------------------------------------------------
    @RequestMapping(value = "/cout={cout}", method = RequestMethod.GET)
    public ResponseEntity<List<Projet>> listProjetCout(@PathVariable(value="cout") Double cout) throws Exception{
        System.out.println("recherche de "+cout);
        List<Projet> projets;
        projets = projetServiceImpl.readCout(cout);
        return new ResponseEntity<>(projets, HttpStatus.OK);
    }

    //-------------------Retrouver le projet correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/id_apidisciplines={id}", method = RequestMethod.GET)
    public ResponseEntity<List<Projet>> getProjetDiscipline(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche les projets de la discipline d'id " + id);
        Disciplines dsc = disciplinesServiceImpl.read(id);
        List<Projet> lpro = projetServiceImpl.getProjets(dsc);
        return new ResponseEntity<>(lpro, HttpStatus.OK);
    }

    //-------------------Créer un projet--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Projet> createProjet(@RequestBody Projet pro) throws Exception {
        System.out.println("Création du projet de la discipline" + pro.getDisciplines());
        projetServiceImpl.create(pro);
        return new ResponseEntity<>(pro, HttpStatus.OK);
    }

    //-------------------Mettre à jour un projet d'un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Projet> majClient(@PathVariable(value = "id") int id,@RequestBody Projet nouvpro) throws Exception{
        System.out.println("maj de la commade n° " + id);
        nouvpro.setID_APIPROJET(id);
        Projet pro = projetServiceImpl.update(nouvpro);
        return new ResponseEntity<>(pro, HttpStatus.OK);
    }

    //-------------------Effacer un projet d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProjet(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du projet n°" + id);
        Projet pro = projetServiceImpl.read(id);
        projetServiceImpl.delete(pro);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver tous les projets --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Projet>> listProjet() throws Exception{
        System.out.println("recherche de tous les projets");
        return new ResponseEntity<>(projetServiceImpl.all(), HttpStatus.OK);
    }

    //-------------------Retrouver tous les projets triés et par page--------------------------------------------------------
    @RequestMapping(value =  "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Projet>> listProjets(Pageable pageable) throws Exception{
        System.out.println("recherche de tous les projets");
        return new ResponseEntity<>(projetServiceImpl.allp(pageable), HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}