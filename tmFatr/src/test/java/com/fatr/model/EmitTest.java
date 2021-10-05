package com.fatr.model;

import javax.persistence.EntityManager;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fatr.util.JPAUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmitTest {
    private String xLgr = "Rua sem saida";
    private String nro = "150";
    private String xCpl = "fdas";
    private String xBairro = "centro";
    private String cMun = "0000000";
    private String xMun = "são paulo";
    private String uF = "sp";
    private String cEP = "00000000";
    private String cPais = "0000";
    private String xPais = "brasil";
    private String fone = "12345789";
    private String cNPJ = "12340000000000";
    private String xNome = "Empresa";
    private String xFant = "";
    private String iE = "111222333444";
    private String iEST = "";
    private String iM = "12345";
    private String cNAE = "1234567";
    private String cRT = "1";
    private Integer nf_serie_atual = 0;
    private Integer ultima_nnf = 154;
    private static Integer id = null;

    private Emit createEmit() {
	Ender enderEmit = EnderBuilder.builder().addxLgr(xLgr).addNro(nro).addxCpl(xCpl).addxBairro(xBairro).addcMun(cMun).addxMun(xMun).addUF(uF).addCEP(cEP).addcPais(cPais).addxPais(xPais)
		.addFone(fone).get();

	Emit emit = EmitBuilder.builder().addId(null).addCNPJ(cNPJ).addxNome(xNome).addxFant(xFant).addEnderEmit(enderEmit).addIE(iE).addIEST(iEST).addIM(iM).addCNAE(cNAE).addCRT(cRT)
		.addNf_serie_atual(nf_serie_atual).addUltima_nnf(ultima_nnf).get();
	return emit;

    }

    @Test
    public void testA_insert() {
	EntityManager em = JPAUtil.getEntityManager();
	Emit emit = this.createEmit();
	em.getTransaction().begin();
	em.persist(emit);

	em.getTransaction().commit();
	id = emit.getId();
	em.close();

	em = JPAUtil.getEntityManager();
	emit = em.find(Emit.class, id);
	em.close();

	MatcherAssert.assertThat(emit, Matchers.notNullValue());
	MatcherAssert.assertThat(emit.getId(), Matchers.notNullValue());

	MatcherAssert.assertThat(emit.getId(), Matchers.equalTo(id));
	Ender enderEmit = emit.getEnderEmit();
	MatcherAssert.assertThat(enderEmit.getxLgr(), Matchers.equalTo(xLgr));
	MatcherAssert.assertThat(enderEmit.getNro(), Matchers.equalTo(nro));
	MatcherAssert.assertThat(enderEmit.getxCpl(), Matchers.equalTo(xCpl));
	MatcherAssert.assertThat(enderEmit.getxBairro(), Matchers.equalTo(xBairro));
	MatcherAssert.assertThat(enderEmit.getcMun(), Matchers.equalTo(cMun));
	MatcherAssert.assertThat(enderEmit.getxMun(), Matchers.equalTo(xMun));
	MatcherAssert.assertThat(enderEmit.getUF(), Matchers.equalTo(uF));
	MatcherAssert.assertThat(enderEmit.getCEP(), Matchers.equalTo(cEP));
	MatcherAssert.assertThat(enderEmit.getcPais(), Matchers.equalTo(cPais));
	MatcherAssert.assertThat(enderEmit.getxPais(), Matchers.equalTo(xPais));
	MatcherAssert.assertThat(enderEmit.getFone(), Matchers.equalTo(fone));
	MatcherAssert.assertThat(emit.getCNPJ(), Matchers.equalTo(cNPJ));
	MatcherAssert.assertThat(emit.getxNome(), Matchers.equalTo(xNome));
	MatcherAssert.assertThat(emit.getxFant(), Matchers.equalTo(xFant));
	MatcherAssert.assertThat(emit.getIE(), Matchers.equalTo(iE));
	MatcherAssert.assertThat(emit.getIEST(), Matchers.equalTo(iEST));
	MatcherAssert.assertThat(emit.getIM(), Matchers.equalTo(iM));
	MatcherAssert.assertThat(emit.getCNAE(), Matchers.equalTo(cNAE));
	MatcherAssert.assertThat(emit.getCRT(), Matchers.equalTo(cRT));
	MatcherAssert.assertThat(emit.getNf_serie_atual(), Matchers.equalTo(nf_serie_atual));
	MatcherAssert.assertThat(emit.getUltima_nnf(), Matchers.equalTo(ultima_nnf));

    }

    @Test
    public void testB_delete() {
	EntityManager em = JPAUtil.getEntityManager();
	Emit emit = em.find(Emit.class, id);

	em.getTransaction().begin();
	em.remove(emit);
	em.getTransaction().commit();
	em.close();

    }
}
