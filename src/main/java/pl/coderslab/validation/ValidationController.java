package pl.coderslab.validation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.author.Author;
import pl.coderslab.book.Book;
import pl.coderslab.publisher.Publisher;
import javax.validation.Validator;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ValidationController {

    private final Validator validator;

    @GetMapping("/validate")
    public String testValidate(Model model) {

        List<ValidationViolationDTO> violations = Stream.of(
                        validate(Book.class, getInvalidBook()),
                        validate(Author.class, getInvalidAuthor()),
                        validate(Publisher.class, getInvalidPublisher())
                ).flatMap(List::stream)
                .toList();

        if (violations.isEmpty()) {
            log.info("No violations found");
        }

        model.addAttribute("violations", violations);
        return "validationResult";
    }

    private <T> List<ValidationViolationDTO> validate(Class<T> validatedType, T validatedObject) {
        return validator.validate(validatedObject)
                .stream()
                .peek(v -> log.info("{}: {}", v.getPropertyPath(), v.getMessage()))
                .map(v -> ValidationViolationDTO.of(validatedType, v))
                .toList();
    }

    private static Book getInvalidBook() {
        var book = new Book();
        book.setTitle("A");
        book.setRating(11);
        book.setDescription("A".repeat(601));
        book.setPages(0);
        return book;
    }

    public static Author getInvalidAuthor() {
        Author author = new Author();
        author.setFirstName("");
        author.setLastName("");
        author.setPesel("123");
        author.setEmail("invalid-email");
        return author;
    }

    public static Publisher getInvalidPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("");
        publisher.setNip("123");
        publisher.setRegon("456");
        return publisher;
    }
}

