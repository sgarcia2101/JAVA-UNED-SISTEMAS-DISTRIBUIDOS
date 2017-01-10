package com.sgarcia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author sergio
 *
 */
public class Cliente {

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.menuInicial();
	}

	public void menuInicial() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			Integer opcion = 0;
			do {
				System.out.println("1. Registrar un nuevo usuario ");
				System.out.println("2. Autenticarse en el sistema (hacer login) ");
				System.out.println("3. Salir ");
				
				opcion = Integer.parseInt(reader.readLine().trim());
				
			} while (opcion < 1 || opcion > 3);

			switch (opcion) {
			case 1:
				registrar();
				break;
			case 2:
				autenticar();
				break;
			case 3:
				System.exit(0);
				break;
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Operacion no permitida");
			menuInicial();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		} finally {
			try {
				reader.close();
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}
	}

	private void registrar() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\nIntroduzca el nombre de usuario: ");
		String texto = scan.next();
		scan.close();

		this.menuInicial();
	}

	private void autenticar() {
		Scanner scan = new Scanner(System.in);
		System.out.println("\nIntroduzca el nombre de usuario: ");
		String texto = scan.next();
		scan.close();

		this.menuPrincipal();
	}

	public void menuPrincipal() {
		Scanner scan = new Scanner(System.in);
		
		try {
			int opcion = 0;
			do {
				System.out.println("1. Subir fichero ");
				System.out.println("2. Bajar fichero ");
				System.out.println("3. Borrar fichero ");
				System.out.println("4. Compartir fichero ");
				System.out.println("5. Listar ficheros ");
				System.out.println("6. Listar Clientes del sistema ");
				System.out.println("7. Salir ");
				opcion = scan.nextInt();
			} while (opcion < 1 || opcion > 7);

			switch (opcion) {
			case 1:
				subirFichero();
				break;
			case 2:
				bajarFichero();
				break;
			case 3:
				borrarFichero();
				break;
			case 4:
				compartirFichero();
				break;
			case 5:
				listarFicheros();
				break;
			case 6:
				listarClientes();
				break;
			case 7:
				menuInicial();
				break;
			}
		} catch (InputMismatchException ime) {
			System.out.println("Operacion no permitida");
		} finally {
			scan.close();
		}

		menuPrincipal();
	}

	private void subirFichero() {
		// TODO Auto-generated method stub

	}

	private void bajarFichero() {
		// TODO Auto-generated method stub

	}

	private void borrarFichero() {
		// TODO Auto-generated method stub

	}

	private void compartirFichero() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	private void listarFicheros() {
		// TODO Auto-generated method stub

	}

	private void listarClientes() {
		// TODO Auto-generated method stub

	}

}
