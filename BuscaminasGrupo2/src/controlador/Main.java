package controlador;

import modelo.Dificultad;
import modelo.Tablero;
import vista.MenuBuscaminas;
import vista.VentanaBuscaminas;
import vista.VentanaRanking;

public class Main {
	private static MenuBuscaminas menuBuscaminas;
	private static VentanaBuscaminas ventanaBuscaminas;
	
	public static void main(String[] args) {
		menuBuscaminas = new MenuBuscaminas();
		menuBuscaminas.setVisible(true);
	}
	public static void abrirVentanaBuscaminas(Dificultad dificultad) {
		Tablero tablero = new Tablero(dificultad);
		ventanaBuscaminas = new VentanaBuscaminas(dificultad, tablero);
		ventanaBuscaminas.setVisible(true);
		menuBuscaminas.setVisible(false);
	}
	public static void abrirVentanaRanking(Dificultad dificultad) {
		VentanaRanking vr = new VentanaRanking();
		if(Dificultad.FACIL != null) {
			
		}
	}
	
}
