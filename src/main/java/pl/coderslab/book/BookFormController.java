package pl.coderslab.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.author.Author;
import pl.coderslab.author.AuthorDao;
import pl.coderslab.publisher.PublisherDao;
import pl.coderslab.publisher.Publisher;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/books/form")
@RequiredArgsConstructor
public class BookFormController {

    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final BookDao bookDao;

    @GetMapping
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookForm";

    }

    @PostMapping
    public String processBookForm(Book book) {
        if (book.getId() == null) {
            bookDao.save(book);
            log.info("Saved {}", book);
        } else {
            bookDao.update(book);
            log.info("Updated {}", book);
        }
        return "redirect:/books/all";
    }

    @GetMapping("/all")
    public String showAllBooks() {
        return "bookList";
    }

    @ModelAttribute("publishers")
    public List<Publisher> getPublishers() {
        return publisherDao.findAll();
    }

    @ModelAttribute("books")
    public List<Book> getBooks() {
        return bookDao.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> getAuthors() {
        return authorDao.findAll();
    }
}
