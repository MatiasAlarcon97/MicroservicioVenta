package com.duoc.MicroservicioVentas.service;

import com.duoc.MicroservicioVentas.model.ModelVenta;
import com.duoc.MicroservicioVentas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceVenta {

    @Autowired
    private VentaRepository ventaRepository;

    private String generarFactura() {
        List<ModelVenta> ventas = ventaRepository.findAll();

        int ultimoNumero = ventas.stream()
                .map(ModelVenta::getFactura)
                .filter(f -> f != null && f.startsWith("FAC_0"))
                .mapToInt(f -> {
                    try {
                        return Integer.parseInt(f.replace("FAC_0", ""));
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .max()
                .orElse(0);

        int siguiente = ultimoNumero + 10;
        return String.format("FAC_0%03d", siguiente);
    }

    public ModelVenta crearVenta(ModelVenta venta) {
        venta.setFactura(generarFactura());
        return ventaRepository.save(venta);
    }

    public ModelVenta actualizarVenta(Long id, ModelVenta nuevaVenta) {
        return ventaRepository.findById(id).map(ventaExistente -> {
            ventaExistente.setFecha(nuevaVenta.getFecha());
            ventaExistente.setMonto_total(nuevaVenta.getMonto_total());
            ventaExistente.setFactura(nuevaVenta.getFactura());
            return ventaRepository.save(ventaExistente);
        }).orElse(null);
    }

    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    public List<ModelVenta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Optional<ModelVenta> obtenerVenta(Long id) {
        return ventaRepository.findById(id);
    }
}

