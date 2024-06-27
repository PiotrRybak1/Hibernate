package pl.coderslab.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("publishers")
@RequiredArgsConstructor

public class PublisherController {
    private final PublisherDao pubisherDao;

    @PostMapping()
    public void savePublisher(){
        Publisher publisher = new Publisher("BlackSheep");
        pubisherDao.save(publisher);
    }
    @PutMapping("/{id}")
    public void updatePublisher(@PathVariable Long id){
        Publisher publisher = pubisherDao.findById(id);
        pubisherDao.update(publisher);
    }
    @GetMapping("/{id}")
    public String getPublisher(@PathVariable Long id){
        Publisher publisher = pubisherDao.findById(id);
        return publisher.toString();
    }
    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable Long id){
        pubisherDao.deleteById(id);
    }
    @GetMapping
    public String findAll(){
        List<Publisher> allPublishers = pubisherDao.findAll();
        allPublishers.forEach(p-> log.info(p.toString()));
        return "allPublishers";
    }

}
