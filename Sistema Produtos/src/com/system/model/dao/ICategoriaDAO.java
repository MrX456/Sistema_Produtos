/*
 * Interface responsavel pelos métodos CRUD de Categpria
 */
package com.system.model.dao;

import com.system.model.bean.Categoria;
import java.util.List;

/*
 * Sistema Produtos/ Model / DAO / ICategoria
 * @author MRX
 * Version : 1.0.0
 */
public interface ICategoriaDAO {

    public abstract Categoria save(Categoria categoria);

    public abstract Categoria findById(int id);

    public abstract List<Categoria> findAll();

    public abstract Categoria remove(int id);

    public abstract List<Categoria> findByDescricaoLike(String descricao);

}
