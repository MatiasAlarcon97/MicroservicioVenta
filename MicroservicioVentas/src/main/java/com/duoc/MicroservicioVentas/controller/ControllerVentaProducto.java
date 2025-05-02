package com.duoc.MicroservicioVentas.controller;

import com.duoc.MicroservicioVentas.model.VentaProducto;
import com.duoc.MicroservicioVentas.service.ServiceVentaProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venta-producto")
public class ControllerVentaProducto {

    @Autowired
    private ServiceVentaProducto serviceVentaProducto;

    @GetMapping
    public List<VentaProducto> listarTodasLasVentasProductos() {
        return serviceVentaProducto.listarVentaProductos();
    }

    @GetMapping("/venta/{idVenta}")
    public List<VentaProducto> listarPorVenta(@PathVariable Long idVenta) {
        return serviceVentaProducto.listarPorIdVenta(idVenta);
    }
}

