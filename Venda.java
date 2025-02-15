import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Venda {
    private Cliente cliente;
    private Vendedor vendedor;
    private List<Produto> produtos;
    private String metodoPagamento;
    private LocalDateTime dataHora;
    private double totalComDesconto;

    public Venda(Cliente cliente, Vendedor vendedor, List<Produto> produtos, String metodoPagamento) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.produtos = produtos;
        this.metodoPagamento = metodoPagamento;
        this.dataHora = LocalDateTime.now();
        
    }

    public double calcularTotal() {
        return produtos.stream().mapToDouble(Produto::getPreco).sum();
    }

    public void aplicarDesconto(double desconto) {
        this.totalComDesconto = calcularTotal() - desconto;
        System.out.println("Desconto de R$" + desconto + " aplicado. Novo total: R$" + totalComDesconto);
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public String getDataHora() {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return dataHora.format(formatter);
    }

    public String getMetodoPagamento(){
        return metodoPagamento;
    }

    public String getVendedor(){
        return vendedor;
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void imprimirRecibo() {
        System.out.println("--- Recibo de Venda ---");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Vendedor: " + vendedor.getNome());
        System.out.println("Data: " + dataHora);
        System.out.println("Produtos:");
        for (Produto produto : produtos) {
            System.out.println(" - " + produto.getNome() + " (" + produto.getCategoria() + "): R$" + produto.getPreco());
        }
        System.out.println("Método de Pagamento: " + metodoPagamento);
        System.out.println("Total: R$" + calcularTotal());
        System.out.println("Total com desconto: R$" + totalComDesconto);
    }
}