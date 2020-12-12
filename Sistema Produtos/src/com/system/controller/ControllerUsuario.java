/*
 * Classe responsavel pela implementação dos métodos de controle de Usuario
 */

package com.system.controller;

import com.system.model.bean.Usuario;

/*
 * Sistema Produtos/ Controller / Usuario
 * @author MRX
 * Version : 1.0.0
 */

public class ControllerUsuario extends Controller implements IControllerUsuario {

    @Override
    public boolean validate(Usuario usuario) {

        if (usuario.getLogin().trim().isEmpty() || usuario.getLogin().length() > 25) {

            //MESSAGE HERE
            return false;

        } else if (usuario.getSenha().trim().isEmpty() || usuario.getSenha().length() > 10 || usuario.getSenha().length() < 4) {

            //MESSAGE HERE
            return false;

        } else if (usuario.getPerfil().trim().isEmpty() || usuario.getPerfil().length() > 7) {

            //MESSAGE HERE
            return false;

        } else if (usuario.getSexo().trim().isEmpty() || usuario.getSexo().length() > 4) {

            //MESSAGE HERE
            return false;

        }

        return true;

    }

    @Override
    public void login(Usuario usuario) {

        try {

            if (usuario.getPerfil().equals("admin")) {

                //Usuario admin(Permissões especiais)
                System.out.println("Bem vindo usuario administrador!");

            } else {

                //Usuario comum
                System.out.println("Bem vindo usuario comum!");

            }

        } catch (NullPointerException e) {

            //Usuario ou senha invalidos
            System.out.println("Acesso negado!");

        }

    }

}
