package com.fatr.dao;

import javax.persistence.EntityManager;

import com.fatr.model.Serie;

public class SerieDao {

	private EntityManager em;

	public SerieDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Serie serie) {
		em.persist(serie);

	}

}
