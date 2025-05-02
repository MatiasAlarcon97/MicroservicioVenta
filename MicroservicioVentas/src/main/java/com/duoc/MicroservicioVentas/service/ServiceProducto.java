package com.duoc.MicroservicioVentas.service;

import com.duoc.MicroservicioVentas.model.ModelProducto;
import com.duoc.MicroservicioVentas.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProducto {

    @Autowired
    private ProductoRepository productoRepository;

    public ModelProducto agregarProducto(ModelProducto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long idProducto) {
        productoRepository.deleteById(idProducto);
    }

    public List<ModelProducto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<ModelProducto> obtenerProducto(Long idProducto) {
        return productoRepository.findById(idProducto);
    }
}

