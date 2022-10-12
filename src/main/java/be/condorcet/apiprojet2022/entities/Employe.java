package be.condorcet.apiprojet2022.entities;

import lombok.*;
import javax.management.ConstructorParameters;
import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIEMPLOYE", schema = "ora3", catalog = "orcl")
public class Employe {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "employe_generator")
    @SequenceGenerator(name="employe_generator", sequenceName =
            "APIEMPLOYE_SEQ", allocationSize=1)
    private Integer id_apiemploye;
    @NonNull
    private String matricule;
    @NonNull
    private String nom;
    @NonNull
    private String prenom;
    @NonNull
    private String tel;
    @NonNull
    private String mail;
}
