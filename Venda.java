import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Venda {
    private final Cliente cliente;
    private final Vendedor vendedor;
    private final List<Produto> produtos;
    private final String metodoPagamento;
    private final LocalDateTime dataHora;
    private double totalComDesconto;

    public Venda(Cliente cliente, Vendedor vendedor, List<Produto> produtos, String metodoPagamento) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.produtos = produtos;
        this.metodoPagamento = metodoPagamento;
        this.dataHora = LocalDateTime.now(); // Captura a data e hora atuais
        this.totalComDesconto = calcularTotal(); // Inicializa o total com desconto como o total da venda
    }

    public void inicializar() {
        processarPontosFidelidade(); // Processa os pontos de fidelidade ao inicializar a venda
    }

    public void processarPontosFidelidade() {
        double total = calcularTotal();
        int pontosGanhos = (int)(total / 10); // 1 ponto a cada R$10 gastos
        cliente.adicionarPontosFidelidade(pontosGanhos); // Adiciona pontos ao cliente
        System.out.println("Pontos de fidelidade ganhos nesta compra: " + pontosGanhos);
    }

    public double calcularTotal() {
        return produtos.stream().mapToDouble(Produto::getPreco).sum(); // Soma os preços dos produtos
    }

    public void aplicarDesconto(double desconto) {
        this.totalComDesconto = calcularTotal() - desconto; // Aplica o desconto ao total
        System.out.println("Desconto de R$" + desconto + " aplicado. Novo total: R$" + totalComDesconto);
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public String getDataHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Formata a data e hora
        return dataHora.format(formatter);
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void imprimirRecibo() {
        System.out.println("--- Recibo de Venda ---");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Vendedor: " + vendedor.getNome());
        System.out.println("Data: " + getDataHora()); // Usa a data formatada
        System.out.println("Produtos:");
        for (Produto produto : produtos) {
            System.out.println(" - " + produto.getNome() + " (" + produto.getCategoria() + "): R$" + produto.getPreco());
        }
        System.out.println("Método de Pagamento: " + metodoPagamento);
        System.out.println("Total: R$" + calcularTotal());
        System.out.println("Total com desconto: R$" + totalComDesconto);
    }
}