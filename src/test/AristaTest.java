package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.Arista;

public class AristaTest {

	@Test
	public void constructorArista() {
		Arista arista = new Arista(1, 2, 3);
		
		assertEquals(arista.consultarOrigen(), 1);
		assertEquals(arista.consultarDestino(), 2);
		assertEquals(3.0, arista.consultarSimilaridad(), 0.0001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorAristaOrigenNegativo() {
		Arista.validarParametros(-1, 2, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorAristaDestinoNegativo() {
		Arista.validarParametros(1, -2, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorAristaSimilaridadNegativa() {
		Arista.validarParametros(1, 2, -3);
	}
	
	
	@Test
	public void validarParametrosTest() {
		Arista.validarParametros(1,2,3);
	}
	
	@Test
	public void equalsAristasIguales() {
		Arista aristaA = new Arista(1, 2, 3);
		Arista aristaB = new Arista(1, 2, 3);

		assertTrue(aristaA.equals(aristaB));
	}
	
	@Test
	public void equalsAristasDistintoPeso() {
		Arista aristaA = new Arista(1, 2, 4);
		Arista aristaB = new Arista(1, 2, 5);
		
		assertFalse(aristaA.equals(aristaB));
	}
	
	@Test
	public void equalsAristasEspejadas() {
		Arista aristaA = new Arista(1, 2, 4);
		Arista aristaB = new Arista(2, 1, 4);
		
		assertFalse(aristaA.equals(aristaB));
	}
	
	@Test
	public void equalsNull() {
		Arista aristaA = new Arista(1, 2, 4);
		Arista aristaB = null;
		
		assertFalse(aristaA.equals(aristaB));
	}
	
	
	@Test
	public void equalsMismaReferencia() {
		Arista aristaA = new Arista(1, 2, 4);
		
		assertTrue(aristaA.equals(aristaA));
	}
	
	@Test
	public void asignarSimilaridad() {
		Arista aristaA = new Arista(1, 2, 4);
		aristaA.asignarSimilaridad(5);
		
		assertEquals(5.0, aristaA.consultarSimilaridad(), 0.0001);

	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void asignarSimilaridadNegativa() {
		Arista aristaA = new Arista(1, 2,3);
		aristaA.asignarSimilaridad(-2);
	}
}
