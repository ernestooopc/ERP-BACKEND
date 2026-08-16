package com.sistema.erp_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.erp_backend.model.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByVentaId(Long ventaId);
    List<DetalleVenta> findByProductoId(Long productoId);
}
