package com.duoc.MicroservicioVentas.service;

import com.duoc.MicroservicioVentas.dto.PedidoConEnvioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnvioClient {

    private final String LOGISTICA_URL = "http://localhost:8080/pedidos-envio"; // cambia el puerto si usas otro


    private final RestTemplate restTemplate;

    public EnvioClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void crearPedidoDesdeVenta(PedidoConEnvioDTO dto) {
        try {
            restTemplate.postForObject(LOGISTICA_URL, dto, Void.class);
        } catch (Exception e) {
            System.err.println("Error al enviar el pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
