package se.lexicon.springbootlibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.springbootlibrary.entity.Details;

public interface DetailsRepository extends JpaRepository<Details, Integer> {
}
