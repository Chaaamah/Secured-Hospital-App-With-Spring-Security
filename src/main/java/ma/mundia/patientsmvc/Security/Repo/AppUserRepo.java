package ma.mundia.patientsmvc.Security.Repo;

import ma.mundia.patientsmvc.Security.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
