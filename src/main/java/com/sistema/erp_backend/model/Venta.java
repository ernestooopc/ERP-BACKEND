package com.sistema.erp_backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ventas")
public class Venta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroComprobante;

    @ManyToOne
    private String cliente;

    @ManyToOne
    private String usuario;

    private BigDecimal total;
    private boolean estado;
    private LocalDateTime fechaVenta;

    public Venta() {
    }   

    public Venta(Long id, String numeroComprobante, String cliente, String usuario, BigDecimal total, boolean estado, LocalDateTime fechaVenta) {
        this.id = id;
        this.numeroComprobante = numeroComprobante;
        this.cliente = cliente;
        this.usuario = usuario;
        this.total = total;
        this.estado = estado;
        this.fechaVenta = fechaVenta;
    }

    public Venta(String numeroComprobante, String cliente, String usuario, BigDecimal total, boolean estado, LocalDateTime fechaVenta) {
        this.numeroComprobante = numeroComprobante;
        this.cliente = cliente;
        this.usuario = usuario;
        this.total = total;
        this.estado = estado;
        this.fechaVenta = fechaVenta;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public String getCliente() {
        return cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public boolean isEstado() {
        return estado;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    



}
