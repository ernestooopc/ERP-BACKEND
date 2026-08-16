package com.sistema.erp_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.erp_backend.model.DetalleVenta;
import com.sistema.erp_backend.model.Producto;
import com.sistema.erp_backend.model.Venta;
import com.sistema.erp_backend.repository.DetalleVentaRepository;
import com.sistema.erp_backend.repository.ProductoRepository;
import com.sistema.erp_backend.repository.VentaRepository;

@Service
public class VentaService {


    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository, DetalleVentaRepository detalleVentaRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public Venta registrarVenta(Venta venta, String serie, List<DetalleVenta> detalles) {  

        venta.setSerie(serie);
        venta.setFechaVenta(LocalDateTime.now());
        venta.setEstado(true);

        String numeroGenerado = generarNumeroComprobante(serie);
        venta.setNumeroComprobante(numeroGenerado);

        Venta ventaGuardada = ventaRepository.save(venta);
        for(DetalleVenta detalle : detalles){
            Producto prod = detalle.getProducto();

        // Validar existencias
        if (prod.getStock() < detalle.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para el producto: " + prod.getNombre());
        }

        // Descontar inventario
        prod.setStock(prod.getStock() - detalle.getCantidad());
        productoRepository.save(prod);

        // Asociar detalle con la venta guardada
        detalle.setVenta(ventaGuardada);
        detalleVentaRepository.save(detalle);
        }

        return ventaGuardada;
    }

    private String generarNumeroComprobante(String serie) {
        Optional<String> ultimoComprobanteOpt = ventaRepository.findUltimoComprobantePorSerie(serie);

        int siguienteCorrelativo = 1;
        if(ultimoComprobanteOpt.isPresent()){
            String ultimoComprobante = ultimoComprobanteOpt.get();

            String[] partes = ultimoComprobante.split("-");
            if(partes.length == 2){
                siguienteCorrelativo = Integer.parseInt(partes[1]) + 1;
            }
        }
        return String.format("%s-%08d", serie, siguienteCorrelativo);
    }

    @Transactional
    public void anularVenta(Long ventaId) {
        
        Venta venta = ventaRepository.findById(ventaId)
        .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + ventaId));

        if(!venta.isEstado()){
            throw new RuntimeException("La venta ya está anulada.");
        }

        venta.setEstado(false);
        ventaRepository.save(venta);


        List<DetalleVenta> detalles = detalleVentaRepository.findByVentaId(ventaId);
        for (DetalleVenta detalle : detalles) {
            Producto prod = detalle.getProducto();
            prod.setStock(prod.getStock() + detalle.getCantidad());
            productoRepository.save(prod);
        }
    }


    @Transactional(readOnly = true)
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Venta obtenerVentaPorId(Long ventaId) {
        return ventaRepository.findById(ventaId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + ventaId));
    }

    @Transactional(readOnly = true)
    public List<DetalleVenta> obtenerDetallesDeVenta(Long ventaId) {
        return detalleVentaRepository.findByVentaId(ventaId);
    }
}

