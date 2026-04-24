package dao;

import model.Pessoa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) para gerenciar operações de banco de dados
 * relacionadas à entidade Usuario.
 * 
 * Esta classe implementa o padrão DAO, separando a lógica de acesso a dados
 * da lógica de negócio. Todas as operações CRUD são implementadas aqui.
 * 
 * Conceitos aplicados:
 * - DAO Pattern: Separação de responsabilidades
 * - CRUD: Create, Read, Update, Delete
 * - PreparedStatement: Prevenção de SQL Injection
 * - Try-with-resources: Gerenciamento automático de recursos
 * 
 * @author Professores POO + BD
 * @version 1.0
 */
public class PessoaDAO {
    
    /**
     * Insere um novo usuário no banco de dados.
     * 
     * O ID do usuário é gerado automaticamente pelo banco (SERIAL).
     * Após a inserção, o ID gerado é atribuído ao objeto usuario.
     * 
     * @param pessoa Objeto Pessoa a ser inserido
     * @return true se a inserção foi bem-sucedida, false caso contrário
     */
    public boolean inserir(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, email, telefone) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getTelefone());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        pessoa.setId(rs.getInt(1));
                    }
                }
                System.out.println("✓ Usuário inserido com sucesso! ID: " + pessoa.getId());
                return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("✗ Erro ao inserir usuário: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Busca um usuário pelo ID.
     * 
     * @param id ID do usuário a ser buscado
     * @return Objeto Usuario se encontrado, null caso contrário
     */
    public Pessoa buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extrairPessoaDoResultSet(rs);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Erro ao buscar usuário: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Lista todos os usuários cadastrados no banco de dados.
     * 
     * @return Lista de usuários (vazia se não houver usuários)
     */
    public List<Pessoa> listarTodos() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY nome";
        
        try (Connection conn = ConexaoBD.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                pessoas.add(extrairPessoaDoResultSet(rs));
            }
            
            System.out.println("✓ " + pessoas.size() + " usuário(s) encontrado(s)");
            
        } catch (SQLException e) {
            System.err.println("✗ Erro ao listar usuários: " + e.getMessage());
        }
        
        return pessoas;
    }
    
    /**
     * Atualiza os dados de um usuário existente.
     * 
     * @param pessoa Objeto Usuario com os dados atualizados
     * @return true se a atualização foi bem-sucedida, false caso contrário
     */
    public boolean atualizar(Pessoa pessoa) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, telefone = ? WHERE id = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getTelefone());
            stmt.setInt(4, pessoa.getId());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                System.out.println("✓ Usuário atualizado com sucesso!");
                return true;
            } else {
                System.out.println("⚠ Nenhum usuário encontrado com o ID: " + pessoa.getId());
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Exclui um usuário do banco de dados.
     * 
     * @param id ID do usuário a ser excluído
     * @return true se a exclusão foi bem-sucedida, false caso contrário
     */
    public boolean excluir(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                System.out.println("✓ Usuário excluído com sucesso!");
                return true;
            } else {
                System.out.println("⚠ Nenhum usuário encontrado com o ID: " + id);
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Erro ao excluir usuário: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Busca usuários por nome (busca parcial, case-insensitive).
     * 
     * @param nome Nome ou parte do nome do usuário
     * @return Lista de usuários encontrados
     */
    public List<Pessoa> buscarPorNome(String nome) {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE LOWER(nome) LIKE LOWER(?) ORDER BY nome";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pessoas.add(extrairPessoaDoResultSet(rs));
                }
            }
            
            System.out.println("✓ " + pessoas.size() + " usuário(s) encontrado(s) com o nome '" + nome + "'");
            
        } catch (SQLException e) {
            System.err.println("✗ Erro ao buscar usuários por nome: " + e.getMessage());
        }
        
        return pessoas;
    }
    
    /**
     * Conta o total de usuários cadastrados.
     * 
     * @return Número total de usuários
     */
    public int contarTotal() {
        String sql = "SELECT COUNT(*) FROM usuario";
        
        try (Connection conn = ConexaoBD.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Erro ao contar usuários: " + e.getMessage());
        }
        
        return 0;
    }
    
    /**
     * Método auxiliar para extrair um objeto Usuario de um ResultSet.
     * 
     * @param rs ResultSet contendo os dados do usuário
     * @return Objeto Usuario com os dados extraídos
     * @throws SQLException se houver erro ao acessar os dados
     */
    private Pessoa extrairPessoaDoResultSet(ResultSet rs) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(rs.getInt("id"));
        pessoa.setNome(rs.getString("nome"));
        pessoa.setEmail(rs.getString("email"));
        pessoa.setTelefone(rs.getString("telefone"));
        return pessoa;
    }
    
    /**
     * Verifica se existe um usuário com o ID especificado.
     * 
     * @param id ID do usuário
     * @return true se o usuário existe, false caso contrário
     */
    public boolean existe(int id) {
        return buscarPorId(id) != null;
    }
}
