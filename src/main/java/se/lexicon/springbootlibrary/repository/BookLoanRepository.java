package se.lexicon.springbootlibrary.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.springbootlibrary.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookLoanRepository extends CrudRepository<BookLoan, Integer> {
    BookLoan findById(int id);
    List<BookLoan> findByBorrowerId(int id);
    List<BookLoan> findByBookId(int id);
    List<BookLoan> findByReturnedFalse();

    @Query("SELECT bl FROM BookLoan bl WHERE bl.returned = false AND bl.dueDate < CURRENT_DATE")
    List<BookLoan> findOverdueUnreturnedLoans();

    @Query("SELECT bl FROM BookLoan bl WHERE bl.loanDate BETWEEN :startDate AND :endDate")
    List<BookLoan> findByLoanDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Modifying
    @Query("UPDATE BookLoan bl SET bl.returned = true WHERE bl.id = :loanId")
    int markAsReturnedByLoanId(@Param("loanId") Integer loanId);
}
