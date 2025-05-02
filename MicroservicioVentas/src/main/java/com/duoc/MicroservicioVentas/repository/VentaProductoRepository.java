package com.duoc.MicroservicioVentas.repository;

import com.duoc.MicroservicioVentas.model.VentaProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaProductoRepository extends JpaRepository<VentaProducto, Long> {
    List<VentaProducto> findByVentaIdVenta(Long idVenta);
}

