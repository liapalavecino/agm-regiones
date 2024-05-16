package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Arista;
import model.BFS;
import model.GrafoPonderado;
import model.Kruskal;

public class PrincipalV2 {

    private JFrame ventana;
    private List<JCheckBox> checkBoxProvincias;
    private JTextField textFieldSimilaridad;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrincipalV2 window = new PrincipalV2();
                    window.ventana.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PrincipalV2() {
    
        inicializar();

    }

    private void inicializar() {
        crearPrincipal();
    }

    private void crearPrincipal() {
        crearVentana();
        tituloTP();
        crearCampos();
        agregarBotones();
    }

    private void crearVentana() {
        ventana = new JFrame();
        ventana.setPreferredSize(new Dimension(600, 475));
        ventana.getContentPane().setBackground(new Color(192, 192, 192));
        ventana.setTitle("Diseñando Regiones");
        ventana.setBounds(500, 500, 700, 550);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.getContentPane().setLayout(null);
    }

    private void tituloTP() {
        JLabel textoIngresarCiudades = new JLabel("Diseñando regiones");
        textoIngresarCiudades.setFont(new Font("Tahoma", Font.BOLD, 20));
        textoIngresarCiudades.setBounds(235, 20, 200, 50);
        ventana.getContentPane().add(textoIngresarCiudades);
    }

    private void crearCampos() {
        JLabel txtProvincias = new JLabel("Provincias:");
        txtProvincias.setBounds(40, 80, 100, 20);
        ventana.getContentPane().add(txtProvincias);

        checkBoxProvincias = new ArrayList<>();
        String[] provinciasArgentinas = {"Buenos Aires", "Catamarca", "Chaco", "Chubut", "Córdoba", "Corrientes",
                "Entre Ríos", "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza", "Misiones", "Neuquén",
                "Río Negro", "Salta", "San Juan", "San Luis", "Santa Cruz", "Santa Fe", "Santiago del Estero",
                "Tierra del Fuego", "Tucumán"};

        // Crea un panel con GridLayout para los checkboxes
        JPanel panelCheckboxes = new JPanel(new GridLayout(0, 1));
        for (String provincia : provinciasArgentinas) {
            JCheckBox checkBox = new JCheckBox(provincia);
            panelCheckboxes.add(checkBox);
            checkBoxProvincias.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(panelCheckboxes);
        scrollPane.setBounds(40, 100, 200, 300); // Ajusta el tamaño y la posición según sea necesario
        ventana.getContentPane().add(scrollPane);
        
        JLabel txtSimilaridad = new JLabel("Similaridad:");
        txtSimilaridad.setBounds(300, 80, 100, 20);
        ventana.getContentPane().add(txtSimilaridad);
        
        textFieldSimilaridad = new JTextField();
        textFieldSimilaridad.setBounds(300, 100, 100, 20);
        ventana.getContentPane().add(textFieldSimilaridad);
        
        
        JLabel txtRegiones= new JLabel("Cantidad de Regiones");
        txtRegiones.setBounds(300, 260, 130, 20);
        ventana.getContentPane().add(txtRegiones);
        
        JTextField txtFieldRegiones = new JTextField();
        txtFieldRegiones.setBounds(300, 280, 130, 20);
        ventana.getContentPane().add(txtFieldRegiones);
        
        
    }

    private void agregarBotones() {
        // Botón para agregar una nueva arista con su similaridad
        JButton botonAgregarSimilaridad = new JButton("Agregar similaridad");
        botonAgregarSimilaridad.setBounds(450, 100, 150, 20);
        ventana.getContentPane().add(botonAgregarSimilaridad);

      
 
        // Botón para crear las regiones
        JButton botonCrearRegiones = new JButton("Regiones");
        botonCrearRegiones.setBounds(450, 280, 150, 20);
        ventana.getContentPane().add(botonCrearRegiones);

        botonCrearRegiones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
            }
        });
    }

    private int obtenerSimilaridadDesdeTextField() {
        // Obtener el texto del JTextField y convertirlo a entero
        try {
            return Integer.parseInt(textFieldSimilaridad.getText());
        } catch (NumberFormatException e) {
            // Si hay un error al convertir, retorna 0 o maneja el error según sea necesario
            return 0;
        }
    }

   

    
    
}
