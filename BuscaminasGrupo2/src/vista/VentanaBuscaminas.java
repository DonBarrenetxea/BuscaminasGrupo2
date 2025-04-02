package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Celda;
import modelo.Dificultad;
import modelo.Tablero;

public class VentanaBuscaminas extends JFrame {

    private static final long serialVersionUID = 1L;
    private static Dificultad dificultad;
    private static int filas;
    private static int columnas;
    private static int minas;
    private static ArrayList<JButton> celdas;
    private static JPanel contentPane;
    private static Tablero tablero;

    public VentanaBuscaminas(Dificultad dificultad, Tablero tablero) {
    	this.dificultad=dificultad;
        this.filas = dificultad.getFilas();
        this.columnas = dificultad.getColumnas();
        this.minas = dificultad.getMinas();
        this.celdas = new ArrayList<>();
        this.tablero = tablero;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(35 * columnas, 35 * filas);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(filas, columnas));
        setResizable(false);
        setContentPane(contentPane);
        agregarCeldas();
    }

    private static void agregarCeldas() {
        ImageIcon icono = new ImageIcon("src/images/blank.gif");

        for (int i = 0; i < filas * columnas; i++) {
            JButton celda = new JButton(icono);
            celdas.add(celda);
            contentPane.add(celda);

            final int index = i;
            celda.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        if (!tablero.getCeldas().get(index).esAbierta()) {
                            revelarCelda(index);
                        }
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                    }
                }
            });
        }
    }

    private static void revelarCelda(int posicion) {
        List<Celda> celdasTablero = tablero.getCeldas();
        Celda celdaElegida = celdasTablero.get(posicion);
        JButton botonCelda = celdas.get(posicion);

        if (celdaElegida.esAbierta()) {
            return;
        }

        if (celdaElegida.esMina()) {
            botonCelda.setIcon(new ImageIcon("src/images/bombdeath.gif"));
        } else {
            int minasCerca = celdaElegida.getMinasCerca();
            String rutaImagen = "";

            switch (minasCerca) {
                case 1:
                    rutaImagen = "src/images/open1.gif";
                    break;
                case 2:
                    rutaImagen = "src/images/open2.gif";
                    break;
                case 3:
                    rutaImagen = "src/images/open3.gif";
                    break;
                case 4:
                    rutaImagen = "src/images/open4.gif";
                    break;
                case 5:
                    rutaImagen = "src/images/open5.gif";
                    break;
                case 6:
                    rutaImagen = "src/images/open6.gif";
                    break;
                case 7:
                    rutaImagen = "src/images/open7.gif";
                    break;
                case 8:
                    rutaImagen = "src/images/open8.gif";
                    break;
                default:
                    rutaImagen = "src/images/open0.gif";
                    revelarCeldasCercanas(posicion, celdasTablero);
                    break;
            }

            botonCelda.setIcon(new ImageIcon(rutaImagen));
        }
        celdaElegida.marcarComoAbierta();
    }

    private static void revelarCeldasCercanas(int posicion, List<Celda> celdasTablero) {
        if (posicion < 0 || posicion >= celdas.size() || celdasTablero.get(posicion).esAbierta()) {
            return; 
        }

        Celda celdaElegidaCerca = celdasTablero.get(posicion);
        JButton botonCeldaCerca = celdas.get(posicion);

        celdaElegidaCerca.marcarComoAbierta();
        botonCeldaCerca.setIcon(new ImageIcon("src/images/open" + celdaElegidaCerca.getMinasCerca() + ".gif"));

        if (celdaElegidaCerca.getMinasCerca() == 0) {
            if (posicion - columnas >= 0) {
                revelarCeldasCercanas(posicion - columnas, celdasTablero); 
            }
            if (posicion + columnas < celdas.size()) {
                revelarCeldasCercanas(posicion + columnas, celdasTablero); 
            }
            if (posicion % columnas != 0) {
                revelarCeldasCercanas(posicion - 1, celdasTablero); 
            }
            if (posicion % columnas != columnas - 1) {
                revelarCeldasCercanas(posicion + 1, celdasTablero); 
            }
            if (posicion - columnas - 1 >= 0 && posicion % columnas != 0) {
                revelarCeldasCercanas(posicion - columnas - 1, celdasTablero); 
            }
            if (posicion - columnas + 1 >= 0 && posicion % columnas != columnas - 1) {
                revelarCeldasCercanas(posicion - columnas + 1, celdasTablero); 
            }
            if (posicion + columnas - 1 < celdas.size() && posicion % columnas != 0) {
                revelarCeldasCercanas(posicion + columnas - 1, celdasTablero); 
            }
            if (posicion + columnas + 1 < celdas.size() && posicion % columnas != columnas - 1) {
                revelarCeldasCercanas(posicion + columnas + 1, celdasTablero);
            }
        }
    }
    
}

