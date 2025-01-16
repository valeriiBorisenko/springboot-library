package se.lexicon.springbootlibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.lexicon.springbootlibrary.entity.AppUser;

import java.time.LocalDate;
import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    AppUser findUserByUsername(String username);
    List<AppUser> findUsersByRegDateBetween(LocalDate startDate, LocalDate endDate);
    AppUser findUserByUserDetailsId(Integer id);
    AppUser findUserByUserDetailsEmailIgnoreCase(String email);
    @Query("select u from  AppUser u  where u.userDetails.email= :email ")
    List<AppUser> findUsersDetailsEmail(String userDetailsEmail);
}
