package modelo;

public class Jugador implements Comparable<Jugador> {
	private String nombre;
	private Dificultad dificultad;
	private int casillasDescubiertas;
	private int minasActivadas;
	private int tiempo;
	private double puntuaje;

	public Jugador(String nombre, Dificultad dificultad, int casillasDescubiertas, int minasActivadas, int tiempo) {
		this.nombre = nombre;
		this.dificultad = dificultad;
		this.casillasDescubiertas = casillasDescubiertas;
		this.minasActivadas = minasActivadas;
		this.tiempo = tiempo;
		this.puntuaje = calcularPuntuacion();
	}

	private double calcularPuntuacion() {
		int coeficiente = switch (dificultad) {
		case FACIL -> 1;
		case MEDIO -> 2;
		case DIFICIL -> 3;
		};
		return ((double) (casillasDescubiertas - minasActivadas) * coeficiente) / tiempo;
	}

	public String getNombre() {
		return nombre;
	}

	public Dificultad getDificultad() {
		return dificultad;
	}

	public int getCasillasDescubiertas() {
		return casillasDescubiertas;
	}

	public int getMinasActivadas() {
		return minasActivadas;
	}

	public int getTiempo() {
		return tiempo;
	}

	public double getPuntuaje() {
		return puntuaje;
	}

	@Override
	public int compareTo(Jugador otro) {
		return Double.compare(otro.puntuaje, this.puntuaje);
	}

	@Override
	public String toString() {
		return nombre + " | " + dificultad + " | " + casillasDescubiertas + " | " + tiempo + "s | "
				+ String.format("%.2f", puntuaje);
	}

}
