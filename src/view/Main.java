package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import model.*;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class Main {

	private JFrame diseñandoRegiones;
	private JMapViewer mapa;

	private Map<Integer, Ciudades> ciudades;
	private List<SimilaridadCoordenadas> similaridadCoordenadas;
	private GrafoPonderado grafo;

	private JTextField indiceSimilitud;
	private JButton btnCrearArista;
	private JComboBox<Object> comboBoxCiudad1;
	private JComboBox<Object> comboBoxCiudad2;

	private JButton btnAGM;
	private GrafoPonderado grafoAGM;
	private List<Arista> aristasAGM;

	private JTextField textField_Regiones;
	private JButton btnSepararRegiones;
	private List<MapMarker> aristasPosibleDeEliminacion;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		grafo = new GrafoPonderado();
		diseñandoRegiones = new JFrame();
		diseñandoRegiones.setBackground(new Color(240, 240, 240));
		diseñandoRegiones.setResizable(false);
		// botonIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
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

		similaridadCoordenadas = new ArrayList<>();

		detectarVertices();
		dibujarArista();
		AGM();
		separarRegiones();

	}

	private void detectarVertices() {
		ciudades = new HashMap<>();
		mapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					Coordinate coord = (Coordinate) mapa.getPosition(e.getPoint());
					int id = ciudades.size();
					Ciudades prov = new Ciudades(coord);
					ciudades.put(id, prov);
					String nombreCiudad = JOptionPane.showInputDialog("Nombre de ciudad: ");
					if (nombreCiudad == null || nombreCiudad.equals("")) {
						JOptionPane.showMessageDialog(diseñandoRegiones,
								"Nombre de ciudad inválido. Intente nuevamente");
						return;
					}
					mapa.addMapMarker(new MapMarkerDot(nombreCiudad, coord));
					prov.agregarNombreCiudad(nombreCiudad);
					comboBoxCiudad1.addItem(prov.consultarNombreCiudad());
					comboBoxCiudad2.addItem(prov.consultarNombreCiudad());
				}
			}
		});
	}

	private void dibujarArista() {
		btnCrearArista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ciudades ciudad1 = ciudades.get(comboBoxCiudad1.getSelectedIndex());
				Coordinate coordenada1 = ciudad1.consultarCoordenada();
				Ciudades ciudad2 = ciudades.get(comboBoxCiudad2.getSelectedIndex());
				Coordinate coordenada2 = ciudad2.consultarCoordenada();

				if (ciudad1.equals(ciudad2)) {
					JOptionPane.showMessageDialog(diseñandoRegiones,
							"No se permite crear un loop. Seleccione diferentes ciudades para origen y destino.");
					return;
				}

				int similaridad = Integer.parseInt(indiceSimilitud.getText());
				if (similaridad < 1) {
					JOptionPane.showMessageDialog(diseñandoRegiones, "Ingrese una similaridad mayor o igual a 1.");
					return;
				}
				int coor1 = comboBoxCiudad1.getSelectedItem().hashCode();
				int coor2 = comboBoxCiudad2.getSelectedItem().hashCode();

				grafo.agregarVertice(coor1);
				grafo.agregarVertice(coor2);

				grafo.agregarArista(coor1, coor2, similaridad);

				SimilaridadCoordenadas pesoCoordenadas = new SimilaridadCoordenadas(coordenada1, coordenada2,
						similaridad);
				similaridadCoordenadas.add(pesoCoordenadas);

				double latitudMedia = (coordenada1.getLat() + coordenada2.getLat()) / 2;
				double longitudMedia = (coordenada1.getLon() + coordenada2.getLon()) / 2;
				Coordinate puntoMedio = new Coordinate(latitudMedia, longitudMedia);
				MapMarkerDot marker = new MapMarkerDot(String.valueOf(similaridad), puntoMedio);
				marker.setColor(new Color(0, 0, 0, 0));
				marker.setBackColor(new Color(0, 0, 0, 0));
				ciudad1.agregarSimilaridad(similaridad);
				ciudad2.agregarSimilaridad(similaridad);

				mapa.addMapMarker(marker);
				aristasPosibleDeEliminacion.add(marker);

				ArrayList<Coordinate> coordenadasLinea = new ArrayList<>();
				coordenadasLinea.add(coordenada1);
				coordenadasLinea.add(coordenada2);
				coordenadasLinea.add(coordenada2);
				MapPolygonImpl linea = new MapPolygonImpl(coordenadasLinea);
				linea.setColor(Color.BLACK);

				mapa.addMapPolygon(linea);
			}
		});
	}

	private void AGM() {
        btnAGM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                grafoAGM = new GrafoPonderado();

                if (grafo.consultarVertices().isEmpty()) {
                    JOptionPane.showMessageDialog(diseñandoRegiones, "El grafo es vacío. No se puede generar un AGM.");
                    return;
                }

                for (int vertice : grafo.consultarVertices()) {
                    grafoAGM.agregarVertice(vertice);
                }

                for (Arista arista : grafo.consultarAristas()) {
                    grafoAGM.agregarArista(arista.consultarOrigen(), arista.consultarDestino(),
                            arista.consultarSimilaridad());
                }

                Kruskal kruskal = new Kruskal(grafoAGM);
                aristasAGM = kruskal.obtenerAGM().consultarAristas();

                limpiarMarkersArista();

                procesarAristas(aristasAGM, grafoAGM, ciudades, Color.RED);
            }

        });
       }
        
	private void separarRegiones() {
        btnSepararRegiones.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent arg0) {
		int eliminarAristas = Integer.parseInt(textField_Regiones.getText()) - 1;
		if (eliminarAristas < 1) {
			JOptionPane.showMessageDialog(diseñandoRegiones, "Por favor, ingrese un número válido.");
			return;
		}

		if (aristasAGM.isEmpty()) {
			JOptionPane.showMessageDialog(diseñandoRegiones, "No hay aristas en el AGM para separar.");
			return;
		}

		for (int j = 0; j < eliminarAristas; j++) {
			Arista aristaDeMayorPeso = aristasAGM.get(0);
			int indice = 0;
			for (int i = 0; i < aristasAGM.size(); i++) {
				Arista aristaAGM = aristasAGM.get(i);
				if (aristaAGM.consultarSimilaridad() > aristaDeMayorPeso.consultarSimilaridad()) {
					aristaDeMayorPeso = aristaAGM;
					indice = i;
				}
			}
			aristasAGM.remove(indice);
		}

		limpiarMarkersArista();

		procesarAristas(aristasAGM, grafoAGM, ciudades, Color.MAGENTA);
	}

	});
	
	
	diseñandoRegiones.setVisible(true);

	ciudades=new HashMap<>();similaridadCoordenadas=new ArrayList<>();aristasPosibleDeEliminacion=new ArrayList<>();}

	public void procesarAristas(List<Arista> aristasAGM, Grafo grafoAGM, Map<Integer, Ciudades> ciudades, Color color) {
		for (Arista aristaAGM : aristasAGM) {
			int coordenadaOrigen = grafoAGM.obtenerIndiceDelVertice(aristaAGM.consultarOrigen());
			int coordenadaDestino = grafoAGM.obtenerIndiceDelVertice(aristaAGM.consultarDestino());

			Ciudades ciudad1 = ciudades.get(coordenadaOrigen);
			Ciudades ciudad2 = ciudades.get(coordenadaDestino);

			agregarLineaYMarcador(ciudad1, ciudad2, aristaAGM.consultarSimilaridad(), color);
		}
	}

	private void limpiarMarkersArista() {
		mapa.removeAllMapPolygons();

		for (MapMarker markerArista : aristasPosibleDeEliminacion) {
			mapa.removeMapMarker(markerArista);
		}

		aristasPosibleDeEliminacion.clear();
	}

	private void agregarLineaYMarcador(Ciudades ciudad1, Ciudades ciudad2, double similaridad, Color colorLinea) {
		Coordinate coordenada1 = ciudad1.consultarCoordenada();
		Coordinate coordenada2 = ciudad2.consultarCoordenada();

		double latitudMedia = (coordenada1.getLat() + coordenada2.getLat()) / 2;
		double longitudMedia = (coordenada1.getLon() + coordenada2.getLon()) / 2;
		Coordinate puntoMedio = new Coordinate(latitudMedia, longitudMedia);

		MapMarkerDot marker = new MapMarkerDot(String.valueOf(similaridad), puntoMedio);
		marker.setColor(new Color(0, 0, 0, 0));
		marker.setBackColor(new Color(0, 0, 0, 0));

		mapa.addMapMarker(marker);
		aristasPosibleDeEliminacion.add(marker);

		ArrayList<Coordinate> coordenadasLinea = new ArrayList<>();
		coordenadasLinea.add(coordenada1);
		coordenadasLinea.add(coordenada2);
		coordenadasLinea.add(coordenada2);

		MapPolygonImpl linea = new MapPolygonImpl(coordenadasLinea);
		linea.setColor(colorLinea);

		mapa.addMapPolygon(linea);
	}

}
