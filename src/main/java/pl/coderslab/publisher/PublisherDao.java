package pl.coderslab.publisher;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class PublisherDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save (Publisher publisher){
        entityManager.persist(publisher);
    }
    public void update(Publisher publisher){
        entityManager.merge(publisher);
    }
    public Publisher findById(Long id){
        return entityManager.find(Publisher.class, id);
    }
    public void deleteById(Long id){
        Publisher publisher = entityManager.find(Publisher.class, id);
        if (publisher != null) {
            entityManager.remove(publisher);
        }
    }
    public List<Publisher> findAll(){
        Query query= entityManager.createQuery("select p from Publisher p");
        return query.getResultList();
    }

}
