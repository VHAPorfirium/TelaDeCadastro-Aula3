package br.com.aula3.aula3.dao;

import br.com.aula3.aula3.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface iClienteDAO {
    Cliente create(Cliente cliente);
    void update(Cliente cliente);
    void delete(Long id);
    Optional<Cliente> findById(Long id);
    List<Cliente> findAll();
}

