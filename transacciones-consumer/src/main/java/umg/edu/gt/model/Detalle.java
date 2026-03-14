package umg.edu.gt.model;

import java.io.Serializable;

public class Detalle implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombreBeneficiario;
    private String tipoTransferencia;
    private String descripcion;
    private Referencia referencias;

    // Getters y Setters
    public String getNombreBeneficiario() { return nombreBeneficiario; }
    public void setNombreBeneficiario(String nombreBeneficiario) { this.nombreBeneficiario = nombreBeneficiario; }

    public String getTipoTransferencia() { return tipoTransferencia; }
    public void setTipoTransferencia(String tipoTransferencia) { this.tipoTransferencia = tipoTransferencia; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Referencia getReferencias() { return referencias; }
    public void setReferencias(Referencia referencias) { this.referencias = referencias; }
}