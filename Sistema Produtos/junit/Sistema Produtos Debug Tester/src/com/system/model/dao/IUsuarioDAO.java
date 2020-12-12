/*
 * Interface responsavel pelos métodos CRUD de Usuario
 */

package com.system.model.dao;

import com.system.model.bean.Usuario;
import java.util.List;

/*
 * Sistema Produtos/ Model / DAO / IUsuario
 * @author MRX
 * Version : 1.0.0
 */

public interface IUsuarioDAO {
    
    public abstract Usuario save(Usuario usuario);
    
    public abstract Usuario findById(int id);
    
    public abstract List<Usuario> findAll();
    
    public abstract Usuario remove(int id);
    
    public abstract Usuario findByLoginSenha(String login, String senha);
    
}
