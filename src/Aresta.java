public class Aresta {
    private Vertice origem;
    private Vertice destino;
    private int peso;
    private String nome;

    public Aresta(Vertice origem, Vertice destino) {
        this.origem = origem;
        this.destino = destino;
    }

    public Aresta(Vertice origem, Vertice destino, String nome) {
        this.origem = origem;
        this.destino = destino;
        this.nome = nome;
    }

    public Aresta(Vertice origem, Vertice destino, int peso, String nome) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Aresta aresta = (Aresta) obj;

        // Comparando vértices de origem e destino
        return (origem.equals(aresta.origem) && destino.equals(aresta.destino)) ||
                (origem.equals(aresta.destino) && destino.equals(aresta.origem)); // Para grafos não direcionados
    }

    @Override
    public String toString() {
        return origem + " -> " + destino + " (peso: " + peso + ")";
    }
}
