import java.util.ArrayList;
import java.util.List;

public class Loja {

    private final List<Vendedor> vendedores;
    private final List<Produto> produtos;
    private final List<Cliente> clientes;
    private final List<Venda> vendas;

    // Construtor
    public Loja() {
        this.vendedores = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.vendas = new ArrayList<>();
        inicializarVendedores();
    }

    // Método para inicializar vendedores fixos
    private void inicializarVendedores() {
        vendedores.add(new Vendedor("Hoshi"));
        vendedores.add(new Vendedor("Woozi"));
        vendedores.add(new Vendedor("Xiaojun"));
        vendedores.add(new Vendedor("Dokyeom"));
        vendedores.add(new Vendedor("Chan"));
    }

    // Métodos de cadastro
    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // Método para registrar uma venda
    public Venda registrarVenda(Cliente cliente, Vendedor vendedor, List<Produto> produtos, String metodoPagamento) {
        if (!verificarEstoque(produtos)) {
            return null;
        }
        Venda venda = new Venda(cliente, vendedor, produtos, metodoPagamento);
        atualizarEstoque(produtos);
        registrarNoHistorico(cliente, vendedor, venda);
        return venda;
    }

    // Métodos para exibir históricos
    public void exibirHistoricoCliente(Cliente cliente) {
        System.out.println("\n--- Histórico de Compras de " + cliente.getNome() + " ---");
        cliente.getHistoricoCompras().forEach(Venda::imprimirRecibo);
    }

    public void exibirHistoricoVendas() {
        System.out.println("\n--- Histórico de Vendas da Loja ---");
        vendas.forEach(Venda::imprimirRecibo);
    }

    // Métodos auxiliares
    private boolean verificarEstoque(List<Produto> produtos) {
        for (Produto produto : produtos) {
            if (produto.getQuantidadeEstoque() <= 0) {
                System.out.println("Erro: Produto " + produto.getNome() + " fora de estoque.");
                return false;
            }
        }
        return true;
    }

    private void atualizarEstoque(List<Produto> produtos) {
        for (Produto produto : produtos) {
            produto.reduzirEstoque();
        }
    }

    private void registrarNoHistorico(Cliente cliente, Vendedor vendedor, Venda venda) {
        vendedor.adicionarVenda(venda);
        cliente.adicionarCompra(venda);
        vendas.add(venda);
    }

    // Getters
    public List<Vendedor> getVendedores() {
        return new ArrayList<>(vendedores);
    }

    public List<Produto> getProdutos() {
        return new ArrayList<>(produtos);
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }

    public List<Venda> getVendas() {
        return new ArrayList<>(vendas);
    }
}