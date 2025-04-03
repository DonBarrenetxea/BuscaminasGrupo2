package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

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
	private static JPanel reinicio;
	private static JLabel imagenReinicio;
	private static Tablero tablero;
	private static JLabel timerCentenas;
	private static JLabel timerDecenas;
	private static JLabel timerUnidades;
	private static JLabel flagCentenas;
	private static JLabel flagDecenas;
	private static JLabel flagUnidades;
	private static Timer timer;
	private static int segundos = 0;
	private static int banderas = minas;

	public VentanaBuscaminas(Dificultad dificultad, Tablero tablero) {
		this.dificultad = dificultad;
		filas = dificultad.getFilas();
		columnas = dificultad.getColumnas();
		minas = dificultad.getMinas();
		celdas = new ArrayList<>();
		this.tablero = tablero;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(35 * columnas, 35 * filas + 50);
		setResizable(false);

		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);
		JPanel topPanel = new JPanel(new FlowLayout());

		JPanel flagPanel = new JPanel(new FlowLayout());
		flagCentenas = new JLabel(new ImageIcon("src/images/time0.gif"));
		flagDecenas = new JLabel(new ImageIcon("src/images/time" + minas / 10 + ".gif"));
		flagUnidades = new JLabel(new ImageIcon("src/images/time" + minas % 10 + ".gif"));
		flagPanel.add(flagCentenas);
		flagPanel.add(flagDecenas);
		flagPanel.add(flagUnidades);
		topPanel.add(flagPanel);

		reinicio = new JPanel(new FlowLayout());
		ImageIcon imagenReinicioSinEscalar = new ImageIcon("src/images/caritaFeliz.png");
		Image imagenReinicioEscalada = imagenReinicioSinEscalar.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		imagenReinicio = new JLabel(new ImageIcon(imagenReinicioEscalada));
		reinicio.add(imagenReinicio);
		topPanel.add(reinicio);

		JPanel timerPanel = new JPanel(new FlowLayout());

		timerCentenas = new JLabel(new ImageIcon("src/images/time0.gif"));
		timerDecenas = new JLabel(new ImageIcon("src/images/time0.gif"));
		timerUnidades = new JLabel(new ImageIcon("src/images/time0.gif"));

		timerPanel.add(timerCentenas);
		timerPanel.add(timerDecenas);
		timerPanel.add(timerUnidades);
		topPanel.add(timerPanel);

		contentPane.add(topPanel, BorderLayout.NORTH);

		JPanel gridPanel = new JPanel();
		gridPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		gridPanel.setLayout(new GridLayout(filas, columnas));
		contentPane.add(gridPanel, BorderLayout.CENTER);

		agregarCeldas(gridPanel);

		iniciarTimer();
	}

	private static void agregarCeldas(JPanel gridPanel) {
		ImageIcon icono = new ImageIcon("src/images/blank.gif");

		for (int i = 0; i < filas * columnas; i++) {
			JButton celda = new JButton(icono);
			celdas.add(celda);
			gridPanel.add(celda);

			final int index = i;
			celda.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						if (!tablero.getCeldas().get(index).esAbierta()) {
							revelarCelda(index);
						}
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						if (calcularBanderas() != 0) {
							ponerBandera(index);
							Celda celdaElegida = tablero.getCeldas().get(index);
							celdaElegida.setBanderaMarcada(true);
						}
					}
				}
			});
		}
	}

	private static void ponerBandera(int posicion) {
		List<Celda> celdasTablero = tablero.getCeldas();
		Celda celdaElegida = celdasTablero.get(posicion);
		JButton botonCelda = celdas.get(posicion);

		if (celdaElegida.esAbierta()) {
			return;
		}
		String rutaImagen = "src/images/bombflagged.gif";
		botonCelda.setIcon(new ImageIcon(rutaImagen));
		celdaElegida.setBanderaMarcada(true);
		actualizarFlags();
	}

	private static int calcularBanderas() {
		List<Celda> celdasTablero = tablero.getCeldas();
		int banderasPuestas = minas;
		for (int i = 0; i < celdasTablero.size(); i++) {
			Celda celdaElegida = celdasTablero.get(i);
			if (celdaElegida.getBanderaMarcada() == true) {
				banderasPuestas--;
			}
		}
		return banderasPuestas;
	}

	private static void revelarCelda(int posicion) {
		List<Celda> celdasTablero = tablero.getCeldas();
		Celda celdaElegida = celdasTablero.get(posicion);
		JButton botonCelda = celdas.get(posicion);

		if (celdaElegida.esAbierta()) {
			return;
		}
		if (celdaElegida.getBanderaMarcada() == true) {
			celdaElegida.setBanderaMarcada(false);
		}
		actualizarFlags();
		if (celdaElegida.esMina()) {
			mostrarTablero();
			botonCelda.setIcon(new ImageIcon("src/images/bombdeath.gif"));
			ImageIcon imagenReinicioSinEscalar = new ImageIcon("src/images/caritaMuerta.png");
			Image imagenReinicioEscalada = imagenReinicioSinEscalar.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
			ImageIcon imagenReinicioFinal = new ImageIcon(imagenReinicioEscalada);
			imagenReinicio.setIcon(imagenReinicioFinal);
			reinicio.revalidate();
			reinicio.repaint();
			
		} else {
			int minasCerca = celdaElegida.getMinasCerca();
			String rutaImagen = "";

			if (minasCerca >= 1 && minasCerca <= 8) {
				rutaImagen = "src/images/open" + minasCerca + ".gif";
			} else {
				rutaImagen = "src/images/open0.gif";
				revelarCeldasCercanas(posicion, celdasTablero);
				actualizarFlags();
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
		if (celdaElegidaCerca.getBanderaMarcada() == true) {
			celdaElegidaCerca.setBanderaMarcada(false);
		}
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

	private static void actualizarFlags() {
		banderas = calcularBanderas();

		int decenasFlag = banderas / 10;
		int unidadesFlag = banderas % 10;

		flagDecenas.setIcon(new ImageIcon("src/images/time" + decenasFlag + ".gif"));
		flagUnidades.setIcon(new ImageIcon("src/images/time" + unidadesFlag + ".gif"));
	}

	private static void iniciarTimer() {
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				segundos++;
				if (segundos > 999) {
					segundos = 999;
					mostrarTablero();
					JOptionPane.showMessageDialog(null, "¡Se acabó el tiempo!", "Fin del juego",
							JOptionPane.WARNING_MESSAGE);
				}

				int centenas = (segundos / 100) % 10;
				int decenas = (segundos / 10) % 10;
				int unidades = segundos % 10;

				timerCentenas.setIcon(new ImageIcon("src/images/time" + centenas + ".gif"));
				timerDecenas.setIcon(new ImageIcon("src/images/time" + decenas + ".gif"));
				timerUnidades.setIcon(new ImageIcon("src/images/time" + unidades + ".gif"));
			}
		});
		timer.start();
	}

	private static void detenerTimer() {
		if (timer != null) {
			timer.stop();
			segundos = 999;
		}
	}

	private static void mostrarTablero() {
		detenerTimer();
		List<Celda> celdasTablero = tablero.getCeldas();

		for (int i = 0; i < celdas.size(); i++) {
			Celda celda = celdasTablero.get(i);
			JButton botonCelda = celdas.get(i);

			if (celda.esMina()) {
				botonCelda.setIcon(new ImageIcon("src/images/bombrevealed.gif"));
			} else if (celda.getBanderaMarcada()) {
				botonCelda.setIcon(new ImageIcon("src/images/bombmisflagged.gif"));
			} else {
				botonCelda.setIcon(new ImageIcon("src/images/open" + celda.getMinasCerca() + ".gif"));
			}
		}
	}
}
