package model;

import java.util.ArrayList;

public class Kruskal {

	private GrafoPonderado arbolGeneradorMinimo;
	private GrafoPonderado grafoOriginal;
	private ArrayList<Arista> aristasGrafoOriginal;
	private UnionFind elementosUnionFind;

	public Kruskal(GrafoPonderado grafo) {
		grafoOriginal = grafo;
		aristasGrafoOriginal = grafoOriginal.consultarAristas();
		arbolGeneradorMinimo = new GrafoPonderado(grafo.consultarVertices());
		elementosUnionFind = new UnionFind(grafo);
		generarArbolGeneradorMinimo();
	}

	private void generarArbolGeneradorMinimo() {
		if (!BFS.esConexo(grafoOriginal))
			throw new IllegalArgumentException("No es un AGM, ya que el grafo no es conexo.");

		Integer vertice = 1;

		while (vertice < grafoOriginal.consultarTamanio()) {
			Arista e = aristaMinimaSinFormarCiclo();

			if (e != null)
				agregarArista(e);

			vertice++;
		}
	}

	private Arista aristaMinimaSinFormarCiclo() {
		Arista aristaMinima = null;
		double pesoMinimo = Double.MAX_VALUE;

		for (Arista arista : aristasGrafoOriginal) {
			if (arista.consultarSimilaridad() < pesoMinimo && !formaCiclo(arista)) {
				pesoMinimo = arista.consultarSimilaridad();
				aristaMinima = new Arista(arista.consultarOrigen(), arista.consultarDestino(), pesoMinimo);
			}
		}
		aristasGrafoOriginal.remove(aristaMinima);

		return aristaMinima;
	}

	private boolean formaCiclo(Arista e) {
		int coordenadaOrigen = grafoOriginal.consultarVertices().indexOf(e.consultarOrigen());
		int coordenadaDestino = grafoOriginal.consultarVertices().indexOf(e.consultarDestino());
		return elementosUnionFind.find(coordenadaOrigen, coordenadaDestino);
	}

	private void agregarArista(Arista e) {
		int coordenadaOrigen = grafoOriginal.consultarVertices().indexOf(e.consultarOrigen());
		int coordenadaDestino = grafoOriginal.consultarVertices().indexOf(e.consultarDestino());
		arbolGeneradorMinimo.agregarArista(e.consultarOrigen(), e.consultarDestino(), e.consultarSimilaridad());
		elementosUnionFind.unir(coordenadaOrigen, coordenadaDestino);
	}

	public GrafoPonderado obtenerAGM() {
		return arbolGeneradorMinimo;
	}
}
