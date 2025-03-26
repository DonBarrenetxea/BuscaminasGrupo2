package controlador;

import vista.MenuBuscaminas;

public class Main {
	private static MenuBuscaminas menuBuscaminas;
	public static void main(String[] args) {
		menuBuscaminas = new MenuBuscaminas();
		menuBuscaminas.setVisible(true);
	}

}
