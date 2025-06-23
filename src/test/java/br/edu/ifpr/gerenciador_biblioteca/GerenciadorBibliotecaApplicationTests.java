package br.edu.ifpr.gerenciador_biblioteca;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import br.edu.ifpr.gerenciador_biblioteca.entities.Emprestimo;
import br.edu.ifpr.gerenciador_biblioteca.entities.Livro;
import br.edu.ifpr.gerenciador_biblioteca.entities.Usuario;
import br.edu.ifpr.gerenciador_biblioteca.repositories.EmprestimoRepository;
import br.edu.ifpr.gerenciador_biblioteca.repositories.LivroRepository;
import br.edu.ifpr.gerenciador_biblioteca.repositories.UsuarioRepository;
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

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EmprestimoRepository emprestimoRepository;

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

		Livro livro = entityManager.find(Livro.class, 1L);

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


	@Test
	public void deveInserirUmUsuario(){

		Usuario usu1 = new Usuario();

		usu1.setNome("John Doe");
		usu1.setEmail("john.doe@email.com");
		
		usu1.setEmprestimo(null);

		usuarioRepository.save(usu1);
	}

	@Test
	public void deveInserirUmEmprestimo(){

		Usuario usuario = usuarioRepository.findById(1L).get();

		Livro livro1 = livroRepository.findById(1L).get();
		Livro livro2 = livroRepository.findById(2L).get();

		Emprestimo emprestimo = new Emprestimo();

		emprestimo.setDataEmprestimo(LocalDate.now());
		emprestimo.setDataDevolucao(LocalDate.now().plusDays(3));

		emprestimo.setUsuario(usuario);

		emprestimo.getLivros().add(livro1);
		emprestimo.getLivros().add(livro2);

		emprestimoRepository.save(emprestimo);
	}

	@Test
	public void devolverLivro(){

		Livro l1 = livroRepository.findById(2L).get();

		//Long idEmprestimo = l1.getEmprestimo().getId();

		Emprestimo emprestimo = emprestimoRepository.findById(3L).get();

		emprestimo.getLivros().remove(l1);

		l1.setDisponivel(true);

		emprestimoRepository.save(emprestimo);

	}

	@Test
	public void deveExibirUmUsuario(){
		Usuario usuario = usuarioRepository.findById(1L).get();
		System.out.println(usuario);

		System.out.println("EMPRÉSTIMOS DO USUÁRIO");
		for(Emprestimo emprestimo: usuario.getEmprestimo()){
			System.out.println(emprestimo.getId() + " - " + emprestimo.getDataEmprestimo());
		}
	}
}
