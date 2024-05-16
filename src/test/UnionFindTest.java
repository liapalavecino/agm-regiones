package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Grafo;
import model.UnionFind;


public class UnionFindTest {

	Grafo grafo;
	UnionFind unionFind;
	ArrayList<Integer> vertices10;

	@Before
	public void init() {
		vertices10 = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			vertices10.add(i);
		}
		
		grafo = new Grafo(vertices10);
		unionFind = new UnionFind(grafo);
		
		grafo.agregarArista(1, 2);
		unionFind.unir(1, 2);
		grafo.agregarArista(2, 3);
		unionFind.unir(2, 3);
		grafo.agregarArista(5, 3);
		unionFind.unir(5, 3);
		// componente conexa grafo1

		grafo.agregarArista(8, 7);
		unionFind.unir(8, 7);
		grafo.agregarArista(9, 7);
		unionFind.unir(9, 7);
		// componente conexa grafo2

		// componente grafo3 = vertice 0; grafo4 = vertice 4; grafo5 = vertice 6
	}

	@Test
	public void raiz() {
		assertEquals(unionFind.raiz(1), unionFind.raiz(2));
		// comparten la raiz 3
	}

	@Test
	public void distintasComponentes() {
		assertFalse(unionFind.raiz(1) == unionFind.raiz(7));
		assertFalse(unionFind.raiz(0) == unionFind.raiz(3));
		assertFalse(unionFind.raiz(0) == unionFind.raiz(4) || unionFind.raiz(0) == unionFind.raiz(6));
	}

	@Test
	public void find() {
		assertTrue(unionFind.find(1, 2));
		assertFalse(unionFind.find(4, 7));
	}

	@Test
	public void union() {
		unionFind.unir(0, 1);
		assertEquals(unionFind.raiz(0), unionFind.raiz(1));

		assertTrue(unionFind.find(0, 1));
		assertTrue(unionFind.find(0, 2));
		assertTrue(unionFind.find(0, 5));

		assertEquals(unionFind.raiz(0), unionFind.raiz(3));
		assertEquals(unionFind.raiz(0), unionFind.raiz(2));
		assertEquals(unionFind.raiz(0), unionFind.raiz(5));

		assertFalse(unionFind.raiz(0) == unionFind.raiz(8));
	}
}