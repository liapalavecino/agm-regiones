package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Menu {

	private JFrame frame;
	private JFrame ventana;
	private Main ventanaMapa;
	private JButton botonIniciar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		crearMenu();
		eventoBotonJugar();
	}

	private void crearMenu() {
		crearVentana();		
		crearBotonJugar();
		insertarImagenDeFondo();
	}

	private void crearVentana() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ventanaMapa = new Main();
		ventana = new JFrame();
		ventana.setResizable(false);
		ventana.setPreferredSize(new Dimension(600, 475));
		ventana.getContentPane().setBackground(new Color(51, 0, 102));
		ventana.setTitle("Diseñando regiones");
		ventana.setBounds(100, 100, 601, 475);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		ventana.setLocationRelativeTo(null);
	}

	private void crearBotonJugar() {
		botonIniciar = new JButton("Iniciar");
		botonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaMapa.setVisible(true);
				ventana.setVisible(false);
			}
		});
		botonIniciar.setFocusable(false);
		botonIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonIniciar.setBounds(222, 250, 149, 42);
		ventana.getContentPane().add(botonIniciar);
	}


	private void eventoBotonJugar() {
		botonIniciar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ventanaMapa = new Main();
				ventanaMapa.setVisible(true);
				ventana.setVisible(false);
			}
		});
	}



	private void insertarImagenDeFondo() {
		JLabel fondoMenu = new JLabel();
		fondoMenu.setIcon(new ImageIcon("src/interfaz/img/tierra-menu.jpg"));
		fondoMenu.setBounds(0, 0, 684, 480);
		ventana.getContentPane().add(fondoMenu);
	}

}

