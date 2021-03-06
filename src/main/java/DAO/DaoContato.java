package DAO;

import entity.Contato;

import java.util.List;

public interface DaoContato {

    void salvarOuAtualizar(Contato obj);

    List<Contato> getContato(String nome, String tipoBusca);

    void deletarContato(Long id);

}
