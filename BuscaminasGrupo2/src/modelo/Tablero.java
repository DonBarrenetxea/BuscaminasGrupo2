package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {

    private List<Celda> celdas;
    private int filas;
    private int columnas;
    private int minas;

    public Tablero(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
        this.celdas = new ArrayList<>();
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas * columnas; i++) {
            celdas.add(new Celda());
        }

        Random rand = new Random();
        int minasColocadas = 0;
        while (minasColocadas < minas) {
            int index = rand.nextInt(filas * columnas);
            Celda celda = celdas.get(index);
            if (!celda.esMina()) {
                celda.colocarMina();
                minasColocadas++;
            }
        }

        for (int i = 0; i < filas * columnas; i++) {
            Celda celda = celdas.get(i);
            if (!celda.esMina()) {
                int minasCerca = contarMinasCerca(i);
                celda.setMinasCerca(minasCerca);
            }
        }
    }

    private int contarMinasCerca(int posicion) {
        int minasCerca = 0;
        int[] vecinos = {
            -columnas - 1, -columnas, -columnas + 1,
            -1, 1,
            columnas - 1, columnas, columnas + 1
        };

        for (int vecino : vecinos) {
            int vecinoPos = posicion + vecino;
            if (vecinoPos >= 0 && vecinoPos < celdas.size() && !esBorde(vecinoPos, posicion)) {
                if (celdas.get(vecinoPos).esMina()) {
                    minasCerca++;
                }
            }
        }
        return minasCerca;
    }

    private boolean esBorde(int vecinoPos, int posicion) {
        int col = posicion % columnas;
        int vecinoCol = vecinoPos % columnas;
        return Math.abs(col - vecinoCol) > 1;
    }

    public List<Celda> getCeldas() {
        return celdas;
    }

    public void setCeldas(List<Celda> celdas) {
        this.celdas = celdas;
    }
}