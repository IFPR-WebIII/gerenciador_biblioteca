package br.edu.ifpr.gerenciador_biblioteca;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import br.edu.ifpr.gerenciador_biblioteca.entities.Livro;
import br.edu.ifpr.gerenciador_biblioteca.repositories.LivroRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false) //evitar que o Spring desfaca as operacoes
class GerenciadorBibliotecaApplicationTests {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	LivroRepository livroRepository;

	@Test
	void deveInserirUmLivro() {

		Livro l1 = new Livro();
		l1.setTitulo("Titulo");
		l1.setAutor("Autor");
		l1.setDisponivel(true);

		entityManager.persist(l1);

		System.out.println("O id do livro inserido foi: " + l1.getId());

		l1.setAutor("Machado de Assis");

	}

	@Test
	void deveAtualizarUmLivro(){

		Livro livro = entityManager.find(Livro.class, 6L);

		livro.setTitulo("Dom Casmurro");

		entityManager.merge(livro); //Opcional

		System.out.println("O livro procurado foi:");
		System.out.println(livro);

	} 

	@Test
	void deveBuscarTodosOsLivros(){

		TypedQuery<Livro> query =  entityManager.createQuery("SELECT l FROM Livro l", Livro.class); //JPQL

		List<Livro> livros = query.getResultList();

		livros.forEach(l -> 
			System.out.println(" - " + l.getTitulo())
		);

		// for (Livro l : livros) {
		// 	System.out.println(" - " + l.getTitulo());
		// }

	}

	@Test
	void deveBuscarUmLivroPeloTitulo(){

		TypedQuery<Livro> query = entityManager.createQuery("SELECT l FROM Livro l WHERE l.titulo LIKE :titulo", Livro.class);

		query.setParameter("titulo", "%Cas%");

		List<Livro> livros = query.getResultList();

		livros.forEach(l -> 
			System.out.println(" - " + l.getTitulo())
		);

		entityManager.createNativeQuery("SELECT * FROM");

	}

	@Test
	void testeDeMetodosSpringJPA(){

		List<Livro> livros = livroRepository.findAll();
	
		livros.forEach(l -> 
			System.out.println(" - " + l.getTitulo())
		);

		List<Livro> livros1 = livroRepository.findByTitulo("Titulo");
		
		List<Livro> livros2 = livroRepository.buscarPorTitulo("Titulo");

		livros1.forEach(l -> 
			System.out.println(" - " + l.getTitulo())
		);

		livros2.forEach(l -> 
			System.out.println(" - " + l.getTitulo())
		);

	}



}
