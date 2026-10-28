package org.example.app;

public class Main {
    public static void main(String[] args) {
        RestauranteFacade sistema = new RestauranteFacade();
        MenuConsole menuConsole = new MenuConsole();
        boolean executando = true;


        while (executando) {
            menuConsole.exibirMenu();
            int opcao = menuConsole.lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> menuConsole.registrarPedido(sistema);
                case 2 -> menuConsole.listarPedidos(sistema);
                case 3 -> menuConsole.fecharRestaurante(sistema);
                case 4 -> menuConsole.modificarPedido(sistema);
                case 0 -> {
                    executando = false;
                    System.out.println("Sistema encerrado.");
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        menuConsole.encerrar();

    }
}