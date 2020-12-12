/*
 * Classe responsavel pelo acesso e CRUD de Categoria
 */
package com.system.model.dao;

import com.system.connection.ConnectionFactory;
import com.system.model.bean.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/*
 * Sistema Produtos/ Model / DAO / Categoria
 * @author MRX
 * Version : 1.0.0
 */
public class CategoriaDAO implements ICategoriaDAO {

    @Override
    public Categoria save(Categoria categoria) {

        EntityManager em = new ConnectionFactory().getConnection();

        try {

            em.getTransaction().begin();

            //Se o categoria ja possui um id
            if (categoria.getId() != null) {

                em.merge(categoria);

            } else {

                em.persist(categoria);

            }

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();

        }

        return categoria;

    }

    @Override
    public Categoria findById(int id) {

        EntityManager em = new ConnectionFactory().getConnection();

        Categoria categoria = null;

        try {

            categoria = em.find(Categoria.class, id);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return categoria;

    }

    @Override
    public List<Categoria> findAll() {

        EntityManager em = new ConnectionFactory().getConnection();

        List<Categoria> categorias = null;

        try {

            categorias = em.createQuery("from Categoria cat").getResultList();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return categorias;
    }

    @Override
    public Categoria remove(int id) {

        EntityManager em = new ConnectionFactory().getConnection();

        Categoria categoria = null;

        try {

            //Buscar categoria para deletar
            categoria = em.find(Categoria.class, id);

            em.getTransaction().begin();
            em.remove(categoria);
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();

        }

        return categoria;

    }

    @Override
    public List<Categoria> findByDescricaoLike(String descricao) {

        //Entity manager e conexão
        EntityManager em = new ConnectionFactory().getConnection();

        List<Categoria> categorias = null;

        try {

            //Query com clausula like. Wildcard esta depois do parametro
            Query q = em.createQuery("from Categoria cat where cat.descricao like :descricao");
            q.setParameter("descricao", descricao + "%");
            categorias = q.getResultList();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return categorias;

    }

}
