package modelo;

public class Jugador implements Comparable<Jugador>{
	
	private String nombre;
	private int puntuaje;
	
	public Jugador(String nombre, int puntuaje) {
		this.nombre = nombre;
		this.puntuaje = puntuaje;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntuaje() {
		return puntuaje;
	}

	@Override
	public int compareTo(Jugador otro) {
		return Integer.compare(this.puntuaje, otro.puntuaje);
	}
	
	@Override
	public String toString() {
		return nombre+" | "+puntuaje;
	}
	

	
	
	
	

}
