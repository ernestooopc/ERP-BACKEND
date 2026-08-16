package com.sistema.erp_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.erp_backend.model.Usuario;
import com.sistema.erp_backend.repository.UsuarioRepository;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    // 1. REGISTRAR USUARIO
    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("El nombre de usuario '" + usuario.getUsername() + "' ya está en uso.");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo '" + usuario.getEmail() + "' ya está registrado.");
        }

        usuario.setEstado(true);
        return usuarioRepository.save(usuario);
    }

    // 2. ACTUALIZAR DATOS DE USUARIO
    @Transactional
    public Usuario actualizarUsuario(Long id, Usuario datosActualizados) {
        Usuario usuarioExistente = buscarPorId(id);

        // Validar unicidad de username si fue modificado
        if (!usuarioExistente.getUsername().equalsIgnoreCase(datosActualizados.getUsername())) {
            if (usuarioRepository.existsByUsername(datosActualizados.getUsername())) {
                throw new RuntimeException("El username ya se encuentra en uso.");
            }
            usuarioExistente.setUsername(datosActualizados.getUsername());
        }

        // Validar unicidad de email si fue modificado
        if (!usuarioExistente.getEmail().equalsIgnoreCase(datosActualizados.getEmail())) {
            if (usuarioRepository.existsByEmail(datosActualizados.getEmail())) {
                throw new RuntimeException("El correo electrónico ya está registrado.");
            }
            usuarioExistente.setEmail(datosActualizados.getEmail());
        }

        usuarioExistente.setNombre(datosActualizados.getNombre());
        usuarioExistente.setApellido(datosActualizados.getApellido());
        usuarioExistente.setTelefono(datosActualizados.getTelefono());
        usuarioExistente.setRol(datosActualizados.getRol());

        return usuarioRepository.save(usuarioExistente);
    }

    // 3. CAMBIAR CONTRASEÑA
    @Transactional
    public void cambiarPassword(Long id, String nuevaPassword) {
        Usuario usuario = buscarPorId(id);
        usuario.setPassword(nuevaPassword);
        usuarioRepository.save(usuario);
    }

    // 4. CONSULTAS
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuariosActivos() {
        return usuarioRepository.findByEstadoTrue();
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsernameAndEstadoTrue(username)
                .orElseThrow(() -> new RuntimeException("Usuario activo no encontrado: " + username));
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarPorRol(String rol) {
        return usuarioRepository.findByRolAndEstadoTrue(rol.toUpperCase());
    }

    // 5. BAJA LÓGICA Y REACTIVACIÓN
    @Transactional
    public void desactivarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.setEstado(false);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void reactivarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        usuario.setEstado(true);
        usuarioRepository.save(usuario);
    }


}
