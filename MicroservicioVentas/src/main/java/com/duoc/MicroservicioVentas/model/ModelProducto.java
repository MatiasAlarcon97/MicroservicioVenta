package com.duoc.MicroservicioVentas.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

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


}
