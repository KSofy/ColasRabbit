package umg.edu.gt.model;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que actúa como un contenedor para un conjunto de transacciones.
 * Representa la estructura que devuelve la API externa cuando el Producer 
 * solicita el listado de movimientos para procesar.
 */
public class LoteTransacciones implements Serializable {
    
    // Garantiza que el objeto pueda ser reconstruido correctamente durante la deserialización
    private static final long serialVersionUID = 1L;

    private String loteId;            // Identificador único del grupo de transacciones
    private String fechaGeneracion;   // Marca de tiempo de cuándo se creó este conjunto de datos
    
    /**
     * Lista que almacena objetos de tipo Transaccion.
     * Aquí es donde se guardan todos los registros individuales que luego 
     * serán filtrados y enviados a sus respectivas colas (BI, GYT, etc.).
     */
    private List<Transaccion> transacciones;

    // --- Métodos de Acceso (Getters y Setters) ---

    public String getLoteId() { 
        return loteId; 
    }
    public void setLoteId(String loteId) { 
        this.loteId = loteId; 
    }

    public String getFechaGeneracion() { 
        return fechaGeneracion; 
    }
    public void setFechaGeneracion(String fechaGeneracion) { 
        this.fechaGeneracion = fechaGeneracion; 
    }

    public List<Transaccion> getTransacciones() { 
        return transacciones; 
    }
    public void setTransacciones(List<Transaccion> transacciones) { 
        this.transacciones = transacciones; 
    }
}