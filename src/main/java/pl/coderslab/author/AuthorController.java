package pl.coderslab.author;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorDao authorDao;

    @PostMapping()
    public void saveAuthor() {
        Author author = new Author("John", "Doe");
        authorDao.saveAuthor(author);

    }

    @PutMapping("/{id}")
    public void updateAuthor(@PathVariable Long id) {
        Author author = authorDao.findById(id);
        authorDao.update(author);
    }

    @GetMapping("/{id}")
    public String getAuthorById(@PathVariable Long id) {
        Author author = authorDao.findById(id);
        return author.toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorDao.deleteById(id);
    }
    @GetMapping
    public String findAll(){
        List<Author> allAuthors = authorDao.findAll();
        allAuthors.forEach(a-> log.info(a.toString()));
        return "allAuthors";
    }

}
