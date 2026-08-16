package com.sistema.erp_backend.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.erp_backend.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Optional<Cliente> findByNumeroDocumento(String numeroDocumento);
    List<Cliente> findByTipoDocumento(String tipoDocumento);
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findByNombreRazonSocialContainingIgnoreCase(String nombreRazonSocial);
    boolean existsByNumeroDocumento(String numeroDocumento);
    List<Cliente> findByEstadoTrue();
}

