package tutorial.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PURRepository extends JpaRepository<PUR, Long> {


    <T> Optional<T> findByName(String name);
}
