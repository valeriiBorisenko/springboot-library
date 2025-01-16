package se.lexicon.springbootlibrary.repository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.springbootlibrary.entity.Details;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DetailsRepositoryTest {

    @Autowired
    private DetailsRepository detailsRepository;
    Details details = new Details("karl@test.com", "Karl", LocalDate.of(2000, 10, 10));

    @BeforeEach
    void setUp() {
        detailsRepository.deleteAll();
        detailsRepository.save(details);
    }

    @Test
    void findDetailsByEmail() {
        List<Details> details1 = detailsRepository.findDetailsByEmail("karl@test.com");
        assertFalse(details1.isEmpty(), "No details found for the given email");
        assertEquals(details.getEmail(), details1.get(0).getEmail());
    }

    @Test
    void findDetailsByNameContains() {
        List<Details> details1 = detailsRepository.findDetailsByNameContains("Karl");
        assertFalse(details1.isEmpty(), "No details found for the given name");
        assertEquals(details.getName(), details1.get(0).getName());
    }

    @Test
    void findDetailsByNameIgnoreCase() {
        List<Details> details1 = detailsRepository.findDetailsByNameIgnoreCase("karl");
        assertFalse(details1.isEmpty(), "No details found for the given name");
        assertEquals(details.getName(), details1.get(0).getName());
    }

    @Test
    void findDetailsByBirthDate() {
        List<Details> details1 = detailsRepository.findDetailsByBirthDate(LocalDate.of(2000, 10, 10));
        assertFalse(details1.isEmpty(), "No details found for the given birth date");
        assertEquals(details.getBirthDate(), details1.get(0).getBirthDate());
    }
}