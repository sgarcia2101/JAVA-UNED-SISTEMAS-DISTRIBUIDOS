package es.uned.common;

import java.io.Serializable;

public class Mensaje implements Serializable {

	private static final long serialVersionUID = 6473037307367070437L;
	private String cuerpo, remitente;
	
	public Mensaje(String cuerpo, String remitente) {
		this.cuerpo = cuerpo;
		this.remitente = remitente;
	}
	
	public String getCuerpo() {
		return cuerpo;
	}
	
	public String getRemitente() {
		return remitente;
	}
}
