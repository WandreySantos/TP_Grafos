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
        for (Aresta aresta : arestas) {
            if (aresta.getOrigem().equals(origem) && aresta.getDestino().equals(destino)) {
                System.out.println("Aresta " + origem + " -> " + destino + " já existe.");
                return;
            }
        }
        Aresta aresta = new Aresta(origem, destino);
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
}
