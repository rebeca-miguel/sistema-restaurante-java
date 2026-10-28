package org.example.app;

import org.example.cardapio.ItemCardapio;
import org.example.cardapio.Lanche;
import org.example.pedido.Pedido;

import java.util.Scanner;

public class MenuConsole {
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        System.out.println("\n=== RESTAURANTE ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("4 - Modificar ou cancelar pedido");
        System.out.println("0 - sair");
    }

    public int lerInteiro(String mensagem) {
        while (true) {
            System.out.println(mensagem);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um inteiro.");
            }
        }
    }

    private boolean lerSimNao(String mensagem) {
        while (true) {
            System.out.println(mensagem + " (s/n): ");
            String resposta = scanner.nextLine().trim();
            if (resposta.equalsIgnoreCase("s")){
                return true;
            }
            if (resposta.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("Digite s para sim ou n para não.");

        }
    }

    public void registrarPedido(RestauranteFacade sistema) {
        System.out.println("Nome do cliente: ");
        String nome = scanner.nextLine();

        Pedido pedido;

        try {
            pedido = sistema.criarPedido(nome);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        System.out.println("Pedidod criado. Número: " + pedido.getNumero());

        boolean continuar = true;

        while (continuar) {
            int opcaoLanche = lerInteiro(
                    "\nEscolha o lanche: 1-X-Burguer (R$16), 2-X-Salada (R$18), 3-X-Bacon (R$22): ");
            if (opcaoLanche < 1 || opcaoLanche > 3) {
                System.out.println("Opção de lanche inválida.");
                continue;
            }

            Lanche lanche = sistema.criarLanche(opcaoLanche);
            personalizarIngredientes(lanche);

            boolean cheddar = lerSimNao(("Adicionar cheddar por R$ 4,00?"));
            boolean baconExtra = lerSimNao("Adicionar bacon extra por R4 6,00?");
            ItemCardapio item =
                    sistema.personalizarAdicionais(lanche, cheddar,baconExtra);
            pedido.adicionarItem(item);

            System.out.println("Adicionado: " + item.getDescricao() + " -" + formatarPreco(item.getPreco()));
            continuar = lerSimNao("Deseja adicionar outro lanche?");

        }

        System.out.println("Pedido #" + pedido.getNumero() + "registrado.");
        exibirPedido(pedido);
    }

    private void personalizarIngredientes(Lanche lanche) {
        if (!lerSimNao("Manter queijo?")) {
            lanche.setQueijo(false);
        }
        if (!lerSimNao("Manter alface?")) {
            lanche.setAlface(false);
        }
        if (!lerSimNao("Manter tomate?")) {
            lanche.setTomate(false);
        }
        if (!lerSimNao("Manter cebola?")) {
            lanche.setCebola(false);
        }
    }

    public void listarPedidos(RestauranteFacade sistema) {
        if (sistema.listarPedidos().isEmpty()) {
            System.out.println("Ainda não há pedidos.");
            return;
        }

        for (Pedido pedido : sistema.listarPedidos()) {
            exibirPedido(pedido);
        }
    }

    public void exibirPedido(Pedido pedido) {
        System.out.println("\nPedido #" + pedido.getNumero() + " - " + pedido.getNomeCliente() + (pedido.isCancelado()? "[CANCELADO]" : ""));
        if (pedido.getItens().isEmpty()) {
            System.out.println(" Nenhum item.");
        } else {
            int numeroItem = 1;

            for (ItemCardapio item : pedido.getItens()) {
                System.out.println(" " + numeroItem + ", " + item.getDescricao() + " - " + formatarPreco(item.getPreco()));

                numeroItem++;
            }
        }
        System.out.println(" Total: " + formatarPreco(pedido.calcularTotal()));
    }

    public void modificarPedido(RestauranteFacade sistema) {
        int numero = lerInteiro("Digite o número do pedido: ");
        Pedido pedido = sistema.buscarPedido(numero);
        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        if (pedido.isCancelado()) {
            System.out.println("Esse pedido já foi cancelado.");
            return;
        }

        System.out.println("1 - Adicionar item");
        System.out.println("2 - Remover item");
        System.out.println("3 - Cancelar pedido");

        int opcao = lerInteiro("Escolha: ");


        switch (opcao) {
            case 1 -> adicionarItemAoPedido(sistema, pedido);

            case 2 -> removerItemAoPedido(pedido);
            case 3 -> {
                if (lerSimNao("Confirma o cancelamento do pedido?")) {
                    pedido.cancelar();
                    System.out.println("Pedido cancelado.");
                }
            }
            default ->System.out.println("Opção inválida.");
        }
        exibirPedido(pedido);

    }

    private void adicionarItemAoPedido(RestauranteFacade sistema, Pedido pedido) {
        int opcao = lerInteiro("Escolha 1-X-Burguer (R$16), 2-X-Salada (R$18), 3-x-Bacon (R$22);");
        if (opcao < 1 || opcao > 3) {
            System.out.println("Opção de lanche inválida.");
            return;
        }
        Lanche lanche = sistema.criarLanche(opcao);
        personalizarIngredientes(lanche);

        boolean cheddar = lerSimNao("Adicionar cheddar por R$ 4,00?");

        boolean baconExtra = lerSimNao("Adiconar bacon extra por R$ 6,00?");

        ItemCardapio item = sistema.personalizarAdicionais(lanche, cheddar, baconExtra);

        pedido.adicionarItem(item);

        System.out.println("Item adicionado.");
    }

    private void removerItemAoPedido(Pedido pedido) {
        if (pedido.getItens().isEmpty()) {
            System.out.println("O pedido não possui itens.");
            return;
        }

        exibirPedido(pedido);

        int indice = lerInteiro("Número do item quedeseja remover: ");
        if (pedido.removerItem(indice - 1)) {
            System.out.println("Itemm removido.");
        } else {
            System.out.println("Numero de item inválido.");
        }
    }

    public void fecharRestaurante(RestauranteFacade sistema) {
        System.out.println("\n=== FECHAMENTO DO DIA ===");
        System.out.println("Total vendido: " + formatarPreco(sistema.calcularTotalDoDia()));
        System.out.println("Combinações diferentes: " + sistema.listarCombinacoesDiferentes().size());
        for (String descricao : sistema.listarCombinacoesDiferentes()) {
            System.out.println(" - " + descricao);
        }
    }

    private String formatarPreco(double preco) {
        return String.format("R$ %.2f", preco);
    }

    void encerrar() {
        scanner.close();
    }
}
