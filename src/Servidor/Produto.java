package Servidor;

public class Produto {
    private String nome;
    private int codigo;
    private double preco;
    private int estoque;

    public Produto(String nome, int codigo, double preco, int estoque) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome(){
        return nome;
    }
    public int getCodigo(){
        return codigo;
    }
    public double getPreco(){
        return preco;
    }
    public int getEstoque(){
        return estoque;
    }

    public synchronized boolean comprar(int qtd) {
        if (estoque >= qtd) {
            estoque -= qtd;
            return true;
        }
        return false;
    }

    public synchronized boolean temEstoque(int qtd) {
        return estoque >= qtd;
    }


    @Override
    public String toString() {
        return "Cód: " + codigo + "|" + "Produto:"+ nome + "|" + preco +"R$ "+ "|" + estoque + " DISPONIVEIS";
    }

}
