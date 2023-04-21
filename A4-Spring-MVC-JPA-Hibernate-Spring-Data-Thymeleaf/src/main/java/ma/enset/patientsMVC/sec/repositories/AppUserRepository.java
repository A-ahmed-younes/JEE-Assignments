package ma.enset.patientsMVC.sec.repositories;

import ma.enset.patientsMVC.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
