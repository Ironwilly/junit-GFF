package com.example.demoJUnit1.services;

import java.util.List;

import com.example.demoJUnit1.model.Articulo;

public interface BaseDatosI {

	public void iniciarBBDD();
	
	public List<Articulo> getArticulos();
	 
	public Articulo findArticuloById(Integer identificador);
	
	public Integer insertarArticuloBBDD(Integer id,Articulo articulo);
	
	
	
	
	
}


