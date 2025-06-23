package br.edu.ifpr.gerenciador_biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.gerenciador_biblioteca.entities.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {}
