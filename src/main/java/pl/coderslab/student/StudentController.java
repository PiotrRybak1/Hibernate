package pl.coderslab.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller()
@RequestMapping("/students")
public class StudentController {

    @GetMapping("/form")
    public String studentForm(Model model){
    model.addAttribute("student", new Student());
    return "/studentForm";
    }
    @PostMapping("/form")
    public String createStudent(Student student){
        log.info(student.toString());
        return "/studentForm";
    }
    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark");
    }
    @ModelAttribute("programmingSkills")
    public List<String> programingSkills() {
        return Arrays.asList("Java", "JavaScript", "Scala", "Kafka", "Python", "GoLang", "SpringBoot", "Linux");
    }
    @ModelAttribute("hobbies")
    public List<String> hobbies() {
        return Arrays.asList("running", "programming", "reading books", "traveling");
    }

}
