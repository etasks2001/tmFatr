package com.fatr.model;

import org.hibernate.type.ShortType;

public class EmitBuilder {

    private Emit emit;

    private EmitBuilder() {
	this.emit = new Emit();
    }

    public static EmitBuilder builder() {
	return new EmitBuilder();
    }

    public EmitBuilder addId(Integer id) {
	this.emit.setId(id);
	return this;
    }

    public EmitBuilder addCNPJ(String CNPJ) {
	this.emit.setCNPJ(CNPJ);
	return this;
    }

    public EmitBuilder addxNome(String xNome) {
	this.emit.setxNome(xNome);
	return this;
    }

    public EmitBuilder addxFant(String xFant) {
	this.emit.setxFant(xFant);
	return this;
    }

    public EmitBuilder addEnderEmit(Ender enderEmit) {
	this.emit.setEnderEmit(enderEmit);
	return this;
    }

    public EmitBuilder addIE(String IE) {
	this.emit.setIE(IE);
	return this;
    }

    public EmitBuilder addIEST(String IEST) {
	this.emit.setIEST(IEST);
	return this;
    }

    public EmitBuilder addIM(String IM) {
	this.emit.setIM(IM);
	return this;
    }

    public EmitBuilder addCNAE(String CNAE) {
	this.emit.setCNAE(CNAE);
	return this;
    }

    public EmitBuilder addCRT(String CRT) {
	this.emit.setCRT(CRT);
	return this;
    }

    public EmitBuilder addNf_serie_atual(ShortType nf_serie_atual) {
	this.emit.setNf_serie_atual(nf_serie_atual);
	return this;
    }

    public EmitBuilder addUltima_nnf(Integer ultima_nnf) {
	this.emit.setUltima_nnf(ultima_nnf);
	return this;
    }

    public Emit get() {
	return this.emit;
    }
}
