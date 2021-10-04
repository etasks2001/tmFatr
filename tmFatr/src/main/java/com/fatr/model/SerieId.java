package com.fatr.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SerieId implements Serializable {
    private Integer id_emit;
    private Integer serie;

    public SerieId(Integer id_emit, Integer serie) {
	this.id_emit = id_emit;
	this.serie = serie;
    }

    public Integer getId_emit() {
	return id_emit;
    }

    public Integer getSerie() {
	return serie;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id_emit == null) ? 0 : id_emit.hashCode());
	result = prime * result + ((serie == null) ? 0 : serie.hashCode());
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
	SerieId other = (SerieId) obj;
	if (id_emit == null) {
	    if (other.id_emit != null)
		return false;
	} else if (!id_emit.equals(other.id_emit))
	    return false;
	if (serie == null) {
	    if (other.serie != null)
		return false;
	} else if (!serie.equals(other.serie))
	    return false;
	return true;
    }

}
