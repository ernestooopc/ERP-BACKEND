package com.sistema.erp_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.erp_backend.exception.ResourceNotFoundException;
import com.sistema.erp_backend.model.DetalleVenta;
import com.sistema.erp_backend.repository.DetalleVentaRepository;


@Service
public class DetalleVentaService {
    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }


    // 1. Obtener todos los productos/ítems de una venta específica
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarPorVentaId(Long ventaId) {
        return detalleVentaRepository.findByVentaId(ventaId);
    }


    // 2. Consultar un ítem de detalle individual por ID
    @Transactional(readOnly = true)
    public DetalleVenta buscarPorId(Long detalleId) {
        return detalleVentaRepository.findById(detalleId)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado con ID: " + detalleId));
    }

    // 3. Listar todos los detalles
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarTodos() {
        return detalleVentaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<DetalleVenta> listarHistorialPorProducto(Long productoId) {
        return detalleVentaRepository.findByProductoId(productoId);
    }

}
