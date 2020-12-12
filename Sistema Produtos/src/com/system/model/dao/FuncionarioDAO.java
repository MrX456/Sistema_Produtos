/*
 * Classe responsavel pelo acesso e CRUD de Funcionario
 */
package com.system.model.dao;

import com.system.connection.ConnectionFactory;
import com.system.model.bean.Funcionario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/*
 * Sistema Produtos/ Model / DAO / Funcionario
 * @author MRX
 * Version : 1.0.0
 */
public class FuncionarioDAO implements IFuncionarioDAO {

    @Override
    public Funcionario save(Funcionario funcionario) {

        EntityManager em = new ConnectionFactory().getConnection();

        try {

            em.getTransaction().begin();

            if (findByMatricula(funcionario.getMatricula()) != null) {
                em.merge(funcionario);
            } else {
                em.persist(funcionario);
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();

        }

        return funcionario;
    }

    @Override
    public Funcionario findByMatricula(int matricula) {

        EntityManager em = new ConnectionFactory().getConnection();

        Funcionario funcionario = null;

        try {

            funcionario = em.find(Funcionario.class, matricula);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return funcionario;

    }

    @Override
    public List<Funcionario> findAll() {

        EntityManager em = new ConnectionFactory().getConnection();

        List<Funcionario> funcionarios = null;

        try {

            funcionarios = em.createQuery("from Funcionario func").getResultList();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return funcionarios;

    }

    @Override
    public Funcionario remove(int matricula) {

        EntityManager em = new ConnectionFactory().getConnection();

        Funcionario funcionario = null;

        try {

            //Buscar funcionario para deletar
            funcionario = em.find(Funcionario.class, matricula);

            em.getTransaction().begin();
            em.remove(funcionario);
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();

        }

        return funcionario;
    }

    @Override
    public List<Funcionario> findByNomeLike(String nome) {

        //Entity manager e conexão
        EntityManager em = new ConnectionFactory().getConnection();

        List<Funcionario> funcionarios = null;

        try {

            //Query com clausula like. Wildcard esta depois do parametro
            Query q = em.createQuery("from Funcionario func where func.nome like :name");
            q.setParameter("name", nome + "%");
            funcionarios = q.getResultList();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return funcionarios;
    }

}
