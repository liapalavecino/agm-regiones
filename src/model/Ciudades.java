package model;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Ciudades {

	private Coordinate coor;
	private String nombre;
	private double similaridad;
	
	
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

	public double consultarSimilaridad() {
		return similaridad;
	}

	public void agregarSimilaridad(double similaridad) {
		this.similaridad = similaridad;
	}
	
}
