import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Loja loja = new Loja();

        // Cadastra produtos
        Produto camisa = new Produto("Camisa", "Vestuário", 50.0, "M", "Azul", 10);
        Produto calca = new Produto("Calça", "Vestuário", 100.0, "42", "Preto", 5);
        loja.cadastrarProduto(camisa);
        loja.cadastrarProduto(calca);

        // Cadastra clientes
        Cliente cliente1 = new Cliente("Joshua Hong");
        loja.cadastrarCliente(cliente1);

        Cliente cliente2 = new Cliente("Hansol Vernon Chwe"); // Nome entre aspas
        loja.cadastrarCliente(cliente2);

        // Cria lista de produtos para a venda
        List<Produto> produtosVenda1 = new ArrayList<>();
        produtosVenda1.add(camisa);
        produtosVenda1.add(calca);

    
        Vendedor vendedor1 = loja.getVendedores().get(0); // Pega o primeiro vendedor da lista

        // Registra venda com o vendedor
        loja.registrarVenda(cliente1, vendedor1, produtosVenda1, "Pix");
        loja.registrarVenda(cliente2, vendedor1, produtosVenda1, "Cartão de crédito");

        // Exibe histórico de compras do cliente
        loja.exibirHistoricoCliente(cliente1);
        loja.exibirHistoricoCliente(cliente2); 

        // Exibe histórico de vendas da loja
        loja.exibirHistoricoVendas();
    }
}