package model;

public class Arista{
	private Integer provinciaOrigen; 
	private Integer provinciaDestino; 
	private double similaridad;
	
	public Arista(Integer provinciaOrigen, Integer provinciaDestino, double similaridad) {
		validarParametros(provinciaOrigen,  provinciaDestino, similaridad);
		this.provinciaOrigen = provinciaOrigen;
		this.provinciaDestino = provinciaDestino;
		this.similaridad = similaridad;
	}

	public int consultarOrigen() {
		return provinciaOrigen;
	}

	public int consultarDestino() {
		return provinciaDestino;
	}

	public double consultarSimilaridad() {
		return similaridad;
	}
	
	public void asignarSimilaridad(int similaridad) {
		validarSimilaridad(similaridad);
		this.similaridad = similaridad;
	}
	
	private void validarSimilaridad(int similaridad) {
		if(similaridad < 1) {
			throw new IllegalArgumentException("El peso de la arista no puede ser negativo");

		}
	}

	public String toString(){
		return "["+this.provinciaOrigen+", "+this.provinciaDestino+", "+this.similaridad+"]";
	}
	
	
	public static void validarParametros(int provinciaOrigen, int provinciaDestino, double similaridad){	
		if (provinciaOrigen < 0 || provinciaDestino < 0 || similaridad < 0) 
			throw new IllegalArgumentException ("Los vértices deben ser provincias existentes y la similaridad ser positiva");
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    Arista other = (Arista) obj;
	    return this.provinciaOrigen.equals(other.provinciaOrigen) &&
	           this.provinciaDestino.equals(other.provinciaDestino) &&
	           this.similaridad == other.similaridad;
	}

}
