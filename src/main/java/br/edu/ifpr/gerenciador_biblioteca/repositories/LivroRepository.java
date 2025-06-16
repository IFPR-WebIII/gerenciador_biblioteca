package br.edu.ifpr.gerenciador_biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.gerenciador_biblioteca.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    public List<Livro> findByTitulo(String titulo); 
    public List<Livro> findByAutor(String autor); 
    public Integer countcountByDisponivelTrue(); 

    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo")
    public List<Livro> buscarPorTitulo(@Param("titulo") String titulo);
    
}
