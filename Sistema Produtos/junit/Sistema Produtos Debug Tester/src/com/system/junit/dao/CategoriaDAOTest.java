/*
 * Testes unitários da classe de dao de Categoria(CRUD)
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
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.system.connection.ConnectionFactory;
import com.system.controller.ControllerCategoria;
import com.system.model.bean.Categoria;
import com.system.model.dao.CategoriaDAO;

/*
 * Sistema Produtos/ Junit / DAO / Categoria
 * @author MRX
 * Version : 1.0.0
 */


@TestMethodOrder(OrderAnnotation.class)
class CategoriaDAOTest {

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

		System.out.println("testSave() running...\n");

		// Criar algumas categorias
		System.out.println("Creating Categoria instances...\n");
		Categoria c1 = new Categoria();
		Categoria c2 = new Categoria();
		Categoria c3 = new Categoria();
		Categoria c4 = new Categoria();

		System.out.println("Setting first Categoria attributes...\n");
		c1.setDescricao("Games");

		System.out.println("Setting second Categoria attributes...\n");
		c2.setDescricao("Informatica");

		System.out.println("Setting third Categoria attributes...\n");
		c3.setDescricao("Automotivo");

		System.out.println("Setting fourth Categoria attributes...\n");
		c4.setDescricao("Papelaria");

		// Criar lista
		List<Categoria> categorias = new ArrayList<Categoria>();
		System.out.println("Adding Categoria objects to list...\n");
		categorias.add(c1);
		categorias.add(c2);
		categorias.add(c3);
		categorias.add(c4);

		// Persistindo dados
		CategoriaDAO dao = new CategoriaDAO();
		ControllerCategoria con = new ControllerCategoria();

		// Salvar dados
		System.out.println("Inserting Categoria objects data...\n");

		int id = 1;

		for (Categoria c : categorias) {

			// Validar dados
			System.out.println("Validating data...\n");
			if (con.validate(c)) {

				dao.save(c);

				// Executando teste
				Assertions.assertEquals(id, c.getId());
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

		CategoriaDAO dao = new CategoriaDAO();

		System.out.println("Searching...\n");
		Categoria c = dao.findById(id);

		// Verificando acertos
		Assertions.assertEquals(id, c.getId());

		// Mostrando dados
		System.out.println("\nID : " + c.getId());
		System.out.println("\nDescrição : " + c.getDescricao());
		System.out.println("\nData Registro : " + c.getDt_registro().toString());

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

		CategoriaDAO dao = new CategoriaDAO();

		System.out.println("Searching...\n");
		Categoria c = dao.findById(id);

		// Verificando acertos
		Assertions.assertNull(c);

		System.out.println("\nData not found");

		System.out.println("\nPASS\n");

		System.out.println("testFindByIdNotExists() finalized.\n");

	}

	@Test
	@Order(5)
	void testFindAll() {

		System.out.println("\ntestFindAll() running...\n");

		CategoriaDAO dao = new CategoriaDAO();

		System.out.println("Searching...\n");
		List<Categoria> categorias = dao.findAll();

		// Verificando acertos
		Assertions.assertFalse(categorias.isEmpty());

		System.out.println("\nAll data retrieved\n");

		// Mostrando dados
		for (Categoria c : categorias) {

			System.out.println("\nID : " + c.getId());
			System.out.println("\nDescrição : " + c.getDescricao());
			System.out.println("\nData Registro : " + c.getDt_registro().toString());
			System.out.println("--------------------------------");
		}

		System.out.println("\nPASS\n");

		System.out.println("testFindAll() finalized.\n");

	}

	@Test
	@Order(2)
	void testUpdate() {

		System.out.println("\ntestUpdate() running...\n");

		System.out.println("Creating Categoria instance...\n");
		Categoria c = new Categoria();

		// Precisamos do id para atualizar
		System.out.println("Setting Categoria attributes...\n");
		c.setId(4);
		c.setDescricao("Escritório");

		System.out.println("Validating and updating data...\n");
		CategoriaDAO dao = new CategoriaDAO();
		ControllerCategoria ctr = new ControllerCategoria();

		if (ctr.validate(c))
			dao.save(c);

		// Buscando novos dados
		Categoria cs = dao.findById(4);

		// testando retorno
		Assertions.assertEquals(4, cs.getId());
		Assertions.assertEquals("Escritório", cs.getDescricao());

		// Mostrando dados
		System.out.println("\nID : " + cs.getId());
		System.out.println("\nDescrição : " + cs.getDescricao());
		System.out.println("\nData Registro : " + cs.getDt_registro().toString());

		System.out.println("\nPASS\n");

		System.out.println("testUpdate() finalized.\n");

	}

	@Test
	@Order(8)
	void testRemove() {

		System.out.println("\ntestRemove() running...\n");

		System.out.println("Generating id number...\n");
		int id = 3;

		System.out.println("Removing Categoria...\n");

		CategoriaDAO dao = new CategoriaDAO();
		dao.remove(id);

		// Verificar se foi removido
		System.out.println("Searching...\n");
		Categoria cs = dao.findById(id);

		// Não deve retornar nada pois foi excluido
		Assertions.assertNull(cs);

		System.out.println("Nothing found");

		System.out.println("\nPASS\n");

		System.out.println("testRemove() finalized.\n");

	}
	
	@Test
	@Order(6)
	void testfindByDescricaoLike() {
	
		System.out.println("\ntestfindByDescricaoLike() running...\n");
	
		System.out.println("Setting letter ...\n");
	
		char letter = 'g';
	
		CategoriaDAO dao = new CategoriaDAO();
	
		System.out.println("Searching 'g'...\n");
		List<Categoria> categorias = dao.findByDescricaoLike(String.valueOf(letter));
	
		// Verificando acertos
		Assertions.assertFalse(categorias.isEmpty());
	
		System.out.println("\nAll data retrieved\n");
	
		// Mostrando dados
		for (Categoria c : categorias) {
	
			System.out.println("\nID : " + c.getId());
			System.out.println("\nDescrição : " + c.getDescricao());
			System.out.println("\nData Registro : " + c.getDt_registro().toString());
			System.out.println("--------------------------------");
		}
	
		System.out.println("\nPASS\n");
	
		System.out.println("testfindByDescricaoLike() finalized.\n");
	
	}

	@Test
	@Order(7)
	void testfindByDescricaoLikeNotExists() {

		System.out.println("\ntestfindByDescricaoLikeNotExists() running...\n");

		System.out.println("Setting letter ...\n");

		char letter = 'z';

		CategoriaDAO dao = new CategoriaDAO();

		System.out.println("Searching 'z'...\n");
		List<Categoria> categorias = dao.findByDescricaoLike(String.valueOf(letter));

		// Verificando acertos
		Assertions.assertTrue(categorias.isEmpty());

		System.out.println("\nData not found");

		System.out.println("\nPASS\n");

		System.out.println("testfindByDescricaoLikeNotExists() finalized.\n");

	}


}
