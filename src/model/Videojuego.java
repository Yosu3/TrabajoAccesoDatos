package model;

public class Videojuego extends Recurso {
	
	private String plataforma;
    private int pegi;
    
	public Videojuego(String id, String titulo, int anio, boolean prestado, String plataforma, int pegi) {
		super(id, titulo, anio, prestado);
		this.plataforma = plataforma;
		this.pegi = pegi;
	}
	
	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public int getPegi() {
		return pegi;
	}

	public void setPegi(int pegi) {
		this.pegi = pegi;
	}

	@Override
	public String obtenerDetalles() {
		// TODO Auto-generated method stub
		return "VIDEOJUEGO -> Plataforma: " + plataforma + " | PEGI: +" + pegi;
	}

	@Override
    public String toString() {
        return super.toString() + " | " + obtenerDetalles();
    }
	
	

}
