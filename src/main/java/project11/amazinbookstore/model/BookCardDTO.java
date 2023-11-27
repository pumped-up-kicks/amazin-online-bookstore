package project11.amazinbookstore.model;

import org.springframework.security.core.Authentication;

public class BookCardDTO {
    public AuthoritiesDTO authorities;
    public BookRequestDTO bookRequest;

    public BookCardDTO(Authentication auth) {
        this.authorities = new AuthoritiesDTO(auth);
        this.bookRequest = new BookRequestDTO();
    }
}
