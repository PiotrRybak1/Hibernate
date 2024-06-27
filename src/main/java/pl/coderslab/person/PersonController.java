package pl.coderslab.person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {
    public final PersonDao personDao;

    @PostMapping
    public void savePerson() {
        Person person = new Person("Zizu", "zizumizu", "zizu@gmail.com");
        PersonDetails personDetails = new PersonDetails("Zenek", "Zenkowich", "23","Francuska","Warszawa");
        person.setPersonDetails(personDetails);
        personDao.save(person);
    }

    @PutMapping("/{id}")
    public void updatePerson(@PathVariable Long id) {
        Person person = personDao.findById(id);
        if (person != null) {
            personDao.update(person);
        }
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable Long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personDao.delete(id);

    }
    @GetMapping("/form")
    public String personForm(Model model){
        model.addAttribute("person", new Person());
        return "/form";
    }
    @PostMapping("/form")
    public String personParam(@ModelAttribute Person person){
        person.setPersonDetails(new PersonDetails("Zenek", "Zenkowich", "23","Francuska","Warszawa"));
        personDao.save(person);
        return "/form";
    }
    @GetMapping("/form")
    public String showPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "personForm";
    }

    @ResponseBody
    @PostMapping("/form")
    public ResponseEntity<String> processPersonForm(@ModelAttribute Person person) {
        person.setPersonDetails(getPersonDetails());

        personDao.save(person);

        return ResponseEntity.ok("Saved " + person);
    }

    private PersonDetails getPersonDetails() {
        PersonDetails details = new PersonDetails();
        details.setFirstName("John");
        details.setLastName("Doe");
        details.setStreetNumber("1234");
        details.setStreet("Main St");
        details.setCity("Sample City");
        return details;
    }

    private ResponseEntity<String> getPersonNotFoundResponse(Long id) {
        log.info("Person with id {} not found", id);
        return ResponseEntity.notFound().build();
    }

}
