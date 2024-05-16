package view;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class SimilaridadCoordenadas {
	private Coordinate origen;
	private Coordinate destino;
	private int similaridad;

	public SimilaridadCoordenadas(Coordinate coordenada1, Coordinate coordenada2, int similaridad) {
		this.origen = coordenada1;
		this.destino = coordenada2;
		this.similaridad = similaridad;
	}

	public Coordinate consultarCoordenada1() {
		return origen;
	}

	public Coordinate consultarCoordenada2() {
		return destino;
	}

	public int consultarSimilaridad() {
		return similaridad;
	}
}
