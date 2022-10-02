package com.example.demoJUnit1.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demoJUnit1.model.Articulo;

@Service
public class BaseDatosImp implements BaseDatosI {

	
	private Map<Integer,Articulo> baseDatos = new HashMap<>();
	
	@Override
	public void iniciarBBDD() {
		baseDatos.put(1, new Articulo("Pantalón", 20D));
		baseDatos.put(2, new Articulo("Camisa", 30D));
		baseDatos.put(3, new Articulo("Jersey", 40D));
		baseDatos.put(4, new Articulo("Vestido", 50D));

	}

	@Override
	public List<Articulo> getArticulos() {
		List<Articulo> listaArticulos = new ArrayList();
		for(Map.Entry<Integer, Articulo> entry : baseDatos.entrySet()) {
			listaArticulos.add(entry.getValue());
		}
		return listaArticulos;
	}

	@Override
	public Articulo findArticuloById(Integer identificador) {
		
		return baseDatos.get(identificador);
	}
	
	

}
