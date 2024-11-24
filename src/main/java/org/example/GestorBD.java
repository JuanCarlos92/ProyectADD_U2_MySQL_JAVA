package org.example;

import org.example.dao.CategoriaDAO;
import org.example.dao.ProductoDAO;
import org.example.dao.SelectDAO;
import org.example.dto.Categoria;
import org.example.dto.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;


public class GestorBD {
    private Connection conexion;
    private SelectDAO selectDAO;


    // Constructor que recibe la conexión
    public GestorBD(Connection conexion) {

        this.conexion = conexion;
        this.selectDAO = new SelectDAO(conexion);
    }

    // Metodo para crear las tablas en la base de datos
    public void crearTablas() {
        String tableCategoria = "CREATE TABLE IF NOT EXISTS Categoria (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(50) NOT NULL UNIQUE" +
                ")";

        String tableProducto = "CREATE TABLE IF NOT EXISTS Producto (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(100) NOT NULL, " +
                "precio DECIMAL(10, 2) NOT NULL, " +
                "categoria_id INT, " +
                "FOREIGN KEY (categoria_id) REFERENCES Categoria(id)" +
                ")";

        try (Statement statement = conexion.createStatement()) {

            statement.execute(tableCategoria);
            statement.execute(tableProducto);

            System.out.println("Tablas creadas correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear las tablas: " + e.getMessage());

        }
    }

    // Inserta una nueva categoría
    public void insertarCategoria(Scanner sc) throws SQLException {
        System.out.print("Introduce el nombre de la categoría: ");
        String nombre = sc.nextLine();

        Categoria categoria = new Categoria(nombre);
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexion);
        categoriaDAO.insertarCategoria(categoria);
    }

    // Inserta un nuevo producto
    public void insertarProducto(Scanner sc) throws SQLException {
        System.out.print("Introduce el nombre del producto: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce el precio del producto: ");
        double precio = sc.nextDouble();
        System.out.print("Introduce el ID de la categoría: ");
        int categoriaId = sc.nextInt();
        sc.nextLine();  // Limpiar buffer de entrada

        Producto producto = new Producto(nombre, precio, categoriaId);
        ProductoDAO productoDAO = new ProductoDAO(conexion);
        productoDAO.insertarProducto(producto);
    }

    // Obtener productos join categoria
    public void obtenerProductos() throws SQLException {
        selectDAO.selectProductoJoinCategoria();

    }

    // Obtener categorías
    public void obtenerCategorias() throws SQLException {
        List<Categoria> categorias = selectDAO.obtenerCategorias();
        for (Categoria categoria : categorias) {
            System.out.println("ID: " + categoria.getId() + " | Nombre: " + categoria.getNombre());
        }
    }

    // Obtener productos join categoria con WHERE
    public void obtenerProductosConWhere() throws SQLException {
        selectDAO.selectProductoJoinCategoriaMayorCien();
    }

    // Eliminar producto
    public void eliminarProducto(Scanner sc) throws SQLException {
        selectDAO.obtenerNombresProductos();

        System.out.print("Introduce el nombre del producto a eliminar: ");
        String nombreProducto = sc.nextLine();

        String sql = "DELETE FROM Producto WHERE nombre = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreProducto);

            //Ejecutar la consulta y obtener el numero de filas afectadas
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Producto " + nombreProducto + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró el producto con el nombre " + nombreProducto);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Eliminar categoría
    public void eliminarCategoria(Scanner sc) throws SQLException {
        selectDAO.obtenerNombresCategorias();

        System.out.print("Introduce el nombre de la categoría a eliminar: ");
        String nombreCategoria = sc.nextLine();

        String sql = "DELETE FROM Categoria WHERE nombre = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreCategoria);

            //Ejecutar la consulta y obtener el numero de filas afectadas
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Categoria " + nombreCategoria + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró la categoria con el nombre " + nombreCategoria);
            }
        }
    }
}


