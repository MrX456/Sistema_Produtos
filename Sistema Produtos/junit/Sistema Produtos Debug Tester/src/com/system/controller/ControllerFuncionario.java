/*
 * Classe responsavel pela implementação dos métodos de controle de Funcionario
 */
package com.system.controller;

import com.system.model.bean.Funcionario;

/*
 * Sistema Produtos/ Controller / Funcionario
 * @author MRX
 * Version : 1.0.0
 */
public class ControllerFuncionario extends Controller implements IControllerFuncionario {

    @Override
    public boolean validate(Funcionario funcionario) {

        if (funcionario.getMatricula().toString().trim().isEmpty()) {

            //MESSAGE HERE
            return false;

        } else if (funcionario.getNome().trim().isEmpty() || funcionario.getNome().length() > 80) {

            //MESSAGE HERE
            return false;

        } else if (funcionario.getDt_nasc().toString().trim().endsWith("/")) {

            //MESSAGE HERE
            return false;

        } else if (funcionario.getSexo().trim().isEmpty() || funcionario.getSexo().length() > 4) {

            //MESSAGE HERE
            return false;

        } else if (funcionario.getEndereco().trim().isEmpty() || funcionario.getEndereco().length() > 80) {

            //MESSAGE HERE
            return false;

        } else if (funcionario.getDt_contratacao().toString().trim().endsWith("/")) {

            //MESSAGE HERE
            return false;

        } else if (funcionario.getSalario().toString().trim().isEmpty()) {

            //MESSAGE HERE
            return false;

        }

        return true;

    }

}
