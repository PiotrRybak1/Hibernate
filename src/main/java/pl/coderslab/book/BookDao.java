package pl.coderslab.book;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Book book) {
        entityManager.persist(book);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public Book findById(Long id) {
        TypedQuery<Book> query = entityManager.createQuery("""
                        SELECT b FROM Book b
                        LEFT JOIN FETCH b.authors
                        WHERE b.id = :id
                        """, Book.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    public void deleteById(Long id) {
        Book book = entityManager.find(Book.class, id);
        if (book != null) {
            entityManager.remove(book);
        }
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("""
                SELECT DISTINCT b FROM Book b
                LEFT JOIN FETCH b.authors
                """, Book.class);
        return query.getResultList();
    }

    public List<Book> findAllByRating(int rating) {
        TypedQuery<Book> query = entityManager.createQuery("""
                        SELECT b FROM Book b
                        WHERE b.rating = :rating
                        """, Book.class)
                .setParameter("rating", rating);
        return query.getResultList();
    }

    public List<Book> findAllWithPublisher() {
        TypedQuery<Book> query = entityManager.createQuery("""
                SELECT b FROM Book b
                WHERE b.publisher IS NOT NULL
                """, Book.class);
        return query.getResultList();
    }

    public List<Book> findAllByPublisherId(Long publisherId) {
        TypedQuery<Book> query = entityManager.createQuery("""
                        SELECT b FROM Book b
                        WHERE b.publisher.id = :publisherId
                        """, Book.class)
                .setParameter("publisherId", publisherId);
        return query.getResultList();
    }

    public List<Book> findAllByAuthorId(Long authorId) {
        TypedQuery<Book> query = entityManager.createQuery("""
                        SELECT b FROM Book b
                        JOIN b.authors a
                        WHERE a.id = :authorId
                        """, Book.class)
                .setParameter("authorId", authorId);
        return query.getResultList();
    }
}

