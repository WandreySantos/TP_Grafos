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
}

  
}
