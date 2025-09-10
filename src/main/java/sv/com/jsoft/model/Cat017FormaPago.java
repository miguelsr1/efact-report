package sv.com.jsoft.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cat_017_forma_pago")
public class Cat017FormaPago extends PanacheEntityBase {
    @Id
    @Size(max = 2)
    @Column(name = "id_forma_pago", nullable = false, length = 2)
    public String idFormaPago;

    @Size(max = 100)
    @NotNull
    @Column(name = "descripcion_forma", nullable = false, length = 100)
    public String descripcionForma;

}