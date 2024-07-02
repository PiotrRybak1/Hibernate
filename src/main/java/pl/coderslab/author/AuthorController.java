package pl.coderslab.author;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.random.RandomGenerator;

@Slf4j
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @PostMapping
    public ResponseEntity<String> saveAuthor() {
        Author author = new Author();
        author.setFirstName("John" + RandomGenerator.getDefault().nextInt(1000));
        author.setLastName("Doe" + RandomGenerator.getDefault().nextInt(1000));
        author.setEmail("John" + RandomGenerator.getDefault().nextInt(1000) + "@doe.com");
        author.setPesel("51112032432");
        authorRepository.save(author);
        log.info("Saved {}", author);
        return ResponseEntity.ok("Author saved: " + author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            return getAuthorNotFoundResponse(id);
        }
        author.get().setFirstName("Updated John" + RandomGenerator.getDefault().nextInt(1000));
        author.get().setLastName("Updated Doe" + RandomGenerator.getDefault().nextInt(1000));
        authorRepository.save(author.get());
        log.info("Updated {}", author.get());
        return ResponseEntity.ok("Author updated: " + author.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getAuthor(@PathVariable Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            return getAuthorNotFoundResponse(id);
        }
        log.info("Got {}", author.get());
        return ResponseEntity.ok(author.get().toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            return getAuthorNotFoundResponse(id);
        }
        authorRepository.deleteById(id);
        log.info("Deleted author with id {}", id);
        return ResponseEntity.ok("Author deleted");
    }

    private ResponseEntity<String> getAuthorNotFoundResponse(Long id) {
        log.info("Author with id {} not found", id);
        return ResponseEntity.notFound().build();
    }

    //not in task goal
    @GetMapping
    public ResponseEntity<String> getAuthors() {
        return ResponseEntity.ok(authorRepository.findAll().toString());
    }
}
