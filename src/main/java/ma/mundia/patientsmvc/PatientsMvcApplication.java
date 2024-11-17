package ma.mundia.patientsmvc;

import ma.mundia.patientsmvc.Entities.Patient;
import ma.mundia.patientsmvc.Repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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

        };
    }

    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {

            UserDetails userDetails = jdbcUserDetailsManager.loadUserByUsername("user");

            if(userDetails == null)
                jdbcUserDetailsManager.createUser(
                    User.withUsername("user").password(passwordEncoder.encode("1234")).roles("USER").build()
            );

            UserDetails userDetails1 = jdbcUserDetailsManager.loadUserByUsername("user1");

            if(userDetails1 == null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build()
                );

            UserDetails userDetails2 = jdbcUserDetailsManager.loadUserByUsername("admin1");

            if(userDetails2 == null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin1").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
                );

        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
