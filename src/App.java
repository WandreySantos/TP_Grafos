import java.util.List;
import java.util.Scanner;

public class App {
    public static void menu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1 - Arestas");
        System.out.println("2 - Ponderar arestas ");
        System.out.println("3 -  ");
        System.out.println("9 - Ver grafo");
        System.out.println("0 - Sair");
        System.out.println("==================");
    }

    public static void addRemov(Grafo grafo, Scanner scan) {
        System.out.println("1- Adicionar\n2- Remover");
        int op = scan.nextInt();
        if (op == 1) {
            System.out.println("Selecione 2 vértices para adicionar uma aresta:");
            System.out.println("Vértices disponíveis: ");
            for (int i = 0; i < grafo.getVertices().size(); i++) {
                System.out.println(i + ": " + grafo.getVertices().get(i).getNome());
            }
            System.out.print(": ");
            int index1 = scan.nextInt();
            System.out.print(": ");
            int index2 = scan.nextInt();

            if (index1 >= 0 && index1 < grafo.getVertices().size() && index2 >= 0
                    && index2 < grafo.getVertices().size()) {
                Vertice origem = grafo.getVertices().get(index1);
                Vertice destino = grafo.getVertices().get(index2);
                grafo.inserirAresta(origem, destino);
            } else {
                System.out.println("Índices inválidos. Tente novamente.");
            }
        } else if (op == 2) {
            System.out.println("Qual aresta irá remover?");
            for (int i = 0; i < grafo.getArestas().size(); i++) {
                System.out.println(i + ": " + grafo.getArestas().get(i).getNome());
            }
            System.out.print("Informe o índice da aresta a ser removida: ");
            int index = scan.nextInt();

            if (index >= 0 && index < grafo.getArestas().size()) {
                Aresta aresta = grafo.getArestas().get(index);
                grafo.removerAresta(aresta.getOrigem(), aresta.getDestino());
            } else {
                System.out.println("Índice inválido. Aresta não encontrada.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    public static Grafo ponderarArestas(Grafo grafo, Scanner scan) {
        List<Aresta> arestas = grafo.getArestas();
        System.out.println(arestas);
        int i = 0;
        for (Aresta aresta : arestas) {
            System.out.println("Qual o peso da aresta " + aresta.getNome() + "?");
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

        System.out.println("Tamanho do grafo: ");
        int tamanho = scan.nextInt();

        Grafo grafo = new Grafo(tamanho);

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
                    ponderarArestas(grafo, scan);
                    break;
                case 3:

                    break;

                case 9:
                    System.out.println(grafo.toString());
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
