package com.sistema.erp_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.erp_backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    // Búsquedas para autenticación y consulta
    Optional<Usuario> findByUsernameAndEstadoTrue(String username);

    Optional<Usuario> findByEmailAndEstadoTrue(String email);

    // Listados filtrados
    List<Usuario> findByEstadoTrue();

    List<Usuario> findByRolAndEstadoTrue(String rol);

    // Validaciones de unicidad
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
