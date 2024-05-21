package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import model.*;

public class GrafoManager {

    private Map<Integer, Ciudades> ciudades;
    private List<SimilaridadCoordenadas> similaridadCoordenadas;
    private GrafoPonderado grafo;
    private GrafoPonderado grafoAGM;
    private List<Arista> aristasAGM;
	private List<MapMarker> aristasPosibleDeEliminacion;

    public GrafoManager() {
        grafo = new GrafoPonderado();
        similaridadCoordenadas = new ArrayList<>();
        aristasAGM = new ArrayList<>();
        aristasPosibleDeEliminacion= new ArrayList<>();
        ciudades = new HashMap<>();
    }

    public void detectarVertices(JMapViewer mapa, JFrame frame, JComboBox<Object> comboBoxCiudad1, JComboBox<Object> comboBoxCiudad2) {
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
                        JOptionPane.showMessageDialog(frame, "Nombre de ciudad inválido. Intente nuevamente");
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

    public void dibujarArista(JComboBox<Object> comboBoxCiudad1, JComboBox<Object> comboBoxCiudad2, JTextField indiceSimilitud, JMapViewer mapa, JFrame frame, List<MapMarker> aristasPosibleDeEliminacion) {
        Ciudades ciudad1 = ciudades.get(comboBoxCiudad1.getSelectedIndex());
        Coordinate coordenada1 = ciudad1.consultarCoordenada();
        Ciudades ciudad2 = ciudades.get(comboBoxCiudad2.getSelectedIndex());
        Coordinate coordenada2 = ciudad2.consultarCoordenada();

        if (ciudad1.equals(ciudad2)) {
            JOptionPane.showMessageDialog(frame, "No se permite crear un loop. Seleccione diferentes ciudades para origen y destino.");
            return;
        }

        int similaridad = Integer.parseInt(indiceSimilitud.getText());
        if (similaridad < 1) {
            JOptionPane.showMessageDialog(frame, "Ingrese una similaridad mayor o igual a 1.");
            return;
        }
        int coor1 = comboBoxCiudad1.getSelectedItem().hashCode();
        int coor2 = comboBoxCiudad2.getSelectedItem().hashCode();

        grafo.agregarVertice(coor1);
        grafo.agregarVertice(coor2);

        grafo.agregarArista(coor1, coor2, similaridad);

        SimilaridadCoordenadas pesoCoordenadas = new SimilaridadCoordenadas(coordenada1, coordenada2, similaridad);
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

    public void mostrarAGM(JMapViewer mapa, List<MapMarker> aristasPosibleDeEliminacion, JFrame frame) {
        grafoAGM = new GrafoPonderado();

        if (grafo.consultarVertices().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El grafo es vacío. No se puede generar un AGM.");
            return;
        }

        for (int vertice : grafo.consultarVertices()) {
            grafoAGM.agregarVertice(vertice);
        }

        for (Arista arista : grafo.consultarAristas()) {
            grafoAGM.agregarArista(arista.consultarOrigen(), arista.consultarDestino(), arista.consultarSimilaridad());
        }

        Kruskal kruskal = new Kruskal(grafoAGM);
		aristasAGM = kruskal.obtenerAGM().consultarAristas();

        limpiarMarkersArista(mapa, aristasPosibleDeEliminacion);

        procesarAristas(aristasAGM, grafoAGM, ciudades, Color.RED, mapa);
    }

    public void separarRegiones(JMapViewer mapa, List<MapMarker> aristasPosibleDeEliminacion, JTextField textField_Regiones, JFrame frame) {
        int eliminarAristas = Integer.parseInt(textField_Regiones.getText()) - 1;
        if (eliminarAristas < 1) {
            JOptionPane.showMessageDialog(frame, "Por favor, ingrese un número válido.");
            return;
        }

        if (aristasAGM.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay aristas en el AGM para separar.");
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

        limpiarMarkersArista(mapa, aristasPosibleDeEliminacion);

        procesarAristas(aristasAGM, grafoAGM, ciudades, Color.MAGENTA, mapa);
    }

    private void limpiarMarkersArista(JMapViewer mapa, List<MapMarker> aristasPosibleDeEliminacion) {
        mapa.removeAllMapPolygons();

        for (MapMarker markerArista : aristasPosibleDeEliminacion) {
            mapa.removeMapMarker(markerArista);
        }
        aristasPosibleDeEliminacion.clear();
    }

    private void procesarAristas(List<Arista> aristasAGM, Grafo grafoAGM, Map<Integer, Ciudades> ciudades, Color color, JMapViewer mapa) {
        for (Arista aristaAGM : aristasAGM) {
            int coordenadaOrigen = grafoAGM.obtenerIndiceDelVertice(aristaAGM.consultarOrigen());
            int coordenadaDestino = grafoAGM.obtenerIndiceDelVertice(aristaAGM.consultarDestino());

            Ciudades ciudad1 = ciudades.get(coordenadaOrigen);
            Ciudades ciudad2 = ciudades.get(coordenadaDestino);

            agregarLineaYMarcador(ciudad1, ciudad2, aristaAGM.consultarSimilaridad(), color, mapa);
        }
    }

    private void agregarLineaYMarcador(Ciudades ciudad1, Ciudades ciudad2, double similaridad, Color colorLinea, JMapViewer mapa) {
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