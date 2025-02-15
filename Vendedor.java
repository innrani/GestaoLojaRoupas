import java.util.ArrayList;
import java.util.List;


public class Vendedor {
    private final String nome;
    private List<Venda> vendas;

    public Vendedor(String nome) {
        this.nome = nome;
        this.vendas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public void adicionarVenda(Venda venda) {
        vendas.add(venda);
    }
    

    public void exibirHistoricoVendas() {
        System.out.println("\n--- Histórico de Vendas de " + nome + " ---");
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda venda : vendas) {
                System.out.println("Cliente: " + venda.getCliente().getNome() + 
                                   " | Total: R$" + venda +
                                   " | Data: " + venda.getDataHora());
            }
            
        }
    }

}
