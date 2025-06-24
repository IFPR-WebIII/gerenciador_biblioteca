package br.edu.ifpr.gerenciador_biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpr.gerenciador_biblioteca.entities.Usuario;
import br.edu.ifpr.gerenciador_biblioteca.repositories.UsuarioRepository;

@Service
public class AutenticacaoService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Usuario login(String email, String senha ){

        Usuario usuario = usuarioRepository.findByEmail(email).get();

        if (usuario == null || !encoder.matches(senha, usuario.getSenha())) {
            throw new IllegalArgumentException("Usuário ou senha incorretos");
        }

        //criar condições de validações

        return usuario; 

    }
}
