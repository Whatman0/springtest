package tutorial.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "purs")
public class PUR {
    private @Id @GeneratedValue long id;
    private @NotBlank String name;
    private @NotBlank String quantity;
    private @NotBlank boolean loggedIn;
}
