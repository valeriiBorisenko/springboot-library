package se.lexicon.springbootlibrary.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.springbootlibrary.entity.AppUser;
import se.lexicon.springbootlibrary.entity.Book;
import se.lexicon.springbootlibrary.entity.BookLoan;
import se.lexicon.springbootlibrary.entity.Details;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookLoanRepositoryTest {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    Details details = new Details("karlson@gmail.com", "Karlson Villy", LocalDate.of(1990, 1, 1));
    Book book = new Book("138-1-26-138440-2", "Spring Boot for Beginners", 10);
    AppUser appUser = new AppUser("Karlson", "1234", LocalDate.of(2024, 10, 10), details);
    BookLoan bookLoan = new BookLoan(LocalDate.now(), LocalDate.now().plusDays(10), false, appUser, book);

    @BeforeEach
    void setUp() {
        bookLoanRepository.deleteAll();
        bookLoanRepository.save(bookLoan);
    }

    @Test
    void findById() {
        BookLoan savedBookLoan = bookLoanRepository.findById(bookLoan.getId());
        assertNotNull(savedBookLoan, "No book loans found");
    }

    @Test
    void findByBorrowerId() {
        List<BookLoan> bookLoanList = bookLoanRepository.findByBorrowerId(appUser.getId());
        assertFalse(bookLoanList.isEmpty(), "No book loans found");
        assertEquals(bookLoan.getBorrower().getId(), bookLoanList.get(0).getBorrower().getId());
    }

    @Test
    void findByBookId() {
        List<BookLoan> bookLoanList = bookLoanRepository.findByBookId(book.getId());
        assertFalse(bookLoanList.isEmpty(), "No book loans found");
        assertEquals(bookLoan.getBook().getId(), bookLoanList.get(0).getBook().getId());
    }

    @Test
    void findByReturnedFalse() {
        List<BookLoan> bookLoanList = bookLoanRepository.findByReturnedFalse();
        assertNotNull(bookLoanList, "No book loans found");
    }

    @Test
    void findOverdueUnreturnedLoans() {
        List<BookLoan> bookLoanList = bookLoanRepository.findOverdueUnreturnedLoans();
        assertNotNull(bookLoanList, "No book loans found");
    }

    @Test
    void findByLoanDateBetween() {
        List<BookLoan> bookLoanList = bookLoanRepository.findByLoanDateBetween(LocalDate.now(), LocalDate.now().plusDays(14));
        assertNotNull(bookLoanList, "No book loans found");
    }

    @Test
    void markAsReturnedByLoanId() {

    }
}
