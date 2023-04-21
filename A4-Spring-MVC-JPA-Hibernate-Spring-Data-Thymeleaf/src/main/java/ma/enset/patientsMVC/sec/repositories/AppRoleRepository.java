package ma.enset.patientsMVC.sec.repositories;

import ma.enset.patientsMVC.sec.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName( String roleName );
}
