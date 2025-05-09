package com.duoc.MicroservicioVentas.model;
import jakarta.persistence.*;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter
@Setter
public class ModelProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    private String nombre;
    private Integer cantidad;
    private Double valor;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<VentaProducto> ventas;

}
