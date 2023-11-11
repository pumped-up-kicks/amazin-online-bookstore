package project11.amazinbookstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                antMatcher("/register"),
                                antMatcher("/processing-registration")
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // Uncommented for testing
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.disable())
                .formLogin(withDefaults());
        return http.build();
    }

}
