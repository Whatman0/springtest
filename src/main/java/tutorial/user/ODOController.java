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
    @CrossOrigin()
    @GetMapping("/odos/getallinst/")
    public ResponseEntity<List<ODO>> getAllInst() {
        return ResponseEntity.ok(odoRepository.findAllInst());
    }
    @CrossOrigin()
    @GetMapping("/odos/getnamebyinst/{device}")
    public ResponseEntity<ArrayList<String>> getNameByInst(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(odoRepository.findAllNamesByDeviceAndInst(device));
    }
    @CrossOrigin()
    @GetMapping("/odos/getquantitybyinst/{device}")
    public ResponseEntity<ArrayList<String>> getQuantityByInst(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(odoRepository.findAllQuantitysByDeviceAndInst(device));
    }
    @CrossOrigin()
    @GetMapping("/odos/getdscrptbyinst/{device}")
    public ResponseEntity<ArrayList<String>> getDscrptByInst(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(odoRepository.findAllDscrptByDeviceAndInst(device));
    }
    @CrossOrigin()
    @GetMapping("/odos/getinstbyinst/{device}")
    public ResponseEntity<ArrayList<String>> getInstByInst(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(odoRepository.findAllInstByDeviceAndInst(device));
    }
    @CrossOrigin()
    @PatchMapping("/odos/setinsttrue/{device}/{name}/{inst}")
    public void SetInstTrue(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name, @PathVariable(value = "inst") Boolean inst) {
        odoRepository.patchTrueInstByDeviceName(device, name, inst);
    }
    @CrossOrigin()
    @PatchMapping("/odos/setinstfalse/{device}/{name}/{inst}")
    public void SetInstFalse(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name, @PathVariable(value = "inst") Boolean inst) {
        odoRepository.patchFalseInstByDeviceName(device, name, inst);
    }
}