package com.duoc.MicroservicioVentas.Controller;

import com.duoc.MicroservicioVentas.controller.ControllerVenta;
import com.duoc.MicroservicioVentas.service.EnvioClient;
import com.duoc.MicroservicioVentas.dto.PedidoConEnvioDTO;
import com.duoc.MicroservicioVentas.dto.VentaConEnvioDTO;
import com.duoc.MicroservicioVentas.model.ModelVenta;
import com.duoc.MicroservicioVentas.repository.VentaRepository;
import com.duoc.MicroservicioVentas.service.ServiceVenta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ControllerVentaTest {

    @Mock
    private ServiceVenta serviceVenta;

    @Mock
    private VentaRepository ventaRepository;

    @Mock
    private EnvioClient envioClient;

    @InjectMocks
    private ControllerVenta controllerVenta;

    private ModelVenta venta;
    private VentaConEnvioDTO ventaConEnvioDTO;

    @BeforeEach
    void setUp() {
        venta = new ModelVenta();
        venta.setIdVenta(1L);
        venta.setFecha(LocalDate.now());
        venta.setMonto_total(15000.0);
        venta.setFactura("FAC_0010");

        ventaConEnvioDTO = new VentaConEnvioDTO();
        ventaConEnvioDTO.setFecha(LocalDate.now());
        ventaConEnvioDTO.setMonto_total(20000.0);
        ventaConEnvioDTO.setDireccion_entrega("Calle Principal 123");
        ventaConEnvioDTO.setFecha_envio(LocalDate.now().plusDays(2));
        ventaConEnvioDTO.setEstado_pedido("PENDIENTE");
    }

    @Test
    void crearVenta_DeberiaRetornarVentaConFacturaGenerada() {
        when(serviceVenta.crearVenta(any(ModelVenta.class))).thenReturn(venta);

        ModelVenta nuevaVenta = new ModelVenta();
        nuevaVenta.setMonto_total(10000.0);

        ModelVenta resultado = controllerVenta.crearVenta(nuevaVenta);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdVenta());
        assertNotNull(resultado.getFactura());
        assertTrue(resultado.getFactura().startsWith("FAC_0"));
        verify(serviceVenta, times(1)).crearVenta(any(ModelVenta.class));
    }

    @Test
    void crearVentaConEnvio_DeberiaCrearVentaYEnvio() {
        when(serviceVenta.crearVentaConEnvio(any(VentaConEnvioDTO.class))).thenReturn(venta);

        ResponseEntity<ModelVenta> response = controllerVenta.crearVentaConEnvio(ventaConEnvioDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getIdVenta());
        verify(serviceVenta, times(1)).crearVentaConEnvio(any(VentaConEnvioDTO.class));
    }

    @Test
    void listarVentas_DeberiaRetornarTodasLasVentas() {
        ModelVenta venta2 = new ModelVenta();
        venta2.setIdVenta(2L);
        venta2.setMonto_total(25000.0);

        List<ModelVenta> ventas = Arrays.asList(venta, venta2);
        when(serviceVenta.listarVentas()).thenReturn(ventas);

        List<ModelVenta> resultado = controllerVenta.listarVentas();

        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getIdVenta());
        assertEquals(2L, resultado.get(1).getIdVenta());
        verify(serviceVenta, times(1)).listarVentas();
    }

    @Test
    void obtenerVenta_Existente_DeberiaRetornarVenta() {
        when(serviceVenta.obtenerVenta(1L)).thenReturn(Optional.of(venta));

        Optional<ModelVenta> resultado = controllerVenta.obtenerVenta(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getIdVenta());
        assertEquals("FAC_0010", resultado.get().getFactura());
    }

    @Test
    void actualizarVenta_DeberiaActualizarCamposCorrectamente() {
        ModelVenta ventaActualizada = new ModelVenta();
        ventaActualizada.setMonto_total(18000.0);
        ventaActualizada.setFactura("FAC_0020");

        when(serviceVenta.actualizarVenta(eq(1L), any(ModelVenta.class))).thenReturn(ventaActualizada);

        ModelVenta resultado = controllerVenta.actualizarVenta(1L, ventaActualizada);

        assertNotNull(resultado);
        assertEquals(18000.0, resultado.getMonto_total());
        assertEquals("FAC_0020", resultado.getFactura());
    }

    @Test
    void eliminarVenta_DeberiaLlamarAlMetodoDeServicio() {
        doNothing().when(serviceVenta).eliminarVenta(1L);

        controllerVenta.eliminarVenta(1L);

        verify(serviceVenta, times(1)).eliminarVenta(1L);
    }

    @Test
    void crearVentaConEnvio_DeberiaManejarErrorEnEnvio() {
        when(serviceVenta.crearVentaConEnvio(any(VentaConEnvioDTO.class)))
                .thenThrow(new RuntimeException("Error en servicio de envío"));

        assertThrows(RuntimeException.class, () -> {
            controllerVenta.crearVentaConEnvio(ventaConEnvioDTO);
        });
    }
}