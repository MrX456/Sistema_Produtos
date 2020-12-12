/*
 * Testes unitários da classe de controle de funcionario
*/

package com.system.junit.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.system.controller.ControllerFuncionario;
import com.system.model.bean.Funcionario;

/*
 * Sistema Produtos/ Junit / Controller / Funcionario
 * @author MRX
 * Version : 1.0.0
 */

class ControllerFuncionarioTest {

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
	void testValidate() throws ParseException {
		
		String[] sexo = { "fem", "masc" };
		
		System.out.println("testValidate() running...\n");

		System.out.println("All atributes must be validate\n");

		System.out.println("Creating Funcionario instance...\n");
		Funcionario f = new Funcionario();
		
		System.out.println("Setting Funcionario attributes...\n");
		
		String  data = "12/06/1974";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date nasc = sdf.parse(data);
		
		String  data2 = "11/04/2016";
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		Date cont = sdf2.parse(data2);
		
		BigDecimal sal = new BigDecimal(1745.90);
		
		f.setMatricula(1048);
		f.setNome("Maria Rita de Cassia");
		f.setDt_nasc(nasc);
		f.setSexo(sexo[0]);
		f.setEndereco("Rua A 234");
		f.setDt_contratacao(cont);
		f.setSalario(sal);

		ControllerFuncionario f1 = new ControllerFuncionario();
		boolean result = f1.validate(f);
		Assertions.assertTrue(result);

		System.out.println("matricula = " + f.getMatricula());
		System.out.println("nome length = " + f.getNome().length());
		DateFormat df = sdf;
		System.out.println("dt nasc = " + df.format(f.getDt_nasc()));
		System.out.println("sexo length = " + f.getSexo().length());
		System.out.println("endereco length = " + f.getEndereco().length());
		System.out.println("data contratacao = " + df.format(f.getDt_contratacao()));
		System.out.println("salario = " + f.getSalario().toString().substring(0, 7));
		

		if (result)
			System.out.println("\nPASS\n");

		System.out.println("testValidate() finalized.\n");

	}
	
	@Test
	void validateNullField() throws ParseException {

		String[] sexo = { "fem", "masc" };

		System.out.println("validateNullField() running...\n");

		System.out.println("All atributes must be validate\n");

		System.out.println("Creating Funcionario instance...\n");
		Funcionario f = new Funcionario();

		System.out.println("Setting Funcionario attributes...\n");

		String data = "12/06/1974";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date nasc = sdf.parse(data);

		String data2 = "11/04/2016";
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		Date cont = sdf2.parse(data2);

		BigDecimal sal = new BigDecimal(1745.90);

		f.setMatricula(1048);
		f.setNome("");
		f.setDt_nasc(nasc);
		f.setSexo(sexo[0]);
		f.setEndereco("");
		f.setDt_contratacao(cont);
		f.setSalario(sal);

		ControllerFuncionario f1 = new ControllerFuncionario();
		boolean result = f1.validate(f);
		Assertions.assertFalse(result);

		System.out.println("matricula = " + f.getMatricula());
		System.out.println("nome empty = " + f.getNome().length());
		DateFormat df = sdf;
		System.out.println("dt nasc = " + df.format(f.getDt_nasc()));
		System.out.println("sexo length = " + f.getSexo().length());
		System.out.println("endereco empty = " + f.getEndereco().length());
		System.out.println("data contratacao = " + df.format(f.getDt_contratacao()));
		System.out.println("salario = " + f.getSalario().toString().substring(0, 7));

		if (!result)
			System.out.println("\nPASS\n");

		System.out.println("validateNullField() finalized.\n");

	}
	
	@Test
	void testOverflowFieldMaxChar() throws ParseException {
		
		String[] sexo = { "fem", "masc" };

		System.out.println("testOverflowFieldMaxChar() running...\n");

		System.out.println("All atributes must be validate[max 80 chars]\n");

		System.out.println("Creating Funcionario instance...\n");
		Funcionario f = new Funcionario();

		System.out.println("Setting Funcionario attributes...\n");

		String data = "12/06/1974";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date nasc = sdf.parse(data);

		String data2 = "11/04/2016";
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		Date cont = sdf2.parse(data2);

		BigDecimal sal = new BigDecimal(1745.90);

		System.out.println("Generating String...\n");
		String text = "";
		Integer counter = 1;

		while (counter < 82) {
			text = text + "x";
			counter++;
		}

		f.setMatricula(1048);
		f.setNome(text);
		f.setDt_nasc(nasc);
		f.setSexo(sexo[0]);
		f.setEndereco(text);
		f.setDt_contratacao(cont);
		f.setSalario(sal);

		ControllerFuncionario f1 = new ControllerFuncionario();
		boolean result = f1.validate(f);
		Assertions.assertFalse(result);

		System.out.println("matricula = " + f.getMatricula());
		System.out.println("nome length = " + f.getNome().length());
		DateFormat df = sdf;
		System.out.println("dt nasc = " + df.format(f.getDt_nasc()));
		System.out.println("sexo length = " + f.getSexo().length());
		System.out.println("endereco length = " + f.getEndereco().length());
		System.out.println("data contratacao = " + df.format(f.getDt_contratacao()));
		System.out.println("salario = " + f.getSalario().toString().substring(0, 7));

		if (!result)
			System.out.println("\nPASS\n");

		System.out.println("testOverflowFieldMaxChar() finalized.\n");
		
	}

}
