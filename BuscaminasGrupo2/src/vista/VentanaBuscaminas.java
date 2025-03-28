package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaBuscaminas extends JFrame {

	private static final long serialVersionUID = 1L;
	private static int filas;
	private static int columnas;
	private static int minas;
	private static ArrayList<JButton> celdas;
	private static JPanel contentPane;

	public VentanaBuscaminas(int columnas, int filas, int minas) {
		this.filas = filas;
		this.columnas = columnas;
		this.minas = minas;
		this.celdas = new ArrayList<>();
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
	    }
	}
}