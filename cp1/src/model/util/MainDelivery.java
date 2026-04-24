package model.util;

import Service.ClienteService;
import Service.EntregadorService;
import Service.PedidoService;
import model.Cliente;
import model.Entregador;
import model.Pedido;
import model.Produto;
import util.MenuDeliveryUtil;

public class MainDelivery {

    public static void main(String[] args) {

        ClienteService clienteService = new ClienteService();
        PedidoService pedidoService = new PedidoService();
        EntregadorService entregadorService = new EntregadorService();

        Pedido pedido = null;
        Cliente cliente = null;

        boolean continuar = true;

        while (continuar) {
            MenuDeliveryUtil.exibirCabecalho();
            MenuDeliveryUtil.exibirMenu();

            int opcao = MenuDeliveryUtil.lerOpcao();

            switch (opcao) {

                case 1:
                    // Se a  classe Cliente tiver um construtor vazio.
                    cliente = new Cliente();
                    cliente.setNome(
                            MenuDeliveryUtil.lerString("Nome do cliente: ")
                    );
                    // ... (outros setters se houver)
                    cliente.setEndereco(
                            MenuDeliveryUtil.lerString("Endereço: ")
                    );

                    pedido = clienteService.criarPedido(cliente);
                    System.out.println("Pedido criado com sucesso!\n");
                    
                    //  Chamando o exibirDetalhes do Cliente
                    cliente.exibirDetalhes();
                    
                    MenuDeliveryUtil.pausar();
                    break;

                case 2:
                    if (pedido == null) {
                        System.out.println("Crie um pedido primeiro!");
                        MenuDeliveryUtil.pausar();
                        break;
                    }

                    String nomeProduto =
                            MenuDeliveryUtil.lerString("Nome do produto: ");
                    double preco =
                            MenuDeliveryUtil.lerDouble("Preço: ");
                    int qtd =
                            MenuDeliveryUtil.lerInt("Quantidade: ");

                    Produto produto = new Produto(nomeProduto, preco);
                    clienteService.adicionarItem(pedido, produto, qtd);

                    System.out.println("Item adicionado!");
                    MenuDeliveryUtil.pausar();
                    break;

                case 3:
                    if (pedido == null) {
                        System.out.println("Crie um pedido primeiro!");
                        MenuDeliveryUtil.pausar();
                        break;
                    }

                    Entregador entregador = new Entregador();
                    entregador.setNome(
                            MenuDeliveryUtil.lerString("Nome do entregador: ")
                    );
                    entregador.setVeiculo(
                            MenuDeliveryUtil.lerString("Veículo: ")
                    );
                    entregador.setStatus("DISPONÍVEL"); // Garantindo o status inicial

                    pedidoService.atribuirEntregador(pedido, entregador);
                    System.out.println("Entregador atribuído com sucesso!\n");
                    
                    //  Chamando o exibirDetalhes do Entregador
                    entregador.exibirDetalhes();
                    
                    MenuDeliveryUtil.pausar();
                    break;

                case 4:
                    if (pedido == null) {
                        System.out.println("Crie um pedido primeiro!");
                        MenuDeliveryUtil.pausar();
                        break;
                    }

                    pedidoService.finalizarPedido(pedido);
                    System.out.println("Pedido finalizado!");
                    MenuDeliveryUtil.pausar();
                    break;

                case 5:
                    if (pedido == null) {
                        System.out.println("Nenhum pedido criado!");
                    } else {
                        pedidoService.exibirResumo(pedido);
                    }
                    MenuDeliveryUtil.pausar();
                    break;

                case 0:
                    continuar = false;
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    MenuDeliveryUtil.pausar();
            }
        }
    }
}