package ma.mundia.patientsmvc.Security.Services;

import ma.mundia.patientsmvc.Security.Entities.AppRoles;
import ma.mundia.patientsmvc.Security.Entities.AppUser;


public interface AccountService {
    AppUser addNewUser(String username, String password, String email, String confirmPassword);

    AppRoles addNewRole(String role);

    AppUser loadUserByUsername(String username);

    void addRoleUser(String username, String role);

    void removeRoleFromUser(String username, String role);
}
