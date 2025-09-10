package sv.com.jsoft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import sv.com.jsoft.util.EntityPanache;

@Entity
@Table(name = "contribuyente")
public class Contribuyente extends EntityPanache {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_contribuyente")
    @SequenceGenerator(name = "id_contribuyente", sequenceName = "id_contribuyente_seq", allocationSize = 1)
    @Column(name = "id_contribuyente")
    @JsonIgnore
    public Long idContribuyente;

    @Column(name = "codigo_actividad")
    public String codigoActividad;

    @Column(name = "id_municipio")
    @JsonIgnore
    public Integer idMunicipio;

    @Column(name = "nit", nullable = false, length = 17)
    public String nit;

    @Column(name = "nrc", nullable = false, length = 8)
    public String nrc;

    @Column(name = "nombre_comercial", nullable = false, length = 300)
    public String nombreComercial;

    @Column(name = "razon_social", nullable = false, length = 300)
    public String razonSocial;

    @Column(name = "telefono", nullable = false, length = 10)
    public String telefono;

    @Column(name = "correo", nullable = false, length = 250)
    public String correo;

    @JsonIgnore
    @Column(name = "estado", nullable = false)
    public boolean estado;

    @Column(name = "direccion", nullable = false, length = 500)
    public String direccion;

    @Column(name = "activo", nullable = false)
    public Boolean activo;

    @Column(name = "codigo_establecimiento", nullable = false, length = 500)
    public String codigoEstablecimiento;

    @Column(name = "usuario", nullable = false, length = 250)
    public String usuario;

    @Column(name = "nombre_responsable", nullable = false, length = 100)
    public String nombreResponsable;
    @Column(name = "tipo_doc_responsable", nullable = false, length = 2)
    public String tipoDocResponsable;
    @Column(name = "numero_doc_responsable", nullable = false, length = 25)
    public String numeroDocResponsable;


    @JsonIgnore
    @Override
    public Long getID() {
        return idContribuyente;
    }

    @Override
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Transient
    public String codigoDepartamento;
    @Transient
    public String codigoMunicipio;
}
