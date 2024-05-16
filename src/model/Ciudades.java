package model;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Ciudades {

	private Coordinate coor;
	private String nombre;
	private int similaridad;
	
	
	public Ciudades(Coordinate coor) {
		this.coor = coor;
	}
	
	public void agregarNombreCiudad(String nombre) {
		this.nombre = nombre;
	}
	
	public Coordinate consultarCoordenada() {
		return this.coor;
	}
	
	public String consultarNombreCiudad() {
		return this.nombre;
	}

	public int consultarSimilaridad() {
		return similaridad;
	}

	public void setSimilaridad(int similaridad) {
		this.similaridad = similaridad;
	}
	
}
