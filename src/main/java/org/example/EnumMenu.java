package org.example;

public enum EnumMenu {

    CREAR_TABLAS(1, "Crear tablas (productos y categorias)"),
    INSERTAR_NUEVA_CATEGORIA(2, "Añadir nueva categoria"),
    INSERTAR_NUEVO_PRODUCTO(3, "Añadir nuevo producto"),
    OBTENER_PRODUCTOS(4, "Obtener la lista de todos los productos"),
    OBTENER_CATEGORIAS(5, "Obtener la lista de todas las categorias"),
    OBTENER_PRODUCTOS_CON_WHERE(6, "Obtener los productos mayor a 100"),
    ELIMINAR_PRODUCTO(7, "Eliminar un producto"),
    ELIMINAR_CATEGORIA(8,"Eliminar una categoria"),
    SALIR(9, "Salir");

    private final int codigo; // Código numérico asociado a cada opción
    private final String descripcion; // Descripción de la opción


    EnumMenu(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }


    //Metodo para obtener una opción del enum a partir de su código.
    public static EnumMenu obtenerCodigo(int codigo) {
        for (EnumMenu opcion : values()) {
            if (opcion.getCodigo() == codigo) {
                return opcion; // Retornar la opción correspondiente
            }
        }
        return null; // Si el código no coincide con ninguna opción
    }

    //Metodo para mostrar las opciones del menú en la consola.
    public static void mostrarOpcionesMenu() {
        for (EnumMenu opcion : EnumMenu.values()) {
            System.out.println(opcion.getCodigo() + ". " + opcion.getDescripcion()); // Imprimir cada opción
        }
    }
}
