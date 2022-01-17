package com.digitalhouse.cadastrousuario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Document(collation = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Usuario {
    @Id
    private String id;
    private String nome;
    private char sexo;
    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$")
    @PastOrPresent
    private String dataNascimento;
    private String dataCriacao;
    private Endereco endereco;

    public Usuario(String nome, char sexo, String dataNascimento){
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.dataCriacao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public Usuario(String nome, char sexo, String dataNascimento, Endereco endereco){
        this(nome, sexo, dataNascimento);
        this.dataCriacao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.endereco = endereco;
    }
}
