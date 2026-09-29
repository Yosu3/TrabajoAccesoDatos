package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Libro;
import model.Pelicula;
import model.Recurso;
import model.Videojuego;

public class FicheroRecursos {

	private static final String RUTA = "recursos.csv";

	public static List<Recurso> cargarRecursos() {
		List<Recurso> recursos = new ArrayList<>();

		File fichero = new File(RUTA);
		if (!fichero.exists()) {
			System.out.println("El fichero de recursos no existe.");
			return recursos;
		}

		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				String[] p = linea.split(";");

				String tipo = p[0];
				String id = p[1];
				String titulo = p[2];
				int anio = Integer.parseInt(p[3]);
				boolean prestado = Boolean.parseBoolean(p[4]);

				switch (tipo) {
				case "LIBRO":
					recursos.add(new Libro(id, titulo, anio, prestado, p[5], Integer.parseInt(p[6])));
					break;

				case "PELÍCULA":
					recursos.add(new Pelicula(id, titulo, anio, prestado, p[5], Integer.parseInt(p[6])));
					break;

				case "VIDEOJUEGO":
					recursos.add(new Videojuego(id, titulo, anio, prestado, p[5], Integer.parseInt(p[6])));
					break;

				default:
					System.out.println("Tipo desconocido: " + tipo);
				}
			}

		} catch (Exception e) {
			System.out.println("Error leyendo recursos.csv: " + e.getMessage());
		}

		return recursos;
	}

	public static void guardarRecursos(List<Recurso> recursos) {

		try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA))) {

			for (Recurso r : recursos) {

				if (r instanceof Libro l) {
					pw.println("LIBRO;" + l.getId() + ";" + l.getTitulo() + ";" + l.getAnio() + ";" + l.isPrestado()
							+ ";" + l.getAutor() + ";" + l.getPaginas());
				}

				else if (r instanceof Pelicula p) {
					pw.println("PELÍCULA;" + p.getId() + ";" + p.getTitulo() + ";" + p.getAnio() + ";" + p.isPrestado()
							+ ";" + p.getDirector() + ";" + p.getDuracion());
				}

				else if (r instanceof Videojuego v) {
					pw.println("VIDEOJUEGO;" + v.getId() + ";" + v.getTitulo() + ";" + v.getAnio() + ";"
							+ v.isPrestado() + ";" + v.getPlataforma() + ";" + v.getPegi());
				}
			}

		} catch (IOException e) {
			System.out.println("Error escribiendo recursos.csv: " + e.getMessage());
		}
	}

}
