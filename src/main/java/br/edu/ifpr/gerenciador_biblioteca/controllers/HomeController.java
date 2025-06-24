package br.edu.ifpr.gerenciador_biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifpr.gerenciador_biblioteca.entities.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @GetMapping({"", "/", "/home"})
    public String home(HttpSession sessao, Model model){

        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        model.addAttribute("usuario", usuario);
        return "home";

    }

}
