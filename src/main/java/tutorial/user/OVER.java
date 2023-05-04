package tutorial.user;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
@Entity
@Table(name = "overs")
public class OVER {
    private @Id @GeneratedValue Long id;
    private @NotBlank String name;
    private @NotBlank Integer quantity;
    private @NotBlank String device;
    private @NotBlank String dscrpt;
    private @NotBlank Boolean inst;

    public OVER() {
    }
    public OVER(@NotBlank String name, @NotBlank Integer quantity, @NotBlank String device, @NotBlank String dscrpt, @NotBlank Boolean inst) {
        this.name = name;
        this.quantity = quantity;
        this.device = device;
        this.dscrpt = dscrpt;
        this.inst = inst;
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
    public void setDscrpt(String dscrpt) {
        this.dscrpt = dscrpt;
    }
    public Boolean getInst() {
        return inst;
    }
    public void setInst(Boolean inst) {
        this.inst = inst;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OVER)) return false;
        OVER over = (OVER) o;
        return Objects.equals(name, over.name) &&
                Objects.equals(device, over.device);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, device, dscrpt);
    }
    @Override
    public String toString() {
        return "OVER{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", device='" + device + '\'' +
                ", dsrpt='" + dscrpt + '\'' +
                '}';
    }
}
