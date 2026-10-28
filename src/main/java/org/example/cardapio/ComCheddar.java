package org.example.cardapio;

public class ComCheddar implements ItemCardapio{
    private final ItemCardapio itemEmbrulhado;

    public ComCheddar(ItemCardapio item) {
        if (item == null) {
            throw  new IllegalArgumentException("O item não pode ser nulo.");
        }
        this.itemEmbrulhado = item;
    }

    @Override
    public String getDescricao() {
        return itemEmbrulhado.getDescricao() + " + cheddar";
    }

    @Override
    public double getPreco() {
        return itemEmbrulhado.getPreco() + 4.00;
    }
}
