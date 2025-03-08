package br.com.aula3.aula3.dao;

import br.com.aula3.aula3.config.ConnectionFactory;
import br.com.aula3.aula3.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO implements iClienteDAO {
    @Override
    public Cliente create(Cliente cliente) {
        try (Connection connection
                     = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO cliente " +
                    "(nome, cpf, telefone, endereco) VALUES " +
                    "(?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEndereco());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                cliente.setId(Long.parseLong(String.valueOf(id)));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    @Override
    public void update(Cliente cliente) {
        try (Connection connection
                     = ConnectionFactory.getConnection()) {
            String query = "UPDATE cliente SET" +
                    "nome = ?, cpf = ?, telefone = ?, endereço = ? WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEndereco());
            ps.setLong(5, cliente.getId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Long id) {
        try (Connection connection
                     = ConnectionFactory.getConnection()){
            String query = "DELETE FROM cliente WHERE id = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Cliente> findById(Long id) {
        Cliente cliente = new Cliente();
        String query = "SELECT * FROM cliente WHERE id = ?";

        try (Connection connection
                     = ConnectionFactory.getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setId(id);
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
            }
            else {
                cliente.setId(id);
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try (Connection connection
                     = ConnectionFactory.getConnection()){
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                clientes.add(cliente);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientes;
    }
}
