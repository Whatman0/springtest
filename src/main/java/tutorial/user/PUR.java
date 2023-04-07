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
    private @Id
    @GeneratedValue long id;
    private @NotBlank String name;
    private Integer quantity;


    public PUR() {
    }

    public PUR(@NotBlank String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
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



    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + quantity + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}