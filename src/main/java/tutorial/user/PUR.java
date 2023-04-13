package tutorial.user;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;


@Entity
@Table(name = "purs")
public class PUR {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name") private @NotBlank String name;
    @Column(name = "quantity") private @NotBlank Integer quantity;

    @Column(name = "device") private @NotBlank String device;


    public PUR() {
    }

    public PUR(@NotBlank String name, Integer quantity, String device) {
        this.name = name;
        this.quantity = quantity;
        this.device = device;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public String getDevice() {
        return device;
    }


    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, device);
    }

    @Override
    public String toString() {
        return "PUR{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", device='" + device + '\'' +
                '}';
    }
}