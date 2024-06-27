package pl.coderslab.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByEmail(String email);

    Author findByPesel(String pesel);

    List<Author> findByLastName(String lastName);

    @Query("SELECT a FROM Author a WHERE a.email LIKE :email%")
    List<Author> findByEmailStartingWith(@Param("email") String email);

    @Query("SELECT a FROM Author a WHERE a.pesel LIKE :pesel%")
    List<Author> findByPeselStartingWith(@Param("pesel") String pesel);
}

