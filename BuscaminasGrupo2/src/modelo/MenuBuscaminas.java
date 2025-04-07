package modelo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import controlador.Main;

public class MenuBuscaminas extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static Dificultad dificultad = Dificultad.FACIL;
    private JTextField textField;

    public MenuBuscaminas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 350);
        setResizable(false);
        
        // Cargar la imagen de fondo
        ImageIcon backgroundIcon = new ImageIcon("src/images/fondo.png");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 600, 350);
        
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("BUSCAMINAS GRUPO 2");
        lblTitulo.setForeground(new Color(0, 255, 255));
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
        gbc_lblTitulo.insets = new Insets(10, 0, 10, 0);
        gbc_lblTitulo.gridx = 1;
        gbc_lblTitulo.gridy = 0;
        contentPane.add(lblTitulo, gbc_lblTitulo);

        JPanel panel_1 = new JPanel();
        panel_1.setOpaque(false);
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(10, 0, 10, 0);
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 1;
        contentPane.add(panel_1, gbc_panel_1);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.WHITE);
        panel_1.add(lblNombre);
        
        textField = new JTextField(3);
        panel_1.add(textField);
        
        JButton btnJugar = new JButton("Jugar");
        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorSonidos.playOnce("src/images/select.wav", -5.0f);
                String nombre = textField.getText().trim();
                if (!nombre.isEmpty()) {
                    Main.abrirVentanaBuscaminas(dificultad);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre antes de jugar.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panel_1.add(btnJugar);

        add(backgroundLabel);
    }
}