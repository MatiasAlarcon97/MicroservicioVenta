package com.duoc.MicroservicioVentas.Controller;

import com.duoc.MicroservicioVentas.controller.ControllerVentaProducto;
import com.duoc.MicroservicioVentas.model.ModelProducto;
import com.duoc.MicroservicioVentas.model.ModelVenta;
import com.duoc.MicroservicioVentas.model.VentaProducto;
import com.duoc.MicroservicioVentas.service.ServiceVentaProducto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ControllerVentaProductoTest {

    @Mock
    private ServiceVentaProducto serviceVentaProducto;

    @InjectMocks
    private ControllerVentaProducto controllerVentaProducto;

    private VentaProducto ventaProducto1, ventaProducto2;

    @BeforeEach
    void setUp() {
        // Configurar ModelVenta y ModelProducto de prueba
        ModelVenta venta = new ModelVenta();
        venta.setIdVenta(1L);

        ModelProducto producto = new ModelProducto();
        producto.setId_producto(101L);
        producto.setNombre("Producto Test");

        // Crear datos de prueba
        ventaProducto1 = new VentaProducto();
        ventaProducto1.setId_vp(1L);
        ventaProducto1.setCantidad(2);
        ventaProducto1.setValor_unitario(15000.0);
        ventaProducto1.setVenta(venta);
        ventaProducto1.setProducto(producto);

        ventaProducto2 = new VentaProducto();
        ventaProducto2.setId_vp(2L);
        ventaProducto2.setCantidad(3);
        ventaProducto2.setValor_unitario(20000.0);
        ventaProducto2.setVenta(venta);
        ventaProducto2.setProducto(producto);
    }

    @Test
    void listarTodasLasVentasProductos_DeberiaRetornarListaCompleta() {
        // Configurar mock
        List<VentaProducto> ventasProductos = Arrays.asList(ventaProducto1, ventaProducto2);
        when(serviceVentaProducto.listarVentaProductos()).thenReturn(ventasProductos);

        // Ejecutar método del controlador
        List<VentaProducto> resultado = controllerVentaProducto.listarTodasLasVentasProductos();

        // Verificaciones
        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getId_vp());
        assertEquals(2L, resultado.get(1).getId_vp());
        verify(serviceVentaProducto, times(1)).listarVentaProductos();
    }

    @Test
    void listarPorVenta_DeberiaRetornarVentasEspecificas() {
        // Configurar mock
        List<VentaProducto> ventasEspecificas = Arrays.asList(ventaProducto1);
        when(serviceVentaProducto.listarPorIdVenta(1L)).thenReturn(ventasEspecificas);

        // Ejecutar método del controlador
        List<VentaProducto> resultado = controllerVentaProducto.listarPorVenta(1L);

        // Verificaciones
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId_vp());
        assertEquals(1L, resultado.get(0).getVenta().getIdVenta());
        verify(serviceVentaProducto, times(1)).listarPorIdVenta(1L);
    }

    @Test
    void listarPorVenta_SinResultados_DeberiaRetornarListaVacia() {
        // Configurar mock para lista vacía
        when(serviceVentaProducto.listarPorIdVenta(99L)).thenReturn(List.of());

        // Ejecutar método del controlador
        List<VentaProducto> resultado = controllerVentaProducto.listarPorVenta(99L);

        // Verificaciones
        assertTrue(resultado.isEmpty());
        verify(serviceVentaProducto, times(1)).listarPorIdVenta(99L);
    }

    @Test
    void listarTodasLasVentasProductos_Vacia_DeberiaRetornarListaVacia() {
        // Configurar mock para lista vacía
        when(serviceVentaProducto.listarVentaProductos()).thenReturn(List.of());

        // Ejecutar método del controlador
        List<VentaProducto> resultado = controllerVentaProducto.listarTodasLasVentasProductos();

        // Verificaciones
        assertTrue(resultado.isEmpty());
        verify(serviceVentaProducto, times(1)).listarVentaProductos();
    }
}