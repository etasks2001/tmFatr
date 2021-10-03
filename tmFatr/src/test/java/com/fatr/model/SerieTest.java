package com.fatr.model;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fatr.dao.SerieDao;
import com.fatr.util.JpaUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SerieTest {
	private EntityManager em;

	@Test
	public void testA_insert() {
		this.em = JpaUtil.getEntityManager();
		SerieId id = new SerieId(1, 55);
		Integer nnf_inicial = 791;
		Integer nnf_final = 900;
		Serie serie = new Serie(id, nnf_inicial, nnf_final);

		em.getTransaction().begin();
		new SerieDao(em).cadastrar(serie);
		em.getTransaction().commit();

		serie = em.find(Serie.class, id);

		MatcherAssert.assertThat(id, Matchers.equalTo(serie.getId()));
		MatcherAssert.assertThat(serie.getNnf_inicial(), Matchers.equalTo(nnf_inicial));
		MatcherAssert.assertThat(serie.getNnf_final(), Matchers.equalTo(nnf_final));
		em.close();

	}

	@Test(expected = RollbackException.class)
	public void testB_insert_duplicate_primary_key() {
		this.em = JpaUtil.getEntityManager();
		SerieId id = new SerieId(1, 55);
		Integer nnf_inicial = 791;
		Integer nnf_final = 900;
		Serie serie = new Serie(id, nnf_inicial, nnf_final);

		em.getTransaction().begin();
		new SerieDao(em).cadastrar(serie);
		em.getTransaction().commit();

	}

	@Test
	public void testC_insert_delete() {
		this.em = JpaUtil.getEntityManager();
		SerieId id = new SerieId(1, 55);

		Serie serie = em.find(Serie.class, id);

		em.getTransaction().begin();
		em.remove(serie);
		em.getTransaction().commit();

		serie = em.find(Serie.class, id);

		MatcherAssert.assertThat(serie, Matchers.nullValue());
	}
}
