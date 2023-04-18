package tutorial.user;

import org.springframework.beans.factory.annotation.Autowired;

public class getPurId1 {
    @Autowired
    private PURRepository purRepository;
    public getPurId1() {
        PUR pur = new PUR();
        Long id1 = pur.getId();
    }
}
