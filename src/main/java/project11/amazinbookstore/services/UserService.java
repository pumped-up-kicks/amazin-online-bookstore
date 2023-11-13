package project11.amazinbookstore.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import project11.amazinbookstore.model.RegisteredUser;
import project11.amazinbookstore.model.Role;
import project11.amazinbookstore.repository.UserRepository;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final InMemoryUserDetailsManager manager;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        manager = new InMemoryUserDetailsManager();
        encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public boolean addNewUser(String username, String password, Role role) {

        if(userRepository.findByUsername(username).isPresent()){
            log.info(String.format("User account %s already exists", username));
            return false;
        }

        String hashedPassword = encoder.encode(password);
        RegisteredUser user = new RegisteredUser(username, hashedPassword, role);
        userRepository.save(user);

        manager.createUser(user);
        log.info(String.format("Created account user %s", username));
        return true;

    }

    /**
     * For Development Only
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService defaultAdminCreation() {
        String hashedPassword = encoder.encode("admin");

        UserDetails user = org.springframework.security.core.userdetails.User.withUsername("admin")
                .password(hashedPassword)
                .roles("ADMIN")
                .build();

        manager.createUser(user);

        return manager;
    }
}
