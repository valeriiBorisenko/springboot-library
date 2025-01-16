package se.lexicon.springbootlibrary.repository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.springbootlibrary.entity.AppUser;
import se.lexicon.springbootlibrary.entity.Details;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private DetailsRepository detailsRepository;

    Details details = new Details("karl@test.com", "Karl", LocalDate.of(2000, 10, 10));
    AppUser appUser = new AppUser("Karl", "1234", LocalDate.of(2024, 10, 10), details);

    @BeforeEach
    void setUp() {
        appUserRepository.deleteAll();
        detailsRepository.deleteAll();
        detailsRepository.save(details);
        appUserRepository.save(appUser);
    }

    @Test
    void findUserByUsername() {
        AppUser appUser1 = appUserRepository.findUserByUsername("Karl");
        assertNotNull(appUser1, "User not found");
        assertEquals(appUser.getUsername(), appUser1.getUsername());
    }

    @Test
    void findUsersByRegDateBetween() {
        List<AppUser> appUser1 = appUserRepository.findUsersByRegDateBetween(LocalDate.of(2024, 9, 1), LocalDate.of(2024, 11, 11));
        assertFalse(appUser1.isEmpty(), "No users found");
        assertEquals(appUser.getRegDate(), appUser1.get(0).getRegDate());
    }

    @Test
    void findUserByUserDetailsId() {
        AppUser appUser1 = appUserRepository.findUserByUserDetailsId(1);
        assertNotNull(appUser1, "User not found");
        assertEquals(appUser.getUserDetails().getId(), appUser1.getUserDetails().getId());
    }

    @Test
    void findUserByUserDetailsEmailIgnoreCase() {
        AppUser appUser1 = appUserRepository.findUserByUserDetailsEmailIgnoreCase("karl@test.com");
        assertNotNull(appUser1, "User not found");
        assertEquals(appUser.getUserDetails().getEmail(), appUser1.getUserDetails().getEmail());
    }
}