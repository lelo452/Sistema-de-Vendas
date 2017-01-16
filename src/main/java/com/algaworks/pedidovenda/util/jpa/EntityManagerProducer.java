package com.algaworks.pedidovenda.util.jpa;

import org.hibernate.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

    private EntityManagerFactory emf;

    public EntityManagerProducer() {
        emf = Persistence.createEntityManagerFactory("PedidoPU");
    }

    @Produces
    @RequestScoped
    public Session createEntityManager() {
        return (Session) emf.createEntityManager();
    }

    public void closeEntityManager(@Disposes Session manager) {
        manager.close();
    }
}
