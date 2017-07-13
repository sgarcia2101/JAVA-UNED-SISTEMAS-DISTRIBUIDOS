package com.sgarcia.commons.entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;

public class Fichero implements Serializable {

	private static final long serialVersionUID = -701591859841871541L;

	// Identificador del propietario original del fichero
	private String propietario;

	// Nombre del fichero
	private String nombre;

	// Peso del fichero en bytes
	private long peso;

	// Suma de chequeo de los bytes del fichero
	private long checksum;

	// Contenido del fichero
	private byte[] data;

	// constructor
	public Fichero(String nombre, String propietario) {
		this.nombre = nombre;
		this.propietario = propietario;

		CheckedInputStream c = null;
		peso = 0;

		try {
			c = new CheckedInputStream(new FileInputStream(nombre), new CRC32());

			peso = new File(nombre).length();
			data = new byte[(int) this.peso];

			while (c.read(data) > 0) {
			}

			c.close();

	        checksum = c.getChecksum().getValue();

		} catch (FileNotFoundException ef) {
			System.err.println("Fichero no encontrado");
		} catch (IOException e) {
			System.err.println("Error leyendo fichero" + e.toString());
		}

	}

	// constructor
	public Fichero(String ruta, String nombre, String propietario) {
		this.nombre = nombre;
		this.propietario = propietario;

		CheckedInputStream c = null;
		peso = 0;

		try {
			c = new CheckedInputStream(new FileInputStream(ruta + File.separator + nombre), new CRC32());

			peso = new File(ruta + File.separator + nombre).length();
			data = new byte[(int) this.peso];

			while (c.read(data) >= 0) {
			}

			c.close();

	        checksum = c.getChecksum().getValue();

		} catch (FileNotFoundException ef) {
			System.err.println("Fichero no encontrado");
		} catch (IOException e) {
			System.err.println("Error leyendo fichero" + e.toString());
		}

	}

	public boolean escribirEn(OutputStream os) {
		long CheckSum;
		CheckedOutputStream cs = new CheckedOutputStream(os, new CRC32());

		try {
			cs.write(data);

			CheckSum = cs.getChecksum().getValue();

			cs.close();

			if (CheckSum != this.checksum) {
				// Falla el checksum, debería mandarse de nuevo
				return (false);
			}

		} catch (Exception e) {
			System.err.println("Error escribiendo fichero" + e.toString());
		}

		// Fichero mandado Ok
		return (true);
	}

	public String obtenerPropietario() {
		return (propietario);
	}

	public String obtenerNombre() {
		return (nombre);
	}

	public long obtenerPeso() {
		return (peso);
	}

	public long obtenerChecksum() {
		return (checksum);
	}

}