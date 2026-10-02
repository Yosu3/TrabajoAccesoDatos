package service;

import model.Libro;
import model.Recurso;
import java.util.ArrayList;

public class RecursoService {
    
    private ArrayList<Recurso> lista;

    public RecursoService() {
        this.lista = new ArrayList<>(FicheroRecursos.cargarRecursos());
    }

    public void agregar(Recurso r) {
        lista.add(r);
        FicheroRecursos.guardarRecursos(lista); 
    }

    public ArrayList<Recurso> obtenerTodos() {
        return lista;
    }

    public Recurso buscarPorId(String id) {
        for (Recurso r : lista) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }
    
    public void guardarCambios() {
        FicheroRecursos.guardarRecursos(lista);
    }

    public boolean eliminar(String id, GestorPrestamos gestorPrestamos) {
    	if (gestorPrestamos != null && gestorPrestamos.tienePrestamosActivosRecurso(id)) {
            System.out.println("ERROR: No se puede eliminar el recurso porque está actualmente prestado.");
            return false;
        }
    	
    	Recurso r = buscarPorId(id);
        if (r != null) {
            boolean eliminado = lista.remove(r);
            if (eliminado) {
            	FicheroRecursos.guardarRecursos(lista);
    	   	
        }
            return eliminado;
      }
        return false;
    }
    
    public boolean eliminar(String id) {
        return eliminar(id, null);
    }

    public ArrayList<Recurso> buscarPorTitulo(String titulo) {
        ArrayList<Recurso> resultado = new ArrayList<>();
        for (Recurso r : lista) {
            if (r.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(r);
            }
        }
        return resultado;
    }
    
    public ArrayList<Recurso> obtenerDisponibles() {
        ArrayList<Recurso> resultado = new ArrayList<>();
        for (Recurso r : lista) {
            if (!r.isPrestado()) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public ArrayList<Recurso> filtrarPorTipo(Class<?> tipo) {
        ArrayList<Recurso> resultado = new ArrayList<>();
        for (Recurso r : lista) {
            if (tipo.isInstance(r)) {
                resultado.add(r);
            }
        }
        return resultado;
    }
    
    public ArrayList<Recurso> buscarPorRangoAnios(int anioInicio, int anioFin) {
        ArrayList<Recurso> resultado = new ArrayList<>();
        for (Recurso r : lista) {
            if (r.getAnio() >= anioInicio && r.getAnio() <= anioFin) {
                resultado.add(r);
            }
        }
        return resultado;
    }
    
    public ArrayList<Libro> buscarLibrosPorAutor(String autor) {
        ArrayList<Libro> resultado = new ArrayList<>();
        for (Recurso r : lista) {
            if (r instanceof Libro l) {
                if (l.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                    resultado.add(l);
                }
            }
        }
        return resultado;
    }
    
    public ArrayList<Recurso> obtenerPrestados() {
        ArrayList<Recurso> resultado = new ArrayList<>();
        for (Recurso r : lista) {
            if (r.isPrestado()) {
                resultado.add(r);
            }
        }
        return resultado;
    }
    
}
