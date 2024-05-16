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
	}

	private void crearMenu() {
		crearVentana();		

//		insertarImagenDeFondo();
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
		ventana.getContentPane().setBackground(new Color(0, 0, 0));
		ventana.setTitle("Diseñando regiones");
		ventana.setBounds(100, 100, 601, 475);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		ventana.setLocationRelativeTo(null);
		
		crearBotonIniciar();
		
		JLabel fondoMenu = new JLabel();
		fondoMenu.setIcon(new ImageIcon("src/view/tierra.png")); 
		fondoMenu.setBounds(15, 0, 495, 400);
		ventana.getContentPane().add(fondoMenu);
	}

	private void crearBotonIniciar() {
		botonIniciar = new JButton("Iniciar");
		botonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaMapa.setVisible(true);
				ventana.setVisible(false);
			}
		});
		botonIniciar.setFocusable(false);
		botonIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonIniciar.setBounds(222, 175, 149, 42);
		ventana.getContentPane().add(botonIniciar);
	}

}