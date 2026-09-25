package service;

import java.util.ArrayList;
import java.util.List;

import model.Prestamo;
import model.Recurso;
import model.Usuario;

public class GestorPrestamos {

	private List<Prestamo> prestamos;
	private List<Usuario> usuarios;
	private List<Recurso> recursos;

	public GestorPrestamos(List<Usuario> usuarios, List<Recurso> recursos) {
		this.prestamos = new ArrayList<>();
		this.usuarios = usuarios;
		this.recursos = recursos;
	}

	private Usuario buscarUsuario(String id) {
		for (Usuario u : usuarios) {
			if (u.getId().equals(id)) {
				return u;
			}
		}
		return null;
	}

	private Recurso buscarRecurso(String id) {
		for (Recurso r : recursos) {
			if (r.getId().equals(id)) {
				return r;
			}
		}
		return null;
	}

	private Prestamo buscarPrestamoActivo(String idRecurso) {
		for (Prestamo p : prestamos) {
			if (p.getRecurso().getId().equals(idRecurso) && p.getEstadoPrestamo().equals("ACTIVO")) {
				return p;
			}
		}
		return null;
	}

	public String realizarPrestamo(String idUsuario, String idRecurso) {

		Usuario usuario = buscarUsuario(idUsuario);
		if (usuario == null) {
			return "ERROR: El usuario no existe.";
		}

		Recurso recurso = buscarRecurso(idRecurso);
		if (recurso == null) {
			return "ERROR: El recurso no existe.";
		}

		if (recurso.isPrestado()) {
			return "ERROR: El recurso ya está prestado.";
		}

		recurso.setPrestado(true);

		Prestamo p = new Prestamo(usuario, recurso, "ACTIVO");
		prestamos.add(p);

		return "Préstamo realizado correctamente.";
	}
}
