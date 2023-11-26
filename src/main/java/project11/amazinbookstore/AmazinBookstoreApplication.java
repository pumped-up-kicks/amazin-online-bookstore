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
			bookRepository.save(new Book("Brave New World", "HarperCollins Publishers", "9780062696120", "img/BNW.jpg", 12));
			bookRepository.save(new Book("The Last Wish", "Orbit", "9780316029186", "img/the_last_wish.jpg", 69));
			bookRepository.save(new Book("A Fatal Grace", "Minotaur Books", "9780312541163", "img/a_fatal_grace.jpg", 80));

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AmazinBookstoreApplication.class, args);
	}

}
