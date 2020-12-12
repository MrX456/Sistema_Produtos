/*
 * Testes unitários da classe de controle de Produto
*/

package com.system.junit.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/*
 * Sistema Produtos/ Junit / Controller / Produto
 * @author MRX
 * Version : 1.0.0
 */

import org.junit.jupiter.api.Test;

import com.system.controller.ControllerCategoria;
import com.system.controller.ControllerProduto;
import com.system.model.bean.Categoria;
import com.system.model.bean.Produto;

class ControllerProdutoTest {

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

		System.out.println("All fields must be validate\n");

		System.out.println("Creating Produto instance...\n");
		Produto p = new Produto();

		System.out.println("Setting Produto attributes...\n");
		p.setDescricao("Playstation 5 All Digital");
		p.setQtde(5);
		BigDecimal val = new BigDecimal(4200.95);
		p.setValor(val);
		Categoria cat = new Categoria();
		cat.setId(1);
		p.setCategoria(cat);

		ControllerProduto c1 = new ControllerProduto();
		boolean result = c1.validate(p);
		Assertions.assertTrue(result);

		System.out.println("Descrição : " + p.getDescricao());
		System.out.println("QTDE : " + p.getQtde());
		System.out.println("Valor : " + p.getValor().toString().substring(0, 7));
		System.out.println("Categoria ID : " + p.getCategoria().getId());

		if (result)
			System.out.println("\nPASS\n");

		System.out.println("testValidate() finalized.\n");

	}

	@Test
	void testValidateNullField() {

		System.out.println("validateNullField() running...\n");

		System.out.println("Descricao must be validate\n");

		System.out.println("Creating Produto instance...\n");
		Produto p = new Produto();

		System.out.println("Setting Produto attributes...\n");
		p.setDescricao("");
		p.setQtde(5);
		BigDecimal val = new BigDecimal(4200.95);
		p.setValor(val);
		Categoria cat = new Categoria();
		cat.setId(1);
		p.setCategoria(cat);

		ControllerProduto c1 = new ControllerProduto();
		boolean result = c1.validate(p);
		Assertions.assertFalse(result);

		if (!result)
			System.out.println("\nPASS\n");

		System.out.println("validateNullField() finalized.\n");

	}

	@Test
	void testOverflowFieldMaxChar() {

		System.out.println("testOverflowFieldMaxChar() running...\n");

		System.out.println("Descricao must be validate[max 80 chars]\n");

		System.out.println("Creating Produto instance...\n");
		Produto p = new Produto();

		System.out.println("Generating String...\n");
		String text = "";
		Integer counter = 1;

		while (counter < 82) {
			text = text + "x";
			counter++;
		}

		System.out.println("Setting Categoria attributes...\n");
		p.setDescricao(text);
		p.setQtde(5);
		BigDecimal val = new BigDecimal(4200.95);
		p.setValor(val);
		Categoria cat = new Categoria();
		cat.setId(1);
		p.setCategoria(cat);

		ControllerProduto c1 = new ControllerProduto();
		boolean result = c1.validate(p);
		Assertions.assertFalse(result);

		System.out.println("descricao length = " + p.getDescricao().length());

		if (!result)
			System.out.println("\nPASS\n");

		System.out.println("testOverflowFieldMaxChar() finalized.\n");

	}

}
