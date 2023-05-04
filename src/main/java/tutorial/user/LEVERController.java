package tutorial.user;

import java.util.*;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LEVERController {
    @Autowired
    private LEVERRepository leverRepository;

    @CrossOrigin()
    @PostMapping("/levers/add")
    public Status AddElement(@Valid @RequestBody LEVER newLever) {
        List<LEVER> levers = leverRepository.findAll();
        for (LEVER lever : levers) {
            if (lever.equals(newLever)) {

                System.out.println(" Такая деталь уже есть, пожалуйста измените количество");
                return Status.ELEMENT_ALREADY_EXISTS;
            }
        }
        leverRepository.save(newLever);
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @PatchMapping("/levers/change/{device}/{name}/{quantity}")
    public void ChangeElement(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name, @PathVariable Integer quantity) {
        leverRepository.patchByDeviceName(device, name, quantity);
    }


    @CrossOrigin()
    @GetMapping("/levers/getquantity/{device}")
    public ResponseEntity<ArrayList<String>> getQuantity(@PathVariable(value = "device") String device){
        return ResponseEntity.ok(leverRepository.findAllQuantitysByDevice(device));
    }
    @CrossOrigin()
    @DeleteMapping("/levers/delete/{device}/{name}")
    public Status deleteLevers(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name){
        LEVER lever = leverRepository.findAllByDeviceAndName(device, name);
        leverRepository.delete(lever);
        return Status.SUCCESS;
    }
    @CrossOrigin()
    @DeleteMapping("/levers/deleteall")
    public Status deleteAllLevers() {
        leverRepository.deleteAll();
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @GetMapping("/levers/getid/{name}")
    public Map<Long, String> getLeverId(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        LEVER lever = (LEVER) leverRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this name :: " + name));
        Map<Long, String> IdDev = new HashMap<>();
        IdDev.put(lever.getId(), lever.getDevice());
        return IdDev;
    }

    @CrossOrigin()
    @GetMapping("/levers/getdevice/{name}")
    public ResponseEntity<LEVER> getDevice(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        LEVER lever = (LEVER) leverRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this name :: " + name));
        return ResponseEntity.ok().body(lever);
    }

    @CrossOrigin()
    @GetMapping("/levers")
    public List<LEVER> getLevers(){
        return leverRepository.findAll();
    }
    @CrossOrigin()
    @GetMapping("/levers/{name}")
    public ResponseEntity<LEVER> get1Lever(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        LEVER lever = (LEVER) leverRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this id :: " + name));
        return ResponseEntity.ok().body(lever);
    }
    @CrossOrigin()
    @GetMapping("/levers/getname/{device}")
    public ResponseEntity<ArrayList<String>> getLeverName(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(leverRepository.findAllNamesByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/levers/getdscrpt/{device}")
    public ResponseEntity<ArrayList<String>> getDscrpt(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(leverRepository.findAllDscrptByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/levers/getinst/{device}")
    public ResponseEntity<ArrayList<String>> getInst(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(leverRepository.findAllInstByDevice(device));
    }
}