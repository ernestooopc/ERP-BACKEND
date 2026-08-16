package com.sistema.erp_backend.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class DetalleVenta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false )
    private Venta venta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "descuento", precision = 10, scale = 2)
    private BigDecimal descuento = BigDecimal.ZERO;

    @Column(name = "sub_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal = BigDecimal.ZERO;


    protected DetalleVenta() {
    }

    public DetalleVenta(Long id, Venta venta, Producto producto, Integer cantidad, BigDecimal precioUnitario, BigDecimal descuento, BigDecimal subTotal) {
        this.id = id;
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.subTotal = subTotal;
    }

    public DetalleVenta(Venta venta, Producto producto, Integer cantidad, BigDecimal precioUnitario, BigDecimal descuento, BigDecimal subTotal) {
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
            BigDecimal desc = (this.descuento != null) ? this.descuento : BigDecimal.ZERO;
            BigDecimal totalBruto = this.precioUnitario.multiply(BigDecimal.valueOf(this.cantidad));
            
            // Garantiza 2 decimales exactos en el subtotal
            this.subTotal = totalBruto.subtract(desc).setScale(2, RoundingMode.HALF_UP);
        }
    }


    public Long getId() {
        return id;
    }

    public Venta getVenta() {
        return venta;
    }

    public Producto getProducto() {
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

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        this.calcularSubtotal(); // Recalcula el subtotal al cambiar la cantidad
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.calcularSubtotal(); // Recalcula el subtotal al cambiar el precio unitario
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = (descuento != null) ? descuento : BigDecimal.ZERO;
        this.calcularSubtotal();
    }
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    






}
