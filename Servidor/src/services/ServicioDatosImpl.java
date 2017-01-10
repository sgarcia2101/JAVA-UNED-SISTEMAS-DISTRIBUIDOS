package services;

import java.util.HashMap;
import java.util.Map;

import com.sgarcia.commons.Fichero;
import com.sgarcia.commons.Usuario;
import com.sgarcia.commons.services.ServicioDatosInterface;

public class ServicioDatosImpl implements ServicioDatosInterface {
	
	Map<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();
	Map<Integer, Fichero> ficheros = new HashMap<Integer, Fichero>();
	Map<Integer, Usuario> metadatos = new HashMap<Integer, Usuario>();
	Map<Integer, Usuario> repositorios = new HashMap<Integer, Usuario>();

}
