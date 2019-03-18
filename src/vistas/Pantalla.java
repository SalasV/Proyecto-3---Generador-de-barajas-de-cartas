package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class Pantalla extends JFrame {

	private JPanel contentPane;
	private JTextField textMazo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla frame = new Pantalla();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pantalla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton buttonPasar = new JButton(">");
		buttonPasar.setBounds(420, 221, 45, 23);
		
		JButton buttonDevolver = new JButton("<");
		buttonDevolver.setBounds(420, 255, 45, 23);
		
		JList listCartas = new JList();
		listCartas.setBounds(15, 47, 347, 455);
		
		JList list = new JList();
		list.setBounds(527, 47, 349, 456);
		
		JLabel lblCartas = new JLabel("CARTAS");
		lblCartas.setBounds(138, 11, 71, 25);
		lblCartas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblMazo = new JLabel("MAZO");
		lblMazo.setBounds(675, 11, 52, 25);
		lblMazo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnGenerarMazo = new JButton("Generar Mazo");
		btnGenerarMazo.setBounds(372, 360, 145, 23);
		
		JButton btnCargarCartas = new JButton("Cargar Cartas");
		btnCargarCartas.setBounds(372, 99, 145, 23);
		
		JButton btnGuardarMazo = new JButton("Guardar Mazo");
		btnGuardarMazo.setBounds(372, 326, 145, 23);
		contentPane.setLayout(null);
		contentPane.add(listCartas);
		contentPane.add(buttonDevolver);
		contentPane.add(buttonPasar);
		contentPane.add(btnCargarCartas);
		contentPane.add(btnGuardarMazo);
		contentPane.add(list);
		contentPane.add(btnGenerarMazo);
		contentPane.add(lblCartas);
		contentPane.add(lblMazo);
		
		textMazo = new JTextField();
		textMazo.setBounds(372, 427, 145, 20);
		contentPane.add(textMazo);
		textMazo.setColumns(10);
		
		JButton btnCargarMazo = new JButton("Cargar Mazo");
		btnCargarMazo.setBounds(372, 458, 145, 23);
		contentPane.add(btnCargarMazo);
	}
}
