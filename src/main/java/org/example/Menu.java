package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public void iniciar() {
        ConexionBD conexionBD = null;
        try {
            conexionBD = new ConexionBD();
            conexionBD.conectar();
            //Crear instancias
            GestorBD gestorBD = new GestorBD(conexionBD.getConexion());

            Scanner sc = new Scanner(System.in); // Inicializa el escáner para leer entradas del usuario
            boolean salir = false; // Bandera para controlar la salida del bucle

            //Bucle while
            while (!salir) {
                System.out.println("Selecciona una Opcion:");
                EnumMenu.mostrarOpcionesMenu(); //Mostrar las opciones del menu

                //Leer y validar la opcion del menu
                EnumMenu opcion = Validaciones.validarMenu(sc);

                switch (opcion) {
                    case CREAR_TABLAS:
                        gestorBD.crearTablas(); // Crea las tablas
                        break;
                    case INSERTAR_NUEVA_CATEGORIA:
                        gestorBD.insertarCategoria(sc); // Inserta nueva categoria
                        break;
                    case INSERTAR_NUEVO_PRODUCTO:
                        gestorBD.insertarProducto(sc);
                        break;
                    case OBTENER_PRODUCTOS:
                        gestorBD.obtenerProductos();
                        break;
                    case OBTENER_CATEGORIAS:
                        gestorBD.obtenerCategorias();
                        break;
                    case OBTENER_PRODUCTOS_CON_WHERE:
                        gestorBD.obtenerProductosConWhere();
                        break;
                    case ELIMINAR_PRODUCTO:
                        gestorBD.eliminarProducto(sc);
                        break;
                    case ELIMINAR_CATEGORIA:
                        gestorBD.eliminarCategoria(sc);
                        break;
                    case SALIR:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }

            }
            sc.close();

        } catch (SQLException e) {
            System.out.println("Error SQLException: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error ClassNotFoundException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        } finally {
            if (conexionBD != null) {
                DesconexionBD.desconectar(conexionBD.getConexion());
            }
        }
    }
}
