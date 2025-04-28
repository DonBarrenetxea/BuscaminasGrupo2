package vista;

import java.awt.BorderLayout;
import java.awt.Color;
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

import controlador.Main;
import modelo.Celda;
import modelo.Dificultad;
import modelo.GestorSonidos;
import modelo.Ranking;
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
	private static Ranking ranking;
	private static String nombre;

	public VentanaBuscaminas(Dificultad dificultad, Ranking ranking, String nombre) {
		setTitle("Buscaminas G2");
		setIconImage(new ImageIcon(getClass().getResource("/images/pixel_art.png")).getImage());
		this.nombre = nombre;
		this.ranking = ranking;
		this.dificultad = dificultad;
		filas = dificultad.getFilas();
		columnas = dificultad.getColumnas();
		minas = dificultad.getMinas();
		celdas = new ArrayList<>();
		tablero = new Tablero(dificultad);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(35 * columnas, 35 * filas + 50);
		setResizable(false);

		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.setBackground(new Color(0, 255, 255));
		JPanel flagPanel = new JPanel(new FlowLayout());
		flagPanel.setBackground(new Color(0, 255, 255));
		flagCentenas = new JLabel(new ImageIcon("src/images/time0.gif"));
		flagDecenas = new JLabel(new ImageIcon("src/images/time" + minas / 10 + ".gif"));
		flagUnidades = new JLabel(new ImageIcon("src/images/time" + minas % 10 + ".gif"));
		flagPanel.add(flagCentenas);
		flagPanel.add(flagDecenas);
		flagPanel.add(flagUnidades);
		topPanel.add(flagPanel);

		reinicio = new JPanel(new FlowLayout());
		ImageIcon imagenReinicioSinEscalar = new ImageIcon("src/images/caritaFeliz.png");
		Image imagenReinicioEscalada = imagenReinicioSinEscalar.getImage().getScaledInstance(45, 45,
				Image.SCALE_SMOOTH);
		imagenReinicio = new JLabel(new ImageIcon(imagenReinicioEscalada));
		imagenReinicio.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        detenerTimer();
		        tablero = new Tablero(dificultad);
		        segundos = 0;
		        banderas = minas;
		        actualizarFlags();
		        JPanel gridPanel = (JPanel) contentPane.getComponent(1);
		        gridPanel.removeAll();
		        celdas.clear();
		        agregarCeldas(gridPanel);
		        gridPanel.revalidate();
		        gridPanel.repaint();
		        iniciarTimer();
		    }
		});
		reinicio.add(imagenReinicio);
		reinicio.setBackground(new Color(0, 255, 255));
		topPanel.add(reinicio);

		JPanel timerPanel = new JPanel(new FlowLayout());

		timerCentenas = new JLabel(new ImageIcon("src/images/time0.gif"));
		timerDecenas = new JLabel(new ImageIcon("src/images/time0.gif"));
		timerUnidades = new JLabel(new ImageIcon("src/images/time0.gif"));

		timerPanel.add(timerCentenas);
		timerPanel.add(timerDecenas);
		timerPanel.add(timerUnidades);
		timerPanel.setBackground(new Color(0, 255, 255));
		topPanel.add(timerPanel);

		contentPane.add(topPanel, BorderLayout.NORTH);

		JPanel gridPanel = new JPanel();
		gridPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		gridPanel.setLayout(new GridLayout(filas, columnas));
		gridPanel.setBackground(new Color(0, 255, 255));
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
						Celda celdaElegida = tablero.getCeldas().get(index);
						if (calcularBanderas() != 0) {
							GestorSonidos.playOnce("src/images/flag.wav", 6.0f);
							if (celdaElegida.getBanderaMarcada() != true && !celdaElegida.esAbierta()) {
								ponerBandera(index);
								celdaElegida.setBanderaMarcada(true);
							} else if (!celdaElegida.esAbierta()) {
								JButton botonCelda = celdas.get(index);
								celdaElegida.setBanderaMarcada(false);
								botonCelda.setIcon(new ImageIcon("src/images/blank.gif"));
							}
						}else if(celdaElegida.getBanderaMarcada()==true && calcularBanderas() == 0) {
							JButton botonCelda = celdas.get(index);
							celdaElegida.setBanderaMarcada(false);
							botonCelda.setIcon(new ImageIcon("src/images/blank.gif"));
						}
						actualizarFlags();
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
		GestorSonidos.playOnce("src/images/celda.wav", -3.0f);
		if (celdaElegida.getBanderaMarcada() == true) {
			celdaElegida.setBanderaMarcada(false);
		}
		actualizarFlags();
		if (celdaElegida.esMina()) {
			botonCelda.setIcon(new ImageIcon("src/images/bombdeath.gif"));
			GestorSonidos.playOnce("src/images/explosion.wav", 6.0f);
			ImageIcon imagenReinicioSinEscalar = new ImageIcon("src/images/caritaMuerta.png");
			Image imagenReinicioEscalada = imagenReinicioSinEscalar.getImage().getScaledInstance(45, 45,
					Image.SCALE_SMOOTH);
			ImageIcon imagenReinicioFinal = new ImageIcon(imagenReinicioEscalada);
			imagenReinicio.setIcon(imagenReinicioFinal);
			mostrarTablero(posicion);
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
		if (!quedanCeldasTapadas()) {
			GestorSonidos.playOnce("src/images/win.wav", 8.0f);
			ImageIcon imagenReinicioSinEscalar = new ImageIcon("src/images/caritaGuay.png");
			Image imagenReinicioEscalada = imagenReinicioSinEscalar.getImage().getScaledInstance(45, 45,
					Image.SCALE_SMOOTH);
			imagenReinicio.setIcon(new ImageIcon(imagenReinicioEscalada));
			reinicio.revalidate();
			reinicio.repaint();
			detenerTimer();
			JOptionPane.showMessageDialog(null, "¡Has ganado!", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
			ranking.agregarJugador(dificultad, nombre,dificultad.getColumnas() * dificultad.getFilas() - dificultad.getMinas(), 0, segundos);
			ranking.guardarRanking("src/Ranking.txt");
			Main.abrirVentanaRanking(dificultad);
		}
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
					mostrarTablero(-1);
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

	private static void mostrarTablero(int posicion) {
		detenerTimer();
		List<Celda> celdasTablero = tablero.getCeldas();

		for (int i = 0; i < celdas.size(); i++) {
			Celda celda = celdasTablero.get(i);
			JButton botonCelda = celdas.get(i);

			if (celda.esMina() && i != posicion) {
				botonCelda.setIcon(new ImageIcon("src/images/bombrevealed.gif"));
			} else if (celda.getBanderaMarcada()) {
				botonCelda.setIcon(new ImageIcon("src/images/bombmisflagged.gif"));
			}

			if (celda.getBanderaMarcada() && celda.esMina()) {
				botonCelda.setIcon(new ImageIcon("src/images/bombflagged.gif"));
			}
		}

		JOptionPane.showMessageDialog(null, "¡Has perdido!", "Fin del juego", JOptionPane.INFORMATION_MESSAGE);

		Main.abrirMenuBuscaminas(ranking);
		segundos = 0;

	}

	private static boolean quedanCeldasTapadas() {
		List<Celda> celdasTablero = tablero.getCeldas();
		for (int i = 0; i < celdasTablero.size(); i++) {
			if (!celdasTablero.get(i).esAbierta() && !celdasTablero.get(i).esMina()) {
				return true;
			}
		}
		return false;
	}
}
