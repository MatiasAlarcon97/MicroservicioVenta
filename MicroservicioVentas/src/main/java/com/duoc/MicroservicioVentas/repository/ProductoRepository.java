package com.duoc.MicroservicioVentas.repository;

import com.duoc.MicroservicioVentas.model.ModelProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ModelProducto, Long> {
}

