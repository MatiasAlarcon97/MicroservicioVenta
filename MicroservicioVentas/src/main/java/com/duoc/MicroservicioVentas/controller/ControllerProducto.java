package com.duoc.MicroservicioVentas.controller;

import com.duoc.MicroservicioVentas.model.ModelProducto;
import com.duoc.MicroservicioVentas.service.ServiceProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ControllerProducto {

    @Autowired
    private ServiceProducto serviceProducto;

    @PostMapping
    public ModelProducto agregarProducto(@RequestBody ModelProducto producto) {
        return serviceProducto.agregarProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        serviceProducto.eliminarProducto(id);
    }

    @GetMapping
    public List<ModelProducto> listarProductos() {
        return serviceProducto.listarProductos();
    }

    @GetMapping("/{id}")
    public ModelProducto obtenerProducto(@PathVariable Long id) {
        return serviceProducto.obtenerProducto(id)
                .orElse(null);
    }

}

