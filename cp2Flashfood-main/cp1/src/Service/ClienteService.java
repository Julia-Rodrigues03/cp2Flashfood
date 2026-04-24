package Service;

import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    public Pedido criarPedido(Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setItens(new ArrayList<>());
        pedido.setStatus("ABERTO");
        return pedido;
    }

    public void adicionarItem(Pedido pedido, Produto produto, int quantidade) {
        ItemPedido item = new ItemPedido(produto, quantidade);
        pedido.getItens().add(item);
    }
}

