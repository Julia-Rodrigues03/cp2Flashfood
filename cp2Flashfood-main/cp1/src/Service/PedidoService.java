package Service;

import model.Entregador;
import model.ItemPedido;
import model.Pedido;

import java.util.List;

public class PedidoService {

    private static final double TAXA_ENTREGA = 8.0;

    public double calcularSubtotal(Pedido pedido) {
        double subtotal = 0;

        for (ItemPedido item : pedido.getItens()) {
            subtotal += item.getProduto().getPreco() * item.getQuantidade();
        }

        return subtotal;
    }

    public double calcularDesconto(double subtotal) {
        if (subtotal > 300) {
            return subtotal * 0.15;
        } else if (subtotal > 200) {
            return subtotal * 0.10;
        } else if (subtotal > 100) {
            return subtotal * 0.05;
        }
        return 0;
    }

    public double calcularValorFinal(Pedido pedido) {
        double subtotal = calcularSubtotal(pedido);
        double desconto = calcularDesconto(subtotal);

        pedido.setSubtotal(subtotal);
        pedido.setDesconto(desconto);
        pedido.setTaxaEntrega(TAXA_ENTREGA);

        return subtotal - desconto + TAXA_ENTREGA;
    }

    public void finalizarPedido(Pedido pedido) {
        double valorFinal = calcularValorFinal(pedido);
        pedido.setValorTotal(valorFinal);
        pedido.setStatus("FINALIZADO");
    }

    public void exibirResumo(Pedido pedido) {
        System.out.println("===== RESUMO DO PEDIDO =====");
        System.out.println("Subtotal: R$ " + pedido.getSubtotal());
        System.out.println("Desconto: R$ " + pedido.getDesconto());
        System.out.println("Taxa de entrega: R$ " + pedido.getTaxaEntrega());
        System.out.println("Valor final: R$ " + pedido.getValorTotal());
    }

    public void atribuirEntregador(Pedido pedido, Entregador entregador) {
        if (!entregador.getStatus().equalsIgnoreCase("DISPONIVEL")) {
            throw new RuntimeException("Entregador indisponível!");
        }

        entregador.setStatus("EM_ENTREGA");
        pedido.setEntregador(entregador);
    }
}
