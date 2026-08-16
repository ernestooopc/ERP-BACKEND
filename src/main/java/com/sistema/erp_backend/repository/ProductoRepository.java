package com.sistema.erp_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.erp_backend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{


    // Buscar producto activo por su código o código de barras
    Optional<Producto> findByCodigoAndEstadoTrue(String codigo);


    // Listar solo productos activos para el punto de venta / catálogo
    List<Producto> findByEstadoTrue();

    // Búsqueda por nombre entre productos activos (para autocompletado en ventas)
    List<Producto> findByNombreContainingIgnoreCaseAndEstadoTrue(String nombre);

    // Validar si ya existe el código antes de registrar
    boolean existsByCodigo(String codigo);


}
