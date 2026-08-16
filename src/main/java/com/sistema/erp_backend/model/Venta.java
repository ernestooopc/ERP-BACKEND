package com.sistema.erp_backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ventas")
public class Venta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serie", nullable = false, length = 10)
    private String serie;

    @Column(name = "numero_comprobante", unique = true, nullable = false, length = 20)
    private String numeroComprobante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "estado", nullable = false)
    private boolean estado = true; // Activo por defecto

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta;


    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles = new ArrayList<>();
    protected Venta() {
    }   

    ////////////////////////
    
    public void agregarDetalle(DetalleVenta detalle) {
        this.detalles.add(detalle);
        detalle.setVenta(this);
        recalcularTotal();
    }

    public void recalcularTotal() {
    if (this.detalles == null) {
        this.total = BigDecimal.ZERO;
        return;
    }
    this.total = this.detalles.stream()
            .map(d -> d.getSubTotal() != null ? d.getSubTotal() : BigDecimal.ZERO)
            .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
    }

    ///////////////////////////////

    public Venta(Long id, String serie, String numeroComprobante, Cliente cliente, Usuario usuario, BigDecimal total, boolean estado, LocalDateTime fechaVenta) {
        this.id = id;
        this.serie = serie;
        this.numeroComprobante = numeroComprobante;
        this.cliente = cliente;
        this.usuario = usuario;
        this.total = total;
        this.estado = estado;
        this.fechaVenta = fechaVenta;
    }

    public Venta(String serie,String numeroComprobante, Cliente cliente, Usuario usuario, BigDecimal total, boolean estado, LocalDateTime fechaVenta) {
        this.serie = serie;
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

    public String getSerie() {
        return serie;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Usuario getUsuario() {
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


    /////////////////////

    public List<DetalleVenta> getDetalles() { 
        return detalles; 
    }

    public void setDetalles(List<DetalleVenta> detalles) { 
        this.detalles = detalles; 
    }

    /////////////////

    public void setId(Long id) {
        this.id = id;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setUsuario(Usuario usuario) {
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
