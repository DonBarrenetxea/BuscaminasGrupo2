package vista;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class VentanaRanking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VentanaRanking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1023, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{67, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 41, 46, 44, 47, 41, 47, 50, 51, 43, 46, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel Titulo = new JLabel("Ranking Buscaminas");
		Titulo.setForeground(new Color(0, 128, 64));
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		GridBagConstraints gbc_Titulo = new GridBagConstraints();
		gbc_Titulo.insets = new Insets(0, 0, 5, 5);
		gbc_Titulo.gridx = 3;
		gbc_Titulo.gridy = 0;
		contentPane.add(Titulo, gbc_Titulo);
		
		JLabel Id_Jugador = new JLabel("Id");
		Id_Jugador.setForeground(new Color(255, 128, 128));
		Id_Jugador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_Id_Jugador = new GridBagConstraints();
		gbc_Id_Jugador.insets = new Insets(0, 0, 5, 5);
		gbc_Id_Jugador.gridx = 0;
		gbc_Id_Jugador.gridy = 1;
		contentPane.add(Id_Jugador, gbc_Id_Jugador);
		
		JLabel Jugador = new JLabel("Jugador");
		Jugador.setForeground(new Color(0, 128, 128));
		Jugador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_Jugador = new GridBagConstraints();
		gbc_Jugador.insets = new Insets(0, 0, 5, 5);
		gbc_Jugador.gridx = 1;
		gbc_Jugador.gridy = 1;
		contentPane.add(Jugador, gbc_Jugador);
		
		JLabel Dificultad = new JLabel("Dificultad");
		Dificultad.setForeground(new Color(128, 128, 255));
		Dificultad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_Dificultad = new GridBagConstraints();
		gbc_Dificultad.insets = new Insets(0, 0, 5, 5);
		gbc_Dificultad.gridx = 2;
		gbc_Dificultad.gridy = 1;
		contentPane.add(Dificultad, gbc_Dificultad);
		
		JLabel Casilas = new JLabel("Casillas descubiertas");
		Casilas.setForeground(new Color(255, 128, 0));
		Casilas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_Casilas = new GridBagConstraints();
		gbc_Casilas.anchor = GridBagConstraints.NORTH;
		gbc_Casilas.insets = new Insets(0, 0, 5, 5);
		gbc_Casilas.gridx = 3;
		gbc_Casilas.gridy = 1;
		contentPane.add(Casilas, gbc_Casilas);
		
		JLabel Tiempo = new JLabel("Tiempo");
		Tiempo.setForeground(new Color(255, 128, 255));
		Tiempo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_Tiempo = new GridBagConstraints();
		gbc_Tiempo.insets = new Insets(0, 0, 5, 5);
		gbc_Tiempo.gridx = 4;
		gbc_Tiempo.gridy = 1;
		contentPane.add(Tiempo, gbc_Tiempo);
		
		JLabel Puntuacion = new JLabel("Puntuacion");
		Puntuacion.setForeground(new Color(128, 128, 0));
		Puntuacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_Puntuacion = new GridBagConstraints();
		gbc_Puntuacion.insets = new Insets(0, 0, 5, 0);
		gbc_Puntuacion.gridx = 5;
		gbc_Puntuacion.gridy = 1;
		contentPane.add(Puntuacion, gbc_Puntuacion);
		
		JLabel Top1 = new JLabel("1");
		Top1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_Top1 = new GridBagConstraints();
		gbc_Top1.insets = new Insets(0, 0, 5, 5);
		gbc_Top1.gridx = 0;
		gbc_Top1.gridy = 2;
		contentPane.add(Top1, gbc_Top1);
		
		JLabel Top2 = new JLabel("2");
		Top2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_Top2 = new GridBagConstraints();
		gbc_Top2.insets = new Insets(0, 0, 5, 5);
		gbc_Top2.gridx = 0;
		gbc_Top2.gridy = 3;
		contentPane.add(Top2, gbc_Top2);
		
		JLabel Top3 = new JLabel("3");
		Top3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_Top3 = new GridBagConstraints();
		gbc_Top3.insets = new Insets(0, 0, 5, 5);
		gbc_Top3.gridx = 0;
		gbc_Top3.gridy = 4;
		contentPane.add(Top3, gbc_Top3);
		
		JLabel Top4 = new JLabel("4");
		Top4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_Top4 = new GridBagConstraints();
		gbc_Top4.insets = new Insets(0, 0, 5, 5);
		gbc_Top4.gridx = 0;
		gbc_Top4.gridy = 5;
		contentPane.add(Top4, gbc_Top4);
		
		JLabel Top5 = new JLabel("5");
		Top5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_Top5 = new GridBagConstraints();
		gbc_Top5.insets = new Insets(0, 0, 5, 5);
		gbc_Top5.gridx = 0;
		gbc_Top5.gridy = 6;
		contentPane.add(Top5, gbc_Top5);
		
		JLabel Top6 = new JLabel("6");
		Top6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_Top6 = new GridBagConstraints();
		gbc_Top6.insets = new Insets(0, 0, 5, 5);
		gbc_Top6.gridx = 0;
		gbc_Top6.gridy = 7;
		contentPane.add(Top6, gbc_Top6);
		
		JLabel Top7 = new JLabel("7");
		Top7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_Top7 = new GridBagConstraints();
		gbc_Top7.insets = new Insets(0, 0, 5, 5);
		gbc_Top7.gridx = 0;
		gbc_Top7.gridy = 8;
		contentPane.add(Top7, gbc_Top7);
		
		JLabel Top8 = new JLabel("8");
		Top8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_Top8 = new GridBagConstraints();
		gbc_Top8.insets = new Insets(0, 0, 5, 5);
		gbc_Top8.gridx = 0;
		gbc_Top8.gridy = 9;
		contentPane.add(Top8, gbc_Top8);
		
		JLabel Top4_1 = new JLabel("9");
		Top4_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_Top4_1 = new GridBagConstraints();
		gbc_Top4_1.insets = new Insets(0, 0, 5, 5);
		gbc_Top4_1.gridx = 0;
		gbc_Top4_1.gridy = 10;
		contentPane.add(Top4_1, gbc_Top4_1);
		
		JLabel Top10 = new JLabel("10");
		Top10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_Top10 = new GridBagConstraints();
		gbc_Top10.insets = new Insets(0, 0, 0, 5);
		gbc_Top10.gridx = 0;
		gbc_Top10.gridy = 11;
		contentPane.add(Top10, gbc_Top10);
	}
}
