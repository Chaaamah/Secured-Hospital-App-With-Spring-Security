package ma.mundia.patientsmvc;

import ma.mundia.patientsmvc.Entities.Patient;
import ma.mundia.patientsmvc.Repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return  args -> {
            /*patientRepository.save(new Patient(null, "Chama", new Date(), false, 12));
            patientRepository.save(new Patient(null, "Jalila", new Date(), true, 400));
            patientRepository.save(new Patient(null, "Bouchra", new Date(), false, 120));
            patientRepository.save(new Patient(null, "Amine", new Date(), true, 30));*/

            patientRepository.findAll().forEach( p -> {
                System.out.println(p.getNom());
            });
        };
    }
}
