package com.duoc.MicroservicioVentas.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VentaConEnvioDTO {
    private LocalDate fecha;
    private Double monto_total;
    private String direccion_entrega;
    private LocalDate fecha_envio;
    private String estado_pedido;

}
