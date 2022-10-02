package com.example.demoJUnit1.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demoJUnit1.model.Articulo;

public class CarritoCompraServiceImp implements CarritoCompraServiceI {

	private List<Articulo> cesta = new ArrayList<>();
	
	@Autowired
	private BaseDatosI baseDatos;

	@Override
	public void limpiarCesta() {
		cesta.clear();

	}

	@Override
	public void addArticulo(Articulo articulo) {

		cesta.add(articulo);

	}

	@Override
	public int getNumArticulo() {

		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulos() {

		return cesta;
	}
	

	

	@Override
	public double totalPrice() {
		
		Double total = 0D;
		
		for (Articulo articulo : cesta) {
			
			total = total + articulo.getPrecio();
		}
		return total;
	}

	@Override
	public double calculadorDescuento(double precio, double porcentajeDescuento) {
	
		return precio - precio*porcentajeDescuento/100;
	}
	
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje) {
		Articulo articulo = baseDatos.findArticuloById(1);
		if(Optional.ofNullable(articulo).isPresent()) {
			return calculadorDescuento(articulo.getPrecio(), porcentaje);
		}else {
			System.out.println("No se encuentra ningún artículo con esa ID: " + idArticulo);
		}
		return null;
	}

	@Override
	public List<Articulo> getArticulosBBDD() {
		baseDatos.iniciarBBDD();
		return baseDatos.getArticulos();
		
	}


	
	public void insertarArticuloDesdeBBDD(Articulo articulo) {
		
		Integer obtenerId = baseDatos.insertarArticuloBBDD(getNumArticulo(), articulo);
		cesta.add(articulo);
		
	}

	

}
