package se.lexicon.springbootlibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.springbootlibrary.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    List<Author> findByFirstName(String firstName);
    List<Author> findByLastName(String lastName);
    @Query( "SELECT a FROM Author a WHERE a.firstName LIKE %:name% OR a.lastName LIKE %:name%")
    List<Author> findByFirstNameOrLastNameContains(@Param("name") String name);
    //@Query("SELECT a FROM Author a JOIN FETCH a.writtenBooks b WHERE b.id = :bookId")
    List<Author> findByWrittenBooksId(int bookId);
    @Query("UPDATE Author a SET a.firstName = :firstName, a.lastName = :lastName WHERE a.id = :author_id")
    void updateAuthorsNameById(@Param("author_id") Integer authorId, @Param("firstName") String firstName, @Param("lastName") String lastName);
    void deleteAuthorById(int id);
}
