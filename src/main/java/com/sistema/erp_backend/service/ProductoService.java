package com.sistema.erp_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.erp_backend.exception.ResourceNotFoundException;
import com.sistema.erp_backend.model.Producto;
import com.sistema.erp_backend.repository.ProductoRepository;


@Service
public class ProductoService {

    private final ProductoRepository productoRepository;


    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Transactional
    public Producto crearProducto(Producto producto) {
        if (productoRepository.existsByCodigo(producto.getCodigo())) {
            throw new RuntimeException("Ya existe un producto con el código: " + producto.getCodigo());
        }
        producto.setEstado(true);
        return productoRepository.save(producto);
    }



    @Transactional
    public Producto actualizarProducto(Long id, Producto datosActualizados) {
        Producto productoExistente = buscarPorId(id);

        // Si cambia el código, verificar que no esté ocupado por otro producto
        if (!productoExistente.getCodigo().equalsIgnoreCase(datosActualizados.getCodigo())) {
            if (productoRepository.existsByCodigo(datosActualizados.getCodigo())) {
                throw new RuntimeException("El código " + datosActualizados.getCodigo() + " ya está asignado a otro producto.");
            }
            productoExistente.setCodigo(datosActualizados.getCodigo());
        }

        productoExistente.setNombre(datosActualizados.getNombre());
        productoExistente.setDescripcion(datosActualizados.getDescripcion());
        productoExistente.setPrecio(datosActualizados.getPrecio());
        productoExistente.setStockMinimo(datosActualizados.getStockMinimo());

        return productoRepository.save(productoExistente);
    }

@Transactional(readOnly = true)
    public List<Producto> listarProductosActivos() {
        return productoRepository.findByEstadoTrue();
    }

    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public Producto buscarPorCodigo(String codigo) {
        return productoRepository.findByCodigoAndEstadoTrue(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Producto activo no encontrado con código: " + codigo));
    }

    @Transactional(readOnly = true)
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCaseAndEstadoTrue(nombre);
    }

    // 4. BAJA LÓGICA (DESACTIVAR)
    @Transactional
    public void desactivarProducto(Long id) {
        Producto producto = buscarPorId(id);
        producto.setEstado(false);
        productoRepository.save(producto);
    }

    // 5. REACTIVAR PRODUCTO
    @Transactional
    public void reactivarProducto(Long id) {
        Producto producto = buscarPorId(id);
        producto.setEstado(true);
        productoRepository.save(producto);
    }


}
