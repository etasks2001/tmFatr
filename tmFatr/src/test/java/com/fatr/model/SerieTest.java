package com.fatr.model;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class SerieTest {
	private EntityManager em;

	private EntityManagerFactory emf;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { Persistence.createEntityManagerFactory("nf") } });
	}

	public SerieTest(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Test
	public void testA_insert() {
		em = emf.createEntityManager();
		SerieId id = new SerieId(1, 55);
		Integer nnf_inicial = 791;
		Integer nnf_final = 900;
		Serie serie = new Serie(id, nnf_inicial, nnf_final);

		em.getTransaction().begin();
		em.persist(serie);
		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		serie = em.find(Serie.class, id);

		MatcherAssert.assertThat(id, Matchers.equalTo(serie.getId()));
		MatcherAssert.assertThat(serie.getNnf_inicial(), Matchers.equalTo(nnf_inicial));
		MatcherAssert.assertThat(serie.getNnf_final(), Matchers.equalTo(nnf_final));
		em.close();

	}

	@Test(expected = RollbackException.class)
	public void testB_insert_duplicate_primary_key() {
		em = emf.createEntityManager();
		SerieId id = new SerieId(1, 55);
		Integer nnf_inicial = 791;
		Integer nnf_final = 900;
		Serie serie = new Serie(id, nnf_inicial, nnf_final);

		em.getTransaction().begin();
		em.persist(serie);
		em.getTransaction().commit();

	}

	@Test
	public void testC_insert_delete() {
		em = emf.createEntityManager();
		SerieId id = new SerieId(1, 55);

		Serie serie = em.find(Serie.class, id);

		em.getTransaction().begin();
		em.remove(serie);
		em.getTransaction().commit();

		em = emf.createEntityManager();
		serie = em.find(Serie.class, id);

		MatcherAssert.assertThat(serie, Matchers.nullValue());
	}
}
