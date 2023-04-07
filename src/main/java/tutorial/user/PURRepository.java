package tutorial.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PURRepository extends JpaRepository<PUR, Long> {
    
}
