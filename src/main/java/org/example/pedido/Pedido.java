package org.example.pedido;

import org.example.cardapio.ItemCardapio;
import org.example.cardapio.Lanche;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private final int numero;
    private String nomeCliente;
    private ArrayList<ItemCardapio> itens = new ArrayList<>();
    private boolean cancelado;

    public Pedido(int numero, String nomeCliente) {
        this.numero = numero;
        setNomeCliente(nomeCliente);
    }

    public int getNumero() {
        return numero;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser vazio.");
        }
        this.nomeCliente = nomeCliente.trim();
    }

    public void adicionarItem(ItemCardapio item) {
       if (cancelado) {
           throw new IllegalArgumentException("Não é possível alterar um pedido cancelado.");
       }
       if (item == null) {
           throw new IllegalArgumentException("O item não pode ser nulo.");
       }
       itens.add(item);
    }

    public boolean removerItem(int indice) {
        if (cancelado) {
            throw new IllegalArgumentException("Não é possível alterar um pedido cancelado.");
        } if (indice < 0 || indice >= itens.size()) {
            return false;
        }
        itens.remove(indice);
        return true;
    }

    public List<ItemCardapio> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public  double calcularTotal() {
        if (cancelado) return 0.0;
        double total = 0.0;
        for (ItemCardapio item : itens) {
            total += item.getPreco();
        }
        return total;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void cancelado(){
        cancelado = true;
        itens.clear();
    }

    public void cancelar() {
    }
}
