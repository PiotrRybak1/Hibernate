package pl.coderslab.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.author.Author;
import pl.coderslab.publisher.Publisher;

import java.util.List;
import java.util.Optional;

interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    List<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.category.name = :categoryName")
    List<Book> findByCategoryName(@Param("categoryName") Category.Name name);

    List<Book> findByCategoryId(Long categoryId);

    List<Book> findByAuthorsContains(Author author);

    List<Book> findByPublisher(Publisher publisher);

    List<Book> findByRatingGreaterThanEqual(Integer rating);

    @Query("SELECT b FROM Book b WHERE b.category.name = ?1 ORDER BY b.title ASC")
    Optional<Book> findFirstByCategoryNameOrderByTitle(Category.Name categoryName);

    @Query("SELECT b FROM Book b WHERE b.rating BETWEEN :min AND :max")
    List<Book> findBooksByRatingBetween(
            @Param("min") Integer minRating,
            @Param("max") Integer maxRating);
}

