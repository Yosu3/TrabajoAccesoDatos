package model;

public abstract class Recurso {
	
    private String id;
    private String titulo;
    private int anio;
    private boolean prestado;
    
    public Recurso(String id, String titulo, int anio, boolean prestado) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.prestado = prestado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public abstract String obtenerDetalles();
	
    @Override
    public String toString() {
        String estadoPrestamo = prestado ? "Prestado" : "Disponible";
        return "ID: " + id + " | Título: " + titulo + " (" + anio + ") | Estado: " + estadoPrestamo;
    }
}
