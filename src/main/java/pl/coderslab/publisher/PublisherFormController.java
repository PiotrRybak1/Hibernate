package pl.coderslab.publisher;

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
@RequestMapping("/publishers/form")
@RequiredArgsConstructor
public class PublisherFormController {

    private final PublisherDao publisherDao;

    @GetMapping
    public String showPublisherForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisherForm";
    }

    @PostMapping
    public String processPublisherForm(@ModelAttribute @Valid Publisher publisher, BindingResult result) {
        if (result.hasErrors()) {
            return "publisherForm";
        }

        if (publisher.getId() == null) {
            publisherDao.save(publisher);
            log.info("Saved {}", publisher);
        } else {
            publisherDao.update(publisher);
            log.info("Updated {}", publisher);
        }
        return "redirect:/publishers/form/all";
    }

    @PostMapping("/edit/{id}")
    public String editPublisher(@PathVariable Long id, Model model) {
        Publisher publisher = publisherDao.findById(id);
        model.addAttribute("publisher", publisher);
        return "publisherForm";
    }

    @PostMapping("/delete/{id}")
    public String deletePublisher(@PathVariable Long id) {
        publisherDao.deleteById(id);
        log.info("Deleted publisher with id {}", id);
        return "redirect:/publishers/form/all";
    }

    @GetMapping("/all")
    public String showAllPublishers() {
        return "publisherList";
    }

    @ModelAttribute("publishers")
    public List<Publisher> getPublishers() {
        return publisherDao.findAll();
    }
}

