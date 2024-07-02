package pl.coderslab.book;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.author.Author;
import pl.coderslab.publisher.Publisher;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5)
    private String title;

    @Min(1)
    @Max(10)
    private int rating;

    @Size(max = 600)
    private String description;

    @Positive
    private int pages;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "book_author")
    @ToString.Exclude
    private Set<Author> authors = new HashSet<>();

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        if (publisher != null) {
            publisher.getBooks().add(this);
        }
    }

    public void addAuthor(Author author) {
        if (author != null) {
            this.authors.add(author);
            author.getBooks().add(this);
        }
    }
}
