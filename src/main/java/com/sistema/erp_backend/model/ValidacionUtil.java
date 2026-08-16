package com.sistema.erp_backend.model;

import java.math.BigDecimal;

public class ValidacionUtil {


    private static final String TELEFONO_REGEX = "\\d{9}";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";


    public static void validarTelefono(String telefono) {
        if (telefono == null || !telefono.trim().matches(TELEFONO_REGEX)) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo y debe tener exactamente 9 dígitos");
        }
    }

    public static void validarUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede ser nulo o vacío");
        }
    }

    public static void validarEmail(String email) {
        if (email == null || !email.trim().matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("El email no puede ser nulo y debe tener un formato válido");
        }
    }

    public static void validarPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("La contraseña no puede ser nula y debe tener al menos 8 caracteres");
        }
    }

    public static void validarStock(Integer stock){
        if(stock == null || stock < 0){
            throw new IllegalArgumentException("El stock no puede ser nulo y debe ser mayor o igual a 0");
        }
    }

    public static void validarPrecio(BigDecimal precio){
        if(precio == null || precio.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El precio no puede ser nulo y debe ser mayor que 0");
        }
    }

    public static void validarCodigo(String codigo){
        if(codigo == null || codigo.trim().isEmpty()){
            throw new IllegalArgumentException("El código no puede ser nulo o vacío");
        }
        if(!codigo.matches("^[a-zA-Z0-9]+$")){
            throw new IllegalArgumentException("El código solo puede contener letras y números");
        }
    }

}
