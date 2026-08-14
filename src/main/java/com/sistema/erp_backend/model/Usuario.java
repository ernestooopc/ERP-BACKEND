package com.sistema.erp_backend.model;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
@Entity
@Table(name = "usuarios")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private String telefono;
    private String rol;
    private Boolean estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public Usuario() {
    }

    public Usuario(Long id, String username, String email, String password, String nombre, String apellido, String telefono, String rol, Boolean estado) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.rol = rol;
        this.estado = estado;
    }
    public Usuario(String username, String email, String password, String nombre, String apellido, String telefono, String rol, Boolean estado) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.rol = rol;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRol() {
        return rol;
    }

    public Boolean getEstado() {
        return estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        if(username == null){                    
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo");
        }
        this.username = username;
    }

    public void setEmail(String email) {
        if(email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            throw new IllegalArgumentException("El email no puede ser nulo y debe tener un formato válido");
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if(password == null || password.length() < 8){                    
            throw new IllegalArgumentException("La contraseña no puede ser nula y debe tener al menos 8 caracteres");
        }
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        if(telefono == null || !telefono.matches("\\d{9}")){
            throw new IllegalArgumentException("El teléfono no puede ser nulo y debe tener 9 dígitos");
        }
        this.telefono = telefono;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
