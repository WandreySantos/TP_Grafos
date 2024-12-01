import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Grafo {
    private List<Vertice> vertices;
    private List<Aresta> arestas;
    public int tamanho;
    private boolean direcionado = false;
    private int[][] matrizAdj;

    Scanner scan = new Scanner(System.in);

    public Grafo(int tamanho) {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.tamanho = tamanho;

        // Inicializa a matriz de adjacência
        this.matrizAdj = new int[tamanho][tamanho];

        preencherMatriz();
        mostrarMatrizAdj();
        criandoGrafo();
    }

    private void preencherMatriz() {
        System.out.println("Preencha a matriz de adjacência (0 para sem aresta, 1 para com aresta):");
        for (int i = 0; i < tamanho; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                int valor = -1;
                while (valor != 0 && valor != 1) {
                    System.out.print("Matriz[" + i + "][" + j + "]: ");
                    valor = scan.nextInt();
                    if (valor != 0 && valor != 1) {
                        System.out.println("Valor inválido! Por favor, insira 0 ou 1.");
                    }
                }
                // Preenche a diagonal superior
                matrizAdj[i][j] = valor;
                // Espelha na diagonal inferior
                matrizAdj[j][i] = valor;
            }
        }
    }

    private void criandoGrafo() {
        // A matriz de adjacência já foi preenchida pela função `preenchendoMatriz`
        for (int i = 0; i < tamanho; i++) {
            inserirVertice("v" + (i + 1));
        }
        for (int i = 0; i < tamanho; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                if (matrizAdj[i][j] == 1) {
                    inserirAresta(vertices.get(i), vertices.get(j));
                }
            }
        }
    }

    public Grafo inserirVertice(String nome) {
        Vertice vertice = new Vertice(nome);
        vertices.add(vertice);
        return this;
    }

    public Grafo removerVertice(int index) {
        vertices.remove(index);
        return this;
    }

    public void inserirAresta(Vertice origem, Vertice destino) {
        for (Aresta aresta : arestas) {
            if (aresta.getOrigem().equals(origem) && aresta.getDestino().equals(destino)) {
                System.out.println("Aresta " + origem + " -> " + destino + " já existe.");
            }
        }
        String nome = origem.getNome() + " -> " + destino.getNome();
        Aresta aresta = new Aresta(origem, destino, nome);
        arestas.add(aresta);
        System.out.println("Aresta " + origem + " -> " + destino + " inserida.");
    }

    public Grafo removerAresta(Vertice origem, Vertice destino) {
        Aresta aresta = new Aresta(origem, destino);
        if (arestas.remove(aresta)) {
            int i = vertices.indexOf(origem);
            int j = vertices.indexOf(destino);
            matrizAdj[i][j] = 0;
            if (!direcionado) {
                matrizAdj[j][i] = 0;
            }
            System.out.println("Aresta " + origem.getNome() + " -> " + destino.getNome() + " removida.");
        } else {
            System.out.println("Aresta " + origem.getNome() + " -> " + destino.getNome() + " não encontrada.");
        }
        return this;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void criarGrafoComMatriz() {
        tamanho = matrizAdj.length;

        for (int i = 0; i < tamanho; i++) {
            inserirVertice("v" + (i + 1));
        }

        for (int i = 0; i < tamanho; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                if (matrizAdj[i][j] == 1) {
                    inserirAresta(getVertices().get(i), getVertices().get(j));
                }
            }
        }
    }

    public void mostrarMatrizAdj() {
        System.out.println("\nMatriz de Adjacência:");
        System.out.print("    ");
        for (int i = 0; i < tamanho; i++) {
            System.out.print("v" + (i + 1) + "     ");
        }
        System.out.println();

        for (int i = 0; i < tamanho; i++) {
            System.out.print("v" + (i + 1) + "  ");
            for (int j = 0; j < tamanho; j++) {
                System.out.print("[" + matrizAdj[i][j] + "]" + "    ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Arestas do Grafo:\n");
        for (Aresta aresta : arestas) {
            sb.append(aresta).append("\n");
        }
        sb.append("\nMatriz de Adjacência:\n");
        sb.append("    ");
        for (int i = 0; i < tamanho; i++) {
            sb.append("v").append(i + 1).append("     ");
        }
        sb.append("\n");
        for (int i = 0; i < tamanho; i++) {
            sb.append("v").append(i + 1).append("  ");
            for (int j = 0; j < tamanho; j++) {
                sb.append("[").append(matrizAdj[i][j]).append("]    ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Boolean isAdjacente(int v1, int v2) {

        return this.matrizAdj[v1][v2] == 1;
    }

    public void getVizinhos(int v) {

        List<Integer> vizinhos = new ArrayList<>();
        for (int i = 0; i < this.matrizAdj.length; i++) {
            if (this.matrizAdj[v][i] == 1) {
                vizinhos.add(i);
            }
        }

        System.out.println("Vizinhos do vértice " + v + ": " + vizinhos);
    }
}
