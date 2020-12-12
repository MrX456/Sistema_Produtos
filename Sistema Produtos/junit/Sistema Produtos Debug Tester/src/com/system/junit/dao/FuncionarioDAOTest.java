/*
 * Testes unitários da classe de dao de Funcionario(CRUD)
 */

package com.system.junit.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.system.connection.ConnectionFactory;
import com.system.controller.ControllerFuncionario;
import com.system.model.bean.Funcionario;
import com.system.model.dao.FuncionarioDAO;

/*
 * Sistema Produtos/ Junit / DAO / Funcionario
 * @author MRX
 * Version : 1.0.0
 */

@TestMethodOrder(OrderAnnotation.class)
class FuncionarioDAOTest {

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
	void testSave() throws ParseException {

		String[] sexo = { "fem", "masc" };

		System.out.println("testSave() running...\n");

		// Criar alguns funcionarios
		System.out.println("Creating Funcionario instances...\n");

		Funcionario f1 = new Funcionario();
		Funcionario f2 = new Funcionario();
		Funcionario f3 = new Funcionario();
		Funcionario f4 = new Funcionario();

		// Setar valores f1
		System.out.println("Setting first Funcionario attributes...\n");
		f1.setMatricula(1048);
		f1.setNome("Maria da Silva"); // Data de nascimento
		String data = "06/01/1986";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date nasc = sdf.parse(data);
		f1.setDt_nasc(nasc);
		f1.setSexo(sexo[0]);
		f1.setTelefone("3385-2150");
		f1.setEndereco("Rua Spark 186"); // Data de contratação data = "04/04/2018";
		Date contratacao = sdf.parse(data);
		f1.setDt_contratacao(contratacao); //
		BigDecimal sal = new BigDecimal(1645.90);
		f1.setSalario(sal);

		// Setar valores f2
		System.out.println("Setting second Funcionario attributes...\n");
		f2.setMatricula(1072);
		f2.setNome("Carlos Delfino de Souza"); // Data de
		data = "03/10/1974";
		nasc = sdf.parse(data);
		f2.setDt_nasc(nasc);
		f2.setSexo(sexo[1]);
		f2.setTelefone("3440-2750");
		f2.setEndereco("Rua Levin 450"); // Data de contratação data = "09/10/2017";
		contratacao = sdf.parse(data);
		f2.setDt_contratacao(contratacao); // Salario
		sal = new BigDecimal(2750.00);
		f2.setSalario(sal);

		// Setar valores f3
		System.out.println("Setting third Funcionario attributes...\n");
		f3.setMatricula(1108);
		f3.setNome("Natalia de Oliveira"); // Data de
		data = "09/02/1991";
		nasc = sdf.parse(data);
		f3.setDt_nasc(nasc);
		f3.setSexo(sexo[0]);
		f3.setTelefone("3250-2599");
		f3.setEndereco("Rua Saul 210"); // Data de contratação data = "12/11/2019";
		contratacao = sdf.parse(data);
		f3.setDt_contratacao(contratacao); // Salario
		sal = new BigDecimal(1690.00);
		f3.setSalario(sal);

		// Setar valores f4
		System.out.println("Setting fourth Funcionario attributes...\n");
		f4.setMatricula(1206);
		f4.setNome("Paulo Diniz"); // Data de nascimento
		data = "06/04/1989";
		nasc = sdf.parse(data);
		f4.setDt_nasc(nasc);
		f4.setSexo(sexo[1]);
		f4.setTelefone("3150-6542");
		f4.setEndereco("Rua Carol 604"); // Data de contratação data = "11/03/2018";
		contratacao = sdf.parse(data);
		f4.setDt_contratacao(contratacao); // Salario
		sal = new BigDecimal(1925.00);
		f4.setSalario(sal);

		// Criar lista
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		System.out.println("Adding Funcionario objects to list...\n");
		funcionarios.add(f1);
		funcionarios.add(f2);
		funcionarios.add(f3);
		funcionarios.add(f4);

		// Persistindo dados
		FuncionarioDAO dao = new FuncionarioDAO();
		ControllerFuncionario con = new ControllerFuncionario();

		// Salvar dados
		System.out.println("Inserting Funcionario objects data...\n");

		int i = 0;

		for (Funcionario f : funcionarios) {

			// Validar dados System.out.println("Validating data...\n"); if
			if (con.validate(f)) {
				dao.save(f);
			}

			// Executando teste Assertions.assertEquals(matricula[i], f.getMatricula());
			System.out.println("\nData sucessfully inserted.\n");

			i++;

		}

		System.out.println("\nPASS\n");

		System.out.println("testSave() finalized.\n");

	}

	@Test
	@Order(4)
	void testFindByMatricula() {

		System.out.println("\ntestFindByMatricula() running...\n");

		System.out.println("Setting matricula number...\n");
		int matricula = 1206;

		FuncionarioDAO dao = new FuncionarioDAO();

		System.out.println("Searching...\n");
		Funcionario f = dao.findByMatricula(matricula);

		// Verificando acertos
		Assertions.assertEquals(matricula, f.getMatricula());

		// Mostrando dados
		System.out.println("\nMatricula : " + f.getMatricula());
		System.out.println("\nNome : " + f.getNome());
		System.out.println("\nNascimento : " + f.getDt_nasc());
		System.out.println("\nSexo : " + f.getSexo());
		System.out.println("\nTelefone : " + f.getTelefone());
		System.out.println("\nEndereço : " + f.getEndereco());
		System.out.println("\nContratação : " + f.getDt_contratacao());
		System.out.println("\nSalario : " + f.getSalario());

		System.out.println("\nPASS\n");

		System.out.println("testFindById() finalized.\n");

	}

	@Test
	@Order(5)
	void testFindByMatriculaNotExists() {

		System.out.println("\ntestFindByMatriculaNotExists() running...\n");

		System.out.println("Setting matricula number...\n");

		int matricula = 1209;

		FuncionarioDAO dao = new FuncionarioDAO();

		System.out.println("Searching...\n");
		Funcionario f = dao.findByMatricula(matricula);

		// Verificando acertos
		Assertions.assertNull(f);

		System.out.println("\nData not found");

		System.out.println("\nPASS\n");

		System.out.println("testFindByIdNotExists() finalized.\n");

	}

	@Test
	@Order(2)
	void testFindAll() {

		System.out.println("\ntestFindAll() running...\n");

		FuncionarioDAO dao = new FuncionarioDAO();

		System.out.println("Searching...\n");
		List<Funcionario> funcionarios = dao.findAll();

		// Verificando acertos
		Assertions.assertFalse(funcionarios.isEmpty());

		System.out.println("\nAll data retrieved\n");

		// Mostrando dados
		for (Funcionario f : funcionarios) {

			System.out.println("\nMatricula : " + f.getMatricula());
			System.out.println("\nNome : " + f.getNome());
			System.out.println("\nNascimento : " + f.getDt_nasc());
			System.out.println("\nSexo : " + f.getSexo());
			System.out.println("\nTelefone : " + f.getTelefone());
			System.out.println("\nEndereço : " + f.getEndereco());
			System.out.println("\nContratação : " + f.getDt_contratacao());
			System.out.println("\nSalario : " + f.getSalario());
			System.out.println("--------------------------------");
		}

		System.out.println("\nPASS\n");

		System.out.println("testFindAll() finalized.\n");

	}

	@Test
	@Order(3)
	void testUpdate() throws ParseException {

		System.out.println("\ntestUpdate() running...\n");

		System.out.println("Creating Funcionario instance...\n");
		Funcionario u = new Funcionario();

		// Precisamos da matricula para atualizar
		Funcionario f1 = new Funcionario();
		System.out.println("Setting first Funcionario attributes...\n");
		f1.setMatricula(1048);// Não modificar
		f1.setNome("Maria da Silva"); // Data de nascimento
		String data = "06/01/1986";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date nasc = sdf.parse(data);
		f1.setDt_nasc(nasc);
		f1.setSexo("fem");
		f1.setTelefone("4004-2150"); // ###
		f1.setEndereco("Rua Levin 164"); // ###
		Date contratacao = sdf.parse(data);
		f1.setDt_contratacao(contratacao); //
		BigDecimal sal = new BigDecimal(1985.95); // ###
		f1.setSalario(sal);

		System.out.println("Validating and updating data...\n");
		FuncionarioDAO dao = new FuncionarioDAO();
		ControllerFuncionario ctr = new ControllerFuncionario();

		if (ctr.validate(f1))
			dao.save(f1);

		// Buscando novos dados
		Funcionario f = dao.findByMatricula(f1.getMatricula());
		// testando retorno
		Assertions.assertEquals("4004-2150", f.getTelefone());
		Assertions.assertEquals("Rua Levin 164", f.getEndereco());
		Assertions.assertEquals("1985.95", f.getSalario().toString());

		// Mostrando dados
		System.out.println("\nMatricula : " + f.getMatricula());
		System.out.println("\nNome : " + f.getNome());
		System.out.println("\nNascimento : " + f.getDt_nasc());
		System.out.println("\nSexo : " + f.getSexo());
		System.out.println("\nTelefone : " + f.getTelefone());
		System.out.println("\nEndereço : " + f.getEndereco());
		System.out.println("\nContratação : " + f.getDt_contratacao());
		System.out.println("\nSalario : " + f.getSalario());

		System.out.println("\nPASS\n");

		System.out.println("testUpdate() finalized.\n");

	}

	@Test
	@Order(8)
	void testRemove() {

		System.out.println("\ntestRemove() running...\n");

		System.out.println("Setting matricula number...\n");
		int matricula = 1072;

		System.out.println("Removing Funcionario...\n");

		FuncionarioDAO dao = new FuncionarioDAO();
		dao.remove(matricula);

		// Verificar se foi removido
		System.out.println("Searching...\n");
		Funcionario f = dao.findByMatricula(matricula);

		// Não deve retornar nada pois foi excluido
		Assertions.assertNull(f);

		System.out.println("Nothing found");

		System.out.println("\nPASS\n");

		System.out.println("testRemove() finalized.\n");

	}

	
	@Test
	@Order(6)
	void testfindByNomeLike() {
	
		System.out.println("\ntestfindByNomeLike() running...\n");
	
		System.out.println("Setting letter ...\n");
	
		char letter = 'm';
	
		FuncionarioDAO dao = new FuncionarioDAO();
	
		System.out.println("Searching 'm'...\n");
		List<Funcionario> funcionarios = dao.findByNomeLike(String.valueOf(letter));
	
		// Verificando acertos
		Assertions.assertFalse(funcionarios.isEmpty());
	
		System.out.println("\nAll data retrieved\n");
	
		// Mostrando dados
		for (Funcionario f : funcionarios) {
	
			System.out.println("\nMatricula : " + f.getMatricula());
			System.out.println("\nNome : " + f.getNome());
			System.out.println("\nNascimento : " + f.getDt_nasc());
			System.out.println("\nSexo : " + f.getSexo());
			System.out.println("\nTelefone : " + f.getTelefone());
			System.out.println("\nEndereço : " + f.getEndereco());
			System.out.println("\nContratação : " + f.getDt_contratacao());
			System.out.println("\nSalario : " + f.getSalario());
			System.out.println("--------------------------------");
		}
	
		System.out.println("\nPASS\n");
	
		System.out.println("testfindByNomeLike() finalized.\n");
	
	}

	@Test
	@Order(7)
	void testfindByNomeLikeNotExists() {

		System.out.println("\ntestfindByNomeLikeNotExists() running...\n");

		System.out.println("Setting letter ...\n");

		char letter = 'z';

		FuncionarioDAO dao = new FuncionarioDAO();

		System.out.println("Searching 'z'...\n");
		List<Funcionario> funcionarios = dao.findByNomeLike(String.valueOf(letter));

		// Verificando acertos
		Assertions.assertTrue(funcionarios.isEmpty());

		System.out.println("\nData not found");

		System.out.println("\nPASS\n");

		System.out.println("testfindByNomeLikeNotExists() finalized.\n");

	}

}
