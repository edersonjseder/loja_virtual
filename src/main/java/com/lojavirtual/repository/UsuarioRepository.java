package com.lojavirtual.repository;

import com.lojavirtual.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    @Query(value = "select u from Usuario u where u.login = ?1")
    Optional<Usuario> findUserByLogin(String login);
    @Query(value = "select u from Usuario u where u.pessoa.id = ?1 or u.login = ?2")
    Usuario findUserByPessoa(Long id, String login);
    @Query(value = "SELECT constraint_name from information_schema.constraint_column_usage where table_name = 'usuarios_acesso' and column_name = 'acesso_id' and constraint_name <> 'unique_acesso_usuario'", nativeQuery = true)
    String consultaConstraintAcesso();
    @Query(value = "select u from Usuario u where u.dataAtualSenha <= current_date - 90")
    List<Usuario> verificarUsuarioSenhaVencida();
}
