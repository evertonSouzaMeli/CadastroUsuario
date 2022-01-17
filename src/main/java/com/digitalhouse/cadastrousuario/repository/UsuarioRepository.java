package com.digitalhouse.cadastrousuario.repository;

import com.digitalhouse.cadastrousuario.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    List<Usuario> findAllByNome(String nome);
}