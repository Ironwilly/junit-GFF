package com.example.demoJUnit1.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demoJUnit1.model.Articulo;

@ExtendWith(MockitoExtension.class)
public class CarritoCompraServiceImpTest {

	
	
	@InjectMocks
	private CarritoCompraServiceImp carrito = new CarritoCompraServiceImp();
	
	@Mock
	private BaseDatosI baseDatos;
	
	
	@Test
	public final void testLimpiarCesta() {
		
		
		carrito.limpiarCesta();
		assertTrue( carrito.getArticulos().isEmpty());
		
	}

	@Test
	public final void testAddArticulo() {
		assertTrue(carrito.getArticulos().isEmpty());
		carrito.addArticulo(new Articulo("Pantalón",20D));
		assertFalse(carrito.getArticulos().isEmpty());
	}

	@Test
	public final void testGetNumArticulo() {
		carrito.addArticulo(new Articulo("Pantalón",20D));
		carrito.addArticulo(new Articulo("Pantalón",20D));
		Integer resultado = carrito.getNumArticulo();
		assertEquals(2, resultado);
	
	}

	@Test
	public final void testGetArticulos() {
		carrito.addArticulo(new Articulo("Pantalón",20D));
		carrito.addArticulo(new Articulo("Camisa",30D));
		List<Articulo> listado = carrito.getArticulos();
		assertEquals(2,listado.size());
		assertEquals("Camisa",listado.get(1).getNombre());
	}
	
	@Test
	public void testGetArticulosBBDD() {
		List<Articulo> lista = new ArrayList<>();
		lista.add(new Articulo("Pantalón",20D));
		lista.add(new Articulo("Camisa",30D));
		lista.add(new Articulo("Jersey",40D));
		lista.add(new Articulo("Vestido",50D));
		when(baseDatos.getArticulos()).thenReturn(lista);
		List<Articulo> listado = carrito.getArticulosBBDD();
		assertEquals(4, listado.size());
	}

	@Test
	public final void testTotalPrice() {
		carrito.addArticulo(new Articulo("Pantalón",20D));
		carrito.addArticulo(new Articulo("Camisa",30D));
		Double resultado = carrito.totalPrice();
		assertEquals(50D, resultado);
	}

	@Test
	public final void testCalculadorDescuento() {
		
		assertEquals(27D,carrito.calculadorDescuento(30D, 10D));
		
	}
	
	
	
	@Test
	public void testAplicarDescuento() {
		Articulo articulo = new Articulo("Camiseta", 20.00);
		when(baseDatos.findArticuloById(any(Integer.class))).thenReturn(articulo);
		Double res = carrito.aplicarDescuento(1,10D);
		assertEquals(18D,res);
		verify(baseDatos).findArticuloById(any(Integer.class));
	}
	
	
	@Test
	public void testInsertarArticuloDesdeBBDD() {
		Articulo articulo = new Articulo("Zapatos", 60.00);
		when(baseDatos.insertarArticuloBBDD(1, articulo)).thenReturn(1);
		assertEquals(1, carrito.getNumArticulo());
		verify(baseDatos).insertarArticuloBBDD(1, articulo);
		
	}
	
	
	

}
