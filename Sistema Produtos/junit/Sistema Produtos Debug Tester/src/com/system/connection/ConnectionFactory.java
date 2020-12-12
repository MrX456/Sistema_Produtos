/*
 * Classe responsavel pela conexão com o banco de dados
 */

package com.system.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Sistema Produtos/ Connection / ConnectionFactory
 * @author MRX
 * Version : 1.0.0
 */

public class ConnectionFactory {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("systemPU");
    
    //Abrir conexão
    public static EntityManager getConnection() {
        
        return emf.createEntityManager();
        
    }
    
}
