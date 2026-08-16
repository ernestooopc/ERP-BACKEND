package com.sistema.erp_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.erp_backend.model.DetalleVenta;
import com.sistema.erp_backend.model.Venta;
import com.sistema.erp_backend.service.DetalleVentaService;
import com.sistema.erp_backend.service.VentaService;


@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final DetalleVentaService detalleService;

    public VentaController(VentaService ventaService, DetalleVentaService detalleService) {
        this.ventaService = ventaService;
        this.detalleService = detalleService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listarTodas() {
        return ResponseEntity.ok(ventaService.listarVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerVentaPorId(id));
    }

    @GetMapping("/{id}/detalles")
    public ResponseEntity<List<DetalleVenta>> obtenerDetallesDeVenta(@PathVariable Long id) {
        return ResponseEntity.ok(detalleService.listarPorVentaId(id));
    }

    @PostMapping
    public ResponseEntity<Venta> registrarVenta(
            @RequestBody Venta venta,
            @RequestParam(defaultValue = "B001") String serie
    ) {
        Venta nuevaVenta = ventaService.registrarVenta(venta, serie, venta.getDetalles());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    @PatchMapping("/{id}/anular")
    public ResponseEntity<Void> anularVenta(@PathVariable Long id) {
        ventaService.anularVenta(id);
        return ResponseEntity.noContent().build();
    }
}
