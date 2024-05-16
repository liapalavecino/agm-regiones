package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.GrafoPonderado;
import model.Kruskal;

public class KruskalTest {

	GrafoPonderado triangulo;
	GrafoPonderado aislado;
	Kruskal agmTriangulo;
	ArrayList<Integer> vertices3;
	ArrayList<Integer> vertices2;

	@Before
	public void init() {
		vertices3 = new ArrayList<>();
		vertices2 = new ArrayList<>();
		
		for (int i = 0; i < 3; i++) {
			vertices3.add(i);
		}
		
		for (int i = 0; i < 2; i++) {
			vertices2.add(i);
		}
		
		triangulo = new GrafoPonderado(vertices3);
		triangulo.agregarArista(0, 1, 0.5);
		triangulo.agregarArista(0, 2, 0.2);
		triangulo.agregarArista(1, 2, 0.6);

		agmTriangulo = new Kruskal(triangulo);

	}

	@Test
	public void happyPathTest() {
		GrafoPonderado agm = new GrafoPonderado(vertices3);
		agm.agregarArista(0, 1, 0.5);
		agm.agregarArista(0, 2, 0.2);

		assertEquals(agm, agmTriangulo.obtenerAGM());
	}

	@Test(expected = IllegalArgumentException.class)
	public void grafoNoConexo() {
		GrafoPonderado g = new GrafoPonderado(vertices3);
		Kruskal agm = new Kruskal(g);
		agm.obtenerAGM();
	}

	@Test
	public void encontrarAntesPesoMaximoTest() {
		GrafoPonderado grafo = new GrafoPonderado(vertices3);
		grafo.agregarArista(0, 1, 0.2);
		grafo.agregarArista(0, 2, 0.3);
		grafo.agregarArista(1, 2, 0.7);

		GrafoPonderado grafoEsperado = new GrafoPonderado(vertices3);
		grafoEsperado.agregarArista(0, 1, 0.2);
		grafoEsperado.agregarArista(0, 2, 0.3);

		Kruskal agm = new Kruskal(grafo);

		assertEquals(grafoEsperado, agm.obtenerAGM());
	}

	@Test
	public void bordeTest() {
		GrafoPonderado grafo = new GrafoPonderado(vertices2);
		grafo.agregarArista(0, 1, 2.0);

		GrafoPonderado grafoEsperado = new GrafoPonderado(vertices2);
		grafoEsperado.agregarArista(0, 1, 2.0);

		Kruskal agm = new Kruskal(grafo);

		assertEquals(grafoEsperado, agm.obtenerAGM());
	}
}
