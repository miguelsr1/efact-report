package sv.com.jsoft.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bitacora_mh")
public class BitacoraMh extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bitacora_mh_id_gen")
    @SequenceGenerator(name = "bitacora_mh_id_gen", sequenceName = "bitacora_seq", allocationSize = 1)
    @Column(name = "id_bitacora", nullable = false)
    public Integer id;

    @Column(name = "id_factura")
    public Long idFactura;

    @Column(name = "estado_mh", length = Integer.MAX_VALUE)
    public String estadoMh;

    @Column(name = "json_dte", length = Integer.MAX_VALUE)
    public String jsonDte;

    @Column(name = "descripcion_msg", length = Integer.MAX_VALUE)
    public String descripcionMsg;

    @Column(name = "observaciones", length = Integer.MAX_VALUE)
    public String observaciones;

    @Column(name = "sello_recibido", length = Integer.MAX_VALUE)
    public String selloRecibido;

    @Column(name = "codigo_generacion", length = Integer.MAX_VALUE)
    public String codigoGeneracion;

    @Column(name = "fh_procesamiento")
    public LocalDateTime fhProcesamiento;

    @Column(name = "json_response", length = Integer.MAX_VALUE)
    public String jsonResponse;
    @Column(name = "tipo_dte", length = 1)
    public String tipoDte;

}