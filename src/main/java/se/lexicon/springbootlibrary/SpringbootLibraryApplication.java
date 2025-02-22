package se.lexicon.springbootlibrary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.lexicon.springbootlibrary.entity.*;
import se.lexicon.springbootlibrary.repository.*;

import java.time.LocalDate;

@SpringBootApplication
public class SpringbootLibraryApplication {

	public static void main(String[] args) {

		System.out.println("Spring starting");
		System.out.println("-------------------");

		SpringApplication.run(SpringbootLibraryApplication.class, args);

		System.out.println("-------------------");
		System.out.println("Spring ending");

	}

	@Bean
	CommandLineRunner runner(
			AppUserRepository appuserRepository,
			DetailsRepository detailsRepository,
			BookRepository bookRepository,
			BookLoanRepository bookLoanRepository,
			AuthorRepository authorRepository
	) {
		return args -> {

			Details details = new Details("karlson@gmail.com", "Karlson Villy", LocalDate.of(1990, 1, 1));
			Book book = new Book("138-1-26-138440-2", "Spring Boot for Beginners", 10);
			AppUser appUser = new AppUser("Karlson", "1234", LocalDate.of(2024, 10, 10), details);

			BookLoan bookLoan = new BookLoan(LocalDate.now(), LocalDate.now().plusDays(10), false, appUser, book);
			bookLoanRepository.save(bookLoan);

			Author author = new Author("Karl", "Villy");
			author.addBook(book);

			authorRepository.save(author);

			System.out.println("-------------------");
			System.out.println("book loan saved: " + bookLoan);
			System.out.println("-------------------");
			System.out.println("Find user by username: " + appuserRepository.findUserByUsername("Karlson"));
			System.out.println("-------------------");
			System.out.println("Find books with max loan days less than 14: " + bookRepository.findByMaxLoanDaysLessThan(14));
			System.out.println("-------------------");
			System.out.println("Find overdue book loans: " + bookLoanRepository.findByReturnedFalse());
			System.out.println("-------------------");
			System.out.println(authorRepository.findByWrittenBooksId(book.getId()));
			System.out.println("-------------------");

		};
	}
}
