package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
	private List<Celda> celdas;
	private int columnas;
	private int filas;
	private int minas;

	public Tablero(Dificultad dificultad) {
		this.columnas = dificultad.getColumnas();
		this.filas = dificultad.getFilas();
		this.minas = dificultad.getMinas();
		this.celdas = new ArrayList<>(columnas * filas);
		inicializarCeldas();
		generarTablero();
	}

	private void inicializarCeldas() {
		for (int i = 0; i < columnas * filas; i++) {
			celdas.add(new Celda());
		}
	}

	public void generarTablero() {
		int contador = 0;

		while (contador < minas) {
			int posicion = (int) (Math.random() * celdas.size());

			if (!celdas.get(posicion).esMina()) {
				celdas.get(posicion).colocarMina();
				contador++;

				if (esPosicionValida(posicion - columnas)) {
					celdas.get(posicion - columnas).incrementarMinasCerca();
				}
				if (esPosicionValida(posicion + columnas)) {
					celdas.get(posicion + columnas).incrementarMinasCerca();
				}
				if (esPosicionValida(posicion - 1) && posicion % columnas != 0) {
					celdas.get(posicion - 1).incrementarMinasCerca();
				}
				if (esPosicionValida(posicion + 1) && posicion % columnas != columnas - 1) {
					celdas.get(posicion + 1).incrementarMinasCerca();
				}
				if (esPosicionValida(posicion - columnas - 1) && posicion % columnas != 0) {
					celdas.get(posicion - columnas - 1).incrementarMinasCerca();
				}
				if (esPosicionValida(posicion - columnas + 1) && posicion % columnas != columnas - 1) {
					celdas.get(posicion - columnas + 1).incrementarMinasCerca();
				}
				if (esPosicionValida(posicion + columnas - 1) && posicion % columnas != 0) {
					celdas.get(posicion + columnas - 1).incrementarMinasCerca();
				}
				if (esPosicionValida(posicion + columnas + 1) && posicion % columnas != columnas - 1) {
					celdas.get(posicion + columnas + 1).incrementarMinasCerca();
				}
			}
		}
	}

	private boolean esPosicionValida(int posicion) {
		return posicion >= 0 && posicion < celdas.size();
	}

	public List<Celda> getCeldas() {
		return celdas;
	}
}