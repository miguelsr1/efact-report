package sv.com.jsoft.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_factura")
public class DetalleFactura extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_factura_id_gen")
    @SequenceGenerator(name = "detalle_factura_id_gen", sequenceName = "detalle_factura_id_detalle_factura_seq", allocationSize = 1)
    @Column(name = "id_detalle_factura", nullable = false)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura")
    public Factura idFactura;

    @Size(max = 26)
    @Column(name = "codigo", length = 26)
    public String codigo;

    @Size(max = 1001)
    @Column(name = "nombre", length = 1001)
    public String nombre;

    @NotNull
    @Column(name = "cantidad", nullable = false, precision = 11, scale = 8)
    public BigDecimal cantidad;

    @NotNull
    @Column(name = "precio_unitario", nullable = false, precision = 11, scale = 6)
    public BigDecimal precioUnitario;

    @Column(name = "codigo_unidad")
    public String codigoUnidad;

    @Column(name = "codigo_item")
    public String codigoItem;

    public DetalleFactura() {
    }

    public DetalleFactura(Factura idFactura, String codigo, String nombre, BigDecimal cantidad, BigDecimal precioUnitario, String codigoUnidad, String codigoItem) {
        this.idFactura = idFactura;
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.codigoUnidad = codigoUnidad;
        this.codigoItem = codigoItem;
    }
}