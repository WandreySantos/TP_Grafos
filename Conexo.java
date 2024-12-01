/**
 * Verifica se o grafo representado por uma matriz de adjacência é conexo.
 * Um grafo é conexo se existe um caminho entre qualquer par de vértices.
 *
 * @param matrizAdj Matriz de adjacência do grafo
 * @return true se o grafo for conexo, false caso contrário
 */
public boolean isConexo(int[][] matrizAdj) {
    int n = matrizAdj.length; // Número de vértices
    boolean[] visitados = new boolean[n]; // Vetor para marcar vértices visitados

    // Realiza uma busca em profundidade a partir do vértice 0
    dfs(0, matrizAdj, visitados);

    // Verifica se todos os vértices foram visitados
    for (boolean visitado : visitados) {
        if (!visitado) {
            // Se algum vértice não foi visitado, o grafo não é conexo
            return false;
        }
    }
    // Todos os vértices são alcançáveis
    return true;
}

/**
 * Busca em profundidade (DFS) para visitar vértices conectados a partir de um vértice inicial.
 *
 * @param vertice Vértice atual sendo explorado
 * @param matrizAdj Matriz de adjacência do grafo
 * @param visitados Vetor para marcar vértices já visitados
 */
private void dfs(int vertice, int[][] matrizAdj, boolean[] visitados) {
    visitados[vertice] = true; // Marca o vértice atual como visitado
    int n = matrizAdj.length; // Número de vértices

    // Percorre todos os vértices adjacentes
    for (int i = 0; i < n; i++) {
        if (matrizAdj[vertice][i] != 0 && !visitados[i]) {
            // Se há uma aresta e o vértice adjacente não foi visitado, chama recursivamente
            dfs(i, matrizAdj, visitados);
        }
    }
}
