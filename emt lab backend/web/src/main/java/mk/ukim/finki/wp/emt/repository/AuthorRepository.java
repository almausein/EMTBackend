package mk.ukim.finki.wp.emt.repository;

import mk.ukim.finki.wp.emt.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
