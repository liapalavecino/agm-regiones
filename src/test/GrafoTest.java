package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.Arista;
import model.Grafo;

public class GrafoTest {

	private Grafo grafo;

	@Before
	public void inicializar() {
		ArrayList<Integer> vertices = new ArrayList<>();
		vertices.add(1);
		vertices.add(2);
		vertices.add(3);

		grafo = new Grafo(vertices);
	}

	@Test(expected = IllegalArgumentException.class)
	public void crearGrafoListaConNuloTest() {
		ArrayList<Integer> listaConNull = new ArrayList<>();
		listaConNull.add(1);
		listaConNull.add(null);
		grafo = new Grafo(listaConNull);
	}

	@Test
	public void agregarAristaTest() {
		inicializar();

		grafo.agregarArista(1, 2);

		HashSet<Integer> vecinosEsperadosPrimerVertice = new HashSet<>();
		vecinosEsperadosPrimerVertice.add(2);
		HashSet<Integer> vecinosEsperadosSegundoVertice = new HashSet<>();
		vecinosEsperadosSegundoVertice.add(1);
		assertEquals(grafo.obtenerVecinosDe(1), vecinosEsperadosPrimerVertice);
		assertEquals(grafo.obtenerVecinosDe(2), vecinosEsperadosSegundoVertice);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaExistenteTest() {
		inicializar();

		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaLoop() {
		inicializar();

		grafo.agregarArista(1, 1);
	}

	@Test
	public void completarGrafoUnicoVerticeTest() {
		ArrayList<Integer> vertices = new ArrayList<>();
		vertices.add(1);
		Grafo grafo = new Grafo(vertices);
		grafo.completar();
		assertEquals(0, grafo.consultarVecinos().get(0).size());
	}

	@Test
	public void completarGrafoDosVertices() {
		ArrayList<Integer> vertices = new ArrayList<>();
		vertices.add(1);
		vertices.add(2);
		Grafo grafo = new Grafo(vertices);
		grafo.completar();

		assertEquals(2, grafo.consultarVecinos().size());
	}

	@Test
	public void completarGrafoNVertices() {
		inicializar();
		grafo.completar();
		int cantVerticesGrafo = 0;
		for (HashSet<Integer> conjunto : grafo.consultarVecinos()) 
			for (Integer vertice : conjunto) 
				cantVerticesGrafo++;
		
		// En un grafo con n vértices, se generan n*(n-1)/2 aristas
		int cantVertices = grafo.consultarTamanio();
		int cantAristasEsperadas = cantVertices * (cantVertices - 1) / 2;
		assertEquals(cantAristasEsperadas, cantVerticesGrafo/2);
	}

	@Test
	public void consultarVerticesTest() {
		inicializar();

		ArrayList<Integer> vertices = grafo.consultarVertices();

		ArrayList<Integer> verticesEsperados = new ArrayList<>();
		verticesEsperados.add(1);
		verticesEsperados.add(2);
		verticesEsperados.add(3);

		assertEquals(vertices, verticesEsperados);
	}

	@Test
	public void consultarExisteAristaTest() {
		inicializar();

		grafo.agregarArista(1, 2);

		assertTrue(grafo.existeArista(1, 2));
		assertTrue(grafo.existeArista(2, 1));
	}

	@Test
	public void consultarVecinosTest() {
		inicializar();
		grafo.completar();

		HashSet<Integer> vecinos = grafo.obtenerVecinosDe(1);
		HashSet<Integer> esperados = new HashSet<>();
		esperados.add(2);
		esperados.add(3);

		assertEquals(vecinos, esperados);
	}
}
