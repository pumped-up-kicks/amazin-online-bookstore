package project11.amazinbookstore.configuration;

import jakarta.servlet.http.Cookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

/**
 * Provides the configuration for Spring Security for the application.
 * @author Patrick Liu, Ethan Leir
 * @version 1.2
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Determines the login security services needed by the application.
     * @param http Spring Security http configuration param.
     * @return Resulting security filter chain.
     * @throws Exception Failure processing request.
     */
    @Bean
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                antMatcher("/register"),
                                antMatcher("/processing-registration")
//                                ,antMatcher("**")
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers(antMatcher("/h2/**")))
                .headers(headers ->
                        headers.frameOptions((frameOptions) -> frameOptions.disable())
                )
                // Uncommented for testing
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.disable())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .addLogoutHandler((request, response, auth) -> {
                            for (Cookie cookie : request.getCookies()) {
                                String cookieName = cookie.getName();
                                Cookie cookieToDelete = new Cookie(cookieName, null);
                                cookieToDelete.setMaxAge(0);
                                response.addCookie(cookieToDelete);
                            }
                        })
                        .permitAll()
                );
        return http.build();
    }

    /**
     * Determines the admin security services needed by the application.
     * @param http Spring Security http configuration param.
     * @return Resulting security filter chain.
     * @throws Exception Failure processing request.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/book/admin/**", "/admin/**", "/h2/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().hasRole("ADMIN")
                )
                .httpBasic(withDefaults());
        return http.build();
    }
}
