package com.digitalhouse.cadastrousuario.controller;;

import com.digitalhouse.cadastrousuario.model.Usuario;
import com.digitalhouse.cadastrousuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/get/nome/{nome}")
    public ResponseEntity<List<Usuario>> getAllByName(@PathVariable String nome){
        return ResponseEntity.ok().body(usuarioService.findAllByNome(nome));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok().body(usuarioService.findAll());
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(usuarioService.findById(id));
    }

    @PostMapping("/post")
    public ResponseEntity<Usuario> post(@RequestBody Usuario usuario){
        usuario.setEndereco(usuarioService.buscaEndereco(usuario));
        return ResponseEntity.ok().body(usuarioService.save(usuario));
    }

    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }
}
