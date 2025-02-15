public class Produto {
    private String nome;
    private String categoria;
    private double preco;
    private String tamanho;
    private String cor;
    private int quantidadeEstoque;

    public Produto(String nome, String categoria, double preco, String tamanho, String cor, int quantidadeEstoque) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.tamanho = tamanho;
        this.cor = cor;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getCor() {
        return cor;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void reduzirEstoque() {
        if (quantidadeEstoque > 0) {
            quantidadeEstoque--;
        }
    }
}