package vistas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import implementaciones.ExistCartaImp;
import implementaciones.MongoMazoImp;
import modelos.Carta;
import modelos.Mazo;

public class Pantalla extends JFrame implements ActionListener {

	private JButton btnGuardarMazo, btnCargarCartas, buttonPasar, buttonDevolver, btnGenerarMazo, btnCargarMazo;
	private DefaultListModel<Carta> modeloCarta, modeloMazo;
	private JList<Carta> listCartas, listMazo;
	private JTextField textMazo;
	private boolean poderRandom, actualizar;
	private JPanel contentPane;
	private ExistCartaImp eci;
	private MongoMazoImp mmi;
	private int valorIni;
	private String nombreMazoExistente;

	/**
	 * Create the frame.
	 */
	public Pantalla() {
		inicializar();

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

	/**
	 * Metodo que inicializa algunas variables
	 */
	private void inicializar() {
		eci = ExistCartaImp.getInstance();
		mmi = MongoMazoImp.getInstance();
		modeloCarta = new DefaultListModel<Carta>();
		modeloMazo = new DefaultListModel<Carta>();
		poderRandom = false;
		valorIni = 0;
		actualizar = false;
	}

	/**
	 * Metodo que segun el boton clicado nos envia a otro metodo para hacer la
	 * accion que se desea
	 */
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
			guardarMazo();
		} else if (e.getSource() == btnCargarMazo) {
			cargarMazo();
		}
	}

	/**
	 * Metodo que carga las cartas a la lista
	 */
	private void cargarCartas() {
		if (modeloCarta.getSize() == 0) {
			for (Carta carta : eci.getCards()) {
				modeloCarta.addElement(carta);
			}
			poderRandom = true;
			listCartas.setModel(modeloCarta);

			System.out.println("Cartas cargadas");
		}
	}

	/**
	 * Metodo que pasa la carta seleccionada de la lista de mazo a la de cartas
	 */
	private void deMazoACarta() {
		valorIni = valorIni - listMazo.getSelectedValue().getValorCarta();
		modeloCarta.addElement(listMazo.getSelectedValue());
		modeloMazo.removeElementAt(listMazo.getSelectedIndex());
		listCartas.setModel(modeloCarta);
	}

	/**
	 * Metodo que pasa la carta seleccionada de la lista de cartas a la de mazo
	 */
	private void deCartaAMazo() {
		if (valorIni + listCartas.getSelectedValue().getValorCarta() <= 20) {
			valorIni = valorIni + listCartas.getSelectedValue().getValorCarta();
			modeloMazo.addElement(listCartas.getSelectedValue());
			modeloCarta.removeElementAt(listCartas.getSelectedIndex());
			listMazo.setModel(modeloMazo);
		}
	}

	/**
	 * Metodo que genera un mazo aleatorio
	 */
	private void generarMazo() {
		if (poderRandom) {
			valorIni = 0;
			modeloCarta.clear();
			modeloMazo.clear();
			cargarCartas();
			int random = 0;

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
			System.out.println("Valor mazo: " + valorIni);
			listMazo.setModel(modeloMazo);
		}
	}

	/**
	 * Metodo que carga un mazo existente
	 */
	private void cargarMazo() {
		Mazo mazo = mmi.obtenerMazoPorNombre(textMazo.getText());
		ArrayList<Integer> ids = new ArrayList();

		if (mazo != null) {
			boolean introducir = true;
			modeloMazo.clear();
			modeloCarta.clear();

			for (Carta carta : mazo.getCartas()) {
				modeloMazo.addElement(carta);
				ids.add(carta.getId());
			}

			for (Carta carta : eci.getCards()) {
				for (int i = 0; i < ids.size(); i++) {
					if (carta.getId() == ids.get(i)) {
						introducir = false;
					}
				}
				if (introducir) {
					modeloCarta.addElement(carta);
				}
				introducir = true;
			}

			listMazo.setModel(modeloMazo);
			listCartas.setModel(modeloCarta);
			nombreMazoExistente=mazo.getNombre();
			actualizar = true;

		} else {
			JOptionPane.showMessageDialog(null, "ERROR - No existe el mazo: " + textMazo.getText(), "ERROR",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Metodo que guarda el mazo
	 */
	private void guardarMazo() {
		if (actualizar==false) {
			guardarNuevoMazo();
		}else {
			actualizarMazo();
		}
	}

	private void actualizarMazo() {
		if (modeloMazo.getSize() != 0) {
			Mazo mazo = new Mazo();
			ArrayList<Carta> deck = new ArrayList();
			int deckValue = 0;

			for (int i = 0; i < modeloMazo.size(); i++) {
				deck.add(modeloMazo.get(i));
				deckValue = deckValue + modeloMazo.get(i).getValorCarta();
			}
			mazo.setNombre(nombreMazoExistente);
			mazo.setValorMazo(deckValue);
			mazo.setCartas(deck);
			mmi.actualizarMazo(mazo);

			JOptionPane.showMessageDialog(null, "Mazo " + nombreMazoExistente + " actualizado correctamente");
			modeloMazo.clear();
			modeloCarta.clear();
			cargarCartas();
			
			nombreMazoExistente=null;
			actualizar=false;
		} else {
			JOptionPane.showMessageDialog(null, "ERROR - No se ha podido actualizar el mazo: " + nombreMazoExistente, "ERROR",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void guardarNuevoMazo() {
		String deckName = JOptionPane.showInputDialog("Introduce el nombre del nuevo mazo");

		if (modeloMazo.getSize() != 0 && mmi.obtenerMazoPorNombre(deckName) == null) {

			Mazo mazo = new Mazo();
			ArrayList<Carta> deck = new ArrayList();
			int deckValue = 0;

			for (int i = 0; i < modeloMazo.size(); i++) {
				deck.add(modeloMazo.get(i));
				deckValue = deckValue + modeloMazo.get(i).getValorCarta();
			}
			mazo.setNombre(deckName);
			mazo.setValorMazo(deckValue);
			mazo.setCartas(deck);
			mmi.insertarMazo(mazo);

			JOptionPane.showMessageDialog(null, "Mazo " + deckName + " creado correctamente");
			modeloMazo.clear();
			modeloCarta.clear();
			cargarCartas();

		} else {
			JOptionPane.showMessageDialog(null, "ERROR - No se ha podido crear el mazo: " + deckName, "ERROR",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
