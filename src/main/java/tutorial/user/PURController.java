package tutorial.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @PatchMapping("/purs/change/{id}/{quantity}")
    public ResponseEntity<PUR> ChangeElement(@PathVariable Long id, @PathVariable Integer quantity) {
        try {
            PUR pur = purRepository.findById(id).get();
            pur.setQuantity(quantity);
            return new ResponseEntity<PUR>(purRepository.save(pur), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin()
    @GetMapping("/purs/getquantity/{name}")
    public Integer getQuantity(@PathVariable(value = "name") String name, Integer quantity)
        throws ResourceNotFoundException {
    PUR pur = (PUR) purRepository.findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this id :: " + quantity));
                return pur.getQuantity();
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
    @GetMapping("/purs/getdevice/{device}")
    public ResponseEntity<PUR> getDevice(@PathVariable(value = "device") String device)
            throws ResourceNotFoundException {
        PUR pur = (PUR) purRepository.findByDevice(device)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this name :: " + device));
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
    @GetMapping("/purs/getname/{id}")
    public String getPurName(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        PUR pur = purRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this id :: " + id));
        return pur.getName();
    }}