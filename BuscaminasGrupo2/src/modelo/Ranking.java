package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ranking {
	private Map<Dificultad, List<Jugador>> rankings;

	public Ranking() {
		rankings = new HashMap<>();
		rankings.put(Dificultad.FACIL, new ArrayList<>());
		rankings.put(Dificultad.MEDIO, new ArrayList<>());
		rankings.put(Dificultad.DIFICIL, new ArrayList<>());
	}
	
	public void agregarJugador(Dificultad dificultad, String nombre, int puntuaje) {
		List<Jugador> lista = rankings.get(dificultad);
		lista.add(new Jugador(nombre, puntuaje));
		Collections.sort(lista);
	}
	
	public void mostrarRanking(Dificultad dificultad) {
		System.out.println("Ranking - Dificultad: " + dificultad);
		for(Jugador jugador : rankings.get(dificultad)) {
		System.out.println(jugador);
		}
	}

}
