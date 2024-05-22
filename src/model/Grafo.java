package model;

import java.util.ArrayList;
import java.util.HashSet;

public class Grafo {

	private ArrayList<Integer> vertices;
	ArrayList<HashSet<Integer>> LVecinos;
	
	
	public Grafo(ArrayList<Integer> vertices) {
		validarVertices(vertices);
		this.vertices = vertices;

		this.LVecinos = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < vertices.size(); i++) {
			LVecinos.add(new HashSet<Integer>());
		}
	}

	public void completar() {
		for (int origen : vertices)
			for (int destino : vertices)
				if (origen != destino && !existeArista(origen, destino))
					agregarArista(origen, destino);
	}

	public void agregarArista(int origen, int destino) {
		validarArista(origen, destino);
		LVecinos.get(obtenerIndiceDelVertice(origen)).add(destino);
		LVecinos.get(obtenerIndiceDelVertice(destino)).add(origen);
	}

	private void validarArista(int origen, int destino) {
		if (existeArista(origen, destino))
			throw new IllegalArgumentException("Esta arista ya existe");
		if (origen == destino)
			throw new IllegalArgumentException("no se permiten loops; (" + origen + ", " + destino + ")");
	}

	private void validarVertices(ArrayList<Integer> vertices) {
		if (vertices.size() <= 1) {


		}
		for (Integer ciudad : vertices) {
			if (ciudad == null) {
				throw new IllegalArgumentException("Al menos un vértice o arista es null");
			}

		}
	}

	public ArrayList<Integer> consultarVertices() {
		return this.vertices;
	}

	public ArrayList<HashSet<Integer>> consultarVecinos() {
		return this.LVecinos;
	}

	public int consultarTamanio() {
		return this.vertices.size();
	}

	public boolean existeArista(int origen, int destino) {
		return !LVecinos.isEmpty() && LVecinos.get(obtenerIndiceDelVertice(origen)).contains(destino);
	}

	public HashSet<Integer> obtenerVecinosDe(int vertice) {
		return LVecinos.get(obtenerIndiceDelVertice(vertice));
	}
	
	public ArrayList<HashSet<Integer>> obtenerVecinos() {
		return LVecinos;
	}
	
	public int contarVertices() {
	    int contador = 0;
	    for (HashSet<Integer> conjunto : LVecinos) {
	        contador += conjunto.size();
	    }
	    return contador;
	}
	
	public void eliminarArista(int origen, int destino) {
	    if (!existeArista(origen, destino)) {
	        throw new IllegalArgumentException("La arista no existe.");
	    }
	    LVecinos.get(obtenerIndiceDelVertice(origen)).remove(destino);
	    LVecinos.get(obtenerIndiceDelVertice(destino)).remove(origen);
	}

	public void agregarVertice(int coordenada) {
		LVecinos.add(new HashSet<>());
		if( obtenerIndiceDelVertice(coordenada) == -1) {
			vertices.add(coordenada);	
		}		
	}
	
    public int obtenerIndiceDelVertice(int unVertice) {
        return vertices.indexOf(unVertice);
    }
	
	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().getName().equals(this.getClass().getName())) {
			return false;
		}
		Grafo objGrafo = (Grafo) obj;
		if (!objGrafo.obtenerVecinos().equals(LVecinos)) {
			return false;
		}
		return true;
	}
}
