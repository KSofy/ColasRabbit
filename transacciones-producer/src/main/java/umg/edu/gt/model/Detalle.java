package umg.edu.gt.model;

import java.io.Serializable;

public class Detalle implements Serializable {
    
    // Identificador único para la serialización, asegura la compatibilidad entre versiones de la clase
    private static final long serialVersionUID = 1L;

    private String nombreBeneficiario; // Persona o entidad que recibe los fondos
    private String tipoTransferencia;  // Ej. "ACH", "Interna", "Internacional"
    private String descripcion;        // Comentario o motivo de la transacción
    private Referencia referencias;    // Objeto anidado que contiene códigos de referencia adicionales

    // --- Getters y Setters (Métodos de acceso para encapsulamiento) ---

    public String getNombreBeneficiario() { 
        return nombreBeneficiario; 
    }
    public void setNombreBeneficiario(String nombreBeneficiario) { 
        this.nombreBeneficiario = nombreBeneficiario; 
    }

    public String getTipoTransferencia() { 
        return tipoTransferencia; 
    }
    public void setTipoTransferencia(String tipoTransferencia) { 
        this.tipoTransferencia = tipoTransferencia; 
    }

    public String getDescripcion() { 
        return descripcion; 
    }
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }

    public Referencia getReferencias() { 
        return referencias; 
    }
    public void setReferencias(Referencia referencias) { 
        this.referencias = referencias; 
    }
}