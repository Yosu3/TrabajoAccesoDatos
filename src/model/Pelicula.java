package model;

public class Pelicula extends Recurso {
	
	private String director;
    private int duracion; 

	public Pelicula(String id, String titulo, int anio, boolean prestado, String director, int duracion) {
		super(id, titulo, anio, prestado);
		this.director = director;
		this.duracion = duracion;
	}

	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public int getDuracion() {
		return duracion;
	}


	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	@Override
    public String obtenerDetalles() {
        return "PELÍCULA -> Director: " + director + " | Duración: " + duracion + " min";
    }

    @Override
    public String toString() {
        return super.toString() + " | " + obtenerDetalles();
    }
}