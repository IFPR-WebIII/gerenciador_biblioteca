package br.edu.ifpr.gerenciador_biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpr.gerenciador_biblioteca.entities.Usuario;
import br.edu.ifpr.gerenciador_biblioteca.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void save(Usuario usuario) throws IllegalArgumentException {


        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        usuarioRepository.save(usuario);

    }
    
}
