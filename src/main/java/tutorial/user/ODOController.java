package tutorial.user;

import java.util.*;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ODOController {
    @Autowired
    private ODORepository odoRepository;

    @CrossOrigin()
    @PostMapping("/odos/add")
    public Status AddElement(@Valid @RequestBody ODO newOdo) {
        List<ODO> odos = odoRepository.findAll();
        for (ODO odo : odos) {
            if (odo.equals(newOdo)) {

                System.out.println(" Такая деталь уже есть, пожалуйста измените количество");
                return Status.ELEMENT_ALREADY_EXISTS;
            }
        }
        odoRepository.save(newOdo);
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @PatchMapping("/odos/change/{device}/{name}/{quantity}")
    public void ChangeElement(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name, @PathVariable Integer quantity) {
        // try {
        odoRepository.patchByDeviceName(device, name, quantity);
        //odo.setQuantity(quantity);
        // return new ResponseEntity<>(odoRepository.save(odo), HttpStatus.OK);
        //catch (Exception e) {

    }


    @CrossOrigin()
    @GetMapping("/odos/getquantity/{device}")
    public ResponseEntity<ArrayList<String>> getQuantity(@PathVariable(value = "device") String device){
        return ResponseEntity.ok(odoRepository.findAllQuantitysByDevice(device));
    }
    @CrossOrigin()
    @DeleteMapping("/odos/delete/{device}/{name}")
    public Status deleteOdos(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name){
        ODO odo = odoRepository.findAllByDeviceAndName(device, name);
        odoRepository.delete(odo);
        return Status.SUCCESS;
    }
    @CrossOrigin()
    @DeleteMapping("/odos/deleteall")
    public Status deleteAllOdos() {
        odoRepository.deleteAll();
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @GetMapping("/odos/getid/{name}")
    public Map<Long, String> getOdoId(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        ODO odo = (ODO) odoRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Odo not found for this name :: " + name));
        Map<Long, String> IdDev = new HashMap<>();
        IdDev.put(odo.getId(), odo.getDevice());
        return IdDev;
    }

    @CrossOrigin()
    @GetMapping("/odos/getdevice/{name}")
    public ResponseEntity<ODO> getDevice(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        ODO odo = (ODO) odoRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Odo not found for this name :: " + name));
        return ResponseEntity.ok().body(odo);
    }

    @CrossOrigin()
    @GetMapping("/odos")
    public List<ODO> getOdos(){
        return odoRepository.findAll();
    }
    @CrossOrigin()
    @GetMapping("/odos/{name}")
    public ResponseEntity<ODO> get1Odo(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        ODO odo = (ODO) odoRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Odo not found for this id :: " + name));
        return ResponseEntity.ok().body(odo);
    }
    @CrossOrigin()
    @GetMapping("/odos/getname/{device}")
    public ResponseEntity<ArrayList<String>> getOdoName(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(odoRepository.findAllNamesByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/odos/getdscrpt/{device}")
    public ResponseEntity<ArrayList<String>> getDscrpt(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(odoRepository.findAllDscrptByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/odos/getinst/{device}")
    public ResponseEntity<ArrayList<String>> getInst(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(odoRepository.findAllInstByDevice(device));
    }
}