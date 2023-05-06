package tutorial.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BLOCKRepository extends JpaRepository<BLOCK, Long> {


    <T> Optional<T> findByName(String name);
    ArrayList<BLOCK> findAllByDevice(String device);
    <T> Optional<T> findByDevice(String device);

    @Query(value = "SELECT p.name FROM blocks p where p.device= :device", nativeQuery = true)
    ArrayList<String> findAllNamesByDevice(@Param("device") String device);

    @Query(value = "SELECT p.quantity FROM blocks p where p.device= :device", nativeQuery = true)
    ArrayList<String> findAllQuantitysByDevice(@Param("device") String device);

    @Query(value = "SELECT p.dscrpt FROM blocks p where p.device= :device", nativeQuery = true)
    ArrayList<String> findAllDscrptByDevice(@Param("device") String device);

    @Transactional
    @Modifying
    @Query(value = "UPDATE blocks p SET p.quantity= :quantity WHERE p.device= :device AND p.name= :name", nativeQuery = true)
    void patchByDeviceName(@Param("device") String device, @Param("name") String name, @Param("quantity") Integer quantity);

    /* @Transactional
     @Modifying*/
    @Query(value = "SELECT * FROM blocks p WHERE p.device= :device AND p.name= :name", nativeQuery = true)
    BLOCK findAllByDeviceAndName(@Param("device") String device, @Param("name") String name);
    @Query(value = "SELECT p.inst FROM blcoks p WHERE p.device= :device", nativeQuery = true)
    ArrayList<String> findAllInstByDevice(@Param("device") String device);
    @Query(value = "SELECT * FROM blocks p WHERE p.inst= true", nativeQuery = true)
    List<ODO> findAllInst();
}
