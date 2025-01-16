package se.lexicon.springbootlibrary.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.springbootlibrary.entity.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
}
