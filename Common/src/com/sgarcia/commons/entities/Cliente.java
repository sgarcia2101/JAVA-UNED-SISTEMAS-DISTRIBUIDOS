package com.sgarcia.commons.entities;

import java.io.Serializable;

public class Cliente implements Serializable {

  private static final long serialVersionUID = -7306709243939707887L;
  
  private int id;
  private String nombre;
  private int repositorioId;

  public Cliente(int id, String nombre, int repositorioId) {
    this.id = id;
    this.nombre = nombre;
    this.repositorioId = repositorioId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getRepositorioId() {
    return repositorioId;
  }

  public void setRepositorioId(int repositorioId) {
    this.repositorioId = repositorioId;
  }

}
