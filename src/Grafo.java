import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Vertice> vertices;
    private List<Aresta> arestas;
    public int tamanho;

    public Grafo(){
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.tamanho=vertices.size();
    }
    public void inserirVertice(String nome){
        Vertice vertice  = new Vertice(nome);
        vertices.add(vertice);
    }
    public void removVertice(int index){
        vertices.remove(index);
    }

    public void inserirAresta(Vertice origem, Vertice destino) {
        String nome = origem.getNome()+"-> "+destino.getNome();
        for (Aresta aresta : arestas) {
            if (aresta.getOrigem().equals(origem) && aresta.getDestino().equals(destino)) {
                System.out.println("Aresta " + origem + " -> " + destino + " já existe.");
                return;
            }
        }

        Aresta aresta = new Aresta(origem, destino, nome);
        arestas.add(aresta);
        System.out.println("Aresta " + origem + " -> " + destino + " inserida.");
    }
    
    public void removerAresta(Vertice origem, Vertice destino){
        Aresta aresta = new Aresta(origem, destino);
        if (arestas.remove(aresta)) {
            System.out.println("Aresta " + origem + " -> " + destino + " removida.");
        } else {
            System.out.println("Aresta " + origem + " -> " + destino + " não encontrada.");
        }
    }

    public List<Vertice> getVertices() {
        return vertices;
    }
    public List<Aresta> getArestas() {
        return arestas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vértices:\n");
        for (Vertice vertice : vertices) {
            sb.append(vertice.getNome()).append("\n");
        }
        sb.append("\nArestas:\n");
        for (Aresta aresta : arestas) {
            sb.append(aresta.getOrigem().getNome())
            .append(" -> ")
            .append(aresta.getDestino().getNome());

            if (aresta.getPeso() != 0) {
                sb.append(" [Peso: ").append(aresta.getPeso()).append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
