public class Aresta {
    private Vertice origem;
    private Vertice destino;
    private int peso;

    public Aresta(Vertice origem, Vertice destino){
        this.origem=origem;
        this.destino=destino;
    }
    public Aresta(Vertice origem, Vertice destino, int peso){
        this.origem=origem;
        this.destino=destino;
        this.peso =peso;
    }

    public Vertice getOrigem() {
        return origem;
    }
    public Vertice getDestino() {
        return destino;
    }
    public int getPeso() {
        return peso;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aresta aresta = (Aresta) obj;
        
        // Comparando vértices de origem e destino
        return (origem.equals(aresta.origem) && destino.equals(aresta.destino)) ||
               (origem.equals(aresta.destino) && destino.equals(aresta.origem));  // Para grafos não direcionados
    } 

    @Override
    public String toString() {
        return origem + " -> " + destino + " (peso: " + peso + ")";
    }
}
