package com.duoc.MicroservicioVentas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class ModelVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    private LocalDate fecha;
    private Double monto_total;
    private String factura;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<VentaProducto> productos;
}
