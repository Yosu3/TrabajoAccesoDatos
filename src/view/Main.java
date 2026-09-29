package view;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> System.out.println("Submenú Recursos en desarrollo");
                case 2 -> System.out.println("Submenú Usuarios en desarrollo");
                case 3 -> System.out.println("Submenú Préstamos en desarrollo");
                case 4 -> {
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
        System.out.println("4. Salir");
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
}
