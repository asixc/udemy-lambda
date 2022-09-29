package org.example.soluciones.defaultmethods;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAgrupador {

	/**
	 * Completad la definicion de la clase AgrupadorConList 
	 * para que implemente la interface
	 * (este ejercicio es de repaso, no usa los metodos default)
	 */
	@Test
	public void test_add() {
		
		Agrupador agrupador = new AgrupadorConList();
		assertEquals(0, agrupador.numeroElementos());
		
		agrupador.add("primero");
		assertEquals(1, agrupador.numeroElementos());
		
	}
	
	
	/**
	 * definid un metodo default addAll que acepte un Collection y
	 * a�ada cada objeto al agrupador
	 * Cuando tengais el metodo, descomentad el codigo comentado en el test
	 */
	@Test
	public void test_addAll() {
		
		Agrupador agrupador = new AgrupadorConList();
		agrupador.addAll(Arrays.asList("primero","segundo"));
		
		assertEquals(2,agrupador.numeroElementos());
	}
}
