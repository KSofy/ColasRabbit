package umg.edu.gt.model;

import java.io.Serializable;

/**
 * Clase que almacena códigos de identificación específicos de la transacción.
 * Al estar anidada dentro de 'Detalle', permite una estructura de datos organizada
 * y fácil de convertir a JSON para la API de AWS.
 */
public class Referencia implements Serializable {
    
    // Versión de serialización para asegurar que el objeto sea legible tras viajar por la red
    private static final long serialVersionUID = 1L;

    private String factura;       // Número de documento contable asociado
    private String codigoInterno;  // Código de rastreo propio del sistema bancario

    // --- Métodos de Acceso (Getters y Setters) ---

    public String getFactura() { 
        return factura; 
    }
    public void setFactura(String factura) { 
        this.factura = factura; 
    }

    public String getCodigoInterno() { 
        return codigoInterno; 
    }
    public void setCodigoInterno(String codigoInterno) { 
        this.codigoInterno = codigoInterno; 
    }
}