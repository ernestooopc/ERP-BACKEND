package com.sistema.erp_backend.model;

import java.time.LocalDateTime;

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
    private String numeroDocumento;
    private String nombreRazonSocial;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDateTime fechaCreacion;

    public Cliente() {
    }

    public Cliente(Long id, String tipoDocumento, String numeroDocumento, String nombreRazonSocial, String direccion, String telefono, String email) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombreRazonSocial = nombreRazonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(String tipoDocumento, String numeroDocumento, String nombreRazonSocial, String direccion, String telefono, String email) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombreRazonSocial = nombreRazonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
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
        if(telefono == null || !telefono.matches("\\d{9}")){
            throw new IllegalArgumentException("El teléfono no puede ser nulo y debe tener 9 dígitos");
        }
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        if(email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            throw new IllegalArgumentException("El email no puede ser nulo y debe tener un formato válido");
        }
        this.email = email;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    

}
