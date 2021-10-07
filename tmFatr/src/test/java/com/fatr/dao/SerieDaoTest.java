package com.fatr.dao;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fatr.model.Serie;
import com.fatr.model.SerieId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/test-application.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SerieDaoTest {
    @Autowired
    private SerieDao dao;
    @Autowired
    private EntityManager em;

    @Autowired
    private Serie serie;

    @Autowired
    private SerieId id;

    @Test
    public void testA_insert() {

	dao.cadastrar(serie);

	Serie serieFound = dao.buscaPeloId(serie.getId());

	MatcherAssert.assertThat(serieFound, Matchers.notNullValue());

	MatcherAssert.assertThat(serie.getId(), Matchers.equalTo(serieFound.getId()));
	MatcherAssert.assertThat(serie.getId().getId_emit(), Matchers.equalTo(serieFound.getId().getId_emit()));
	MatcherAssert.assertThat(serie.getId().getSerie(), Matchers.equalTo(serieFound.getId().getSerie()));

	MatcherAssert.assertThat(serie.getNnf_inicial(), Matchers.equalTo(serieFound.getNnf_inicial()));
	MatcherAssert.assertThat(serie.getNnf_final(), Matchers.equalTo(serieFound.getNnf_final()));
	em.detach(serie);
    }

    @Test
    public void testA1_buscaUsuarioNaoEncontrado() {

	Serie serie = dao.buscaPeloId(id);

	MatcherAssert.assertThat(serie, Matchers.nullValue());

    }

    @Test
    public void testB_insert_duplicate_primary_key() {
	Assert.assertThrows(RollbackException.class, () -> dao.cadastrar(serie));

    }

    @Test
    public void testC_insert_delete() {
	Serie serie = em.find(Serie.class, id);

	MatcherAssert.assertThat(serie, Matchers.notNullValue());

	dao.apagar(serie);

	serie = em.find(Serie.class, id);

	MatcherAssert.assertThat(serie, Matchers.nullValue());
    }

}
