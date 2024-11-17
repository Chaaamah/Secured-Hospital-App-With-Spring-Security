package ma.mundia.patientsmvc.Security.Services;

import lombok.AllArgsConstructor;
import ma.mundia.patientsmvc.Security.Entities.AppRoles;
import ma.mundia.patientsmvc.Security.Entities.AppUser;
import ma.mundia.patientsmvc.Security.Repo.AppRolesRepo;
import ma.mundia.patientsmvc.Security.Repo.AppUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.PasswordAuthentication;
import java.util.UUID;

@Service
@Transactional //methode transactionnelle
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepo appUserRepo;
    private AppRolesRepo appRolesRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser appUser = appUserRepo.findByUsername(username);
        if (appUser != null) throw new RuntimeException("L'utilisateur exist deja");
        if (!password.equals(confirmPassword)) throw new RuntimeException("Mot de passe incorrect");

        appUser = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser savedAppUser = appUserRepo.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRoles addNewRole(String role) {
        AppRoles appRoles = appRolesRepo.findById(role).orElse(null);

        if (appRoles != null) throw new RuntimeException("Ce role exist deja");
        appRoles = AppRoles.builder()
                .role(role)
                .build();
        return appRolesRepo.save(appRoles);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepo.findByUsername(username);
    }

    @Override
    public void addRoleUser(String username, String role) {
        AppUser appUser = appUserRepo.findByUsername(username);
        AppRoles appRoles = appRolesRepo.findById(role).get();

        appUser.getRoles().add(appRoles);
        //appUserRepo.save(appUser);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepo.findByUsername(username);
        AppRoles appRoles = appRolesRepo.findById(role).get();

        appUser.getRoles().remove(appRoles);
    }
}
