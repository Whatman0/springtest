package tutorial.user;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;


@Entity
@Table(name = "purs")
public class PUR {
    private @Id @GeneratedValue Long id;
    private @NotBlank String name;
    private @NotBlank Integer quantity;
    private @NotBlank String device;


    public PUR() {
    }

    public PUR(@NotBlank String name, @NotBlank Integer quantity, @NotBlank String device) {
        this.name = name;
        this.quantity = quantity;
        this.device = device;
    }

    public Long getId() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PUR)) return false;
        PUR pur = (PUR) o;
        return Objects.equals(name, pur.name) ;
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