package br.edu.ifpr.gerenciador_biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.gerenciador_biblioteca.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
