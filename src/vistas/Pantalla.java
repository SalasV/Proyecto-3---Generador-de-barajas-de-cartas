package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import implementaciones.ExistCartaImp;
import modelos.Carta;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;

public class Pantalla extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textMazo;
	private JButton btnGuardarMazo, btnCargarCartas, buttonPasar, buttonDevolver, btnGenerarMazo, btnCargarMazo;
	private ExistCartaImp eci;
	private JList<Carta> listCartas, listMazo;
	int valorIni;
	DefaultListModel<Carta> modeloCarta, modeloMazo;

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
		eci = ExistCartaImp.getInstance();
		modeloCarta = new DefaultListModel<Carta>();
		modeloMazo = new DefaultListModel<Carta>();
		valorIni = 0;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		buttonPasar = new JButton(">");
		buttonPasar.setBounds(420, 221, 45, 23);

		buttonDevolver = new JButton("<");
		buttonDevolver.setBounds(420, 255, 45, 23);

		listCartas = new JList();
		listCartas.setBounds(15, 47, 347, 455);

		listMazo = new JList();
		listMazo.setBounds(527, 47, 349, 456);

		JLabel lblCartas = new JLabel("CARTAS");
		lblCartas.setBounds(138, 11, 71, 25);
		lblCartas.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblMazo = new JLabel("MAZO");
		lblMazo.setBounds(675, 11, 52, 25);
		lblMazo.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnGenerarMazo = new JButton("Generar Mazo");
		btnGenerarMazo.setBounds(372, 360, 145, 23);

		btnCargarCartas = new JButton("Cargar Cartas");
		btnCargarCartas.setBounds(372, 99, 145, 23);

		btnGuardarMazo = new JButton("Guardar Mazo");
		btnGuardarMazo.setBounds(372, 326, 145, 23);

		contentPane.setLayout(null);
		contentPane.add(listCartas);
		contentPane.add(buttonDevolver);
		contentPane.add(buttonPasar);
		contentPane.add(btnCargarCartas);
		contentPane.add(btnGuardarMazo);
		contentPane.add(listMazo);
		contentPane.add(btnGenerarMazo);
		contentPane.add(lblCartas);
		contentPane.add(lblMazo);

		textMazo = new JTextField();
		textMazo.setBounds(372, 427, 145, 20);
		contentPane.add(textMazo);
		textMazo.setColumns(10);

		btnCargarMazo = new JButton("Cargar Mazo");
		btnCargarMazo.setBounds(372, 458, 145, 23);
		contentPane.add(btnCargarMazo);

		btnCargarCartas.addActionListener(this);
		buttonDevolver.addActionListener(this);
		btnGenerarMazo.addActionListener(this);
		btnGuardarMazo.addActionListener(this);
		btnCargarMazo.addActionListener(this);
		buttonPasar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCargarCartas) {
			cargarCartas();
		} else if (e.getSource() == buttonDevolver) {
			deMazoACarta();
		} else if (e.getSource() == buttonPasar) {
			deCartaAMazo();
		} else if (e.getSource() == btnGenerarMazo) {
			generarMazo();
		} else if (e.getSource() == btnGuardarMazo) {

		} else if (e.getSource() == btnCargarMazo) {

		}
	}

	private void generarMazo() {
		modeloCarta.clear();
		modeloMazo.clear();
		cargarCartas();
		int random=0;

		while (valorIni <= 20) {
			random = (int) (Math.random() * modeloCarta.size() + 1) - 1;

			if (valorIni + listCartas.getModel().getElementAt(random).getValorCarta() <= 20) {
				modeloMazo.addElement(listCartas.getModel().getElementAt(random));
				valorIni = valorIni + listCartas.getModel().getElementAt(random).getValorCarta();
				modeloCarta.removeElementAt(random);
			} else {
				valorIni = valorIni + listCartas.getModel().getElementAt(random).getValorCarta();
			}
		}
		valorIni = valorIni - listCartas.getModel().getElementAt(random).getValorCarta();
		listMazo.setModel(modeloMazo);
	}

	private void cargarCartas() {
		if (modeloCarta.getSize() == 0) {
			for (Carta carta : eci.getCards()) {
				modeloCarta.addElement(carta);
			}
			listCartas.setModel(modeloCarta);
		}
	}

	private void deCartaAMazo() {
		if (valorIni + listCartas.getSelectedValue().getValorCarta() <= 20) {
			valorIni = valorIni + listCartas.getSelectedValue().getValorCarta();
			modeloMazo.addElement(listCartas.getSelectedValue());
			modeloCarta.removeElementAt(listCartas.getSelectedIndex());
			listMazo.setModel(modeloMazo);
		}
	}

	private void deMazoACarta() {
		valorIni = valorIni - listMazo.getSelectedValue().getValorCarta();
		modeloCarta.addElement(listMazo.getSelectedValue());
		modeloMazo.removeElementAt(listMazo.getSelectedIndex());
		listCartas.setModel(modeloCarta);
	}
}
