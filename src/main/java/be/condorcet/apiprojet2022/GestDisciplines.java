package be.condorcet.apiprojet2022;


import be.condorcet.apiprojet2022.entities.Disciplines;
import be.condorcet.apiprojet2022.repositories.DisciplinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/disciplines")
public class GestDisciplines {

    @Autowired
    private DisciplinesRepository disciplinesRepository;

    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model) {
        System.out.println("recherche disciplines");
        // pour afficher la liste des employés
        // http://localhost:5000/clients/tous

        try {
            Collection<Disciplines> ldsc = disciplinesRepository.findAll();
            // lemp = employeRepository.findAllByEmploye();
            //lemp = employeRepository.
            model.put("mesDisciplines", ldsc);

        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affichagetoutesDisciplines";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String nom, @RequestParam String description, Map<String, Object> model) {
        System.out.println("création de discipline'");
        Disciplines apid = new Disciplines(nom, description);
        try {
            disciplinesRepository.save(apid);//mise à jour du client avec son id par l'environnement
            System.out.println(apid);
            Collection<Disciplines> ldsc = disciplinesRepository.findAll();
            model.put("nouvdsc", apid);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création------- - " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "nouvelleDiscipline";
    }

    @RequestMapping("/update")
    public String update(@RequestParam int id_apidiscipline,@RequestParam String nom, @RequestParam String description, Map<String, Object> model) {
        System.out.println("modification de discipline'");

        try {
            Optional<Disciplines> ldsc = disciplinesRepository.findById(id_apidiscipline);
            ldsc.ifPresent((dsc) -> {
                dsc = ldsc.get();
                dsc.setNom(nom);
                dsc.setDescription(description);
                disciplinesRepository.save(dsc);
                model.put("nouvdsc", description);
            });
            if (ldsc.isEmpty()){
                throw new Exception("ID introuvable");
            }else{
                System.out.println("Modification effectuée");
            }

        } catch (Exception e) {
            System.out.println("----------erreur lors de la modification -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }

        return "modificationDiscipline";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int id_apidisciplines, Map<String, Object> model) {
        System.out.println("suppression de disciplines'");

        try {
            Optional<Disciplines> ldsc = disciplinesRepository.findById(id_apidisciplines);
            ldsc.ifPresent((dsc) -> {
                dsc = ldsc.get();
                disciplinesRepository.deleteById(id_apidisciplines);
                model.put("supprdsc", dsc);
            });
            if (ldsc.isEmpty()){
                throw new Exception("ID introuvable");
            }else{
                System.out.println("Suppression effectuée");
            }


        } catch (Exception e) {
            System.out.println("----------erreur lors de la suppression-------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }

        return "suppressionDiscipline";
    }

    @RequestMapping("/recherche")
    public String recherche(@RequestParam int id_apidisciplines, Map<String, Object> model) {
        System.out.println("recherche de discipline'");

        try {
            Optional<Disciplines> ldsc = disciplinesRepository.findById(id_apidisciplines);
            ldsc.ifPresent((dsc) -> {
                dsc = ldsc.get();
                model.put("madsc", dsc);
            });
            if (ldsc.isEmpty()){
                throw new Exception("ID introuvable");
            }else{
                System.out.println("Recherche effectuée avec succès");
            }


        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }

        return "affDiscipline";
    }

}
