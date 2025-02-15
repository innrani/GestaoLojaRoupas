import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Loja loja = new Loja();

        // Cadastra produtos
        Produto camisa = new Produto("Camisa", "Vestuário", 50.0, "M", "Azul", 10);
        Produto calca = new Produto("Calça", "Vestuário", 100.0, "42", "Preto", 5);
        Produto sapato = new Produto("Sapato", "Calçados", 120.0, "40", "Preto", 8);
        Produto jaqueta = new Produto("Jaqueta", "Vestuário", 200.0, "M", "Preto", 3);

        loja.cadastrarProduto(camisa);
        loja.cadastrarProduto(calca);
        loja.cadastrarProduto(sapato);
        loja.cadastrarProduto(jaqueta);

        // Cadastra clientes
        Cliente cliente1 = new Cliente("Joshua Hong");
        Cliente cliente2 = new Cliente("Hansol Vernon Chwe");
        Cliente cliente3 = new Cliente("Woomyun");
        Cliente cliente4 = new Cliente("Hendery");
        Cliente cliente5 = new Cliente("Kun");

        loja.cadastrarCliente(cliente1);
        loja.cadastrarCliente(cliente2);
        loja.cadastrarCliente(cliente3);
        loja.cadastrarCliente(cliente4);
        loja.cadastrarCliente(cliente5);

        // Cria lista de produtos para a venda
        List<Produto> produtosVenda1 = new ArrayList<>();
        produtosVenda1.add(camisa);
        produtosVenda1.add(calca);

        List<Produto> produtosVenda2 = new ArrayList<>();
        produtosVenda2.add(sapato);
        produtosVenda2.add(jaqueta);

        Vendedor vendedor1 = loja.getVendedores().get(0); // Pega o primeiro vendedor da lista

        // Registra vendas com o vendedor
        loja.registrarVenda(cliente1, vendedor1, produtosVenda1, "Pix");
        loja.registrarVenda(cliente2, vendedor1, produtosVenda1, "Cartão de crédito");
        loja.registrarVenda(cliente3, vendedor1, produtosVenda2, "Boleto");
        loja.registrarVenda(cliente4, vendedor1, produtosVenda2, "Pix");
        loja.registrarVenda(cliente5, vendedor1, produtosVenda1, "Cartão de crédito");

        // Exibe histórico de compras dos clientes
        loja.exibirHistoricoCliente(cliente1);
        loja.exibirHistoricoCliente(cliente2); 
        loja.exibirHistoricoCliente(cliente3);
        loja.exibirHistoricoCliente(cliente4);
        loja.exibirHistoricoCliente(cliente5);

        // Exibe histórico de vendas da loja
        loja.exibirHistoricoVendas();

        LojaCLI lojaCLI = new LojaCLI(loja);
        lojaCLI.iniciar(); 
}

}
 