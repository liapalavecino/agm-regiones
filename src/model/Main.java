package model;

import java.util.ArrayList;
import java.util.HashSet;

public class Main { 
	public static void main(String[] args) {
		// Crear un conjunto de vértices
		ArrayList<Integer> vertices = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			vertices.add(i);
		}
		// Crear un grafo con los vértices creados
		Grafo grafo = new Grafo(vertices);
		
		GrafoPonderado grafoConPesos = new GrafoPonderado(grafo.consultarVertices());

		grafoConPesos.agregarArista(0, 1, 4);
		grafoConPesos.agregarArista(0, 7, 8);
		grafoConPesos.agregarArista(1, 2, 8);
		grafoConPesos.agregarArista(1, 7, 12);
		grafoConPesos.agregarArista(2, 8, 3);
		grafoConPesos.agregarArista(2, 3, 6);
		grafoConPesos.agregarArista(2, 5, 4);
		grafoConPesos.agregarArista(3, 4, 9);
		grafoConPesos.agregarArista(3, 5, 13);
		grafoConPesos.agregarArista(4, 5, 10);
		grafoConPesos.agregarArista(5, 6, 3);
		grafoConPesos.agregarArista(6, 7, 1);
		grafoConPesos.agregarArista(6, 8, 5);
		grafoConPesos.agregarArista(7, 8, 6);
		Kruskal agm = new Kruskal(grafoConPesos);
		
		System.out.println("\nES CONEXO GRAFO ORIGINAL: " + BFS.esConexo(grafoConPesos));

		System.out.println("\nGRAFO AGM \n");
		
		for (HashSet<Integer> arista : agm.obtenerAGM().consultarVecinos()) {
			System.out.println(arista.toString());
		}
		
		System.out.println("\nGRAFO ORIGINAL \n");
		
		for (HashSet<Integer> arista : grafoConPesos.consultarVecinos()) {
			System.out.println(arista.toString());
		}

		System.out.println("\nES CONEXO GRAFO AGM: " + BFS.esConexo(agm.obtenerAGM()));
	}

}