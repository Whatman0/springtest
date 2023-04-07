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
               // purRepository.save(newPur);
                return Status.QUANTITY_IS_POS_CHANGE;
                }
        }
        purRepository.save(newPur);
        return Status.SUCCESS;








    }
}




