package project11.amazinbookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project11.amazinbookstore.model.Book;

import java.util.Optional;

/**
 * The repository for tracking books.
 * @author Bobby Ngo
 * @version 1.0
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long id);
    Optional<Book> findByTitle(String title);

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByPublisher(String publisher);
}
