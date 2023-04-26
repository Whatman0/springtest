package tutorial.user;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
@Entity
@Table(name = "mets")
public class MET {
    private @Id @GeneratedValue Long id;
    private @NotBlank String name;
    private @NotBlank Integer quantity;
    private @NotBlank String device;
    private @NotBlank String dscrpt;
    public MET() {
    }
    public MET(@NotBlank String name, @NotBlank Integer quantity, @NotBlank String device, @NotBlank String dscrpt) {
        this.name = name;
        this.quantity = quantity;
        this.device = device;
        this.dscrpt = dscrpt;
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
    public String getDscrpt(){
        return dscrpt;
    }
    public void setDscrpt() {
        this.dscrpt = dscrpt;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MET)) return false;
        MET met = (MET) o;
        return Objects.equals(name, met.name) &&
                Objects.equals(device, met.device);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, device, dscrpt);
    }
    @Override
    public String toString() {
        return "MET{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", device='" + device + '\'' +
                ", dsrpt='" + dscrpt + '\'' +
                '}';
    }
}
