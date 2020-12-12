/*
 * Testes unitários da classe de controle de usuário
*/

package com.system.junit.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.system.controller.*;
import com.system.model.bean.Usuario;
import com.system.model.dao.UsuarioDAO;

/*
 * Sistema Produtos/ Junit / Controller / Usuario
 * @author MRX
 * Version : 1.0.0
 */

@TestMethodOrder(OrderAnnotation.class)
class ControllerUsuarioTest {

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
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
		System.out.println("\n\n");
	}

	@Test
	@Order(3)
	void testValidate() {

		String[] sexo = { "fem", "masc" };
		String[] perfil = { "admin", "usuário" };

		System.out.println("testValidate() running...\n");

		System.out.println("All atributes must be validate\n");

		System.out.println("Creating Usuario instance...\n");
		Usuario u = new Usuario();

		System.out.println("Setting Usuario attributes...\n");
		u.setLogin("claudia123");
		u.setSenha("1234");
		u.setPerfil(perfil[0]);
		u.setSexo(sexo[0]);

		ControllerUsuario u1 = new ControllerUsuario();
		boolean result = u1.validate(u);
		Assertions.assertTrue(result);

		System.out.println("login length = " + u.getLogin().length());
		System.out.println("senha length = " + u.getSenha().length());
		System.out.println("perfil length = " + u.getPerfil().length());
		System.out.println("sexo length = " + u.getSexo().length());

		if (result)
			System.out.println("\nPASS\n");

		System.out.println("testValidate() finalized.\n");

	}

	@Test
	@Order(4)
	void testValidateFailNullField() {

		String[] sexo = { "fem", "masc" };
		String[] perfil = { "admin", "usuário" };

		System.out.println("testValidateFailNullField() running...\n");

		System.out.println("Empty field login\n");

		System.out.println("Creating Usuario instance...\n");
		Usuario u = new Usuario();

		System.out.println("Setting Usuario atributes...\n");
		u.setLogin("");
		u.setSenha("1234");
		u.setPerfil(perfil[0]);
		u.setSexo(sexo[0]);

		ControllerUsuario u1 = new ControllerUsuario();
		boolean result = u1.validate(u);
		Assertions.assertFalse(result);

		System.out.println("login length = empty " + u.getLogin().length());
		System.out.println("senha length = " + u.getSenha().length());
		System.out.println("perfil length = " + u.getPerfil().length());
		System.out.println("sexo length = " + u.getSexo().length());

		if (!result)
			System.out.println("\nPASS\n");

		System.out.println("testValidateFailNullField() finalized.\n");

	}

	@Test
	@Order(5)
	void testValidateFailOverflowLengthField() {

		String[] sexo = { "fem", "masc" };
		String[] perfil = { "admin", "usuário" };

		System.out.println("testValidateFailOverflowLengthField() running...\n");

		System.out.println("Overflow field senha[limit characters 10]\n");

		System.out.println("Generating String...\n");

		String text = "";
		Integer counter = 0;

		while (counter < 11) {
			text = text + "a";
			counter++;
		}

		System.out.println("Creating Usuario instance...\n");
		Usuario u = new Usuario();

		System.out.println("Setting Usuario atributes...\n");
		u.setLogin("cs1234");
		u.setSenha(text);
		u.setPerfil(perfil[0]);
		u.setSexo(sexo[0]);

		ControllerUsuario u1 = new ControllerUsuario();
		boolean result = u1.validate(u);
		Assertions.assertFalse(result);

		System.out.println("login length " + u.getLogin().length());
		System.out.println("senha length = " + u.getSenha().length());
		System.out.println("perfil length = " + u.getPerfil().length());
		System.out.println("sexo length = " + u.getSexo().length());

		if (!result)
			System.out.println("\nPASS\n");

		System.out.println("testValidateFailOverflowLengthField() finalized.\n");

	}

	@Test
	@Order(6)
	void testLoginAdmin() {

		System.out.println("testLoginAdmin() running...\n");

		System.out.println("Generating values...\n");
		String login = "admin02";
		String senha = "admin";

		// Buscar usuario
		UsuarioDAO dao = new UsuarioDAO();

		System.out.println("Searching...\n");
		Usuario u = dao.findByLoginSenha(login, senha);

		System.out.println("\nVerifing profile...\n");

		Assertions.assertEquals("admin", u.getPerfil());

		// Fazer login
		ControllerUsuario ctr = new ControllerUsuario();
		ctr.login(u);

		System.out.println("\nPASS\n");

		System.out.println("testLoginAdmin() finalized.\n");

	}

	@Test
	@Order(7)
	void testLoginUsuario() {

		System.out.println("testLoginUsuario() running...\n");

		System.out.println("Generating values...\n");
		String login = "clara4324";
		String senha = "rsdf";

		// Buscar usuario
		UsuarioDAO dao = new UsuarioDAO();

		System.out.println("Searching...\n");
		Usuario u = dao.findByLoginSenha(login, senha);

		System.out.println("\nVerifing profile...\n");

		Assertions.assertEquals("usuario", u.getPerfil());

		// Fazer login
		ControllerUsuario ctr = new ControllerUsuario();
		ctr.login(u);

		System.out.println("\nPASS\n");

		System.out.println("testLoginUsuario() finalized.\n");

	}

	@Test
	@Order(8)
	void testLoginFailed() {

		System.out.println("testLoginFailed() running...\n");

		System.out.println("Generating values...\n");
		String login = "abc123";
		String senha = "asdf";

		// Buscar usuario que não existe
		UsuarioDAO dao = new UsuarioDAO();

		System.out.println("Searching...\n");
		Usuario u = dao.findByLoginSenha(login, senha);

		Assertions.assertNull(u);
		
		System.out.println("\nUser not found\n");

		// Fazer login
		ControllerUsuario ctr = new ControllerUsuario();
		ctr.login(u);

		System.out.println("\nPASS\n");

		System.out.println("testLoginFailed() finalized.\n");

	}

	@Test
	@Order(1)
	void testReachMaxLength() {

		System.out.println("testReachMaxLength() running...\n");

		System.out.println("Max char allowed 10\n");

		System.out.println("Generating String...\n");

		String text = "";
		Integer counter = 0;

		while (counter < 10) {
			text = text + counter.toString();
			counter++;
		}

		// Verificando se texto excedeu o numero maximo de caracteres permitidos(max 10)
		ControllerUsuario u = new ControllerUsuario();
		Assertions.assertFalse(u.reachMaxLength(10, text));

		// Mostrando qtde de caracteres da String gerada
		System.out.println("String length = " + text.length());
		System.out.println(text);
		if (text.length() < 11)
			System.out.println("\nPASS\n");

		System.out.println("testReachMaxLength() finalized.\n");

	}

	@Test
	@Order(2)
	void testReachMaxLengthOverflow() {

		System.out.println("testReachMaxLengthOverflow() running...\n");

		System.out.println("Max char allowed 10\n");

		System.out.println("Generating String...\n");

		String text = "";
		Integer counter = 1;

		while (counter < 11) {
			text = text + counter.toString();
			counter++;
		}

		// Verificando se texto excedeu o numero maximo de caracteres permitidos(max 10)
		ControllerUsuario u = new ControllerUsuario();
		Assertions.assertTrue(u.reachMaxLength(10, text));

		// Mostrando qtde de caracteres da String gerada
		System.out.println("String length = " + text.length());
		System.out.println(text);
		if (text.length() > 10)
			System.out.println("\nPASS\n");

		System.out.println("testReachMaxLengthOverflow() finalized.\n");

	}

}
