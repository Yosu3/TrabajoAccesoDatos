package service;

import model.Usuario;

public class Controlador {

	public static void main(String[] args) {



	        GestionUsuarios service = new GestionUsuarios();

	        service.crearUsuario(new Usuario("1", "Ana", "ana@mail.com"));
	        service.crearUsuario(new Usuario("2", "Luis", "luis@mail.com"));

	        service.crearUsuario(new Usuario("1", "Pepe", "pepe@mail.com"));

	        System.out.println("Usuarios actuales:");
	        for (Usuario u : service.listarUsuarios()) {
	            System.out.println(u);
	        }

	        System.out.println("Buscando ID 2:");
	        System.out.println(service.buscarPorId("2"));

	        service.modificarUsuario("2", "Luis Modificado", "nuevo@mail.com");

	        service.eliminarUsuario("1");

	        System.out.println("Usuarios finales:");
	        for (Usuario u : service.listarUsuarios()) {
	            System.out.println(u);
	        }
	    }
	}