package org.example.dao;

import org.example.dto.Categoria;
import org.example.dto.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectDAO {
    private Connection conexion;

    public SelectDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean existeCategoria(int categoriaId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Categoria WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, categoriaId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return false; // Si no hay resultados, devuelve false
    }

    public void obtenerNombresCategorias() {
        String sql = "SELECT nombre FROM Categoria";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Mostrar los nombres de las categorias
            while (rs.next()) {
                String nombreCategoria = rs.getString("nombre");
                System.out.println("Categoria: " + nombreCategoria);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void obtenerNombresProductos() {
        String sql = "SELECT nombre FROM Producto";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Mostrar los nombres de los productos
            while (rs.next()) {
                String nombreProducto = rs.getString("nombre");
                System.out.println("Producto: " + nombreProducto);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Metodo para obtener productos con sus categorías
    private List<Producto> obtenerProductos(String sql) {
        List<Producto> productos = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int productoId = rs.getInt("producto_id");
                String productoNombre = rs.getString("producto_nombre");
                double precio = rs.getDouble("precio");
                int categoriaId = rs.getInt("categoria_id");
                String categoriaNombre = rs.getString("categoria_nombre");

                // Crea un objeto Producto y lo añade a la lista
//                System.out.printf("ID: %d, Nombre: %s, Precio: %.2f, CategoriaID: %d, CategoriaNombre: %s%n",
//                        productoId, productoNombre, precio, categoriaId, categoriaNombre);
                Producto producto = new Producto(productoId, productoNombre, precio, categoriaId, categoriaNombre);
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error SQLException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return productos; // Devuelve la lista
    }

    public void obtenerTodosLosProductos(String sql) {

        List<Producto> productos = obtenerProductos(sql);

        if (productos.isEmpty()) {
            System.out.println("No hay productos");
        } else {
            System.out.println("Lista de todos los productos:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    public void obtenerProductosMayorCien(String sql) {

        List<Producto> productos = obtenerProductos(sql);

        if (productos.isEmpty()) {
            System.out.println("No hay productos con un precio mayor a 100");
        } else {
            System.out.println("Lista de productos con precio mayor a 100:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    // Obtiene todas las categorías
    public List<Categoria> obtenerCategorias() {
        List<Categoria> categoriaLista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM Categoria";


        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                categoriaLista.add(new Categoria(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return categoriaLista;
    }

    // obtener productos con categorías
    public void selectProductoJoinCategoria() {
        String sql = "SELECT p.id AS producto_id, p.nombre AS producto_nombre, p.precio, " +
                "c.id AS categoria_id, c.nombre AS categoria_nombre " +
                "FROM Producto p " +
                "JOIN Categoria c ON p.categoria_id = c.id";

        obtenerTodosLosProductos(sql);
    }

    // obtener productos con categorías precio > 100
    public void selectProductoJoinCategoriaMayorCien() {
        String sql = "SELECT p.id AS producto_id, p.nombre AS producto_nombre, p.precio, " +
                "c.id AS categoria_id, c.nombre AS categoria_nombre " +
                "FROM Producto p " +
                "JOIN Categoria c ON p.categoria_id = c.id " +
                "WHERE p.precio > 100";

        obtenerProductosMayorCien(sql);
    }
}

