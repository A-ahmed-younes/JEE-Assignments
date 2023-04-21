package ma.enset.patientsMVC;

import ma.enset.patientsMVC.entities.Patient;
import ma.enset.patientsMVC.repositories.PatientRepository;
import ma.enset.patientsMVC.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMVC {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMVC.class, args);
    }

//    @Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser( "Ahmed", "1234", "1234" );
            securityService.saveNewUser( "Achraf", "1234", "1234" );
            securityService.saveNewUser( "Anas", "1234", "1234" );

            securityService.saveNewRole("USER", "user");
            securityService.saveNewRole("ADMIN", "admin");

            securityService.addRoleToUser("Ahmed", "ADMIN");
            securityService.addRoleToUser("Ahmed", "USER");
            securityService.addRoleToUser("Anas", "USER");
            securityService.addRoleToUser("Achraf", "USER");
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository etudiantRepository){
        return args -> {
            etudiantRepository.save(
                    new Patient( null, "Ahmed", new Date(), true));
            etudiantRepository.save(
                    new Patient( null, "Achraf", new Date(), true));
            etudiantRepository.save(
                    new Patient( null, "Anas", new Date(), true));
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
