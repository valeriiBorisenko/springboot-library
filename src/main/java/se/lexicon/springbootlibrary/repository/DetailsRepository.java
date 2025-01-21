package se.lexicon.springbootlibrary.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.springbootlibrary.entity.Details;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {
    List<Details> findDetailsByEmail(String email);
    List<Details> findDetailsByNameContains(String name);
    List<Details> findDetailsByNameIgnoreCase(String name);
    List<Details> findDetailsByBirthDate(LocalDate birthday);
}
