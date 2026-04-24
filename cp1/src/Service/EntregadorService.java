package Service;

import model.Entregador;

public class EntregadorService {

    public boolean estaDisponivel(Entregador entregador) {
        return entregador.getStatus().equalsIgnoreCase("DISPONIVEL");
    }

    public void iniciarEntrega(Entregador entregador) {
        if (!estaDisponivel(entregador)) {
            throw new RuntimeException("Entregador não está disponível");
        }
        entregador.setStatus("EM_ENTREGA");
    }

    public void finalizarEntrega(Entregador entregador) {
        entregador.setStatus("DISPONIVEL");
    }
}
