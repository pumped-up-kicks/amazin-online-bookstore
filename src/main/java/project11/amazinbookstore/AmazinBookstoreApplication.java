package project11.amazinbookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import project11.amazinbookstore.model.Book;
import project11.amazinbookstore.model.RegisteredUser;
import project11.amazinbookstore.model.Role;
import project11.amazinbookstore.repository.BookRepository;
import project11.amazinbookstore.repository.UserRepository;
import project11.amazinbookstore.services.UserService;

/**
 * Entrypoint of the application.
 * @author Zakaria Ismail
 * @version 1.0
 */
@SpringBootApplication
public class AmazinBookstoreApplication {
	@Bean
	CommandLineRunner commandLineRunner (UserService userService, BookRepository bookRepository) {
		return args -> {
			userService.addNewUser("user1", "password", Role.USER);

			// add multiple books
			bookRepository.save(new Book("Brave New World", "HarperCollins Publishers", "9780062696120", "BNW", 12, 200));
			bookRepository.save(new Book("The Last Wish", "Orbit", "9780316029186", "the_last_wish", 69, 300));
			bookRepository.save(new Book("A Fatal Grace", "Minotaur Books", "9780312541163", "a_fatal_grace", 80, 240));

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AmazinBookstoreApplication.class, args);
	}

}
