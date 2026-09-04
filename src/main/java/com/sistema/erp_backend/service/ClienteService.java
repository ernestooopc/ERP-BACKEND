package com.sistema.erp_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.erp_backend.exception.ResourceNotFoundException;
import com.sistema.erp_backend.model.Cliente;
import com.sistema.erp_backend.repository.ClienteRepository;


@Service
public class ClienteService {


    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // 1. REGISTRAR CLIENTE
    @Transactional
    public Cliente registrarCliente(Cliente cliente) {
        // Validar si el número de documento ya está registrado
        if (clienteRepository.existsByNumeroDocumento(cliente.getNumeroDocumento())) {
            throw new RuntimeException("Ya existe un cliente registrado con el número de documento: " + cliente.getNumeroDocumento());
        }

        // Asignar fecha de registro actual
        cliente.setFechaCreacion(LocalDateTime.now());

        return clienteRepository.save(cliente);
    }

    // 2. ACTUALIZAR CLIENTE
    @Transactional
    public Cliente actualizarCliente(Long id, Cliente datosActualizados) {
        Cliente clienteExistente = buscarPorId(id);

        // Si cambia el documento, verificar que el nuevo no esté duplicado en otro registro
        if (!clienteExistente.getNumeroDocumento().equals(datosActualizados.getNumeroDocumento())) {
            if (clienteRepository.existsByNumeroDocumento(datosActualizados.getNumeroDocumento())) {
                throw new RuntimeException("El documento " + datosActualizados.getNumeroDocumento() + " ya pertenece a otro cliente.");
            }
            clienteExistente.setTipoDocumento(datosActualizados.getTipoDocumento());
            clienteExistente.setNumeroDocumento(datosActualizados.getNumeroDocumento());
        }

        clienteExistente.setNombreRazonSocial(datosActualizados.getNombreRazonSocial());
        clienteExistente.setDireccion(datosActualizados.getDireccion());
        clienteExistente.setTelefono(datosActualizados.getTelefono());
        clienteExistente.setEmail(datosActualizados.getEmail());

        return clienteRepository.save(clienteExistente);
    }

    // 3. CONSULTAS
    @Transactional(readOnly = true)
    public List<Cliente> listarClientesActivos() {
        return clienteRepository.findByEstadoTrue();
    }

    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorNumeroDocumento(String numeroDocumento) {
        return clienteRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con el documento: " + numeroDocumento));
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNombre(String texto) {
        return clienteRepository.findByNombreRazonSocialContainingIgnoreCase(texto);
    }



    @Transactional
    public void desactivarCliente(Long id) {
        Cliente cliente = buscarPorId(id);
        cliente.setEstado(false);
        clienteRepository.save(cliente);
    }

    @Transactional
    public void reactivarCliente(Long id) {
        Cliente cliente = buscarPorId(id);
        cliente.setEstado(true);
        clienteRepository.save(cliente);
    }

}
