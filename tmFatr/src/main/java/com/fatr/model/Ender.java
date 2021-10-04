package com.fatr.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Ender implements Serializable {

    public Ender() {

    }

    public static void main(String[] args) {
	Character c = '1';
    }

    private String xLgr;
    private String nro;
    private String xCpl;
    private String xBairro;
    private String cMun;
    private String xMun;
    private String UF;
    private String CEP;
    private String cPais;
    private String xPais;
    private String fone;

    public String getxLgr() {
	return xLgr;
    }

    public String getNro() {
	return nro;
    }

    public String getxCpl() {
	return xCpl;
    }

    public String getxBairro() {
	return xBairro;
    }

    public String getcMun() {
	return cMun;
    }

    public String getxMun() {
	return xMun;
    }

    public String getUF() {
	return UF;
    }

    public String getCEP() {
	return CEP;
    }

    public String getcPais() {
	return cPais;
    }

    public String getxPais() {
	return xPais;
    }

    public String getFone() {
	return fone;
    }

    public void setxLgr(String xLgr) {
	this.xLgr = xLgr;
    }

    public void setNro(String nro) {
	this.nro = nro;
    }

    public void setxCpl(String xCpl) {
	this.xCpl = xCpl;
    }

    public void setxBairro(String xBairro) {
	this.xBairro = xBairro;
    }

    public void setcMun(String cMun) {
	this.cMun = cMun;
    }

    public void setxMun(String xMun) {
	this.xMun = xMun;
    }

    public void setUF(String uF) {
	this.UF = uF;
    }

    public void setCEP(String cEP) {
	this.CEP = cEP;
    }

    public void setcPais(String cPais) {
	this.cPais = cPais;
    }

    public void setxPais(String xPais) {
	this.xPais = xPais;
    }

    public void setFone(String fone) {
	this.fone = fone;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((CEP == null) ? 0 : CEP.hashCode());
	result = prime * result + ((UF == null) ? 0 : UF.hashCode());
	result = prime * result + ((cMun == null) ? 0 : cMun.hashCode());
	result = prime * result + ((cPais == null) ? 0 : cPais.hashCode());
	result = prime * result + ((fone == null) ? 0 : fone.hashCode());
	result = prime * result + ((nro == null) ? 0 : nro.hashCode());
	result = prime * result + ((xBairro == null) ? 0 : xBairro.hashCode());
	result = prime * result + ((xCpl == null) ? 0 : xCpl.hashCode());
	result = prime * result + ((xLgr == null) ? 0 : xLgr.hashCode());
	result = prime * result + ((xMun == null) ? 0 : xMun.hashCode());
	result = prime * result + ((xPais == null) ? 0 : xPais.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Ender other = (Ender) obj;
	if (CEP == null) {
	    if (other.CEP != null)
		return false;
	} else if (!CEP.equals(other.CEP))
	    return false;
	if (UF == null) {
	    if (other.UF != null)
		return false;
	} else if (!UF.equals(other.UF))
	    return false;
	if (cMun == null) {
	    if (other.cMun != null)
		return false;
	} else if (!cMun.equals(other.cMun))
	    return false;
	if (cPais == null) {
	    if (other.cPais != null)
		return false;
	} else if (!cPais.equals(other.cPais))
	    return false;
	if (fone == null) {
	    if (other.fone != null)
		return false;
	} else if (!fone.equals(other.fone))
	    return false;
	if (nro == null) {
	    if (other.nro != null)
		return false;
	} else if (!nro.equals(other.nro))
	    return false;
	if (xBairro == null) {
	    if (other.xBairro != null)
		return false;
	} else if (!xBairro.equals(other.xBairro))
	    return false;
	if (xCpl == null) {
	    if (other.xCpl != null)
		return false;
	} else if (!xCpl.equals(other.xCpl))
	    return false;
	if (xLgr == null) {
	    if (other.xLgr != null)
		return false;
	} else if (!xLgr.equals(other.xLgr))
	    return false;
	if (xMun == null) {
	    if (other.xMun != null)
		return false;
	} else if (!xMun.equals(other.xMun))
	    return false;
	if (xPais == null) {
	    if (other.xPais != null)
		return false;
	} else if (!xPais.equals(other.xPais))
	    return false;
	return true;
    }

}
