package service;

import model.Recurso;
import java.util.ArrayList;

public class RecursoService {
    
    private ArrayList<Recurso> lista = new ArrayList<>();

    public void agregar(Recurso r) {
        lista.add(r);
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

    public boolean eliminar(String id) {
        Recurso r = buscarPorId(id);
        if (r != null) {
            return lista.remove(r);
        }
        return false;
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
}
