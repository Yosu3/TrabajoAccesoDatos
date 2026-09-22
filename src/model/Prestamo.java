package model;

import java.time.LocalDate;

public class Prestamo {
	
	private Usuario usuario;
	private Recurso recurso;
	private String estadoPrestamo;
	private LocalDate fechaPrestamo;    
	private LocalDate fechaDevolucion; 
	
	public Prestamo(Usuario usuario, Recurso recurso, String estadoPrestamo) {
		super();
		this.usuario = usuario;
		this.recurso = recurso;
		this.estadoPrestamo = estadoPrestamo;
		this.fechaPrestamo = LocalDate.now();
		this.fechaDevolucion = null;         
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public String getEstadoPrestamo() {
		return estadoPrestamo;
	}

	public void setEstadoPrestamo(String estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	
	@Override
	public String toString() {
		return "Prestamo [usuario=" + usuario + ", recurso=" + recurso + ", estado=" + estadoPrestamo
				+ ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion + "]";
	}
}
