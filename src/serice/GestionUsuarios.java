package serice;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class GestionUsuarios {		
		 private List<Usuario> usuarios = new ArrayList<>();

		    public boolean crearUsuario(Usuario u) {
		        if (buscarPorId(u.getId()) != null) {
		            System.out.println("ERROR: El ID " + u.getId() + " ya existe.");
		            return false;
		        }
		        usuarios.add(u);
		        return true;
		    }

		    public List<Usuario> listarUsuarios() {
		        return usuarios;
		    }

		    public Usuario buscarPorId(String id) {
		        for (Usuario u : usuarios) {
		            if (u.getId().equals(id)) {
		                return u;
		            }
		        }
		        return null;
		    }

		    public List<Usuario> buscarPorNombre(String nombre) {
		        List<Usuario> resultado = new ArrayList<>();
		        for (Usuario u : usuarios) {
		            if (u.getNombre().equalsIgnoreCase(nombre)) {
		                resultado.add(u);
		            }
		        }
		        return resultado;
		    }

		    public Usuario buscarPorEmail(String email) {
		        for (Usuario u : usuarios) {
		            if (u.getEmail().equalsIgnoreCase(email)) {
		                return u;
		            }
		        }
		        return null;
		    }

		    public boolean modificarUsuario(String id, String nuevoNombre, String nuevoEmail) {
		        Usuario u = buscarPorId(id);
		        if (u == null) return false;

		        u.setNombre(nuevoNombre);
		        u.setEmail(nuevoEmail);
		        return true;
		    }

		    public boolean eliminarUsuario(String id) {
		        Usuario u = buscarPorId(id);
		        if (u == null) return false;

		        usuarios.remove(u);
		        return true;
		    }
		}

