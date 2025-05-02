package com.duoc.MicroservicioVentas.repository;

import com.duoc.MicroservicioVentas.model.ModelVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<ModelVenta, Long> {
}

