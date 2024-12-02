import java.util.List;

public class Vertice {
    private String nome;
    private List<Aresta> arestas;
    
    public Vertice(String nome) {
        this.nome = nome;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }
    public String getNome() {
        return nome;
    }
    public List<Aresta> getArestas() {
        return arestas;
    }

    public int getGrau(Vertice vertice){
        return arestas.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Vertice vertice = (Vertice) obj;
        return nome.equals(vertice.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public String toString() {
        return nome;
    }

}
