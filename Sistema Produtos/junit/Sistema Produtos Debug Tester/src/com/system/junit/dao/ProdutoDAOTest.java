/*
 * Testes unitários da classe de dao de Produto(CRUD)
 */
package com.system.junit.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
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
import com.system.controller.ControllerProduto;
import com.system.model.bean.Categoria;
import com.system.model.bean.Produto;
import com.system.model.dao.CategoriaDAO;
import com.system.model.dao.ProdutoDAO;

/*
 * Sistema Produtos/ Junit / DAO / Produto
 * @author MRX
 * Version : 1.0.0
 */

@TestMethodOrder(OrderAnnotation.class)
class ProdutoDAOTest {

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

		// Criar algumas categorias
		System.out.println("Creating Produto instances...\n");
		Produto p1 = new Produto();
		Produto p2 = new Produto();
		Produto p3 = new Produto();
		Produto p4 = new Produto();

		System.out.println("Setting first Produto attributes...\n");
		p1.setDescricao("Playstation 5 All Digital");
		p1.setQtde(5);
		BigDecimal val = new BigDecimal(4200.95);
		p1.setValor(val);
		Categoria cat = new Categoria();
		cat.setId(1);
		p1.setCategoria(cat);

		System.out.println("Setting second Produto attributes...\n");
		p2.setDescricao("Xbox Series X");
		p2.setQtde(5);
		BigDecimal val2 = new BigDecimal(4600.95);
		p2.setValor(val2);
		Categoria cat2 = new Categoria();
		cat2.setId(1);
		p2.setCategoria(cat2);

		System.out.println("Setting third Produto attributes...\n");
		p3.setDescricao("Pacote Sulfite Chamex 500fls");
		p3.setQtde(80);
		BigDecimal val3 = new BigDecimal(25.90);
		p3.setValor(val3);
		Categoria cat3 = new Categoria();
		cat3.setId(4);
		p3.setCategoria(cat3);

		System.out.println("Setting fourth Produto attributes...\n");
		p4.setDescricao("Teclado USB Microsoft");
		p4.setQtde(30);
		BigDecimal val4 = new BigDecimal(59.90);
		p4.setValor(val4);
		Categoria cat4 = new Categoria();
		cat4.setId(2);
		p4.setCategoria(cat4);

		// Criar lista
		List<Produto> produtos = new ArrayList<Produto>();
		System.out.println("Adding Produto objects to list...\n");
		produtos.add(p1);
		produtos.add(p2);
		produtos.add(p3);
		produtos.add(p4);

		// Persistindo dados
		ProdutoDAO dao = new ProdutoDAO();
		ControllerProduto con = new ControllerProduto();

		// Salvar dados
		System.out.println("Inserting Produto objects data...\n");

		int id = 1;

		for (Produto p : produtos) {

			// Validar dados
			System.out.println("Validating data...\n");
			if (con.validate(p)) {

				dao.save(p);

				// Executando teste
				Assertions.assertEquals(id, p.getId());
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

		ProdutoDAO dao = new ProdutoDAO();

		System.out.println("Searching...\n");
		Produto p = dao.findById(id);

		// Verificando acertos
		Assertions.assertEquals(id, p.getId());

		// Mostrando dados
		System.out.println("\nID : " + p.getId());
		System.out.println("\nDescrição : " + p.getDescricao());
		System.out.println("\nQTDE : " + p.getQtde());
		System.out.println("\nValor : " + p.getValor());
		System.out.println("\nCategoria : " + p.getCategoria().getDescricao());

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

		ProdutoDAO dao = new ProdutoDAO();

		System.out.println("Searching...\n");
		Produto p = dao.findById(id);

		// Verificando acertos
		Assertions.assertNull(p);

		System.out.println("\nData not found");

		System.out.println("\nPASS\n");

		System.out.println("testFindByIdNotExists() finalized.\n");

	}

	@Test
	@Order(5)
	void testFindAll() {

		System.out.println("\ntestFindAll() running...\n");

		ProdutoDAO dao = new ProdutoDAO();

		System.out.println("Searching...\n");
		List<Produto> produtos = dao.findAll();

		// Verificando acertos
		Assertions.assertFalse(produtos.isEmpty());

		System.out.println("\nAll data retrieved\n");

		// Mostrando dados
		for (Produto p : produtos) {

			// Mostrando dados
			System.out.println("\nID : " + p.getId());
			System.out.println("\nDescrição : " + p.getDescricao());
			System.out.println("\nQTDE : " + p.getQtde());
			System.out.println("\nValor : " + p.getValor());
			System.out.println("\nCategoria : " + p.getCategoria().getDescricao());
			System.out.println("--------------------------------");
		}

		System.out.println("\nPASS\n");

		System.out.println("testFindAll() finalized.\n");

	}

	@Test
	@Order(2)
	void testUpdate() {

		System.out.println("\ntestUpdate() running...\n");

		System.out.println("Creating Produto instance...\n");
		Produto p = new Produto();

		// Precisamos do id para atualizar
		System.out.println("Setting Produto attributes...\n");
		p.setId(3);
		p.setDescricao("Pacote Sulfite Chamex 500fls");
		p.setQtde(40);
		BigDecimal val = new BigDecimal(21.90);
		p.setValor(val);
		Categoria cat = new Categoria();
		cat.setId(4);
		p.setCategoria(cat);

		System.out.println("Validating and updating data...\n");
		ProdutoDAO dao = new ProdutoDAO();
		ControllerProduto ctr = new ControllerProduto();

		if (ctr.validate(p))
			dao.save(p);

		// Buscando novos dados
		Produto ps = dao.findById(3);

		// testando retorno
		Assertions.assertEquals(3, ps.getId());
		Assertions.assertEquals(40, ps.getQtde());

		// Mostrando dados
		System.out.println("\nID : " + p.getId());
		System.out.println("\nDescrição : " + p.getDescricao());
		System.out.println("\nQTDE : " + p.getQtde());
		System.out.println("\nValor : " + p.getValor().toString().substring(0, 7));
		System.out.println("\nCategoria ID : " + p.getCategoria().getId());

		System.out.println("\nPASS\n");

		System.out.println("testUpdate() finalized.\n");

	}

	@Test
	@Order(8)
	void testRemove() {

		System.out.println("\ntestRemove() running...\n");

		System.out.println("Generating id number...\n");
		int id = 3;

		System.out.println("Removing Produto...\n");

		ProdutoDAO dao = new ProdutoDAO();
		dao.remove(id);

		// Verificar se foi removido
		System.out.println("Searching...\n");
		Produto ps = dao.findById(id);

		// Não deve retornar nada pois foi excluido
		Assertions.assertNull(ps);

		System.out.println("Nothing found");

		System.out.println("\nPASS\n");

		System.out.println("testRemove() finalized.\n");

	}

	@Test
	@Order(6)
	void testfindByDescricaoLike() {

		System.out.println("\ntestfindByDescricaoLike() running...\n");

		System.out.println("Setting letter ...\n");

		char letter = 'p';

		ProdutoDAO dao = new ProdutoDAO();

		System.out.println("Searching 'p'...\n");
		List<Produto> produtos = dao.findByDescricaoLike(String.valueOf(letter));

		// Verificando acertos
		Assertions.assertFalse(produtos.isEmpty());

		System.out.println("\nAll data retrieved\n");

		// Mostrando dados
		for (Produto p : produtos) {

			System.out.println("\nID : " + p.getId());
			System.out.println("\nDescrição : " + p.getDescricao());
			System.out.println("\nQTDE : " + p.getQtde());
			System.out.println("\nValor : " + p.getValor());
			System.out.println("\nCategoria : " + p.getCategoria().getDescricao());
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

		ProdutoDAO dao = new ProdutoDAO();

		System.out.println("Searching 'z'...\n");
		List<Produto> produtos = dao.findByDescricaoLike(String.valueOf(letter));

		// Verificando acertos
		Assertions.assertTrue(produtos.isEmpty());

		System.out.println("\nData not found");

		System.out.println("\nPASS\n");

		System.out.println("testfindByDescricaoLikeNotExists() finalized.\n");

	}

}
