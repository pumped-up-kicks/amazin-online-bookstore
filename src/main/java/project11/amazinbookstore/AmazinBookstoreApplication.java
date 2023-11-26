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
			bookRepository.save(new Book("title", "publisher", "isbn", "picture", 12));
			bookRepository.save(new Book("title2", "publisher1", "isbn1", "picture1", 69));
			bookRepository.save(new Book("title3", "publisher2", "isbn2", "picture2", 80));

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AmazinBookstoreApplication.class, args);
	}

}
