package com.fatr.util.builder;

import com.fatr.model.Ender;

public class EnderBuilder {
    private Ender ender;

    private EnderBuilder() {
	this.ender = new Ender();
    }

    public static EnderBuilder builder() {
	return new EnderBuilder();
    }

    public EnderBuilder addxLgr(String xLgr) {
	this.ender.setxLgr(xLgr);
	return this;
    }

    public EnderBuilder addNro(String nro) {
	this.ender.setNro(nro);
	return this;
    }

    public EnderBuilder addxCpl(String xCpl) {
	this.ender.setxCpl(xCpl);
	return this;
    }

    public EnderBuilder addxBairro(String xBairro) {
	this.ender.setxBairro(xBairro);
	return this;
    }

    public EnderBuilder addcMun(String cMun) {
	this.ender.setcMun(cMun);
	return this;
    }

    public EnderBuilder addxMun(String xMun) {
	this.ender.setxMun(xMun);
	return this;
    }

    public EnderBuilder addUF(String UF) {
	this.ender.setUF(UF);
	return this;
    }

    public EnderBuilder addCEP(String CEP) {
	this.ender.setCEP(CEP);
	return this;
    }

    public EnderBuilder addcPais(String cPais) {
	this.ender.setcPais(cPais);
	return this;
    }

    public EnderBuilder addxPais(String xPais) {
	this.ender.setxPais(xPais);
	return this;
    }

    public EnderBuilder addFone(String fone) {
	this.ender.setFone(fone);
	return this;
    }

    public Ender get() {
	return this.ender;
    }
}
