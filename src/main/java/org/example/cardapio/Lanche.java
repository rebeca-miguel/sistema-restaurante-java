package org.example.cardapio;

public class Lanche implements ItemCardapio{

    String nome;
    double preco;
    private boolean queijo = true;
    private boolean alface = true;
    private boolean tomate = true;
    private boolean cebola = true;

    public Lanche(String nome, double preco) {
        setNome(nome);
        setPreco(preco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw  new IllegalArgumentException("O nome do lanche não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public double getPreco() {
        return preco;
    }

    public  void setPreco(double preco) {
        if (preco < 0 || Double.isNaN(preco) || Double.isInfinite(preco)) {
            throw new IllegalArgumentException("O preço não pode ser negativo ou inválido.");
        }
        this.preco = preco;
    }

    public boolean isQueijo() {
        return queijo;
    }

    public void setQueijo(boolean queijo) {
        this.queijo = queijo;
    }

    public boolean isAlface() {
        return alface;
    }

    public void setAlface(boolean alface) {
        this.alface = alface;
    }

    public boolean isTomate() {
        return tomate;
    }

    public void setTomate(boolean tomate) {
        this.tomate = tomate;
    }

    public boolean isCebola() {
        return cebola;
    }

    public void setCebola(boolean cebola) {
        this.cebola = cebola;
    }

    @Override
    public String getDescricao() {
        StringBuilder descricao = new StringBuilder(nome);
        if (!queijo) descricao.append(" sem queijo");
        if (!alface) descricao.append(" sem alface");
        if (!tomate) descricao.append(" sem tomate");
        if (!cebola) descricao.append(" sem cebola");

        return descricao.toString();
    }


}
