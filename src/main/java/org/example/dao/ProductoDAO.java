package org.example.dao;

import org.example.dto.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoDAO {
    private Connection conexion;
    private SelectDAO selectDAO;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
        this.selectDAO = new SelectDAO(conexion);
    }

    // Inserta un nuevo producto
    public boolean insertarProducto(Producto producto) throws SQLException {
        String sql = insertProducto();

        try {
            // Deshabilita el auto-commit para iniciar la transacción
            conexion.setAutoCommit(false);

            if (!selectDAO.existeCategoria(producto.getCategoriaId())) {
                System.out.println("La categoría con ID " + producto.getCategoriaId() + " no existe. No se puede insertar el producto.");
                return false;
            }

            try (PreparedStatement ps = conexion.prepareStatement((sql))) {
                ps.setString(1, producto.getNombre());
                ps.setDouble(2, producto.getPrecio());
                ps.setInt(3, producto.getCategoriaId());
                ps.executeUpdate();

                System.out.println("Producto insertado correctamente: " + producto.getNombre());
                // Confirma la transacción
                conexion.commit();

            } catch (SQLException e) {
                System.out.println("Error al insertar producto: " + e.getMessage());
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexion.setAutoCommit(true);
        }

    }

    private String insertProducto() {
        return "INSERT INTO Producto (nombre, precio, categoria_id) VALUES (?, ?, ?)";
    }
}