package tutorial.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface OVERRepository extends JpaRepository<OVER, Long> {


    <T> Optional<T> findByName(String name);
    ArrayList<OVER> findAllByDevice(String device);
    <T> Optional<T> findByDevice(String device);

    @Query(value = "SELECT p.name FROM overs p where p.device= :device", nativeQuery = true)
    ArrayList<String> findAllNamesByDevice(@Param("device") String device);

    @Query(value = "SELECT p.quantity FROM overs p where p.device= :device", nativeQuery = true)
    ArrayList<String> findAllQuantitysByDevice(@Param("device") String device);

    @Query(value = "SELECT p.dscrpt FROM overs p where p.device= :device", nativeQuery = true)
    ArrayList<String> findAllDscrptByDevice(@Param("device") String device);

    @Transactional
    @Modifying
    @Query(value = "UPDATE overs p SET p.quantity= :quantity WHERE p.device= :device AND p.name= :name", nativeQuery = true)
    void patchByDeviceName(@Param("device") String device, @Param("name") String name, @Param("quantity") Integer quantity);

    /* @Transactional
     @Modifying*/
    @Query(value = "SELECT * FROM overs p WHERE p.device= :device AND p.name= :name", nativeQuery = true)
    OVER findAllByDeviceAndName(@Param("device") String device, @Param("name") String name);
}
