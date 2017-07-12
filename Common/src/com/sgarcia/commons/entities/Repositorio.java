package com.sgarcia.commons.entities;

import java.io.Serializable;

public class Repositorio implements Serializable {

  private static final long serialVersionUID = 8790641408094892959L;
  
  private int id;
  private String nombre;

  public Repositorio(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
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

}
