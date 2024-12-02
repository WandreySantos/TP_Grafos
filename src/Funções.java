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

    public int[] dijkstra(int[][] matrizAdj, int origem) {
        int n = matrizAdj.length;
        int[] dist = new int[n];
        boolean[] visitados = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[origem] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minDist(dist, visitados);
            visitados[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visitados[v] && matrizAdj[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE && 
                    dist[u] + matrizAdj[u][v] < dist[v]) {
                    dist[v] = dist[u] + matrizAdj[u][v];
                }
            }
        }
        return dist;
    }

    private int minDist(int[] dist, boolean[] visitados) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visitados[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public int[][] floydWarshall(int[][] matrizAdj) {
        int n = matrizAdj.length;
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (matrizAdj[i][j] != 0) {
                    dist[i][j] = matrizAdj[i][j];
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }

    public void bfs(int[][] matrizAdj, int origem) {
        int n = matrizAdj.length;
        boolean[] visitados = new boolean[n];
        Queue<Integer> fila = new LinkedList<>();
        
        visitados[origem] = true;
        fila.add(origem);

        while (!fila.isEmpty()) {
            int v = fila.poll();
            System.out.print(v + " ");
            
            for (int i = 0; i < n; i++) {
                if (matrizAdj[v][i] != 0 && !visitados[i]) {
                    visitados[i] = true;
                    fila.add(i);
                }
            }
        }
    }

    public boolean isEuleriano(int[][] matrizAdj) {
        if (!isConexo(matrizAdj)) {
            return false;
        }

        int n = matrizAdj.length;
        int verticesImpar = 0;

        for (int i = 0; i < n; i++) {
            int grau = 0;
            for (int j = 0; j < n; j++) {
                if (matrizAdj[i][j] != 0) {
                    grau++;
                }
            }
            if (grau % 2 != 0) {
                verticesImpar++;
            }
        }

        return verticesImpar == 0 || verticesImpar == 2;
    }
}
