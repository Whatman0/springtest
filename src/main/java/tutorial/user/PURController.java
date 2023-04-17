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
    @GetMapping("/purs/getquantity/{id}/{quantity}")
    public ResponseEntity<PUR> getQuantity(@PathVariable(value = "id") Long id, @PathVariable(value = "quantity") Integer quantity)
        throws ResourceNotFoundException {
    PUR quant = purRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this id :: " + quantity));
                return ResponseEntity.ok().body(quant);
                }
    @CrossOrigin()
    @DeleteMapping("/purs/delete/{id}")
    public Status deletePurs(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        PUR pur = purRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this id :: " + id));
        purRepository.delete(pur);
        return Status.SUCCESS;
    }
    @CrossOrigin()
    @GetMapping("/purs/getid{id}")
    public ResponseEntity<PUR> getPurId(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        PUR pur = purRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this id :: " + id));
        return ResponseEntity.ok().body(pur);
    }
    @CrossOrigin()
    @GetMapping("/purs/getname/{id}/{name}")
    public ResponseEntity<PUR> getPurName(@PathVariable(value = "id") Long id, @PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        PUR pur = purRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pur not found for this id :: " + name));
        return ResponseEntity.ok().body(pur);
    }}