package project11.amazinbookstore.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class AuthoritiesDTO {

    List<GrantedAuthority> authorities;

    public AuthoritiesDTO(Authentication auth) {
        this.authorities = new ArrayList<>(auth.getAuthorities());
    }

    private boolean searchRole(String role) {
        return authorities.stream().anyMatch(a -> a.getAuthority().equals(role));
    }

    public boolean isAdmin() {
        return searchRole("ROLE_ADMIN");
    }

    public boolean isUser() {
        return searchRole("USER");
    }

    public boolean isAnonymous() {
        return searchRole("ROLE_ANONYMOUS");
    }

}
