package be.condorcet.apiprojet2022.webservices;

import be.condorcet.apiprojet2022.entities.Employe;
import be.condorcet.apiprojet2022.services.EmployeServiceImpl;
import be.condorcet.apiprojet2022.services.InterfEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/employes")
public class RestEmploye {
    @Autowired
    private InterfEmployeService employeServiceImpl;


    //-------------------Retrouver l'employé correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employe> getEmploye(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche de l'employé d' id " + id);
        Employe employe = employeServiceImpl.read(id);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    //-------------------Retrouver les employés portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Employe>> listEmployesNom(@PathVariable(value="nom") String nom) throws Exception{
        System.out.println("recherche de "+nom);
        List<Employe> employes;
        employes = employeServiceImpl.read(nom);
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }
    //-------------------Retrouver l'employé correspondant à un triplet (matricule,tel,mail) unique donné--------------------------------------------------------
    @RequestMapping(value = "/{matricule}/{tel}/{mail}", method = RequestMethod.GET)
    public ResponseEntity<Employe> getClientUnique(@PathVariable(value = "matricule") String matricule, @PathVariable(value = "tel") String tel,@PathVariable(value = "mail") String mail)  throws Exception{
        System.out.println("recherche de l'employé "+matricule+" "+tel+" "+mail);
        Employe employe = employeServiceImpl.readTriplet(matricule,tel,mail);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }
    //-------------------Créer un employé--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Employe> createEmploye(@RequestBody Employe employe) throws Exception {
        System.out.println("Création d'Employé " + employe.getNom());
        employeServiceImpl.create(employe);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    //-------------------Mettre à jour un employé d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employe> majClient(@PathVariable(value = "id") int id,@RequestBody Employe nouvemp) throws Exception{
        System.out.println("maj d'Employé id =  " + id);
        nouvemp.setId_apiemploye(id);
        Employe empact = employeServiceImpl.update(nouvemp);
        return new ResponseEntity<>(empact, HttpStatus.OK);
    }

    //-------------------Effacer un employé d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEmploye(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de l'employé d'id " + id);
        Employe employe = employeServiceImpl.read(id);
        employeServiceImpl.delete(employe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver tous les employés --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Employe>> listEmploye() throws Exception{
        System.out.println("recherche de tous les employés");
        return new ResponseEntity<>(employeServiceImpl.all(), HttpStatus.OK);
    }

    //-------------------Retrouver tous les employés triés et par page--------------------------------------------------------
    @RequestMapping(value =  "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<Employe>> listEmployes(Pageable pageable) throws Exception{
        System.out.println("recherche de tous les employés");
        return new ResponseEntity<>(employeServiceImpl.allp(pageable), HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
