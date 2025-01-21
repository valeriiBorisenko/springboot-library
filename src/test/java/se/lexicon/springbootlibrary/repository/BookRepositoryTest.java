package se.lexicon.springbootlibrary.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.springbootlibrary.entity.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    Book book = new Book("138-1-26-138440-2", "Spring Boot for Beginners", 10);

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        bookRepository.save(book);
    }

    @Test
    void findByIsbnIgnoreCase() {
        Book book1 = bookRepository.findByIsbnIgnoreCase("138-1-26-138440-2");
        assertNotNull(book1, "Book not found");
        assertEquals(book.getIsbn(), book1.getIsbn());
    }

    @Test
    void findByTitleContains() {
        Book book1 = bookRepository.findByTitleContains("Spring Boot");
        assertNotNull(book1, "Book not found");
        assertEquals(book.getTitle(), book1.getTitle());
    }

    @Test
    void findByMaxLoanDaysLessThan() {
        List<Book> bookList = bookRepository.findByMaxLoanDaysLessThan(14);
        assertFalse(bookList.isEmpty(), "No books found");
        assertEquals(book.getMaxLoanDays(), bookList.get(0).getMaxLoanDays());
    }
}