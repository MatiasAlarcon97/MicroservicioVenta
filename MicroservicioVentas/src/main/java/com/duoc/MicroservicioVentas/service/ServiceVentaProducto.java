package com.duoc.MicroservicioVentas.service;

import com.duoc.MicroservicioVentas.model.VentaProducto;
import com.duoc.MicroservicioVentas.repository.VentaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceVentaProducto {

    @Autowired
    private VentaProductoRepository ventaProductoRepository;

    public List<VentaProducto> listarVentaProductos() {
        return ventaProductoRepository.findAll();
    }

    public List<VentaProducto> listarPorIdVenta(Long idVenta) {
        return ventaProductoRepository.findByVentaIdVenta(idVenta);
    }
}

