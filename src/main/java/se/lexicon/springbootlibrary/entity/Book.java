package se.lexicon.springbootlibrary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, name = "max_loan_days")
    private int maxLoanDays;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "writtenBooks", fetch = FetchType.LAZY)
    private Set<Author> authors = new HashSet<>();

    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.getWrittenBooks().add(this);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getWrittenBooks().remove(this);
    }
}
