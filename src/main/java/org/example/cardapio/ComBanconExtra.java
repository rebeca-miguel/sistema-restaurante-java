package org.example.cardapio;

public class ComBanconExtra implements ItemCardapio{
    private final ItemCardapio itemEmbrulhado;

    public ComBanconExtra(ItemCardapio item) {
        if (item == null) {
            throw new IllegalArgumentException("O item não pode ser nullo.");
        }
        this.itemEmbrulhado = item;
    }

    @Override
    public String getDescricao() {
        return itemEmbrulhado.getDescricao() + " + bacon";
    }

    @Override
    public double getPreco() {
        return itemEmbrulhado.getPreco() + 6.00;
    }
}
