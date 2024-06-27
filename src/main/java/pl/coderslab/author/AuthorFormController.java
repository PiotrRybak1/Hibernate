package pl.coderslab.author;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/authors/form")
@RequiredArgsConstructor
public class AuthorFormController {

    private final AuthorDao authorDao;

    @GetMapping
    public String showAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "authorForm";
    }

    @PostMapping
    public String processAuthorForm(@ModelAttribute @Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "authorForm";
        }

        if (author.getId() == null) {
            log.info("Saved {}", author);
            authorDao.saveAuthor(author);
        } else {
            authorDao.update(author);
            log.info("Updated {}", author);
        }
        return "redirect:/authors/form/all";
    }

    @PostMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id, Model model) {
        Author author = authorDao.findById(id);
        model.addAttribute("author", author);
        return "authorForm";
    }

    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorDao.deleteById(id);
        log.info("Deleted author with id {}", id);
        return "redirect:/authors/form/all";
    }

    @GetMapping("/all")
    public String showAllAuthors() {
        return "authorList";
    }

    @ModelAttribute("authors")
    public List<Author> getAuthors() {
        return authorDao.findAll();
    }
}

