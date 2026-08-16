package com.sistema.erp_backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistema.erp_backend.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{

    Optional<Venta> findByNumeroComprobante(String numeroComprobante);


    @Query("SELECT v.numeroComprobante FROM Venta v WHERE v.numeroComprobante LIKE CONCAT(:serie, '-%') ORDER BY v.id DESC LIMIT 1")
    Optional<String> findUltimoComprobantePorSerie(@Param("serie") String serie);

    List<Venta> findByFechaVentaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<Venta> findByClienteId(Long clienteId);
    List<Venta> findByUsuarioId(Long usuarioId);
    List<Venta> findByEstado(boolean estado);
}
