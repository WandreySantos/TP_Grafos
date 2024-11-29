import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scan;
    public static void menu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1 - Arestas");
        System.out.println("2 - Ponderar arestas ");
        System.out.println("3 -  ");
        System.out.println("9 - Ver matriz");
        System.out.println("0 - Sair");
        System.out.println("==================");
    }

    public static void mostrarMatrizAdj(int[][] matrizAdj) {
        System.out.println("\nMatriz de Adjacência:");
        int tamanho = matrizAdj.length;
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

    public static void preencherMatrizAdj(int[][] matrizAdj, Scanner scan, boolean direcionado) {
        int tamanho = matrizAdj.length;
        System.out.println("Preencha a matriz de adjacência (0 para sem aresta e 1 para com aresta):");

        for (int i = 0; i < tamanho; i++) {
            for (int j = i; j < tamanho; j++) {
                if (i == j && !direcionado) {
                    continue;
                }

                int valor = -1;
                // Garantir que o valor seja 0 ou 1
                while (valor != 0 && valor != 1) {
                    System.out.print("Matriz[" + i + "][" + j + "]: ");
                    valor = scan.nextInt();
                    if (valor != 0 && valor != 1) {
                        System.out.println("Valor inválido! Por favor, insira 0 ou 1.");
                    }
                }
                matrizAdj[i][j] = valor;

                if (!direcionado && i != j) {
                    matrizAdj[j][i] = matrizAdj[i][j];
                }
            }
        }
    }
    
    public static void addRemov(Grafo grafo, Scanner scan){
        System.out.println("1- Adicionar\n2- Remover");
        int op = scan.nextInt();
        if (op == 1) {
            System.out.println("Selecione 2 vértices para adicionar uma aresta:");
            System.out.println("Vértices disponíveis: ");
            for (int i = 0; i < grafo.getVertices().size(); i++) {
                System.out.println(i + ": " + grafo.getVertices().get(i));
            }
            System.out.print(": ");
            int index1 = scan.nextInt();
            System.out.print(": ");
            int index2 = scan.nextInt();
            Vertice origem = grafo.getVertices().get(index1);
            Vertice destino = grafo.getVertices().get(index2);
            grafo.inserirAresta(origem, destino);
        } else {
            System.out.println("Qual aresta irá remover?");
            for (int i = 0; i < grafo.getArestas().size(); i++) {
                System.out.println(i + ": " + grafo.getArestas().get(i));
            }
            System.out.print("Informe o índice da aresta a ser removida: ");
            int index = scan.nextInt();

            if (index >= 0 && index < grafo.getArestas().size()) {
                Aresta aresta = grafo.getArestas().get(index);

                grafo.removerAresta(aresta.getOrigem(), aresta.getDestino());
            } else {
                System.out.println("Índice inválido. Aresta não encontrada.");
            }
        }
    }
    
    public static int[][] crirarMatrizComGrafo(Grafo grafo) {
        List<Vertice> vertices = grafo.getVertices();
        int tamanho = vertices.size();
        int[][] matrizAdj = new int[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                matrizAdj[i][j] = 0;
            }
        }
        for (Aresta aresta : grafo.getArestas()) {
            int origem = vertices.indexOf(aresta.getOrigem());
            int destino = vertices.indexOf(aresta.getDestino());
            matrizAdj[origem][destino] = 1;
            matrizAdj[destino][origem] = 1;
        }
        return matrizAdj;
    }
    public static Grafo criarGrafoComMatriz(int[][] matrizAdj, Grafo grafo) {
        int tamanho = matrizAdj.length;

        for (int i = 0; i < tamanho; i++) {
            grafo.inserirVertice("v" + (i + 1));
        }

        for (int i = 0; i < tamanho; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                if (matrizAdj[i][j] == 1) {
                    grafo.inserirAresta(grafo.getVertices().get(i), grafo.getVertices().get(j));
                }
            }
        }
        return grafo;
    }
    
    public static Grafo ponderarArestas(Grafo grafo, Scanner scan){
        List<Aresta> arestas = grafo.getArestas();
        System.out.println(arestas);
        int i = 0;
        for (Aresta aresta : arestas) {
            System.out.println("Qual o peso da aresta " + aresta.getNome()+ "?");
            int peso = scan.nextInt();
            aresta.setPeso(i); // Define o peso como o índice
            arestas.get(i).setPeso(peso);
            i++;
        }
        return grafo;
    }
    public static void main(String[] args) throws Exception {
        boolean saida = true;
        Scanner scan = new Scanner(System.in);
        Grafo grafo = new Grafo();

        System.out.println("Tamanho do grafo: ");
        int tamanho = scan.nextInt();

        int[][] matrizAdj = new int[tamanho][tamanho];
        preencherMatrizAdj(matrizAdj, scan, false);

        grafo = criarGrafoComMatriz(matrizAdj, grafo);

        mostrarMatrizAdj(matrizAdj);
        // mostrarListaAdj();

        // Menu principal
        while (saida) {
            System.out.println("Escolha uma opção: ");
            menu();
            int opção = scan.nextInt();
            switch (opção) {
                case 1:
                    addRemov(grafo, scan);
                    break;
                case 2:
                    ponderarArestas(grafo,scan);
                    break;
                case 3:
                    
                    break;

                case 9:
                    matrizAdj = crirarMatrizComGrafo(grafo);
                    System.out.print(grafo.toString());
                    mostrarMatrizAdj(matrizAdj);
                    break;
                case 0:
                    saida = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
