package mx.edu.utez.ultima.models.incidencias;

public class Incidencias {

    private Long id_incidencia;

    private String titulo;

    private String descripcion;
    private String tipo;
    private String status;

    public Incidencias() {
    }

    public Incidencias(Long id_incidencia, String titulo, String descripcion, String tipo, String status) {
        this.id_incidencia = id_incidencia;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.status = status;
    }

    public Long getId_incidencia() {
        return id_incidencia;
    }

    public void setId_incidencia(Long id_incidencia) {
        this.id_incidencia = id_incidencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
