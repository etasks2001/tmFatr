package com.fatr.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Emit")
public class Emit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String CNPJ;
    private String xNome;
    private String xFant;
    @Embedded
    private Ender enderEmit;
    private String IE;
    private String IEST;
    private String IM;
    private String CNAE;
    private String CRT;
    private Integer nf_serie_atual;
    private Integer ultima_nnf;

    public Integer getId() {
	return id;
    }

    public String getCNPJ() {
	return CNPJ;
    }

    public String getxNome() {
	return xNome;
    }

    public String getxFant() {
	return xFant;
    }

    public String getIE() {
	return IE;
    }

    public String getIEST() {
	return IEST;
    }

    public String getIM() {
	return IM;
    }

    public String getCNAE() {
	return CNAE;
    }

    public String getCRT() {
	return CRT;
    }

    public Integer getNf_serie_atual() {
	return nf_serie_atual;
    }

    public Integer getUltima_nnf() {
	return ultima_nnf;
    }

    public Ender getEnderEmit() {
	return enderEmit;
    }

    public void setEnderEmit(Ender enderEmit) {
	this.enderEmit = enderEmit;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setCNPJ(String cNPJ) {
	CNPJ = cNPJ;
    }

    public void setxNome(String xNome) {
	this.xNome = xNome;
    }

    public void setxFant(String xFant) {
	this.xFant = xFant;
    }

    public void setIE(String iE) {
	IE = iE;
    }

    public void setIEST(String iEST) {
	IEST = iEST;
    }

    public void setIM(String iM) {
	IM = iM;
    }

    public void setCNAE(String cNAE) {
	CNAE = cNAE;
    }

    public void setCRT(String cRT) {
	CRT = cRT;
    }

    public void setNf_serie_atual(Integer nf_serie_atual) {
	this.nf_serie_atual = nf_serie_atual;
    }

    public void setUltima_nnf(Integer ultima_nnf) {
	this.ultima_nnf = ultima_nnf;
    }

}
