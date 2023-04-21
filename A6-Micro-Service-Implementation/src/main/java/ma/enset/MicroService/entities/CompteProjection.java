package ma.enset.MicroService.entities;

import ma.enset.MicroService.enums.TypeCompte;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Compte.class, name = "compteProjection")
public interface CompteProjection {
    String getId();

    TypeCompte getType();
}
