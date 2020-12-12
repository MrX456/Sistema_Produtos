/*
 * Testes unitários da classe de controle de Categoria
*/
package com.system.junit.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.system.controller.ControllerCategoria;
import com.system.model.bean.Categoria; 

/*
 * Sistema Produtos/ Junit / Controller / Categoria
 * @author MRX
 * Version : 1.0.0
 */

class ControllerCategoriaTest {

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
	void testValidate() {

		System.out.println("testValidate() running...\n");

		System.out.println("Descricao must be validate\n");

		System.out.println("Creating Categoria instance...\n");
		Categoria c = new Categoria();
		
		System.out.println("Setting Categoria attributes...\n");
		c.setDescricao("Games");
		
		ControllerCategoria c1 = new ControllerCategoria();
		boolean result = c1.validate(c);
		Assertions.assertTrue(result);
		
		System.out.println("Descrição : " + c.getDescricao());
		
		if (result)
			System.out.println("\nPASS\n");

		System.out.println("testValidate() finalized.\n");
		
	}
	

	@Test
	void testValidateNullField() {
		
		System.out.println("validateNullField() running...\n");
		
		System.out.println("Descricao must be validate\n");

		System.out.println("Creating Categoria instance...\n");
		Categoria c = new Categoria();
		
		System.out.println("Setting Categoria attributes...\n");
		c.setDescricao("");
		
		ControllerCategoria c1 = new ControllerCategoria();
		boolean result = c1.validate(c);
		Assertions.assertFalse(result);
		
		if (!result)
			System.out.println("\nPASS\n");

		System.out.println("validateNullField() finalized.\n");
		
	}
	
	@Test
	void testOverflowFieldMaxChar() {
		
		System.out.println("testOverflowFieldMaxChar() running...\n");
		
		System.out.println("Descricao must be validate[max 80 chars]\n");

		System.out.println("Creating Categoria instance...\n");
		Categoria c = new Categoria();
		
		System.out.println("Generating String...\n");
		String text = "";
		Integer counter = 1;

		while (counter < 82) {
			text = text + "x";
			counter++;
		}
		
		System.out.println("Setting Categoria attributes...\n");
		c.setDescricao(text);
		
		ControllerCategoria c1 = new ControllerCategoria();
		boolean result = c1.validate(c);
		Assertions.assertFalse(result);
		
		System.out.println("descricao length = " + c.getDescricao().length());
		
		if (!result)
			System.out.println("\nPASS\n");

		System.out.println("testOverflowFieldMaxChar() finalized.\n");
		
	}

}
