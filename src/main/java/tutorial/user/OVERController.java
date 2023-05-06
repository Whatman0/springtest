package tutorial.user;

import java.util.*;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OVERController {
    @Autowired
    private OVERRepository overRepository;

    @CrossOrigin()
    @PostMapping("/overs/add")
    public Status AddElement(@Valid @RequestBody OVER newOver) {
        List<OVER> overs = overRepository.findAll();
        for (OVER over : overs) {
            if (over.equals(newOver)) {

                System.out.println(" Такая деталь уже есть, пожалуйста измените количество");
                return Status.ELEMENT_ALREADY_EXISTS;
            }
        }
        overRepository.save(newOver);
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @PatchMapping("/overs/change/{device}/{name}/{quantity}")
    public void ChangeElement(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name, @PathVariable Integer quantity) {
        // try {
        overRepository.patchByDeviceName(device, name, quantity);
        //over.setQuantity(quantity);
        // return new ResponseEntity<>(overRepository.save(over), HttpStatus.OK);
        //catch (Exception e) {

    }


    @CrossOrigin()
    @GetMapping("/overs/getquantity/{device}")
    public ResponseEntity<ArrayList<String>> getQuantity(@PathVariable(value = "device") String device){
        return ResponseEntity.ok(overRepository.findAllQuantitysByDevice(device));
    }
    @CrossOrigin()
    @DeleteMapping("/overs/delete/{device}/{name}")
    public Status deleteOvers(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name){
        OVER over = overRepository.findAllByDeviceAndName(device, name);
        overRepository.delete(over);
        return Status.SUCCESS;
    }
    @CrossOrigin()
    @DeleteMapping("/overs/deleteall")
    public Status deleteAllOvers() {
        overRepository.deleteAll();
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @GetMapping("/overs/getid/{name}")
    public Map<Long, String> getOverId(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        OVER over = (OVER) overRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Over not found for this name :: " + name));
        Map<Long, String> IdDev = new HashMap<>();
        IdDev.put(over.getId(), over.getDevice());
        return IdDev;
    }

    @CrossOrigin()
    @GetMapping("/overs/getdevice/{name}")
    public ResponseEntity<OVER> getDevice(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        OVER over = (OVER) overRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Over not found for this name :: " + name));
        return ResponseEntity.ok().body(over);
    }

    @CrossOrigin()
    @GetMapping("/overs")
    public List<OVER> getOvers(){
        return overRepository.findAll();
    }
    @CrossOrigin()
    @GetMapping("/overs/{name}")
    public ResponseEntity<OVER> get1Over(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        OVER over = (OVER) overRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Over not found for this id :: " + name));
        return ResponseEntity.ok().body(over);
    }
    @CrossOrigin()
    @GetMapping("/overs/getname/{device}")
    public ResponseEntity<ArrayList<String>> getOverName(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(overRepository.findAllNamesByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/overs/getdscrpt/{device}")
    public ResponseEntity<ArrayList<String>> getDscrpt(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(overRepository.findAllDscrptByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/overs/getinst/{device}")
    public ResponseEntity<ArrayList<String>> getInst(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(overRepository.findAllInstByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/overs/getallinst/")
    public ResponseEntity<List<ODO>> getAllInst() {
        return ResponseEntity.ok(overRepository.findAllInst());
    }
    @CrossOrigin()
    @GetMapping("/overs/getnamebyinst/{device}")
    public ResponseEntity<ArrayList<String>> getNameByInst(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(overRepository.findAllNamesByDeviceAndInst(device));
    }
    @CrossOrigin()
    @GetMapping("/overs/getquantitybyinst/{device}")
    public ResponseEntity<ArrayList<String>> getQuantityByInst(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(overRepository.findAllQuantitysByDeviceAndInst(device));
    }
    @CrossOrigin()
    @GetMapping("/overs/getdscrptbyinst/{device}")
    public ResponseEntity<ArrayList<String>> getDscrptByInst(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(overRepository.findAllDscrptByDeviceAndInst(device));
    }
}