package project11.amazinbookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Tracks account information for a user.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegisteredUser implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Creates a RegisteredUser object with the given username, password, and role.
     * @param username the username of the user.
     * @param password the password of the user.
     * @param role the role of the user (USER or ADMIN for determining level of authorization).
     */
    public RegisteredUser(String username, String password, Role role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets the authorities permitted to the user.
     * @return the list of authorities of the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Gets whether the account is not expired.
     * @return true if the account is not expired, false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Gets whether the account is not locked.
     * @return true if the account is not locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Gets whether the account credentials have not expired.
     * @return true if the credentials have not expired, false otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Gets whether the account is enabled.
     * @return true if the account is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
