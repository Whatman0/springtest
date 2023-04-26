package tutorial.user;

import java.util.*;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class METController {
    @Autowired
    private METRepository metRepository;

    @CrossOrigin()
    @PostMapping("/mets/add")
    public Status AddElement(@Valid @RequestBody MET newMet) {
        List<MET> mets = metRepository.findAll();
        for (MET met : mets) {
            if (met.equals(newMet)) {

                System.out.println(" Такая деталь уже есть, пожалуйста измените количество");
                return Status.ELEMENT_ALREADY_EXISTS;
            }
        }
        metRepository.save(newMet);
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @PatchMapping("/mets/change/{device}/{name}/{quantity}")
    public void ChangeElement(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name, @PathVariable Integer quantity) {
        // try {
        metRepository.patchByDeviceName(device, name, quantity);
        //met.setQuantity(quantity);
        // return new ResponseEntity<>(metRepository.save(met), HttpStatus.OK);
        //catch (Exception e) {

    }


    @CrossOrigin()
    @GetMapping("/mets/getquantity/{device}")
    public ResponseEntity<ArrayList<String>> getQuantity(@PathVariable(value = "device") String device){
        return ResponseEntity.ok(metRepository.findAllQuantitysByDevice(device));
    }
    @CrossOrigin()
    @DeleteMapping("/mets/delete/{device}/{name}")
    public Status deleteMets(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name){
        MET met = metRepository.findAllByDeviceAndName(device, name);
        metRepository.delete(met);
        return Status.SUCCESS;
    }
    @CrossOrigin()
    @DeleteMapping("/mets/deleteall")
    public Status deleteAllMets() {
        metRepository.deleteAll();
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @GetMapping("/mets/getid/{name}")
    public Map<Long, String> getMetId(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        MET met = (MET) metRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Met not found for this name :: " + name));
        Map<Long, String> IdDev = new HashMap<>();
        IdDev.put(met.getId(), met.getDevice());
        return IdDev;
    }

    @CrossOrigin()
    @GetMapping("/mets/getdevice/{name}")
    public ResponseEntity<MET> getDevice(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        MET met = (MET) metRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Met not found for this name :: " + name));
        return ResponseEntity.ok().body(met);
    }

    @CrossOrigin()
    @GetMapping("/mets")
    public List<MET> getMets(){
        return metRepository.findAll();
    }
    @CrossOrigin()
    @GetMapping("/mets/{name}")
    public ResponseEntity<MET> get1Met(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        MET met = (MET) metRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Met not found for this id :: " + name));
        return ResponseEntity.ok().body(met);
    }
    @CrossOrigin()
    @GetMapping("/mets/getname/{device}")
    public ResponseEntity<ArrayList<String>> getMetName(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(metRepository.findAllNamesByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/mets/getdscrpt/{device}")
    public ResponseEntity<ArrayList<String>> getDscrpt(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(metRepository.findAllDscrptByDevice(device));
    }
}