package com.sistema.erp_backend.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Entity
@Table(name = "detalles")
public class Detalle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false )
    private String venta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private String producto;
    private Integer cantidad;
    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;
    @Column(name = "descuento", precision = 10, scale = 2)
    private BigDecimal descuento;
    @Column(name = "sub_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;


    public Detalle() {
    }

    public Detalle(Long id, String venta, String producto, Integer cantidad, BigDecimal precioUnitario, BigDecimal descuento, BigDecimal subTotal) {
        this.id = id;
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.subTotal = subTotal;
    }

    public Detalle(String venta, String producto, Integer cantidad, BigDecimal precioUnitario, BigDecimal descuento, BigDecimal subTotal) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.subTotal = subTotal;
    }

    @PrePersist
    @PreUpdate
    public void calcularSubtotal() {
        if (this.precioUnitario != null && this.cantidad != null) {
            BigDecimal totalBruto = this.precioUnitario.multiply(BigDecimal.valueOf(this.cantidad));
            BigDecimal desc = (this.descuento != null) ? this.descuento : BigDecimal.ZERO;
            this.subTotal = totalBruto.subtract(desc);
        }
    }


    public Long getId() {
        return id;
    }

    public String getVenta() {
        return venta;
    }

    public String getProducto() {
        return producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }
    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVenta(String venta) {
        this.venta = venta;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    






}
