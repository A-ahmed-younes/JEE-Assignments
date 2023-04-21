package ma.enset.MicroService.entities;

import ma.enset.MicroService.enums.TypeCompte;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Compte {
    @Id
    private String id;
    private Date createDate;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private TypeCompte type;
    @ManyToOne
    private Customer customer;
}
