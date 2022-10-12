package be.condorcet.apiprojet2022;

import be.condorcet.apiprojet2022.entities.Employe;
import be.condorcet.apiprojet2022.repositories.EmployeRepository;
import be.condorcet.apiprojet2022.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/employes")   //chaque fois qu'on utilise client c'est cette classe là qui est utilisée
public class GestEmploye {

    @Autowired
    private EmployeRepository employeRepository;

    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model) {
        System.out.println("recherche employés");
        // pour afficher la liste des employés
        // http://localhost:5000/clients/tous

        try {
            Collection<Employe> lemp = employeRepository.findAll();
            // lemp = employeRepository.findAllByEmploye();
            //lemp = employeRepository.
            model.put("mesEmployes", lemp);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affichagetousEmployes";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String matricule, @RequestParam String nom, @RequestParam String
            prenom, @RequestParam String tel, @RequestParam String mail, Map<String, Object> model) {
        System.out.println("création d'employé'");
        Employe apip = new Employe(matricule, nom, prenom, tel, mail);
        try {
            employeRepository.save(apip);//mise à jour du client avec son id par l'environnement
            System.out.println(apip);
            Collection<Employe> lemp = employeRepository.findAll();
            model.put("nouvemp", apip);
            //model.put("mesEmployes", lemp);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création------- - " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "nouveauEmploye";
    }

    @RequestMapping("/update")
    public String update(@RequestParam int id_apiemploye,@RequestParam String matricule, @RequestParam String nom, @RequestParam String
            prenom, @RequestParam String tel, @RequestParam String mail, Map<String, Object> model) {
        System.out.println("modification d'employé'");

        try {
            Optional<Employe> lemp = employeRepository.findById(id_apiemploye);
            lemp.ifPresent((emp) -> {
                emp = lemp.get();
                emp.setMatricule(matricule);
                emp.setNom(nom);
                emp.setPrenom(prenom);
                emp.setTel(tel);
                emp.setMail(mail);
                employeRepository.save(emp);
                model.put("nouvemp", emp);
            });

            // lemp = employeRepository.findAllByEmploye();
            //lemp = employeRepository.

        } catch (Exception e) {
            System.out.println("----------erreur lors de la modification -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }

       /* try {
            employeRepository.save(apip);//mise à jour du client avec son id par l'environnement
            System.out.println(apip);
            Collection<Employe> lemp= employeRepository.findAll();
            model.put("nouvemp",apip);
            //model.put("mesEmployes", lemp);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création------- - " + e);
            model.put("error",e.getMessage());
            return "error";
        }*/
        return "modificationEmploye";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int id_apiemploye, Map<String, Object> model) {
        System.out.println("suppression d'employé'");

        try {
            Optional<Employe> lemp = employeRepository.findById(id_apiemploye);
            lemp.ifPresent((emp) -> {
                emp = lemp.get();
                employeRepository.deleteById(id_apiemploye);
                model.put("suppremp", emp);
            });

        } catch (Exception e) {
            System.out.println("----------erreur lors de la suppression-------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }

        return "suppressionEmploye";
    }

    @RequestMapping("/recherche")
    public String recherche(@RequestParam int id_apiemploye, Map<String, Object> model) {
        System.out.println("recherche d'employé'");

        try {
            Optional<Employe> lemp = employeRepository.findById(id_apiemploye);
            lemp.ifPresent((emp) -> {
                emp = lemp.get();
                model.put("monemp", emp);
            });

        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }

        return "affEmploye";
    }

}
