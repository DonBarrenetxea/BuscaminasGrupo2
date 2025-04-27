package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

	public void agregarJugador(Dificultad dificultad, String nombre, int casillasDescubiertas, int minasActivadas,
			int tiempo) {
		List<Jugador> lista = rankings.get(dificultad);
		Jugador nuevo = new Jugador(nombre, dificultad, casillasDescubiertas, minasActivadas, tiempo);
		lista.add(nuevo);
		Collections.sort(lista);
	}

	public void mostrarRanking(Dificultad dificultad) {
		System.out.println("Ranking - Dificultad: " + dificultad);
		for (Jugador jugador : rankings.get(dificultad)) {
			System.out.println(jugador);
		}
	}

	public void guardarRanking(String archivo) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
			for (Dificultad dif : rankings.keySet()) {
				for (Jugador j : rankings.get(dif)) {
					writer.write(j.getNombre() + "," + dif + "," + j.getCasillasDescubiertas() + "," + j.getTiempo()
							+ "," + j.getPuntuaje());
					writer.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cargarRanking(String archivo) {
		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = reader.readLine()) != null) {
				String[] partes = linea.split(",");
				String nombre = partes[0];
				Dificultad dif = Dificultad.valueOf(partes[1]);
				int casillas = Integer.parseInt(partes[2]);
				int tiempo = Integer.parseInt(partes[3]);
				int minasActivadas = 0;
				Jugador j = new Jugador(nombre, dif, casillas, minasActivadas, tiempo);
				rankings.get(dif).add(j);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Jugador> getTopJugadores(Dificultad dificultad) {
		List<Jugador> lista = rankings.get(dificultad);
		return lista.subList(0, Math.min(10, lista.size()));
	}

	private void guardarEnArchivo() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("ranking.txt"))) {
			for (Dificultad dificultad : rankings.keySet()) {
				for (Jugador j : rankings.get(dificultad)) {
					writer.println(j.getNombre() + ";" + dificultad + ";" + j.getCasillasDescubiertas() + ";"
							+ j.getMinasActivadas() + ";" + j.getTiempo() + ";" + j.getPuntuaje());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
