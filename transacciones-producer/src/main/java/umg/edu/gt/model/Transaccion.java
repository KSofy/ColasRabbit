package umg.edu.gt.model;

import java.io.Serializable;

/**
 * Clase principal que representa una transacción financiera individual.
 * Es el objeto base que se envía a través de las colas de RabbitMQ y se 
 * persiste en la infraestructura de AWS.
 */
public class Transaccion implements Serializable {
    
    // Identificador de versión para la serialización, asegura que el emisor y receptor 
    // interpreten el objeto de la misma manera.
    private static final long serialVersionUID = 1L;

    private String idTransaccion; // Identificador único de la operación (UUID)
    private Double monto;         // Cantidad de dinero de la transacción
    private String moneda;        // Tipo de moneda (ej. "GTQ" o "USD")
    private String cuentaOrigen;  // Número de cuenta desde donde sale el dinero
    private String bancoDestino;  // Nombre del banco receptor (usado para el ruteo en RabbitMQ)
    private Detalle detalle;      // Objeto anidado con información secundaria (beneficiario, referencias)

    // --- Métodos de Acceso (Getters y Setters) ---

    public String getIdTransaccion() { 
        return idTransaccion; 
    }
    public void setIdTransaccion(String idTransaccion) { 
        this.idTransaccion = idTransaccion; 
    }

    public Double getMonto() { 
        return monto; 
    }
    public void setMonto(Double monto) { 
        this.monto = monto; 
    }

    public String getMoneda() { 
        return moneda; 
    }
    public void setMoneda(String moneda) { 
        this.moneda = moneda; 
    }

    public String getCuentaOrigen() { 
        return cuentaOrigen; 
    }
    public void setCuentaOrigen(String cuentaOrigen) { 
        this.cuentaOrigen = cuentaOrigen; 
    }

    public String getBancoDestino() { 
        return bancoDestino; 
    }
    public void setBancoDestino(String bancoDestino) { 
        this.bancoDestino = bancoDestino; 
    }

    public Detalle getDetalle() { 
        return detalle; 
    }
    public void setDetalle(Detalle detalle) { 
        this.detalle = detalle; 
    }
}