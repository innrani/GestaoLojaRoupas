import java.util.ArrayList;
import java.util.List;

public class Cliente {
    String nome;
    List<Venda> historicoCompras;
    private int pontosFidelidade;
    public String compra;

    public Cliente(String nome) {
        this.nome = nome;
        this.historicoCompras = new ArrayList<>();
        this.pontosFidelidade = 0;
    }

    public void adicionarPontosFidelidade(int pontos) {
        this.pontosFidelidade += pontos;
    }

    public int getPontosFidelidade() {
        return pontosFidelidade;
    }

    public void resetarPontosFidelidade() {
        this.pontosFidelidade = 0;
    }

    public void adicionarCompra(Venda venda) {
        historicoCompras.add(venda);
    }
    
    public String getNome() {
        return nome;
    }

    public List<Venda> getHistoricoCompras() {
        return historicoCompras;
    
    }

    
    }
