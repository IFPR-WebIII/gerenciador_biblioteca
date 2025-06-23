package br.edu.ifpr.gerenciador_biblioteca.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Livro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;

    private String autor;
    private Boolean disponivel; 

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;

}
