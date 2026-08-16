package com.sistema.erp_backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<Map<String, String>> estadoServidor() {
        return ResponseEntity.ok(Map.of(
            "estado", "OK",
            "mensaje", "API ERP Backend funcionando correctamente"
        ));
    }

}
