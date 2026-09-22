package model;

public class Libro extends Recurso {

	private String autor;
    private int paginas;
    
	public Libro(String id, String titulo, int anio, boolean prestado, String autor, int paginas) {
		super(id, titulo, anio, prestado);
		this.autor = autor;
		this.paginas = paginas;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	@Override
    public String obtenerDetalles() {
        return "LIBRO -> Autor: " + autor + " | Páginas: " + paginas;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + obtenerDetalles();
    }
}