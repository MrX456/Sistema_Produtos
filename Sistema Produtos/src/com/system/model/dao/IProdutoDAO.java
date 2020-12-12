/*
 * Interface responsavel pelos métodos CRUD de Produto
 */
package com.system.model.dao;

import com.system.model.bean.Produto;
import java.util.List;

/*
 * Sistema Produtos/ Model / DAO / IProduto
 * @author MRX
 * Version : 1.0.0
 */
public interface IProdutoDAO {
    
    public abstract Produto save(Produto produto);

    public abstract Produto findById(int id);

    public abstract List<Produto> findAll();

    public abstract Produto remove(int id);

    public abstract List<Produto> findByDescricaoLike(String descricao);
    
}
