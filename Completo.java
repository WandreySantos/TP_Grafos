/**
 * Verifica se o grafo representado por uma matriz de adjacência é completo.
 * Um grafo é completo se todos os pares de vértices possuem arestas conectando-os.
 *
 * @param matrizAdj Matriz de adjacência do grafo
 * @return true se o grafo for completo, false caso contrário
 */
public boolean isCompleto(int[][] matrizAdj) {
    int n = matrizAdj.length; // Número de vértices (tamanho da matriz)

    // Percorre todos os pares de vértices (i, j)
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            // Ignora os casos onde i == j (vértice consigo mesmo)
            if (i != j && matrizAdj[i][j] == 0) {
                // Se não houver aresta entre dois vértices diferentes, o grafo não é completo
                return false;
            }
        }
    }
    // Se todas as arestas estiverem presentes, o grafo é completo
    return true;
}
