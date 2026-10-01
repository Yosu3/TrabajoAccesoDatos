package view;

import java.util.Scanner;
import model.Libro;
import model.Pelicula;
import model.Recurso;
import model.Usuario;
import model.Videojuego;
import service.GestionUsuarios;
import service.GestorPrestamos;
import service.RecursoService;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final RecursoService recursoService = new RecursoService();
    private static final GestionUsuarios gestionUsuarios = new GestionUsuarios();
    private static final GestorPrestamos gestorPrestamos = new GestorPrestamos(gestionUsuarios.listarUsuarios(), recursoService.obtenerTodos());

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> menuRecursos();
                case 2 -> menuUsuarios();
                case 3 -> menuPrestamos();
                case 4 -> menuConsultas();
                case 5 -> {
                    System.out.println("Saliendo del sistema...");
                    salir = true;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    } 
           

	private static void mostrarMenuPrincipal() {
        System.out.println("\n=== GESTIÓN DE BIBLIOTECA ===");
        System.out.println("1. Gestión de Recursos");
        System.out.println("2. Gestión de Usuarios");
        System.out.println("3. Gestión de Préstamos");
        System.out.println("4. Consultas y Búsquedas");
        System.out.println("5. Salir");
    }
    
    private static void menuRecursos() {
    	System.out.println("\n--- GESTIÓN DE RECURSOS ---");
    	System.out.println("1. Crear Libro");
    	System.out.println("2. Crear Película");
    	System.out.println("3. Crear Videojuego");
    	System.out.println("4. Listar Recursos");
    	System.out.println("5. Buscar Recurso por ID");
    	System.out.println("6. Modificar Recurso"); 
        System.out.println("7. Eliminar Recurso");  
        System.out.println("8. Volver");
    	
    	int opcion = leerEntero("Seleccione una opción: ");
    	switch (opcion) {
    	
    	case 1 -> {
    		String id = generarIdRecurso();
    		String titulo = leerTexto("Título: ");
    		int anio = leerEnteroPositivo("Año: (mínimo 1500): ", 1500);
    		String autor = leerTexto("Autor: ");
    		int paginas = leerEnteroPositivo("Páginas (mínimo 1): ", 1);
    		Libro libro = new Libro(id, titulo, anio, false, autor, paginas);
    	    recursoService.agregar(libro);
    		System.out.println("\n Libro añadido correctamente.");
    		System.out.println(libro);

    	}
    	
    	case 2 -> {
    		String id = generarIdRecurso();
    		String titulo = leerTexto("Título: ");
    		int anio = leerEnteroPositivo("Año (mínimo 1895): ", 1895);
    		String director = leerTexto("Director: ");
    		int duracion = leerEnteroPositivo("Duración en min (mínimo 1): ", 1);
    		Pelicula pelicula = new Pelicula(id, titulo, anio, false, director, duracion);
    		recursoService.agregar(pelicula);
    		System.out.println("\n Película añadida correctamente.");
    		System.out.println(pelicula);
    	}
    	
    	case 3 -> {
    		String id = generarIdRecurso();
    		String titulo = leerTexto("Título: ");
    		int anio = leerEnteroPositivo("Año (mínimo 1950): ", 1950);
    		String plataforma = leerTexto("Plataforma: ");
    		int pegi = leerEnteroPositivo("PEGI (0 a 18): ", 0);
    	    Videojuego videojuego = new Videojuego(id, titulo, anio, false, plataforma, pegi);
    		recursoService.agregar(videojuego);
    	    System.out.println("\n¡Videojuego añadido correctamente!");
    	    System.out.println(videojuego);

    		}
    	
    	case 4 -> {
    		System.out.println("\n-- Lista de Recursos --");
    		for (Recurso r : recursoService.obtenerTodos()) {
    		System.out.println(r);
    		}
    	}
    	
    	case 5 -> {
    		String id = leerTexto("ID a buscar: ");
    		Recurso r = recursoService.buscarPorId(id);
    		System.out.println(r != null ? r : "Recurso no encontrado.");
    		}
    	
    	case 6 -> modificarRecurso();
    		
    	case 7 -> {
    		String id = leerTexto("ID a eliminar: ");
    		System.out.println(recursoService.eliminar(id, gestorPrestamos) ? "Eliminado correctamente." : "No se pudo eliminar.");
    		}
    	
    	case 8 -> {}
    	default -> System.out.println("Opción no válida.");
    	}
    }
    
    private static void menuUsuarios() {
    	System.out.println("\n--- GESTIÓN DE USUARIOS ---");
    	System.out.println("1. Crear Usuario");
    	System.out.println("2. Listar Usuarios");
    	System.out.println("3. Buscar Usuario por ID");
    	System.out.println("4. Modificar Usuario"); 
        System.out.println("5. Eliminar Usuario");  
        System.out.println("6. Volver");
    	
    	int opcion = leerEntero("Seleccione una opción: ");
    	switch (opcion) {
    	
    	case 1 -> {
    		String id = generarIdUsuario();
    		String nombre = leerTexto("Nombre: ");
    		String email = leerTexto("Email: ");
    		Usuario usuario = new Usuario(id, nombre, email);
    	    if (gestionUsuarios.crearUsuario(usuario)) {
    	        System.out.println("\n¡Usuario creado correctamente!");
    	        System.out.println(usuario);
    	    } else {
    	        System.out.println("Error al crear usuario.");
        }
    }
    	
    	case 2 -> {
    		System.out.println("\n-- Lista de Usuarios --");
    		for (Usuario u : gestionUsuarios.listarUsuarios()) {
    		System.out.println(u);
    	}
    }
    	
    	case 3 -> {
    		String id = leerTexto("ID a buscar: ");
    		Usuario u = gestionUsuarios.buscarPorId(id);
    		System.out.println(u != null ? u : "Usuario no encontrado.");
    	}
    	
    	case 4 -> modificarUsuario();
    	
    	case 5 -> {
    		String id = leerTexto("ID a eliminar: ");
    		System.out.println(gestionUsuarios.eliminarUsuario(id, gestorPrestamos) ? "Usuario eliminado." : "No se pudo eliminar.");
    	}
    	
    	case 6 -> {}
    		default -> System.out.println("Opción no válida.");
    	}
    }

    private static void menuPrestamos() {
        System.out.println("\n--- GESTIÓN DE PRÉSTAMOS ---");
        System.out.println("1. Realizar Préstamo");
        System.out.println("2. Devolver Recurso");
        System.out.println("3. Volver");

        int opcion = leerEntero("Seleccione una opción: ");
        switch (opcion) {
        
        case 1 -> {
                String idUsuario = leerTexto("ID Usuario: ");
                String idRecurso = leerTexto("ID Recurso: ");
                System.out.println(gestorPrestamos.realizarPrestamo(idUsuario, idRecurso));
            }
            
        case 2 -> {
                String idRecurso = leerTexto("ID Recurso a devolver: ");
                System.out.println(gestorPrestamos.devolverPrestamo(idRecurso));
            }
            
        case 3 -> {}
            default -> System.out.println("Opción no válida.");
        }
    }
    
    private static void menuConsultas() {
        System.out.println("\n--- CONSULTAS Y BÚSQUEDAS ---");
        System.out.println("1. Buscar recurso por título");
        System.out.println("2. Ver recursos disponibles");
        System.out.println("3. Ver préstamos activos");
        System.out.println("4. Ver préstamos de un usuario");
        System.out.println("5. Filtrar recursos por tipo");
        System.out.println("6. Volver");

        int opcion = leerEntero("Seleccione una opción: ");
        switch (opcion) {
        
            case 1 -> {
                String titulo = leerTexto("Introduzca el título o parte de él: ");
                var resultados = recursoService.buscarPorTitulo(titulo);
                if (resultados.isEmpty()) {
                    System.out.println("No se encontraron recursos.");
                } else {
                    resultados.forEach(System.out::println);
                }
            }
            
            case 2 -> {
                System.out.println("\n-- Recursos Disponibles --");
                var disp = recursoService.obtenerDisponibles();
                if (disp.isEmpty()) System.out.println("No hay recursos disponibles.");
                else disp.forEach(System.out::println);
            }
            
            case 3 -> {
                System.out.println("\n-- Préstamos Activos --");
                var activos = gestorPrestamos.obtenerPrestamosActivos();
                if (activos.isEmpty()) System.out.println("No hay préstamos activos.");
                else activos.forEach(System.out::println);
            }
            
            case 4 -> {
                String idUser = leerTexto("ID Usuario: ");
                var userPrestamos = gestorPrestamos.obtenerPrestamosDeUsuario(idUser);
                if (userPrestamos.isEmpty()) System.out.println("El usuario no tiene préstamos registrados.");
                else userPrestamos.forEach(System.out::println);
            }
            
            case 5 -> {
                System.out.println("1. Libros | 2. Películas | 3. Videojuegos");
                int tipo = leerEntero("Seleccione tipo: ");
                if (tipo == 1) recursoService.filtrarPorTipo(Libro.class).forEach(System.out::println);
                else if (tipo == 2) recursoService.filtrarPorTipo(Pelicula.class).forEach(System.out::println);
                else if (tipo == 3) recursoService.filtrarPorTipo(Videojuego.class).forEach(System.out::println);
                else System.out.println("Tipo no válido.");
            }
            
            case 6 -> {}
            default -> System.out.println("Opción no válida.");
        }
    }
    
    private static void modificarRecurso() {
        String id = leerTexto("ID del recurso a modificar: ");
        Recurso r = recursoService.buscarPorId(id);
        if (r == null) {
            System.out.println("No se encontró el recurso.");
            return;
        }
        System.out.println("Modificando: " + r);
        String nuevoTitulo = leerTexto("Nuevo Título (enter para mantener actual): ");
        if (!nuevoTitulo.isEmpty()) r.setTitulo(nuevoTitulo);
        
        int nuevoAnio = leerEnteroPositivo("Nuevo Año (0 para mantener actual): ", 0);
        if (nuevoAnio > 0) r.setAnio(nuevoAnio);
        
        recursoService.guardarCambios();
        
        System.out.println("Recurso modificado con éxito: " + r);
    }
    
    private static void modificarUsuario() {
        String id = leerTexto("ID del usuario a modificar: ");
        Usuario u = gestionUsuarios.buscarPorId(id);
        if (u == null) {
            System.out.println("No se encontró el usuario.");
            return;
        }
        System.out.println("Modificando: " + u);
        String nuevoNombre = leerTexto("Nuevo Nombre (enter para mantener actual): ");
        if (!nuevoNombre.isEmpty()) u.setNombre(nuevoNombre);
        
        String nuevoEmail = leerTexto("Nuevo Email (enter para mantener actual): ");
        if (!nuevoEmail.isEmpty()) u.setEmail(nuevoEmail);
        
        String nombreFinal = nuevoNombre.isEmpty() ? u.getNombre() : nuevoNombre;
        String emailFinal = nuevoEmail.isEmpty() ? u.getEmail() : nuevoEmail;
        gestionUsuarios.modificarUsuario(id, nombreFinal, emailFinal);
    }
    	
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Debe introducir un número entero válido.");
            }
        }
    }
    
    private static int leerEnteroPositivo(String mensaje, int min) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= min) {
                return valor;
            }
            System.out.println("ERROR: El valor debe ser mayor o igual a " + min + ".");
        }
    }
    
    private static String generarIdRecurso() {
        int max = 0;
        for (Recurso r : recursoService.obtenerTodos()) {
            try {
                int idActual = Integer.parseInt(r.getId());
                if (idActual > max) max = idActual;
            } catch (NumberFormatException ignored) {}
        }
        return String.valueOf(max + 1);
    }

    private static String generarIdUsuario() {
        int max = 0;
        for (Usuario u : gestionUsuarios.listarUsuarios()) {
            try {
                int idActual = Integer.parseInt(u.getId());
                if (idActual > max) max = idActual;
            } catch (NumberFormatException ignored) {}
        }
        return String.valueOf(max + 1);
    }

}
