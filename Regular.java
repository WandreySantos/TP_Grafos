/**
 * Verifica se o grafo representado por uma matriz de adjacência é regular.
 * Um grafo é regular se todos os vértices possuem o mesmo grau.
 *
 * @param matrizAdj Matriz de adjacência do grafo
 * @return true se o grafo for regular, false caso contrário
 */
public boolean isRegular(int[][] matrizAdj) {
    int n = matrizAdj.length; // Número de vértices
    int grau = 0; // Grau esperado (inicializa com o grau do primeiro vértice)

    // Calcula o grau do primeiro vértice (linha 0)
    for (int j = 0; j < n; j++) {
        if (matrizAdj[0][j] != 0) {
            grau++;
        }
    }

    // Compara o grau de todos os outros vértices com o grau do primeiro
    for (int i = 1; i < n; i++) {
        int grauAtual = 0;
        for (int j = 0; j < n; j++) {
            if (matrizAdj[i][j] != 0) {
                grauAtual++;
            }
        }
        // Se o grau atual for diferente do esperado, o grafo não é regular
        if (grauAtual != grau) {
            return false;
        }
    }
    // Todos os vértices têm o mesmo grau
    return true;
}
