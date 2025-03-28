package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Main;
import modelo.Dificultad;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenuBuscaminas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Dificultad dificultad=Dificultad.FACIL;

	public MenuBuscaminas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("BUSCAMINAS GRUPO 2");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 128));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnNewButton_3 = new JButton("Jugar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.abrirVentanaBuscaminas(dificultad);
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 0;
		panel_1.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 128));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.WEST;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		panel_1.add(panel_2, gbc_panel_2);
		
		JLabel lblNewLabel = new JLabel("Dificultad: ");
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Facil");
		panel_2.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 128));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);
		
		JButton btnNewButton = new JButton("FACIL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setText("Facil");
				dificultad=dificultad.FACIL;
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("NORMAL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setText("Normal");
				dificultad=dificultad.MEDIO;
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DIFICIL");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setText("Dificil");
				dificultad=dificultad.DIFICIL;
			}
		});
		panel.add(btnNewButton_2);
	}

}
