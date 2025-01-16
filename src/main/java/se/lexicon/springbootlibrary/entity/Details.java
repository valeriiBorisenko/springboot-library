package se.lexicon.springbootlibrary.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;
    private String name;
    private LocalDate birthDate;

    protected Details() {}

    public Details(int id, String email, String name, LocalDate birthDate) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Details(String email, String name, LocalDate birthDate) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;
        return id == details.id && Objects.equals(email, details.email) && Objects.equals(name, details.name) && Objects.equals(birthDate, details.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, birthDate);
    }
}
