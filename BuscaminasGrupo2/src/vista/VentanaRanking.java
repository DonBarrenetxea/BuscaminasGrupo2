package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import modelo.Jugador;

public class VentanaRanking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VentanaRanking(List<Jugador> topJugadores) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1023, 600);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);

		JLabel titulo = new JLabel("Ranking Buscaminas", SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 26));
		titulo.setForeground(new Color(0, 128, 64));
		titulo.setBounds(350, 20, 300, 40);
		contentPane.add(titulo);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(60, 80, 50, 25);
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblId);

		JLabel lblJugador = new JLabel("Jugador");
		lblJugador.setBounds(150, 80, 100, 25);
		lblJugador.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblJugador);

		JLabel lblDificultad = new JLabel("Dificultad");
		lblDificultad.setBounds(289, 80, 100, 25);
		lblDificultad.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblDificultad);

		JLabel lblCasillas = new JLabel("Casillas Descubiertas");
		lblCasillas.setBounds(437, 80, 180, 25);
		lblCasillas.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblCasillas);

		JLabel lblTiempo = new JLabel("Tiempo");
		lblTiempo.setBounds(696, 80, 80, 25);
		lblTiempo.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTiempo);

		JLabel lblPuntuacion = new JLabel("Puntuación");
		lblPuntuacion.setBounds(820, 80, 100, 25);
		lblPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblPuntuacion);

		int startY = 120;
		int incrementoY = 30;

		for (int i = 0; i < topJugadores.size(); i++) {
			Jugador jugador = topJugadores.get(i);

			JLabel lblIdDato = new JLabel(String.valueOf(i + 1));
			lblIdDato.setBounds(60, startY + (i * incrementoY), 50, 25);
			contentPane.add(lblIdDato);

			JLabel lblNombreDato = new JLabel(jugador.getNombre());
			lblNombreDato.setBounds(150, startY + (i * incrementoY), 100, 25);
			contentPane.add(lblNombreDato);

			JLabel lblDificultadDato = new JLabel(jugador.getDificultad().toString());
			lblDificultadDato.setBounds(280, startY + (i * incrementoY), 100, 25);
			contentPane.add(lblDificultadDato);

			JLabel lblCasillasDato = new JLabel(String.valueOf(jugador.getCasillasDescubiertas()));
			lblCasillasDato.setBounds(430, startY + (i * incrementoY), 100, 25);
			contentPane.add(lblCasillasDato);

			JLabel lblTiempoDato = new JLabel(String.valueOf(jugador.getTiempo()));
			lblTiempoDato.setBounds(630, startY + (i * incrementoY), 80, 25);
			contentPane.add(lblTiempoDato);

			JLabel lblPuntuacionDato = new JLabel(String.format("%.2f", jugador.getPuntuaje()));
			lblPuntuacionDato.setBounds(760, startY + (i * incrementoY), 80, 25);
			contentPane.add(lblPuntuacionDato);
		}
	}
}
