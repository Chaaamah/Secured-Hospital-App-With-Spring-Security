package ma.mundia.patientsmvc.Security.Repo;

import ma.mundia.patientsmvc.Security.Entities.AppRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRolesRepo extends JpaRepository<AppRoles, String> {
}
