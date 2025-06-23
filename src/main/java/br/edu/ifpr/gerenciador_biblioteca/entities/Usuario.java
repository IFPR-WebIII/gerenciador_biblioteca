package br.edu.ifpr.gerenciador_biblioteca.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Entity //Para o Spring criar as tabelas no banco
@Data //para gerar os getters, setters, toString e etc
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gerar primary key "auto increment"
    private Long id;
    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Emprestimo> emprestimo  = new ArrayList<>();
    
}
