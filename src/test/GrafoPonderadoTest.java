package test;

import model.GrafoPonderado;
import model.Arista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GrafoPonderadoTest {

    private GrafoPonderado grafoPonderado;
    private ArrayList<Integer> vertices;

    @BeforeEach
    public void setUp() {
        vertices = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            vertices.add(i);
        }
        grafoPonderado = new GrafoPonderado(vertices);
        grafoPonderado.agregarArista(0, 1, 10);
        grafoPonderado.agregarArista(1, 2, 20);
        grafoPonderado.agregarArista(2, 3, 30);
        grafoPonderado.agregarArista(3, 4, 40);
        grafoPonderado.agregarArista(4, 0, 50);
    }

    @Test
    public void testAgregarArista() {
        grafoPonderado.agregarArista(0, 2, 60);
        assertEquals(60, grafoPonderado.obtenerPesoArista(0, 2));
    }

    @Test
    public void testGetPesoArista() {
        assertEquals(10, grafoPonderado.obtenerPesoArista(0, 1));
        assertEquals(20, grafoPonderado.obtenerPesoArista(1, 2));
        assertEquals(30, grafoPonderado.obtenerPesoArista(2, 3));
        assertEquals(40, grafoPonderado.obtenerPesoArista(3, 4));
        assertEquals(50, grafoPonderado.obtenerPesoArista(4, 0));
    }

    @Test
    public void testConsultarAristas() {
        ArrayList<Arista> aristas = grafoPonderado.consultarAristas();
        assertEquals(5, aristas.size());
        for (Arista arista : aristas) {
            assertTrue(vertices.contains(arista.consultarOrigen()));
            assertTrue(vertices.contains(arista.consultarDestino()));
        }
    }
}
