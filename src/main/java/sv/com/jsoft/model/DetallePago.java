package sv.com.jsoft.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pago")
public class DetallePago extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_det_pago", nullable = false)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura")
    public Factura idFactura;

    @Column(name = "monto_pago", precision = 13, scale = 6)
    public BigDecimal montoPago;

    @Column(name = "referencia", length = Integer.MAX_VALUE)
    public String referencia;

    @Column(name = "periodo")
    public Short periodo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_forma_pago")
    public Cat017FormaPago idFormaPago;

    @Column(name = "codigo_plazo")
    public String codigoPlazo;

    public DetallePago() {
    }

    public DetallePago(Factura idFactura, BigDecimal montoPago, String referencia, Short periodo, Cat017FormaPago idFormaPago, String codigoPlazo) {
        this.idFactura = idFactura;
        this.montoPago = montoPago;
        this.referencia = referencia;
        this.periodo = periodo;
        this.idFormaPago = idFormaPago;
        this.codigoPlazo = codigoPlazo;
    }
}