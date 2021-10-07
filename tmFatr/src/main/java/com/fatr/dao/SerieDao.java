package com.fatr.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.fatr.model.Serie;

public class SerieDao {
    private EntityManager em;

    @Autowired
    public SerieDao(EntityManager em) {
	this.em = em;
    }

    public void cadastrar(Serie serie) {
	em.getTransaction().begin();
	em.persist(serie);
	em.getTransaction().commit();
    }

    public Serie buscaPeloId(Object id) {

	return em.find(Serie.class, id);
    }

    public void apagar(Serie serie) {
	em.getTransaction().begin();
	em.remove(serie);
	em.getTransaction().commit();

    }

}
