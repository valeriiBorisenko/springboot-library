package se.lexicon.springbootlibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.springbootlibrary.entity.Details;

import java.time.LocalDate;
import java.util.List;

public interface DetailsRepository extends JpaRepository<Details, Integer> {
    List<Details> findDetailsByEmail(String email);
    List<Details> findDetailsByNameContains(String name);
    List<Details> findDetailsByNameIgnoreCase(String name);
    List<Details> findDetailsByBirthDate(LocalDate birthday);
}
