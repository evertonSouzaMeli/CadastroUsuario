package com.digitalhouse.cadastrousuario.service;

import com.digitalhouse.cadastrousuario.model.Endereco;
import com.digitalhouse.cadastrousuario.model.Usuario;
import com.digitalhouse.cadastrousuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        return usuarioRepository.findById(usuarioRepository.save(new Usuario(usuario.getNome(), usuario.getSexo(), usuario.getDataNascimento(), usuario.getEndereco())).getId()).orElse(new Usuario());
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public long count() {
        return usuarioRepository.count();
    }

    public Usuario findById(String id) throws Exception {
        return usuarioRepository.findById(id).orElse(new Usuario());
    }

    public List<Usuario> findAllByNome(String nome){
        return usuarioRepository.findAllByNome(nome);
    }

    public Endereco buscaEndereco(Usuario usuario){
        String url = "https://viacep.com.br/ws/" + usuario.getEndereco().getCep() + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        Endereco enderecoResponse = restTemplate.getForObject(url, Endereco.class);
        enderecoResponse.setComplemento(usuario.getEndereco().getComplemento());
        enderecoResponse.setNumero(usuario.getEndereco().getNumero());
        return enderecoResponse;
    }

    public void delete(String id) {
        usuarioRepository.deleteById(id);
    }
}
