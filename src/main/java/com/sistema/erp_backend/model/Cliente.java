package com.sistema.erp_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoDocumento; // 1: DNI, 2: RUC, 3: Pasaporte
    @Column(name = "numero_documento", unique = true, nullable = false)
    private String numeroDocumento;
    private String nombreRazonSocial;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDateTime fechaCreacion;
    private boolean estado = true;

    protected Cliente() {
    }

    public Cliente(Long id, String tipoDocumento, String numeroDocumento, String nombreRazonSocial, String direccion, String telefono, String email, LocalDateTime fechaCreacion, boolean estado) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombreRazonSocial = nombreRazonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Cliente(String tipoDocumento, String numeroDocumento, String nombreRazonSocial, String direccion, String telefono, String email, LocalDateTime fechaCreacion, boolean estado) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombreRazonSocial = nombreRazonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }
    public boolean isEstado() {
    return estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        if (!DocumentoValidator.isValid(getTipoDocumento(), numeroDocumento)) {
            throw new IllegalArgumentException(
                "El número de " + getTipoDocumento() + " ingresado ('" + numeroDocumento + "') no es válido."
            );
        }
        this.numeroDocumento = numeroDocumento.trim();
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        ValidacionUtil.validarTelefono(telefono);
        this.telefono = telefono.trim();
    }

    public void setEmail(String email) {
        ValidacionUtil.validarEmail(email);
        this.email = email;
    }

    public void setEstado(boolean estado) {
    this.estado = estado;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }



}
