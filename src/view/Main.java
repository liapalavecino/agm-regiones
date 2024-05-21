package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

public class Main {

    private JFrame diseñandoRegiones;
    private JMapViewer mapa;

    private GrafoManager grafoManager;

    private JTextField indiceSimilitud;
    private JButton btnCrearArista;
    private JComboBox<Object> comboBoxCiudad1;
    private JComboBox<Object> comboBoxCiudad2;

    private JButton btnAGM;
    private JTextField textField_Regiones;
    private JButton btnSepararRegiones;
    private List<MapMarker> aristasPosibleDeEliminacion;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.diseñandoRegiones.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    Main() {
        initialize();
    }

    private void initialize() {
        grafoManager = new GrafoManager();
        diseñandoRegiones = new JFrame();
        diseñandoRegiones.setBackground(new Color(240, 240, 240));
        diseñandoRegiones.setResizable(false);
        diseñandoRegiones.setBounds(410, 160, 750, 500);
        diseñandoRegiones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        diseñandoRegiones.getContentPane().setLayout(null);
        diseñandoRegiones.setFocusable(true);
        diseñandoRegiones.setLocationRelativeTo(null);
        diseñandoRegiones.setTitle("Diseñando regiones");

        JLabel lblNewLabel_1_1 = new JLabel("Diseñando Regiones");
        lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(63, 10, 157, 22);
        diseñandoRegiones.getContentPane().add(lblNewLabel_1_1);

        Coordinate coordenadaInicial = new Coordinate(-40.5, -64.45);

        aristasPosibleDeEliminacion = new ArrayList<>();

        mapa = new JMapViewer();
        mapa.setBounds(278, 0, 454, 460);
        diseñandoRegiones.getContentPane().add(mapa);

        JLabel lblNewLabel_1 = new JLabel("Agregue la similaridad");
        lblNewLabel_1.setBounds(20, 142, 147, 14);
        diseñandoRegiones.getContentPane().add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));

        comboBoxCiudad1 = new JComboBox<>();
        comboBoxCiudad1.setBounds(20, 75, 100, 22);
        diseñandoRegiones.getContentPane().add(comboBoxCiudad1);

        comboBoxCiudad2 = new JComboBox<>();
        comboBoxCiudad2.setBounds(168, 75, 100, 22);
        diseñandoRegiones.getContentPane().add(comboBoxCiudad2);

        JLabel lblNewLabel_1_2 = new JLabel("Coordenada 1");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_2.setBounds(28, 51, 89, 14);
        diseñandoRegiones.getContentPane().add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_2_1 = new JLabel("Coordenada 2");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_2_1.setBounds(175, 51, 89, 14);
        diseñandoRegiones.getContentPane().add(lblNewLabel_1_2_1);

        indiceSimilitud = new JTextField();
        indiceSimilitud.setBounds(168, 138, 100, 22);
        diseñandoRegiones.getContentPane().add(indiceSimilitud);
        indiceSimilitud.setColumns(10);

        btnAGM = new JButton("Mostrar AGM");
        btnAGM.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAGM.setBounds(75, 229, 126, 23);
        diseñandoRegiones.getContentPane().add(btnAGM);

        JLabel lblCantidadRegiones = new JLabel("Cantidad de regiones");
        lblCantidadRegiones.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCantidadRegiones.setBounds(10, 302, 147, 14);
        diseñandoRegiones.getContentPane().add(lblCantidadRegiones);

        textField_Regiones = new JTextField();
        textField_Regiones.setBounds(168, 299, 100, 22);
        diseñandoRegiones.getContentPane().add(textField_Regiones);
        textField_Regiones.setColumns(10);

        btnSepararRegiones = new JButton("Crear");
        btnSepararRegiones.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnSepararRegiones.setBounds(90, 336, 94, 23);
        diseñandoRegiones.getContentPane().add(btnSepararRegiones);
        btnCrearArista = new JButton("Agregar");
        btnCrearArista.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnCrearArista.setBounds(90, 178, 94, 23);
        diseñandoRegiones.getContentPane().add(btnCrearArista);

        mapa.setDisplayPosition(coordenadaInicial, 4);

        grafoManager.detectarVertices(mapa, diseñandoRegiones, comboBoxCiudad1, comboBoxCiudad2);
        dibujarArista();
        mostrarAGM();
        separarRegiones();
    }

    private void dibujarArista() {
        btnCrearArista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                grafoManager.dibujarArista(comboBoxCiudad1, comboBoxCiudad2, indiceSimilitud, mapa, diseñandoRegiones, aristasPosibleDeEliminacion);
            }
        });
    }

    private void mostrarAGM() {
        btnAGM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                grafoManager.mostrarAGM(mapa, aristasPosibleDeEliminacion, diseñandoRegiones);
            }
        });
    }

    private void separarRegiones() {
        btnSepararRegiones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                grafoManager.separarRegiones(mapa, aristasPosibleDeEliminacion, textField_Regiones, diseñandoRegiones);
            }
        });
    }
}
