package se.lexicon.springbootlibrary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class AppUser {
    private int id;
    private String userName;
    private String password;
    private LocalDate regDate;

    @OneToOne
    private Details userDetails;
}
