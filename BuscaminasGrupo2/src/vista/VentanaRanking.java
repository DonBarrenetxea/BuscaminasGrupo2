package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controlador.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import modelo.Dificultad;
import modelo.Jugador;

public class VentanaRanking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VentanaRanking(List<Jugador> topJugadores, Dificultad dificultad) {
		setTitle("Buscaminas G2");
		setIconImage(new ImageIcon(getClass().getResource("/images/pixel_art.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setBackground(new Color(0, 0, 128));
		setContentPane(contentPane);

		JLabel titulo = new JLabel("RANKING BUSCAMINAS - " + dificultad, SwingConstants.CENTER);
	    titulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
	    titulo.setForeground(new Color(0, 255, 255));
	    titulo.setBounds(200, 21, 500, 40); 
	    contentPane.add(titulo);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(54, 80, 50, 25);
		lblId.setForeground(new Color(249, 244, 198));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblId);

		JLabel lblJugador = new JLabel("Jugador");
		lblJugador.setBounds(127, 80, 100, 25);
		lblJugador.setForeground(new Color(249, 244, 198));
		lblJugador.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblJugador);

		JLabel lblDificultad = new JLabel("Dificultad");
		lblDificultad.setBounds(239, 80, 100, 25);
		lblDificultad.setForeground(new Color(249, 244, 198));
		lblDificultad.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblDificultad);

		JLabel lblCasillas = new JLabel("Casillas Descubiertas");
		lblCasillas.setBounds(360, 80, 180, 25);
		lblCasillas.setForeground(new Color(249, 244, 198));
		lblCasillas.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblCasillas);

		JLabel lblTiempo = new JLabel("Tiempo");
		lblTiempo.setBounds(595, 80, 80, 25);
		lblTiempo.setForeground(new Color(249, 244, 198));
		lblTiempo.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTiempo);

		JLabel lblPuntuacion = new JLabel("Puntuación");
		lblPuntuacion.setBounds(724, 80, 100, 25);
		lblPuntuacion.setForeground(new Color(249, 244, 198));
		lblPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblPuntuacion);

		int startY = 120;
		int incrementoY = 30;

		for (int i = 0; i < topJugadores.size(); i++) {
			Jugador jugador = topJugadores.get(i);

			JLabel lblIdDato = new JLabel(String.valueOf(i + 1));
			lblIdDato.setBounds(60, startY + (i * incrementoY), 50, 25);
			lblIdDato.setForeground(new Color(249, 244, 198));
			contentPane.add(lblIdDato);

			JLabel lblNombreDato = new JLabel(jugador.getNombre());
			lblNombreDato.setBounds(150, startY + (i * incrementoY), 100, 25);
			lblNombreDato.setForeground(new Color(249, 244, 198));
			contentPane.add(lblNombreDato);

			JLabel lblDificultadDato = new JLabel(jugador.getDificultad().toString());
			lblDificultadDato.setBounds(280, startY + (i * incrementoY), 100, 25);
			lblDificultadDato.setForeground(new Color(249, 244, 198));
			contentPane.add(lblDificultadDato);

			JLabel lblCasillasDato = new JLabel(String.valueOf(jugador.getCasillasDescubiertas()));
			lblCasillasDato.setBounds(430, startY + (i * incrementoY), 100, 25);
			lblCasillasDato.setForeground(new Color(249, 244, 198));
			contentPane.add(lblCasillasDato);

			JLabel lblTiempoDato = new JLabel(String.valueOf(jugador.getTiempo()));
			lblTiempoDato.setBounds(630, startY + (i * incrementoY), 80, 25);
			lblTiempoDato.setForeground(new Color(249, 244, 198));
			contentPane.add(lblTiempoDato);

			JLabel lblPuntuacionDato = new JLabel(String.format("%.2f", jugador.getPuntuaje()));
			lblPuntuacionDato.setBounds(760, startY + (i * incrementoY), 80, 25);
			lblPuntuacionDato.setForeground(new Color(249, 244, 198));
			contentPane.add(lblPuntuacionDato);
					
		}
		JButton botonVolver = new JButton("Volver al Menu");
        botonVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
        botonVolver.setForeground(new Color(0, 0, 128));
        botonVolver.setBackground(new Color(249, 244, 198));
        botonVolver.setBounds(360, 500, 200, 40);
        contentPane.add(botonVolver);

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                Main.abrirMenuBuscaminas(Main.getRanking());
            }
        });
	}
}
