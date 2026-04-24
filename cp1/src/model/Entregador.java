package model;

/**
 * Subclasse Entregador que herda de Pessoa.
 */
public class Entregador extends Pessoa {
    private String veiculo;
    private String status;

    public Entregador() {
        super(); // Chama o construtor vazio de Pessoa
        this.status = "DISPONÍVEL"; // Define o status padrão
    }

    public Entregador(String nome, String email, String telefone, String veiculo) {
        super(nome, email, telefone); // Usa o construtor da Pessoa
        this.veiculo = veiculo;
        this.status = "DISPONÍVEL";
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @Override indica que estamos substituindo o método da Superclasse.
     */
    @Override
    public void exibirDetalhes() {
        // 1. Chama a impressão padrão da classe Pessoa (ID, Nome, Email, Telefone)
        super.exibirDetalhes();
        
        // 2. Adiciona as informações exclusivas do Entregador logo abaixo
        System.out.println("Veículo: " + veiculo);
        System.out.println("Status: " + status);
        System.out.println("========================================");
    }
}