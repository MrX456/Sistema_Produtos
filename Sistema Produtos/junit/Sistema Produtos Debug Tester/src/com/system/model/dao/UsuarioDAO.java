/*
 * Classe responsavel pelo acesso e CRUD de Usuario
 */

package com.system.model.dao;

import com.system.connection.ConnectionFactory;
import com.system.model.bean.Usuario;
import java.util.List;
import javax.persistence.EntityManager;


/*
 * Sistema Produtos/ Model / DAO / Usuario
 * @author MRX
 * Version : 1.0.0
 */

public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public Usuario save(Usuario usuario) {

        EntityManager em = new ConnectionFactory().getConnection();

        try {

            em.getTransaction().begin();

            //Se o usuario ja possui um id
            if (usuario.getId() != null) {

                em.merge(usuario);

            } else {

                em.persist(usuario);

            }

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();

        }

        return usuario;

    }

    @Override
    public Usuario findById(int id) {

        EntityManager em = new ConnectionFactory().getConnection();

        Usuario usuario = null;

        try {

            usuario = em.find(Usuario.class, id);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return usuario;

    }

    @Override
    public List<Usuario> findAll() {

        EntityManager em = new ConnectionFactory().getConnection();

        List<Usuario> usuarios = null;

        try {

            usuarios = em.createQuery("from Usuario usu").getResultList();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return usuarios;
    }

    @Override
    public Usuario remove(int id) {
        
        EntityManager em = new ConnectionFactory().getConnection();

        Usuario usuario = null;
        
        try {
            
            //Buscar usuario para deletar
            usuario = em.find(Usuario.class, id);
            
            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            
            em.getTransaction().rollback();
            e.printStackTrace();
            
        } finally {
            
            em.close();
            
        }
        
        return usuario;
    }

    @Override
    public Usuario findByLoginSenha(String login, String senha) {
        
        EntityManager em = new ConnectionFactory().getConnection();

        Usuario usuario = null;

        //Método será usuado para permitir acesso ao sistema
        try {

            org.hibernate.Query q = (org.hibernate.Query) em.createQuery("from Usuario u where u.login in :login and u.senha in :senha");
            q.setParameter("login", login);
            q.setParameter("senha", senha);
            usuario = (Usuario) q.uniqueResult();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            em.close();

        }

        return usuario;

    }

}
