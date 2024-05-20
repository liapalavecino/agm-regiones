package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Menu window = new Menu();
					window.ventana.setVisible(true);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		inicializar();
	}

	private void inicializar() {
		ventana = new JFrame();
		ventana.setResizable(false);
		ventana.getContentPane().setBackground(new Color(0, 0, 0));
		ventana.setTitle("Diseñando regiones");
		ventana.setBounds(410,  500, 750, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		ventana.setLocationRelativeTo(null);
		
		crearBotonIniciar();
		
		JLabel fondoMenu = new JLabel();
		fondoMenu.setIcon(new ImageIcon("src/view/tierra.png")); 
		fondoMenu.setBounds(-135, -30, 900, 550);
		ventana.getContentPane().add(fondoMenu);
	}

	private void crearBotonIniciar() {
	    botonIniciar = new JButton("Iniciar");
	    botonIniciar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    		ventanaMapa = new Main(); 	
	        	ventana.setVisible(false);    
	        }
	    });
	    botonIniciar.setFocusable(false);
	    botonIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
	    botonIniciar.setBounds(293, 210, 149, 42);
	    ventana.getContentPane().add(botonIniciar);
	}

}