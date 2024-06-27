package pl.coderslab.publisher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Publisher findByNip(String nip);

    Publisher findByRegon(String regon);
}

