package sv.com.jsoft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import sv.com.jsoft.util.EntityPanache;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "factura")
public class Factura extends EntityPanache {

    @Id
    @Column(name = "id_factura")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_factura")
    @SequenceGenerator(name = "id_factura", sequenceName = "id_factura_seq", allocationSize = 1)
    @JsonIgnore
    public Long idFactura;

    @Column(name = "id_contribuyente")
    public Long idContribuyente;
    @Column(name = "id_cliente")
    public Long idCliente;
    @Column(name = "codigo_dte")
    public String codigoDte;
    @Column(name = "condicion_operacion")
    public String condicionOperacion;
    @Column(name = "codigo_generacion")
    public String codigoGeneracion;
    @Column(name = "numero_control")
    public String numeroControl;
    @Column(name = "monto_operacion", nullable = false, precision = 13, scale = 6)
    public BigDecimal montoOperacion;

    @OneToMany(mappedBy = "idFactura", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<DetalleFactura> detalleFacturas = new ArrayList<>();

    @Column(name = "numero_factura")
    public Long numeroFactura;

    @Column(name = "fecha_creacion")
    public LocalDateTime fechaCreacion;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "estado", nullable = false)
    public Short estado;

    @OneToMany(mappedBy = "idFactura", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<DetallePago> detallePagos = new ArrayList<>();

    @Column(name = "id_establecimiento", nullable = false)
    public Long idEstablecimiento;

    @Column(name = "id_punto_venta", nullable = false)
    public Long idPuntoVenta;

    @Column(name = "retencion_isr")
    public int retencionIsr;

    @Column(name = "codigo_facturacion")
    public String codigoFacturacion;
    @Column(name = "codigo_transmision")
    public String codigoTransmision;
    @Column(name = "ambiente")
    public String ambiente;

    @Column(name = "aplica_iva_retenido")
    public Boolean aplicaIvaRetenido;
    @Column(name = "aplica_renta_retenido")
    public Boolean aplicaRentaRetenido;


    @Override
    @JsonIgnore
    public Long getID() {
        return idFactura;
    }
}
