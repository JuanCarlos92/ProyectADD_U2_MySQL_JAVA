package org.example;

import java.util.Scanner;

public class Validaciones {

    //Valida la opción de menú ingresada por el usuario.
    public static EnumMenu validarMenu(Scanner scanner) {
        EnumMenu opcionSeleccionada = null; // Variable para almacenar la opción seleccionada
        boolean esValido = false; // Bandera para controlar la validez de la opción

        // Bucle para seguir pidiendo la opción hasta que sea válida
        while (!esValido) {
            System.out.print("Elige una opción: ");
            if (scanner.hasNextInt()) { // Verificar si la entrada es un entero
                int codigo = scanner.nextInt();
                scanner.nextLine();
                opcionSeleccionada = EnumMenu.obtenerCodigo(codigo); // Obtener la opción correspondiente
                if (opcionSeleccionada != null) {
                    esValido = true; // Cambiar a true si la opción es válida
                } else {
                    System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Debes Introducir un número.");
                scanner.next(); // Descartar la entrada inválida
            }
        }
        return opcionSeleccionada; // Retornar la opción seleccionada
    }
}
