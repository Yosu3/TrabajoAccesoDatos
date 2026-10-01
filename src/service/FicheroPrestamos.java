package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Prestamo;
import model.Recurso;
import model.Usuario;

public class FicheroPrestamos {

	private static final String RUTA = "prestamos.csv";

	public static List<Prestamo> cargarPrestamos(List<Usuario> usuarios, List<Recurso> recursos) {
		List<Prestamo> prestamos = new ArrayList<>();

		File fichero = new File(RUTA);
		if (!fichero.exists()) {
			System.out.println("El fichero de préstamos no existe.");
			return prestamos;
		}

		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			String linea;
			int numLinea = 0;

			while ((linea = br.readLine()) != null) {
				numLinea++;
				
				if (linea.isBlank()) continue;
				
				try {
					String[] p = linea.split(";");
					
					if (p.length != 5) {
						throw new IllegalArgumentException("número de campos incorrecto");
					}

				String usuarioId = p[0];
				String recursoId = p[1];
				String estado = p[2];
				LocalDate fechaPrestamo = LocalDate.parse(p[3]);
				LocalDate fechaDevolucion = p[4].equals("null") ? null : LocalDate.parse(p[4]);

				Usuario u = usuarios.stream().filter(x -> x.getId().equals(usuarioId)).findFirst().orElse(null);
				Recurso r = recursos.stream().filter(x -> x.getId().equals(recursoId)).findFirst().orElse(null);

				if (u == null || r == null) {
					System.out.println("Préstamo inválido: usuario o recurso no existe.");
					continue;
				}

				Prestamo pr = new Prestamo(u, r, estado);
				pr.setFechaPrestamo(fechaPrestamo);
				pr.setFechaDevolucion(fechaDevolucion);

				prestamos.add(pr);
				
			} catch (RuntimeException e) {
				System.out.println("Línea " + numLinea + " incorrecta en prestamos.csv, se ignora: " + e.getMessage());
			}
		}

		} catch (Exception e) {
			System.out.println("Error leyendo prestamos.csv: " + e.getMessage());
		}

		return prestamos;
	}

	public static void guardarPrestamos(List<Prestamo> prestamos) {

		try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA))) {

			for (Prestamo p : prestamos) {
				pw.println(p.getUsuario().getId() + ";" + p.getRecurso().getId() + ";" + p.getEstadoPrestamo() + ";"
						+ p.getFechaPrestamo() + ";" + p.getFechaDevolucion());
			}

		} catch (IOException e) {
			System.out.println("Error escribiendo prestamos.csv: " + e.getMessage());
		}

	}
}
