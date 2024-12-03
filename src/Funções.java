import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Funções {

    public boolean isAciclico(int[][] matrizAdj) {
        int n = matrizAdj.length;
        boolean[] visitados = new boolean[n];
        boolean[] recStack = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visitados[i]) {
                if (isCyclicUtil(i, matrizAdj, visitados, recStack)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCyclicUtil(int vertice, int[][] matrizAdj, boolean[] visitados, boolean[] recStack) {
        visitados[vertice] = true;
        recStack[vertice] = true;
        int n = matrizAdj.length;

        for (int i = 0; i < n; i++) {
            if (matrizAdj[vertice][i] != 0) {
                if (!visitados[i] && isCyclicUtil(i, matrizAdj, visitados, recStack)) {
                    return true;
                } else if (recStack[i]) {
                    return true;
                }
            }
        }

        recStack[vertice] = false;
        return false;
    }

    public boolean isCompleto(int[][] matrizAdj) {
        int n = matrizAdj.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && matrizAdj[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isConexo(int[][] matrizAdj) {
        int n = matrizAdj.length;
        boolean[] visitados = new boolean[n];

        dfs(0, matrizAdj, visitados);

        for (boolean visitado : visitados) {
            if (!visitado) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int vertice, int[][] matrizAdj, boolean[] visitados) {
        visitados[vertice] = true;
        int n = matrizAdj.length;

        for (int i = 0; i < n; i++) {
            if (matrizAdj[vertice][i] != 0 && !visitados[i]) {
                dfs(i, matrizAdj, visitados);
            }
        }
    }

    public boolean isRegular(int[][] matrizAdj) {
        int n = matrizAdj.length;
        int grau = 0;

        for (int j = 0; j < n; j++) {
            if (matrizAdj[0][j] != 0) {
                grau++;
            }
        }

        for (int i = 1; i < n; i++) {
            int grauAtual = 0;
            for (int j = 0; j < n; j++) {
                if (matrizAdj[i][j] != 0) {
                    grauAtual++;
                }
            }
            if (grauAtual != grau) {
                return false;
            }
        }
        return true;
    }

    public void dijkstra(int[][] matrizAdj, int verticeInicial, List<Vertice> vertices) {

        int tamanho = vertices.size();
        int[] distancias = new int[tamanho];
        boolean[] visitados = new boolean[tamanho];
        int[] predecessores = new int[tamanho];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(predecessores, -1);
        distancias[verticeInicial] = 0;

        for (int i = 0; i < tamanho - 1; i++) {
            int u = encontrarMenorDistancia(distancias, visitados, tamanho);
            visitados[u] = true;

            for (int v = 0; v < tamanho; v++) {
                if (!visitados[v] && matrizAdj[u][v] != 0 && distancias[u] + matrizAdj[u][v] < distancias[v]) {
                    distancias[v] = distancias[u] + matrizAdj[u][v];
                    predecessores[v] = u;
                }
            }
        }

        System.out.println("Menores distâncias:");
        for (int i = 0; i < tamanho; i++) {
            System.out.print(vertices.get(i).getNome() + ": " + distancias[i]);
            System.out.print(" (Caminho: ");
            printCaminho(predecessores, i, vertices);
            System.out.println(")");
        }
    }

    private void printCaminho(int[] predecessores, int destino, List<Vertice> vertices) {
        if (predecessores[destino] == -1) {
            System.out.print(vertices.get(destino).getNome());
            return;
        }
        printCaminho(predecessores, predecessores[destino], vertices);
        System.out.print(" -> " + vertices.get(destino).getNome());
    }

    private int encontrarMenorDistancia(int[] distancias, boolean[] visitados, int tamanho) {
        int min = Integer.MAX_VALUE, indiceMin = -1;

        for (int i = 0; i < tamanho; i++) {
            if (!visitados[i] && distancias[i] < min) {
                min = distancias[i];
                indiceMin = i;
            }
        }
        return indiceMin;
    }

    public void floydWarshall(int[][] matrizAdj, List<Vertice> vertices) {
        int tamanho = vertices.size();
        int[][] distancias = new int[tamanho][tamanho];
        int[][] predecessores = new int[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (i == j) {
                    distancias[i][j] = 0;
                } else if (matrizAdj[i][j] != 0) {
                    distancias[i][j] = matrizAdj[i][j];
                    predecessores[i][j] = i;
                } else {
                    distancias[i][j] = Integer.MAX_VALUE;
                    predecessores[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < tamanho; k++) {
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho; j++) {
                    if (distancias[i][k] != Integer.MAX_VALUE && distancias[k][j] != Integer.MAX_VALUE
                            && distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        predecessores[i][j] = predecessores[k][j];
                    }
                }
            }
        }

        System.out.println("Menores distâncias entre todos os pares de vértices:");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (distancias[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(distancias[i][j] + " ");
                }
            }
            System.out.println();
        }

        System.out.println("Caminhos entre os vértices:");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (i != j && distancias[i][j] != Integer.MAX_VALUE) {
                    System.out.print(vertices.get(i).getNome() + " -> " + vertices.get(j).getNome() + ": ");
                    printCaminho(predecessores, i, j, vertices);
                    System.out.println();
                }
            }
        }
    }

    private void printCaminho(int[][] predecessores, int origem, int destino, List<Vertice> vertices) {
        if (predecessores[origem][destino] == -1) {
            System.out.print("Sem caminho");
            return;
        }
        if (origem != destino) {
            printCaminho(predecessores, origem, predecessores[origem][destino], vertices);
        }
        System.out.print(vertices.get(destino).getNome() + " ");
    }

    public void bfs(int[][] matrizAdj, int verticeInicial, List<Vertice> vertices) {

        int tamanhoAtual = vertices.size();
        boolean[] visitados = new boolean[tamanhoAtual];
        List<String> resultado = new ArrayList<>();

        Queue<Integer> fila = new LinkedList<>();
        fila.add(verticeInicial);
        visitados[verticeInicial] = true;

        while (!fila.isEmpty()) {
            int verticeAtual = fila.poll();
            resultado.add(vertices.get(verticeAtual).getNome()); // Adiciona o nome do vértice

            for (int i = 0; i < tamanhoAtual; i++) {
                if (matrizAdj[verticeAtual][i] == 1 && !visitados[i]) {
                    fila.add(i);
                    visitados[i] = true;
                }
            }
        }

        System.out.println("BFS: " + resultado);
    }

    public boolean isEuleriano(int[][] matrizAdj, List<Vertice> vertices) {
        // Verifica se o grafo é conexo
        Funções funcoes = new Funções();
        if (!funcoes.isConexo(matrizAdj)) {
            return false; // Se não é conexo, não pode ser euleriano
        }

        // Verifica se todos os vértices têm grau par
        for (int i = 0; i < vertices.size(); i++) {
            int grau = 0;
            for (int j = 0; j < matrizAdj[i].length; j++) {
                if (matrizAdj[i][j] == 1) {
                    grau++;
                }
            }
            if (grau % 2 != 0) { // Se o grau for ímpar
                return false;
            }
        }

        return true; // Se é conexo e todos os vértices têm grau par, é euleriano
    }

}
