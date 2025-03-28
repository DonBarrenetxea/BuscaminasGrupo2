package controlador;

import modelo.Dificultad;
import vista.MenuBuscaminas;
import vista.VentanaBuscaminas;

public class Main {
	private static MenuBuscaminas menuBuscaminas;
	private static VentanaBuscaminas ventanaBuscaminas;
	
	public static void main(String[] args) {
		menuBuscaminas = new MenuBuscaminas();
		menuBuscaminas.setVisible(true);
	}
	public static void abrirVentanaBuscaminas(Dificultad dificultad) {
		ventanaBuscaminas = new VentanaBuscaminas(dificultad.getColumnas(),dificultad.getFilas(),dificultad.getMinas());
		ventanaBuscaminas.setVisible(true);
		menuBuscaminas.setVisible(false);
	}
	
}
