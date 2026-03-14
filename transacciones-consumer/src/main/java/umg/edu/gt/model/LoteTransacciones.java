package umg.edu.gt.model;

import java.io.Serializable;
import java.util.List;

public class LoteTransacciones implements Serializable {
    private static final long serialVersionUID = 1L;

    private String loteId;
    private String fechaGeneracion;
    private List<Transaccion> transacciones;

    // Getters y Setters
    public String getLoteId() { return loteId; }
    public void setLoteId(String loteId) { this.loteId = loteId; }

    public String getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(String fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }

    public List<Transaccion> getTransacciones() { return transacciones; }
    public void setTransacciones(List<Transaccion> transacciones) { this.transacciones = transacciones; }
}