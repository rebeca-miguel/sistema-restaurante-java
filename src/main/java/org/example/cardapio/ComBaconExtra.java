package org.example.cardapio;

public class ComBaconExtra implements ItemCardapio{
    private final ItemCardapio itemEmbrulhado;

    public ComBaconExtra(ItemCardapio item) {
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
