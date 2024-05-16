package model;

public class UnionFind {

	private int[] padres;

	public UnionFind(Grafo grafo) {
		padres = new int[grafo.consultarTamanio()];

		inicializarPadres();
	}

	private void inicializarPadres() {
		for (int i = 0; i < padres.length; i++)
			padres[i] = i;
	}

	public void unir(int i, int j) {
		int raiz_i = raiz(i);
		int raiz_j = raiz(j);

		padres[raiz_i] = raiz_j;
	}

	public boolean find(int i, int j) {
		return raiz(i) == raiz(j);
	}

	public int raiz(int i) {
		while (padres[i] != i)
			i = padres[i];

		return i;
	}
}
