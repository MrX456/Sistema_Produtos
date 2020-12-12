/*
 * Interface responsavel pelos métodos CRUD de Funcionario
 */

package com.system.model.dao;

import com.system.model.bean.Funcionario;
import java.util.List;

/*
 * Sistema Produtos/ Model / DAO / IFuncionario
 * @author MRX
 * Version : 1.0.0
 */

public interface IFuncionarioDAO {
    
    public abstract Funcionario save(Funcionario funcionario);
    
    public abstract Funcionario findByMatricula(int matricula);
    
    public abstract List<Funcionario> findAll();
    
    public abstract Funcionario remove(int matricula);
    
    public abstract List<Funcionario> findByNomeLike(String nome);
    
}
