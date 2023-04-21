package ma.enset.MicroService.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "customer")
    // avoid infinite loop exception
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    private List<Compte> comptes;
}
