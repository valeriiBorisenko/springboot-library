package se.lexicon.springbootlibrary.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.springbootlibrary.entity.AppUser;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    AppUser findUserByUsername(String username);
    List<AppUser> findUsersByRegDateBetween(LocalDate startDate, LocalDate endDate);
    AppUser findUserByUserDetailsId(Integer id);
    AppUser findUserByUserDetailsEmailIgnoreCase(String email);
}
