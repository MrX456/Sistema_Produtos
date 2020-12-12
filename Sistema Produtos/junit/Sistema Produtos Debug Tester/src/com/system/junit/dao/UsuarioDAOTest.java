/*
 * Testes unitários da classe de dao de usuário(CRUD)
 */

package com.system.junit.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.system.connection.ConnectionFactory;
import com.system.controller.ControllerUsuario;
import com.system.model.bean.Usuario;
import com.system.model.dao.UsuarioDAO;

/*
 * Sistema Produtos/ Junit / DAO / Usuario
 * @author MRX
 * Version : 1.0.0
 */

@TestMethodOrder(OrderAnnotation.class)
class UsuarioDAOTest {

	private static EntityManager em;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		System.out.println("Starting tests...\n");

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

		System.out.println("\nFinishing tests...");
		System.out.println("\nTests finished");

	}

	@BeforeEach
	void setUp() throws Exception {

		System.out.println("\nPreparing next test...\n");
		// Abrir conexão
		em = new ConnectionFactory().getConnection();

	}

	@AfterEach
	void tearDown() throws Exception {

		// Fechar conexão
		em.close();
		System.out.println("\n\n");
		
	}

	@Test
	@Order(1)
	void testSave() {

		String[] sexo = { "fem", "masc" };
		String[] perfil = { "admin", "usuario" };

		System.out.println("testSave() running...\n");

		// Criar alguns usuarios
		System.out.println("Creating Usuario instances...\n");
		Usuario u1 = new Usuario();
		Usuario u2 = new Usuario();
		Usuario u3 = new Usuario();
		Usuario u4 = new Usuario();

		// Setar valores u1
		System.out.println("Setting first Usuario attributes...\n");
		u1.setLogin("ana123");
		u1.setSenha("1234");
		u1.setPerfil(perfil[0]);
		u1.setSexo(sexo[0]);

		// u2
		System.out.println("Setting another Usuario attributes...\n");
		u2.setLogin("marcio56");
		u2.setSenha("4567");
		u2.setPerfil(perfil[1]);
		u2.setSexo(sexo[1]);

		// u3
		System.out.println("Setting another Usuario attributes...\n");
		u3.setLogin("paulor432");
		u3.setSenha("0ads");
		u3.setPerfil(perfil[0]);
		u3.setSexo(sexo[1]);

		// u4
		System.out.println("Setting last Usuario attributes...\n");
		u4.setLogin("clara4324");
		u4.setSenha("rsdf");
		u4.setPerfil(perfil[1]);
		u4.setSexo(sexo[0]);

		// Criar lista
		List<Usuario> usuarios = new ArrayList<Usuario>();
		System.out.println("Adding Usuario objects to list...\n");
		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);
		usuarios.add(u4);

		// Persistindo dados
		UsuarioDAO dao = new UsuarioDAO();
		ControllerUsuario con = new ControllerUsuario();

		// Salvar dados
		System.out.println("Inserting Usuario objects data...\n");

		int id = 1;

		for (Usuario u : usuarios) {

			// Validar dados
			System.out.println("Validating data...\n");
			if (con.validate(u)) {

				dao.save(u);

				// Executando teste
				Assertions.assertEquals(id, u.getId());
				System.out.println("\nData sucessfully inserted.\n");

				id++;

			}
		}

		System.out.println("\nPASS\n");

		System.out.println("testSave() finalized.\n");

	}

	@Test
	@Order(3)
	void testFindById() {

		System.out.println("\ntestFindById() running...\n");

		System.out.println("Generating id number...\n");
		// Valor deve ser entre 1 e 4
		int id = (int) (Math.random() * 4) + 1;

		UsuarioDAO dao = new UsuarioDAO();

		System.out.println("Searching...\n");
		Usuario u = dao.findById(id);

		// Verificando acertos
		Assertions.assertEquals(id, u.getId());

		// Mostrando dados
		System.out.println("\nID : " + u.getId());
		System.out.println("\nLogin : " + u.getLogin());
		System.out.println("\nSenha : " + u.getSenha());
		System.out.println("\nPerfil : " + u.getPerfil());
		System.out.println("\nSexo : " + u.getSexo());

		System.out.println("\nPASS\n");

		System.out.println("testFindById() finalized.\n");

	}

	@Test
	@Order(4)
	void testFindByIdNotExists() {

		System.out.println("\ntestFindByIdNotExists() running...\n");

		System.out.println("Generating id number...\n");
		// Valor deve ser maior que 4
		int id = 12;

		UsuarioDAO dao = new UsuarioDAO();

		System.out.println("Searching...\n");
		Usuario u = dao.findById(id);

		// Verificando acertos
		Assertions.assertNull(u);

		System.out.println("\nData not found");

		System.out.println("\nPASS\n");

		System.out.println("testFindByIdNotExists() finalized.\n");

	}

	@Test
	@Order(5)
	void testFindAll() {

		System.out.println("\ntestFindAll() running...\n");

		UsuarioDAO dao = new UsuarioDAO();

		System.out.println("Searching...\n");
		List<Usuario> usuarios = dao.findAll();

		// Verificando acertos
		Assertions.assertFalse(usuarios.isEmpty());

		System.out.println("\nAll data retrieved\n");

		// Mostrando dados
		for (Usuario u : usuarios) {

			System.out.println("\nID : " + u.getId());
			System.out.println("\nLogin : " + u.getLogin());
			System.out.println("\nSenha : " + u.getSenha());
			System.out.println("\nPerfil : " + u.getPerfil());
			System.out.println("\nSexo : " + u.getSexo());
			System.out.println("--------------------------------");
		}

		System.out.println("\nPASS\n");

		System.out.println("testFindAll() finalized.\n");

	}

	@Test
	@Order(2)
	void testUpdate() {

		System.out.println("\ntestUpdate() running...\n");

		System.out.println("Creating Usuario instance...\n");
		Usuario u = new Usuario();

		// Precisamos do id para atualizar
		System.out.println("Setting Usuario attributes...\n");
		u.setId(1);
		u.setLogin("admin02");
		u.setSenha("admin");
		u.setPerfil("admin");
		u.setSexo("fem");

		System.out.println("Validating and updating data...\n");
		UsuarioDAO dao = new UsuarioDAO();
		ControllerUsuario ctr = new ControllerUsuario();

		if (ctr.validate(u))
			dao.save(u);

		// Buscando novos dados
		Usuario us = dao.findById(1);

		// testando retorno
		Assertions.assertEquals(1, us.getId());
		Assertions.assertEquals("admin02", us.getLogin());
		Assertions.assertEquals("admin", us.getSenha());
		Assertions.assertEquals("admin", us.getPerfil());
		Assertions.assertEquals("fem", us.getSexo());

		// Mostrando dados
		System.out.println("\nID : " + us.getId());
		System.out.println("\nLogin : " + us.getLogin());
		System.out.println("\nSenha : " + us.getSenha());
		System.out.println("\nPerfil : " + us.getPerfil());
		System.out.println("\nSexo : " + us.getSexo());

		System.out.println("\nPASS\n");

		System.out.println("testUpdate() finalized.\n");

	}

	@Test
	@Order(8)
	void testRemove() {

		System.out.println("\ntestRemove() running...\n");

		System.out.println("Generating id number...\n");
		int id = 2;

		System.out.println("Removing Usuario...\n");

		UsuarioDAO dao = new UsuarioDAO();
		dao.remove(id);

		// Verificar se foi removido
		System.out.println("Searching...\n");
		Usuario us = dao.findById(id);

		// Não deve retornar nada pois foi excluido
		Assertions.assertNull(us);

		System.out.println("Nothing found");

		System.out.println("\nPASS\n");

		System.out.println("testRemove() finalized.\n");

	}

	@Test
	@Order(6)
	void testFindByLoginSenha() {

		System.out.println("\ntestFindByLoginSenha() running...\n");

		System.out.println("Generating values...\n");
		String login = "admin02";
		String senha = "admin";

		UsuarioDAO dao = new UsuarioDAO();

		System.out.println("Searching...\n");
		Usuario u = dao.findByLoginSenha(login, senha);

		// Verificando acertos
		Assertions.assertEquals(1, u.getId());
		Assertions.assertEquals("admin02", u.getLogin());
		Assertions.assertEquals("admin", u.getSenha());
		Assertions.assertEquals("admin", u.getPerfil());
		Assertions.assertEquals("fem", u.getSexo());

		// Mostrando dados
		System.out.println("\nID : " + u.getId());
		System.out.println("\nLogin : " + u.getLogin());
		System.out.println("\nSenha : " + u.getSenha());
		System.out.println("\nPerfil : " + u.getPerfil());
		System.out.println("\nSexo : " + u.getSexo());

		System.out.println("\nPASS\n");

		System.out.println("testFindByLoginSenha() finalized.\n");

	}

	@Test
	@Order(7)
	void testFindByLoginSenhaNotExists() {

		System.out.println("\ntestFindByLoginSenhaNotExists() running...\n");

		System.out.println("Generating values...\n");
		String login = "paula123";
		String senha = "rsxtr";

		UsuarioDAO dao = new UsuarioDAO();

		System.out.println("Searching...\n");
		Usuario u = dao.findByLoginSenha(login, senha);

		// Verificando acertos(Usuario não existe)
		Assertions.assertNull(u);

		System.out.println("\nData not found");

		System.out.println("\nPASS\n");

		System.out.println("testFindByLoginSenhaNotExists() finalized.\n");
	}

}
