package vista;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Jugador;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;
import java.awt.Color;

public class VentanaRanking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VentanaRanking(List<Jugador> topJugadores) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1023, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Titulo = new JLabel("Ranking Buscaminas");
		Titulo.setBounds(345, 26, 274, 32);
		Titulo.setForeground(new Color(0, 128, 64));
		Titulo.setFont(new Font("Tahoma", Font.BOLD, 26));
		contentPane.add(Titulo);

		String[] headers = {"Id", "Jugador", "Dificultad", "Casillas", "Tiempo", "Puntuación"};
		for (int i = 0; i < headers.length; i++) {
			JLabel header = new JLabel(headers[i]);
			header.setFont(new Font("Tahoma", Font.BOLD, 16));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(0, 0, 10, 10);
			gbc.gridx = i;
			gbc.gridy = 1;
			contentPane.add(header, gbc);
		}

		for (int i = 0; i < topJugadores.size(); i++) {
			Jugador j = topJugadores.get(i);

			String[] datos = {
				String.valueOf(i + 1),
				j.getNombre(),
				j.getDificultad().toString(),
				String.valueOf(j.getCasillasDescubiertas()),
				String.valueOf(j.getTiempo()),
				String.format("%.2f", j.getPuntuaje())
			};

			for (int col = 0; col < datos.length; col++) {
				JLabel dato = new JLabel(datos[col]);
				dato.setFont(new Font("Tahoma", Font.PLAIN, 14));
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.insets = new Insets(2, 2, 2, 2);
				gbc.gridx = col;
				gbc.gridy = i + 2;
				contentPane.add(dato, gbc);
			}
		}
	}
}