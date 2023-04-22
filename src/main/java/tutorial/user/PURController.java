package tutorial.user;

import java.util.*;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PURController {
    @Autowired
    private PURRepository purRepository;

    @CrossOrigin()
    @PostMapping("/purs/add")
    public Status AddElement(@Valid @RequestBody PUR newPur) {
        List<PUR> purs = purRepository.findAll();
        for (PUR pur : purs) {
            if (pur.equals(newPur)) {

                System.out.println(" Такая деталь уже есть, пожалуйста измените количество");
                return Status.ELEMENT_ALREADY_EXISTS;
            }
        }
        purRepository.save(newPur);
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @PatchMapping("/purs/change/{device}/{name}/{quantity}")
    public ResponseEntity<PUR> ChangeElement(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name, @PathVariable Integer quantity) {
        try {
            PUR pur = (PUR) purRepository.patchByDeviceName(device, name);
            pur.setQuantity(quantity);
            return new ResponseEntity<PUR>(purRepository.save(pur), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin()
    @GetMapping("/purs/getquantity/{device}")
    public ResponseEntity<ArrayList<String>> getQuantity(@PathVariable(value = "device") String device){
        return ResponseEntity.ok(purRepository.findAllQuantitysByDevice(device));
                }
    @CrossOrigin()
    @DeleteMapping("/purs/delete/{name}")
    public Status deletePurs(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        PUR pur = (PUR) purRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this id :: " + name));
        purRepository.delete(pur);
        return Status.SUCCESS;
    }
    @CrossOrigin()
    @DeleteMapping("/purs/deleteall")
    public Status deleteAllPurs() {
        purRepository.deleteAll();
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @GetMapping("/purs/getid/{name}")
    public Map<Long, String> getPurId(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        PUR pur = (PUR) purRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this name :: " + name));
    Map<Long, String> IdDev = new HashMap<>();
        IdDev.put(pur.getId(), pur.getDevice());
        return IdDev;
    }

    @CrossOrigin()
    @GetMapping("/purs/getdevice/{name}")
    public ResponseEntity<PUR> getDevice(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        PUR pur = (PUR) purRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this name :: " + name));
        return ResponseEntity.ok().body(pur);
    }

    @CrossOrigin()
    @GetMapping("/purs")
    public List<PUR> getPurs(){
        return purRepository.findAll();
    }
    @CrossOrigin()
    @GetMapping("/purs/{name}")
    public ResponseEntity<PUR> get1Pur(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        PUR pur = (PUR) purRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this id :: " + name));
        return ResponseEntity.ok().body(pur);
    }
    @CrossOrigin()
    @GetMapping("/purs/getname/{device}")
    public ResponseEntity<ArrayList<String>> getPurName(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(purRepository.findAllNamesByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/purs/getdscrpt/{device}")
    public ResponseEntity<ArrayList<String>> getDscrpt(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(purRepository.findAllDscrptByDevice(device));
    }
}