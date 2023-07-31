package com.lojavirtual.repository;

import com.lojavirtual.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    @Query(value = "select u from Usuario u where u.login = ?1")
    Optional<Usuario>  findUserByLogin(String login);
}
