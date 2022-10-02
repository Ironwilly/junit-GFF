package com.example.demoJUnit1.services;

import java.util.List;

import com.example.demoJUnit1.model.Articulo;

public interface CarritoCompraServiceI {

	public void limpiarCesta();
	
	public void addArticulo(Articulo articulo);
	
	public int getNumArticulo();
	
	public List<Articulo> getArticulos();
	
	public List<Articulo> getArticulosBBDD();
	
	public double totalPrice();
	
	public double calculadorDescuento(double precio, double porcentajeDescuento);
}
