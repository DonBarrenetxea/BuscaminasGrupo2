package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

import controlador.Main;
import modelo.Dificultad;

public class MenuBuscaminas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static Dificultad dificultad = Dificultad.FACIL;
    private JTextField textField;

    public MenuBuscaminas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 350);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 128));
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
        lblNewLabel_1.setBackground(new Color(0, 0, 128));
        lblNewLabel_1.setForeground(new Color(249, 244, 198));
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 0;
        contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 0, 128));
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 5);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 1;
        contentPane.add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panel_1.rowHeights = new int[]{0, 0};
        gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);

        JButton btnNewButton_3 = new JButton("Jugar");
        btnNewButton_3.setForeground(new Color(0, 0, 128));
        btnNewButton_3.setBackground(new Color(249, 244, 198));
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = textField.getText().trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre antes de jugar.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    Main.abrirVentanaBuscaminas(dificultad);
                }
            }
        });
        GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
        gbc_btnNewButton_3.anchor = GridBagConstraints.EAST;
        gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_3.gridx = 0;
        gbc_btnNewButton_3.gridy = 0;
        panel_1.add(btnNewButton_3, gbc_btnNewButton_3);

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(0, 0, 128));
        GridBagConstraints gbc_panel_3 = new GridBagConstraints();
        gbc_panel_3.insets = new Insets(0, 0, 0, 5);
        gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_3.gridx = 1;
        gbc_panel_3.gridy = 0;
        panel_1.add(panel_3, gbc_panel_3);

        JLabel lblNewLabel_5 = new JLabel("Nombre:");
        lblNewLabel_5.setForeground(new Color(249, 244, 198));
        panel_3.add(lblNewLabel_5);

        textField = new JTextField();
        textField.setForeground(new Color(0, 0, 128));
        textField.setBackground(new Color(249, 244, 198));

        AbstractDocument document = (AbstractDocument) textField.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (fb.getDocument().getLength() + string.length() <= 3) {
                    super.insertString(fb, offset, string.toUpperCase(), attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (fb.getDocument().getLength() + text.length() - length <= 3) {
                    super.replace(fb, offset, length, text.toUpperCase(), attrs);
                }
            }
        });

        textField.setColumns(3);
        panel_3.add(textField);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(0, 0, 128));
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.anchor = GridBagConstraints.WEST;
        gbc_panel_2.gridx = 2;
        gbc_panel_2.gridy = 0;
        panel_1.add(panel_2, gbc_panel_2);

        JLabel lblNewLabel = new JLabel("Dificultad: ");
        lblNewLabel.setForeground(new Color(249, 244, 198));
        lblNewLabel.setBackground(new Color(82, 87, 192));
        panel_2.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("Facil");
        lblNewLabel_2.setBackground(new Color(249, 244, 198));
        lblNewLabel_2.setForeground(new Color(0, 255, 255));
        panel_2.add(lblNewLabel_2);
        
        JLabel lblNewLabel_4 = new JLabel("");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_4.gridx = 2;
        gbc_lblNewLabel_4.gridy = 1;
        contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

        JLabel lblNewLabel_3 = new JLabel("");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 2;
        contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
        
        ImageIcon icon = new ImageIcon("src/images/pixel_art.png");
        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        lblNewLabel_4.setIcon(icon);
        lblNewLabel_3.setIcon(icon);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 128));
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 2;
        contentPane.add(panel, gbc_panel);

        JButton btnNewButton = new JButton("FACIL");
        btnNewButton.setForeground(new Color(0, 0, 128));
        btnNewButton.setBackground(new Color(249, 244, 198));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNewLabel_2.setText("Facil");
                dificultad = Dificultad.FACIL;
            }
        });
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("NORMAL");
        btnNewButton_1.setForeground(new Color(0, 0, 128));
        btnNewButton_1.setBackground(new Color(249, 244, 198));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNewLabel_2.setText("Normal");
                dificultad = Dificultad.MEDIO;
            }
        });
        panel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("DIFICIL");
        btnNewButton_2.setForeground(new Color(0, 0, 128));
        btnNewButton_2.setBackground(new Color(249, 244, 198));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNewLabel_2.setText("Dificil");
                dificultad = Dificultad.DIFICIL;
            }
        });
        panel.add(btnNewButton_2);
    }
}
