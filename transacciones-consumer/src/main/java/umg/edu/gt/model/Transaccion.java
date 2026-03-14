package umg.edu.gt.model;

import java.io.Serializable;

/**
* Clase Transaccion enriquecida.
* Este modelo es el que utiliza el Consumer para procesar la información 
* y añadir los datos de autoría antes de enviarlos a la nube.
*/
public class Transaccion implements Serializable {
   
   private static final long serialVersionUID = 1L;

   // --- Campos originales de la transacción bancaria ---
   private String idTransaccion;
   private Double monto;
   private String moneda;
   private String cuentaOrigen;
   private String bancoDestino;
   private Detalle detalle; // Información anidada del beneficiario

   // --- Campos de enriquecimiento (Autoría del estudiante) ---
   // Estos campos permiten que la API de AWS identifique quién procesó el registro
   private String nombre;
   private String carnet;
   private String correo;

   // --- Getters y Setters de los datos bancarios ---
   public String getIdTransaccion() { return idTransaccion; }
   public void setIdTransaccion(String idTransaccion) { this.idTransaccion = idTransaccion; }

   public Double getMonto() { return monto; }
   public void setMonto(Double monto) { this.monto = monto; }

   public String getMoneda() { return moneda; }
   public void setMoneda(String moneda) { this.moneda = moneda; }

   public String getCuentaOrigen() { return cuentaOrigen; }
   public void setCuentaOrigen(String cuentaOrigen) { this.cuentaOrigen = cuentaOrigen; }

   public String getBancoDestino() { return bancoDestino; }
   public void setBancoDestino(String bancoDestino) { this.bancoDestino = bancoDestino; }

   public Detalle getDetalle() { return detalle; }
   public void setDetalle(Detalle detalle) { this.detalle = detalle; }
   
   // --- Getters y Setters de los datos de autoría ---
   public String getNombre() { return nombre; }
   public void setNombre(String nombre) { this.nombre = nombre; }

   public String getCarnet() { return carnet; }
   public void setCarnet(String carnet) { this.carnet = carnet; }

   public String getCorreo() { return correo; }
   public void setCorreo(String correo) { this.correo = correo; }
}