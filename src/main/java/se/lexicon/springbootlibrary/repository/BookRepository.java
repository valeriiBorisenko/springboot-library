package se.lexicon.springbootlibrary.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.springbootlibrary.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findByIsbnIgnoreCase(String isbn);
    Book findByTitleContains(String title);
    List<Book> findByMaxLoanDaysLessThan(int maxLoanDays);
}
