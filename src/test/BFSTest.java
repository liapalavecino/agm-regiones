package test;

import model.Grafo;
import model.BFS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BFSTest {

    private Grafo grafo;
    private ArrayList<Integer> vertices;

    @BeforeEach
    public void setUp() {
        vertices = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            vertices.add(i);
        }
        grafo = new Grafo(vertices);
        grafo.completar();
    }

    @Test
    public void testEsConexo() {
        assertTrue(BFS.esConexo(grafo));
    }

    @Test
    public void testAlcanzables() {
        Set<Integer> alcanzables = BFS.alcanzables(grafo, 0);
        assertEquals(5, alcanzables.size());
        for (int i = 0; i < 5; i++) {
            assertTrue(alcanzables.contains(i));
        }
    }

    @Test
    public void testComprobarValidezDeGrafo() {
        assertThrows(NullPointerException.class, () -> BFS.comprobarValidezDeGrafo(null));
        assertThrows(IllegalArgumentException.class, () -> BFS.comprobarValidezDeGrafo(new Grafo(new ArrayList<>())));
        ArrayList<Integer> singleVertex = new ArrayList<>();
        singleVertex.add(1);
        assertThrows(IllegalArgumentException.class, () -> BFS.comprobarValidezDeGrafo(new Grafo(singleVertex)));
    }
}
