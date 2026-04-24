package model;

/**
 * Subclasse Cliente que herda de Pessoa.
 */
public class Cliente extends Pessoa {
    private String endereco;

    public Cliente() {
        super();
    }

    public Cliente(String nome, String email, String telefone, String endereco) {
        super(nome, email, telefone);
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // Imprint ID, Nome, Email, Telefone
        System.out.println("Endereço de Entrega: " + endereco);
        System.out.println("========================================");
    }
}