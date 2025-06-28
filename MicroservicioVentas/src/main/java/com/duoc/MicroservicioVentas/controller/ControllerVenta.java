package com.duoc.MicroservicioVentas.controller;

import com.duoc.MicroservicioVentas.dto.VentaConEnvioDTO;
import com.duoc.MicroservicioVentas.model.ModelVenta;
import com.duoc.MicroservicioVentas.service.ServiceVenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class ControllerVenta {

    @Autowired
    private ServiceVenta serviceVenta;

    @PostMapping
    public ModelVenta crearVenta(@RequestBody ModelVenta venta) {
        return serviceVenta.crearVenta(venta);
    }


    @PostMapping("/crear-con-envio")
    public ResponseEntity<ModelVenta> crearVentaConEnvio(@RequestBody VentaConEnvioDTO dto) {
        ModelVenta ventaCreada = serviceVenta.crearVentaConEnvio(dto);
        return ResponseEntity.ok(ventaCreada);
    }

    @PutMapping("/{id}")
    public ModelVenta actualizarVenta(@PathVariable Long id, @RequestBody ModelVenta venta) {
        return serviceVenta.actualizarVenta(id, venta);
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Long id) {
        serviceVenta.eliminarVenta(id);
    }

    @GetMapping
    public List<ModelVenta> listarVentas() {
        return serviceVenta.listarVentas();
    }

    @GetMapping("/{id}")
    public Optional<ModelVenta> obtenerVenta(@PathVariable Long id) {
        return serviceVenta.obtenerVenta(id);
    }
}

