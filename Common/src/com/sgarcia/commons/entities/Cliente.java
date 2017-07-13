package com.sgarcia.commons.entities;

import java.io.Serializable;

/**
 * The Class Cliente.
 *
 * @author Sergio Garcia Lalana
 * @email sergiopedrola@gmail.com
 */
public class Cliente implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -7306709243939707887L;

  /** The id. */
  private int id;

  /** The nombre. */
  private String nombre;

  /** The repositorio id. */
  private int repositorioId;

  /**
   * Instantiates a new cliente.
   *
   * @param id the id
   * @param nombre the nombre
   * @param repositorioId the repositorio id
   */
  public Cliente(int id, String nombre, int repositorioId) {
    this.id = id;
    this.nombre = nombre;
    this.repositorioId = repositorioId;
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

  /**
   * Gets the repositorio id.
   *
   * @return the repositorio id
   */
  public int getRepositorioId() {
    return repositorioId;
  }

  /**
   * Sets the repositorio id.
   *
   * @param repositorioId the new repositorio id
   */
  public void setRepositorioId(int repositorioId) {
    this.repositorioId = repositorioId;
  }

}
