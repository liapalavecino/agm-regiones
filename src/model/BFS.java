package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BFS {

	private static List<Integer> L;
	private static boolean[] marcados;
	
	public static boolean esConexo(Grafo grafo) {
		comprobarValidezDeGrafo(grafo);
		int origen = grafo.consultarVertices().get(0);
		return alcanzables(grafo, origen).size() == grafo.consultarTamanio();
	}
	
	private static void inicializarRecorrido(Grafo g, int origen) {		
		L = new ArrayList<Integer>();
		marcados = new boolean[g.consultarTamanio()];
		L.add(origen);
	}
		
	public static Set<Integer> alcanzables(Grafo grafo, int origen) {
		Set<Integer> ret = new HashSet<Integer>();
		inicializarRecorrido(grafo, origen);
		int indice = 0;
		
		while (!L.isEmpty()) {							
			int i = L.get(0);							
			marcados[indice] = true;							
			ret.add(i);									
			agregarVecinosPendientes(grafo, i);
			L.remove(0);
			indice++;	
		}
		return ret;				
	}	
	
	private static void agregarVecinosPendientes(Grafo g, int vertice) {
		for (int vecino : g.obtenerVecinosDe(vertice))
			if (!marcados[g.obtenerIndiceDelVertice(vecino)] && !L.contains(vecino))			
				L.add(vecino);										
	}
		
	public static void comprobarValidezDeGrafo(Grafo grafo) {
		if (grafo == null)
			throw new NullPointerException("El grafo no puede ser null.");
		if (grafo.consultarTamanio() == 0)
			throw new IllegalArgumentException("El grafo no puede ser tamaño cero.");
		if (grafo.consultarTamanio() == 1)
			throw new IllegalArgumentException("El grafo no puede ser tamaño uno.");
	}
	
}
