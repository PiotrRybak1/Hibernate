package pl.coderslab.book;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.author.Author;
import pl.coderslab.author.AuthorDao;
import pl.coderslab.author.AuthorRepository;
import pl.coderslab.publisher.Publisher;
import pl.coderslab.publisher.PublisherDao;

import java.util.Arrays;
import java.util.Optional;
import java.util.random.RandomGenerator;

@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookDao bookDao;
    private final CategoryRepository categoryRepository;
    private final PublisherDao publisherDao;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    @EventListener(ContextRefreshedEvent.class)
    void initializeCategories() {
        Arrays.stream(Category.Name.values())
                .map(Category::new)
                .forEach(categoryRepository::save);
    }

    private final static RandomGenerator RANDOM = RandomGenerator.getDefault();

    // Required to avoid LazyInitializationException without JOIN FETCH query
    // This normally will never be in the controller class
    @PostMapping
    @Transactional
    public ResponseEntity<String> saveBook() {
        Optional<Author> author1 = authorRepository.findById(1L);
        Optional<Author> author2 = authorRepository.findById(2L);

        if (author1.isEmpty() || author2.isEmpty()) {
            return ResponseEntity.badRequest().body("One or both authors not found");
        }

        Publisher publisher = new Publisher();
        publisher.setName("Sample Publisher " + RANDOM.nextInt(100));
        publisherDao.save(publisher);

        Book book = new Book();
        book.setPages(10);
        book.setTitle("Sample Book" + RANDOM.nextInt(100));
        book.setRating(RANDOM.nextInt(1, 11));
        book.setDescription("A description of a sample book.");
        book.setPublisher(publisher);
        book.addAuthor(author1.get());
        book.addAuthor(author2.get());

        Category randomCategory = categoryRepository.findAll()
                .get(RandomGenerator.getDefault().nextInt(3));

        book.setCategory(randomCategory);
        bookDao.save(book);
        log.info("Saved {}", book);
        return ResponseEntity.ok("Book saved: " + book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            return getBookNotFoundResponse(id);
        }
        Book book = bookOpt.get();
        book.setTitle("Updated Sample Book");
        book.setRating(RANDOM.nextInt(1, 11));
        book.setDescription("Updated description of the sample book.");
        bookDao.save(book);
        log.info("Updated {}", book);
        return ResponseEntity.ok("Book updated: " + book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBook(@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            return getBookNotFoundResponse(id);
        }
        Book book = bookOpt.get();
        log.info("Got {}", book);
        return ResponseEntity.ok(book.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            return getBookNotFoundResponse(id);
        }
        Book book = bookOpt.get();
        bookDao.deleteById(id);
        log.info("Deleted book with id {}", id);
        return ResponseEntity.ok("Book deleted");
    }

    private ResponseEntity<String> getBookNotFoundResponse(Long id) {
        log.info("Book with id {} not found", id);
        return ResponseEntity.notFound().build();
    }

    //not in task goal
    @GetMapping
    public ResponseEntity<String> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll().toString());
    }
//
//    @GetMapping("/by-rating/{rating}")
//    public ResponseEntity<String> getBooksByRating(@PathVariable Integer rating) {
//        return ResponseEntity.ok(bookRepository.findAllByRating(rating).toString());
//    }
//
//    @GetMapping("/with-publisher")
//    public ResponseEntity<String> getBooksWithPublisher() {
//        List<Book> books = bookRepository.findAllWithPublisher();
//        return ResponseEntity.ok(books.toString());
//    }
//
//    @GetMapping("/by-publisher/{publisherId}")
//    public ResponseEntity<String> getBooksByPublisher(@PathVariable Long publisherId) {
//        List<Book> books = bookRepository.findAllByPublisherId(publisherId);
//        return ResponseEntity.ok(books.toString());
//    }
//
//    @GetMapping("/by-author/{authorId}")
//    public ResponseEntity<String> getBooksByAuthor(@PathVariable Long authorId) {
//        List<Book> books = bookRepository.findAllByAuthorId(authorId);
//        return ResponseEntity.ok(books.toString());
//    }
}
