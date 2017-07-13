package com.sgarcia.commons.entities;

import java.io.Serializable;

/**
 * The Class Repositorio.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public class Repositorio implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8790641408094892959L;

  /** The id. */
  private int id;

  /** The nombre. */
  private String nombre;

  /**
   * Instantiates a new repositorio.
   *
   * @param id the id
   * @param nombre the nombre
   */
  public Repositorio(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the nombre.
   *
   * @return the nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Sets the nombre.
   *
   * @param nombre the new nombre
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

}
