package com.fatr.model;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fatr.dao.SerieDao;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(locations = "/spring/test-application.xml")
public class SerieTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private Serie serie;

    @Autowired
    private SerieId id;

    @Test
    public void testA_insert() {

	em.getTransaction().begin();
	new SerieDao(em).cadastrar(serie);
	em.getTransaction().commit();

	Serie serieFound = em.find(Serie.class, serie.getId());

	MatcherAssert.assertThat(serie.getId(), Matchers.equalTo(serieFound.getId()));
	MatcherAssert.assertThat(serie.getId().getId_emit(), Matchers.equalTo(serieFound.getId().getId_emit()));
	MatcherAssert.assertThat(serie.getId().getSerie(), Matchers.equalTo(serieFound.getId().getSerie()));

	MatcherAssert.assertThat(serie.getNnf_inicial(), Matchers.equalTo(serieFound.getNnf_inicial()));
	MatcherAssert.assertThat(serie.getNnf_final(), Matchers.equalTo(serieFound.getNnf_final()));
	em.detach(serie);
    }

    @Test(expected = RollbackException.class)
    public void testB_insert_duplicate_primary_key() {

	em.getTransaction().begin();
	new SerieDao(em).cadastrar(serie);
	em.getTransaction().commit();

    }

    @Test
    public void testC_insert_delete() {

	Serie serie = em.find(Serie.class, id);

	MatcherAssert.assertThat(serie, Matchers.notNullValue());

	em.getTransaction().begin();
	em.remove(serie);
	em.getTransaction().commit();

	serie = em.find(Serie.class, id);

	MatcherAssert.assertThat(serie, Matchers.nullValue());
    }
}
