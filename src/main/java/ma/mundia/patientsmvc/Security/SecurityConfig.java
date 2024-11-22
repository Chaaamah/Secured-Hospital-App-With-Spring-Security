package ma.mundia.patientsmvc.Security;

import lombok.AllArgsConstructor;
import ma.mundia.patientsmvc.Security.Services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserDetailsServiceImpl userDetailsServiceImpl;

    //@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build(),
                User.withUsername("chama2").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("chama3").password(passwordEncoder.encode("1234")).roles("USER").build()
        );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();

        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/webjars/", "/h2-console/").permitAll();

        httpSecurity.rememberMe();
//        httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER"); //Authorisation
//        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");

        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");

        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();

        httpSecurity.userDetailsService(userDetailsServiceImpl);

        return httpSecurity.build();
    }



}
