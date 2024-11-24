package org.example.dao;

import org.example.dto.Categoria;

import java.sql.*;


public class CategoriaDAO {
    private Connection conexion;
    private SelectDAO selectDAO;

    public CategoriaDAO(Connection conexion) {

        this.conexion = conexion;
        this.selectDAO = new SelectDAO(conexion);
    }

    // Inserta una nueva categoría
    public boolean insertarCategoria(Categoria categoria) throws SQLException {
        String sql = insertCategoria();

        try {
            conexion.setAutoCommit(false);

            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, categoria.getNombre());
                ps.executeUpdate();
                System.out.println("Categoría insertada correctamente: " + categoria.getNombre());

                // Confirma la transacción
                conexion.commit();

            } catch (SQLException e) {
                System.out.println("Error al insertar categoría: " + e.getMessage());
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexion.setAutoCommit(true);
        }

    }

    private String insertCategoria() {
        return "INSERT INTO Categoria (nombre) VALUES (?)";
    }
}
