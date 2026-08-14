package com.sistema.erp_backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    //para manejar valores decimales con precisión, se utiliza BigDecimal en lugar de float o double
    private BigDecimal precio;
    private Integer stock;
    private Integer stockMinimo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;


    public Producto() {
    }

    
    public Producto(Long id, String codigo, String nombre, String descripcion, BigDecimal precio, Integer stock, Integer stockMinimo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public Producto(String codigo, String nombre, String descripcion, BigDecimal precio, Integer stock, Integer stockMinimo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        if(codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede ser nulo o vacío");
        }if(!codigo.matches("^[a-zA-Z0-9]+$")) {            
            throw new IllegalArgumentException("El código solo puede contener letras y números");
        }
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        if (precio != null && precio.compareTo(BigDecimal.ZERO) > 0){
            this.precio = precio;
        } else{
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
    }

    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        if(stock != null && stock >= 0){
            this .stock = stock;
        }else{
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
    public Integer getStockMinimo() {
        return stockMinimo;
    }
    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @PrePersist
    protected void onCreate(){
        this.fechaCreacion = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.fechaActualizacion = LocalDateTime.now();
    }
    
}
