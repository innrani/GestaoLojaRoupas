import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LojaCLI {
    private final Loja loja;
    private final Scanner scanner;
    private final List<Vendedor> vendedores;

    public LojaCLI(Loja loja) {
        this.loja = loja;
        this.scanner = new Scanner(System.in);
        this.vendedores = new ArrayList<>();
        inicializarVendedores();
    }

    private void inicializarVendedores() {
        vendedores.add(new Vendedor("Hoshi"));
        vendedores.add(new Vendedor("Woozi"));
        vendedores.add(new Vendedor("Xiaojun"));
        vendedores.add(new Vendedor("Dokyeom"));
        vendedores.add(new Vendedor("Chan"));
    }

    public void iniciar() {
        System.out.println("Bem-vindo à Loja!");

        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> cadastrarCliente();
                case 3 -> registrarVenda();
                case 4 -> exibirHistoricoCliente();
                case 5 -> exibirHistoricoVendas();
                case 6 -> exibirClientesMaisFrequentes();
                case 7 -> aplicarDescontoFidelidade();
                case 8 -> exibirEstoqueProduto(); 
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Cadastrar Cliente");
        System.out.println("3. Registrar Venda");
        System.out.println("4. Exibir Histórico de Compras do Cliente");
        System.out.println("5. Exibir Histórico de Vendas da Loja");
        System.out.println("6. Exibir Clientes Mais Frequentes");
        System.out.println("7. Aplicar Desconto de Fidelidade");
        System.out.println("8. Exibir Estoque de um Produto"); 
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Tamanho: ");
        String tamanho = scanner.nextLine();
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        System.out.print("Quantidade em estoque: ");
        int quantidadeEstoque = scanner.nextInt();
        scanner.nextLine();

        Produto produto = new Produto(nome, categoria, preco, tamanho, cor, quantidadeEstoque);
        loja.cadastrarProduto(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private void cadastrarCliente() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();

        Cliente cliente = new Cliente(nome);
        loja.cadastrarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void registrarVenda() {
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = buscarClientePorNome(nomeCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Nome do vendedor: ");
        String nomeVendedor = scanner.nextLine();
        Vendedor vendedor = buscarVendedorPorNome(nomeVendedor);
        if (vendedor == null) {
            System.out.println("Vendedor não encontrado.");
            return;
        }

        List<Produto> produtosVenda = new ArrayList<>();
        while (true) {
            System.out.print("Nome do produto (ou 'fim' para encerrar): ");
            String nomeProduto = scanner.nextLine();
            if (nomeProduto.equals("fim")) {
                break;
            }

            Produto produto = buscarProdutoPorNome(nomeProduto);
            if (produto == null) {
                System.out.println("Produto não encontrado.");
            } else if (produto.getQuantidadeEstoque() <= 0) {
                System.out.println("Produto fora de estoque.");
            } else {
                produto.reduzirEstoque();
                produtosVenda.add(produto);
                System.out.println("Produto adicionado à venda!");
            }
        }

        System.out.print("Método de pagamento (Dinheiro, Cartão de Débito, Cartão de Crédito, Pix): ");
        String metodoPagamento = scanner.nextLine();

        Venda venda = loja.registrarVenda(cliente, vendedor, produtosVenda, metodoPagamento);
        vendedor.adicionarVenda(venda);
        System.out.println("Venda registrada com sucesso para o vendedor " + vendedor.getNome() + "!");

        // Processa os pontos de fidelidade e aplica o desconto, se necessário
        venda.processarPontosFidelidade();
    }

    private void exibirHistoricoCliente() {
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = buscarClientePorNome(nomeCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("\n--- Histórico de Compras de " + cliente.getNome() + " ---");
        for (Venda venda : cliente.getHistoricoCompras()) {
            System.out.println("Data: " + venda.getDataHora());
            System.out.println("Vendedor: " + venda.getVendedor().getNome());
            System.out.println("Produtos:");
            for (Produto produto : venda.getProdutos()) {
                System.out.println(" - " + produto.getNome() + " (" + produto.getCategoria() + ")");
            }
            System.out.println("Método de Pagamento: " + venda.getMetodoPagamento());
            System.out.println("Total: R$" + venda.calcularTotal());
            System.out.println("-----------------------------");
        }
    }

    private void exibirHistoricoVendas() {
        System.out.println("\n--- Histórico de Vendas da Loja ---");
        for (Venda venda : loja.getVendas()) {
            System.out.println("Cliente: " + venda.getCliente().getNome());
            System.out.println("Vendedor: " + venda.getVendedor().getNome());
            System.out.println("Data: " + venda.getDataHora());
            System.out.println("Produtos:");
            for (Produto produto : venda.getProdutos()) {
                System.out.println(" - " + produto.getNome() + " (" + produto.getCategoria() + ")");
            }
            System.out.println("Método de Pagamento: " + venda.getMetodoPagamento());
            System.out.println("Total: R$" + venda.calcularTotal());
            System.out.println("-----------------------------");
        }
    }

    private void exibirClientesMaisFrequentes() {
        System.out.println("\n--- Clientes Mais Frequentes ---");
        List<Cliente> clientesOrdenados = loja.getClientes().stream()
            .sorted((c1, c2) -> Integer.compare(c2.getHistoricoCompras().size(), c1.getHistoricoCompras().size()))
            .collect(Collectors.toList());

        for (Cliente cliente : clientesOrdenados) {
            System.out.println("Cliente: " + cliente.getNome() + 
                              " | Total de Compras: " + cliente.getHistoricoCompras().size());
        }
    }

    private void aplicarDescontoFidelidade() {
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = buscarClientePorNome(nomeCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Pontos atuais: " + cliente.getPontosFidelidade());
        if (cliente.getPontosFidelidade() >= 100) {
            System.out.println("Cliente elegível para desconto de 10% na próxima compra!");
            System.out.println("O desconto será aplicado automaticamente na próxima venda.");
        } else {
            System.out.println("Pontos insuficientes. O cliente precisa de " + (100 - cliente.getPontosFidelidade()) + " pontos para obter um desconto.");
        }
    }

    private void exibirEstoqueProduto() {
        System.out.print("Nome do produto: ");
        String nomeProduto = scanner.nextLine();
        Produto produto = buscarProdutoPorNome(nomeProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
        } else {
            System.out.println("\n--- Estoque do Produto ---");
            System.out.println("Produto: " + produto.getNome());
            System.out.println("Quantidade em estoque: " + produto.getQuantidadeEstoque());
        }
    }

    private Vendedor buscarVendedorPorNome(String nome) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNome().equals(nome)) { 
                return vendedor;
            }
        }
        return null;
    }

    private Cliente buscarClientePorNome(String nome) {
        for (Cliente cliente : loja.getClientes()) {
            if (cliente.getNome().equals(nome)) { 
                return cliente;
            }
        }
        return null;
    }

    private Produto buscarProdutoPorNome(String nome) {
        for (Produto produto : loja.getProdutos()) {
            if (produto.getNome().equals(nome)) {
                return produto;
            }
        }
        return null;
    }
}