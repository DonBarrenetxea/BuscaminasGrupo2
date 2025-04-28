package controlador;

import java.util.List;

import modelo.Dificultad;
import modelo.GestorSonidos;
import modelo.Jugador;
import modelo.Ranking;
import modelo.Tablero;
import vista.MenuBuscaminas;
import vista.VentanaBuscaminas;
import vista.VentanaRanking;

public class Main {
	private static MenuBuscaminas menuBuscaminas;
	private static VentanaBuscaminas ventanaBuscaminas;
	private static VentanaRanking ventanaRanking;
	private static Ranking ranking;

	public static void main(String[] args) { 	
		ranking = new Ranking();
		ranking.cargarRanking("src/Ranking.txt");

		menuBuscaminas = new MenuBuscaminas(ranking);
		menuBuscaminas.setVisible(true);
		GestorSonidos soundManager = new GestorSonidos();
		soundManager.playLoop("src/images/ambient.wav", -10.0f);

	}
	public static void abrirVentanaBuscaminas(Dificultad dificultad, String nombre) {
		ventanaBuscaminas = new VentanaBuscaminas(dificultad, ranking, nombre);
		ventanaBuscaminas.setVisible(true);
		menuBuscaminas.setVisible(false);
	}

	public static void abrirVentanaRanking(Dificultad dificultad) {
	    List<Jugador> topJugadores = ranking.getTopJugadores(dificultad);
	    ventanaRanking = new VentanaRanking(topJugadores, dificultad);
	    ventanaRanking.setVisible(true);
	    if (ventanaBuscaminas != null) {
	    ventanaBuscaminas.setVisible(false);
	    }
	    if (menuBuscaminas != null) {
	    	menuBuscaminas.setVisible(false);
	    }
	}

	public static void abrirMenuBuscaminas(Ranking ranking) {
	    if (ventanaBuscaminas != null) {
	        ventanaBuscaminas.setVisible(false);
	    }
	    menuBuscaminas.setVisible(true);
	}

	public static Ranking getRanking() {
		return ranking;
	}
	

	

}
