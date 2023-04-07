package tutorial.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PURController {
    @Autowired
    PURRepository purRepository;
    @CrossOrigin()
    @PostMapping("/purs")
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
    @PutMapping("/purs")
    public Status ChangeElement(@Valid @RequestBody PUR Pur) {
        List<PUR> purs = purRepository.findAll();
        for (PUR pur : purs){
            if (pur.equals(Pur)) {
                pur.setQuantity(+1);
                return Status.PLUS_ONE;
            }
        }
        return Status.SUCCESS;
    }
}




