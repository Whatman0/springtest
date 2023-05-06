package tutorial.user;

import java.util.*;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BLOCKController {
    @Autowired
    private BLOCKRepository blockRepository;

    @CrossOrigin()
    @PostMapping("/blocks/add")
    public Status AddElement(@Valid @RequestBody BLOCK newBlock) {
        List<BLOCK> blocks = blockRepository.findAll();
        for (BLOCK block : blocks) {
            if (block.equals(newBlock)) {

                System.out.println(" Такая деталь уже есть, пожалуйста измените количество");
                return Status.ELEMENT_ALREADY_EXISTS;
            }
        }
        blockRepository.save(newBlock);
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @PatchMapping("/blocks/change/{device}/{name}/{quantity}")
    public void ChangeElement(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name, @PathVariable Integer quantity) {
        // try {
        blockRepository.patchByDeviceName(device, name, quantity);
        //block.setQuantity(quantity);
        // return new ResponseEntity<>(blockRepository.save(block), HttpStatus.OK);
        //catch (Exception e) {

    }


    @CrossOrigin()
    @GetMapping("/blocks/getquantity/{device}")
    public ResponseEntity<ArrayList<String>> getQuantity(@PathVariable(value = "device") String device){
        return ResponseEntity.ok(blockRepository.findAllQuantitysByDevice(device));
    }
    @CrossOrigin()
    @DeleteMapping("/blocks/delete/{device}/{name}")
    public Status deletePurs(@PathVariable(value = "device") String device, @PathVariable(value = "name") String name){
        BLOCK block = blockRepository.findAllByDeviceAndName(device, name);
        blockRepository.delete(block);
        return Status.SUCCESS;
    }
    @CrossOrigin()
    @DeleteMapping("/blocks/deleteall")
    public Status deleteAllPurs() {
        blockRepository.deleteAll();
        return Status.SUCCESS;
    }

    @CrossOrigin()
    @GetMapping("/blocks/getid/{name}")
    public Map<Long, String> getPurId(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        BLOCK block = (BLOCK) blockRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Block not found for this name :: " + name));
        Map<Long, String> IdDev = new HashMap<>();
        IdDev.put(block.getId(), block.getDevice());
        return IdDev;
    }

    @CrossOrigin()
    @GetMapping("/blocks/getdevice/{name}")
    public ResponseEntity<BLOCK> getDevice(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        BLOCK block = (BLOCK) blockRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Block not found for this name :: " + name));
        return ResponseEntity.ok().body(block);
    }

    @CrossOrigin()
    @GetMapping("/blocks")
    public List<BLOCK> getPurs(){
        return blockRepository.findAll();
    }
    @CrossOrigin()
    @GetMapping("/blocks/{name}")
    public ResponseEntity<BLOCK> get1Pur(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {
        BLOCK block = (BLOCK) blockRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Block not found for this id :: " + name));
        return ResponseEntity.ok().body(block);
    }
    @CrossOrigin()
    @GetMapping("/blocks/getname/{device}")
    public ResponseEntity<ArrayList<String>> getPurName(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(blockRepository.findAllNamesByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/blocks/getdscrpt/{device}")
    public ResponseEntity<ArrayList<String>> getDscrpt(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(blockRepository.findAllDscrptByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/blocks/getinst/{device}")
    public ResponseEntity<ArrayList<String>> getInst(@PathVariable(value = "device") String device) {
        return ResponseEntity.ok(blockRepository.findAllInstByDevice(device));
    }
    @CrossOrigin()
    @GetMapping("/blocks/getallinst/")
    public ResponseEntity<List<ODO>> getAllInst() {
        return ResponseEntity.ok(blockRepository.findAllInst());
    }
}
