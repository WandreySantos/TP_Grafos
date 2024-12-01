/**
 * Verifica se o grafo representado por uma matriz de adjacência é acíclico.
 * Um grafo é acíclico se não possui ciclos.
 *
 * @param matrizAdj Matriz de adjacência do grafo
 * @return true se o grafo for acíclico, false caso contrário
 */
public boolean isAciclico(int[][] matrizAdj) {
    int n = matrizAdj.length; // Número de vértices
    boolean[] visitados = new boolean[n]; // Marca vértices visitados
    boolean[] recStack = new boolean[n]; // Marca os vértices na pilha de recursão

    // Verifica se há ciclos em cada componente do grafo
    for (int i = 0; i < n; i++) {
        if (!visitados[i]) {
            // Chama a função recursiva para detectar ciclos
            if (isCyclicUtil(i, matrizAdj, visitados, recStack)) {
                return false; // Ciclo detectado
            }
        }
    }
    // Não foram detectados ciclos
    return true;
}

/**
 * Função auxiliar para detectar ciclos em uma busca em profundidade.
 *
 * @param vertice Vértice atual sendo explorado
 * @param matrizAdj Matriz de adjacência do grafo
 * @param visitados Vetor para marcar vértices já visitados
 * @param recStack Vetor para marcar vértices na pilha de recursão
 * @return true se houver um ciclo, false caso contrário
 */
private boolean isCyclicUtil(int vertice, int[][] matrizAdj, boolean[] visitados, boolean[] recStack) {
    visitados[vertice] = true; // Marca o vértice atual como visitado
    recStack[vertice] = true; // Adiciona o vértice à pilha de recursão
    int n = matrizAdj.length; // Número de vértices

    // Verifica todos os vértices adjacentes
    for (int i = 0; i < n; i++) {
        if (matrizAdj[vertice][i] != 0) { // Há uma aresta entre os vértices
            if (!visitados[i] && isCyclicUtil(i, matrizAdj, visitados, recStack)) {
                return true; // Ciclo detectado em chamada recursiva
            } else if (recStack[i]) {
                return true; // Ciclo detectado via pilha de recursão
            }
        }
    }

    recStack[vertice] = false; // Remove o vértice da pilha de recursão
    return false;
}
