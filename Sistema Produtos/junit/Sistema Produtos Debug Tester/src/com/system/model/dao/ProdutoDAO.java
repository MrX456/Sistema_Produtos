/*
 * Classe responsavel pelo acesso e CRUD de Produto
 */
package com.system.model.dao;

import com.system.connection.ConnectionFactory;
import com.system.model.bean.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/*
 * Sistema Produtos/ Model / DAO / Produto
 * @author MRX
 * Version : 1.0.0
 */
public class ProdutoDAO implements IProdutoDAO {

    @Override
    public Produto save(Produto produto) {

        //Entity manager e conexão
        EntityManager em = new ConnectionFactory().getConnection();

        try {

            em.getTransaction().begin();

            //Caso produto ja esteja cadastrad0 vamos atualizar
            if (produto.getId() != null) {
                em.merge(produto);

                //Caso a produto não possua um id vamos usar o create
            } else {
                //Persistindo dados(inserção)
                em.persist(produto);
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            //O hibernate possui este método para desfazer alterações em caso de erro
            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();

        }

        return produto;

    }

    @Override
    public Produto findById(int id) {

        EntityManager em = new ConnectionFactory().getConnection();

        Produto produto = null;

        try {

            produto = em.find(Produto.class, id);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return produto;

    }

    @Override
    public List<Produto> findAll() {

        EntityManager em = new ConnectionFactory().getConnection();

        List<Produto> produtos = null;

        try {

            produtos = em.createQuery("from Produto prod").getResultList();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return produtos;

    }

    @Override
    public Produto remove(int id) {

        EntityManager em = new ConnectionFactory().getConnection();

        Produto produto = null;

        try {

            //Buscar produto para deletar
            produto = em.find(Produto.class, id);

            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();

        }

        return produto;

    }

    @Override
    public List<Produto> findByDescricaoLike(String descricao) {

        //Entity manager e conexão
        EntityManager em = new ConnectionFactory().getConnection();

        List<Produto> produtos = null;

        try {

            //Query com clausula like. Wildcard esta depois do parametro
            Query q = em.createQuery("from Produto prod where prod.descricao like :descricao");
            q.setParameter("descricao", descricao + "%");
            produtos = q.getResultList();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return produtos;
    }

}
