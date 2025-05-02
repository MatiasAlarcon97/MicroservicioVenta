package com.duoc.MicroservicioVentas.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
public class VentaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vp;

    private Integer cantidad;
    private Double valor_unitario;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private ModelVenta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ModelProducto producto;
}
