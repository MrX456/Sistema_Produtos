/*
 * Classe responsavel pela implementação dos métodos de controle gerais
 */

package com.system.controller;

/*
 * Sistema Produtos/ Controller
 * @author MRX
 * Version : 1.0.0
 */

public abstract class Controller implements IController {

    @Override
    public boolean reachMaxLength(int maxLength, String text) {

        //Limitar o numero de caracteres em cada caixa de texto
        if (text.trim().length() > maxLength) {
            return true;
        } else {
            return false;
        }

    }

}
