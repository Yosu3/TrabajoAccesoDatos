package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class FicheroUsuarios {

	private static final String RUTA = "usuarios.csv";

	public static List<Usuario> cargarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();

		File fichero = new File(RUTA);
		if (!fichero.exists()) {
			System.out.println("El fichero de usuarios no existe. Se creará uno nuevo.");
			return usuarios; 
		}

		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(";");

				if (partes.length != 3) {
					System.out.println("Línea incorrecta en usuarios.csv: " + linea);
					continue;
				}

				String id = partes[0];
				String nombre = partes[1];
				String email = partes[2];

				usuarios.add(new Usuario(id, nombre, email));
			}

		} catch (IOException e) {
			System.out.println("Error leyendo usuarios.csv: " + e.getMessage());
		}

		return usuarios;
	}

	public static void guardarUsuarios(List<Usuario> usuarios) {

		try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA))) {

			for (Usuario u : usuarios) {
				pw.println(u.getId() + ";" + u.getNombre() + ";" + u.getEmail());
			}

		} catch (IOException e) {
			System.out.println("Error escribiendo usuarios.csv: " + e.getMessage());
		}
	}

}
