package model;

import java.util.ArrayList;

public class GrafoPonderado extends Grafo {
	private ArrayList<Arista> aristas;

	public GrafoPonderado() {
		super(new ArrayList<>());
		this.aristas = new ArrayList<>();
	}
	public GrafoPonderado(ArrayList<Integer> vertices) {
		super(vertices);
		this.aristas = new ArrayList<Arista>();
	}
	
	public void agregarArista(int origen, int destino, double peso) {
		super.agregarArista(origen, destino); 
		Arista nuevaArista = new Arista(origen, destino, peso);
		if (!aristas.contains(nuevaArista)) {
			aristas.add(nuevaArista);
		}
	}

	public double getPesoArista(int origen, int destino) {
		for (int i = 0; i < aristas.size(); i++) {
			if ((aristas.get(i).consultarOrigen() == origen && aristas.get(i).consultarDestino() == destino)
					|| (aristas.get(i).consultarOrigen() == destino && aristas.get(i).consultarDestino() == origen)) {
				return aristas.get(i).consultarSimilaridad();
			}
		}
		throw new RuntimeException("No se encontró un peso entre " + origen + " y " + destino);
	}
	
	public ArrayList<Arista> consultarAristas(){
		return this.aristas;
	}
}
