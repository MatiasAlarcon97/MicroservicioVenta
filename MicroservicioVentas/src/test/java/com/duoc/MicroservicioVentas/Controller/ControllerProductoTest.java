package com.duoc.MicroservicioVentas.Controller;

import com.duoc.MicroservicioVentas.controller.ControllerProducto;
import com.duoc.MicroservicioVentas.model.ModelProducto;
import com.duoc.MicroservicioVentas.service.ServiceProducto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ControllerProductoTest {

    @Mock
    private ServiceProducto serviceProducto;

    @InjectMocks
    private ControllerProducto controllerProducto;

    private ModelProducto producto;

    @BeforeEach
    void setUp() {
        producto = new ModelProducto();
        producto.setId_producto(1L);
        producto.setNombre("Producto de prueba");
        producto.setCantidad(10);
        producto.setValor(19990.0);
    }

    @Test
    void agregarProducto_DeberiaRetornarProductoCreado() {
        // Configurar mock
        when(serviceProducto.agregarProducto(any(ModelProducto.class))).thenReturn(producto);

        // Ejecutar método del controlador
        ModelProducto resultado = controllerProducto.agregarProducto(producto);

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId_producto());
        assertEquals("Producto de prueba", resultado.getNombre());
        verify(serviceProducto, times(1)).agregarProducto(any(ModelProducto.class));
    }

    @Test
    void eliminarProducto_DeberiaLlamarAlServicio() {
        // No necesita configuración de when porque es void

        // Ejecutar método del controlador
        controllerProducto.eliminarProducto(1L);

        // Verificar que se llamó al servicio
        verify(serviceProducto, times(1)).eliminarProducto(1L);
    }

    @Test
    void listarProductos_DeberiaRetornarListaDeProductos() {
        // Configurar mock
        ModelProducto producto2 = new ModelProducto();
        producto2.setId_producto(2L);
        producto2.setNombre("Otro producto");

        List<ModelProducto> productos = Arrays.asList(producto, producto2);
        when(serviceProducto.listarProductos()).thenReturn(productos);

        // Ejecutar método del controlador
        List<ModelProducto> resultado = controllerProducto.listarProductos();

        // Verificaciones
        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getId_producto());
        assertEquals(2L, resultado.get(1).getId_producto());
        verify(serviceProducto, times(1)).listarProductos();
    }

    @Test
    void obtenerProducto_Existente_DeberiaRetornarProducto() {
        // Configurar mock
        when(serviceProducto.obtenerProducto(1L)).thenReturn(Optional.of(producto));

        // Ejecutar método del controlador
        ModelProducto resultado = controllerProducto.obtenerProducto(1L);

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId_producto());
        assertEquals("Producto de prueba", resultado.getNombre());
    }

    @Test
    void obtenerProducto_NoExistente_DeberiaRetornarNull() {
        // Configurar mock
        when(serviceProducto.obtenerProducto(99L)).thenReturn(Optional.empty());

        // Ejecutar método del controlador
        ModelProducto resultado = controllerProducto.obtenerProducto(99L);

        // Verificaciones
        assertNull(resultado);
    }

    @Test
    void obtenerProducto_ConDatosInvalidos_DeberiaManejarError() {
        // Configurar mock para lanzar excepción
        when(serviceProducto.obtenerProducto(-1L)).thenThrow(new IllegalArgumentException("ID inválido"));

        // Ejecutar y verificar que lanza excepción
        assertThrows(IllegalArgumentException.class, () -> {
            controllerProducto.obtenerProducto(-1L);
        });
    }
}
