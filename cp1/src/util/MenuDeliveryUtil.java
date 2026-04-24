package util;

import java.util.Scanner;

public class MenuDeliveryUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirCabecalho() {
        System.out.println("======================================");
        System.out.println("        SISTEMA DE DELIVERY");
        System.out.println("======================================");
    }

    public static void exibirMenu() {
        System.out.println("1 - Criar novo pedido");
        System.out.println("2 - Adicionar item ao pedido");
        System.out.println("3 - Atribuir entregador");
        System.out.println("4 - Finalizar pedido");
        System.out.println("5 - Exibir resumo do pedido");
        System.out.println("0 - Sair");
        System.out.println("--------------------------------------");
    }

    public static int lerOpcao() {
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        scanner.nextLine(); // limpar buffer
        return scanner.nextLine();
    }

    public static int lerInt(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextInt();
    }

    public static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextDouble();
    }

    public static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }
}