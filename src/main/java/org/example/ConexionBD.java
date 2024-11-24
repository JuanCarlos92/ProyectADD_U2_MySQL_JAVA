package org.example;

import java.sql.*;

public class ConexionBD {
    private Connection conexion;
    private static final String URL = "jdbc:mysql://localhost/productobd";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public ConexionBD() {}

    // Metodo para conectar a la BD
    public void conectar() throws SQLException, ClassNotFoundException {
        // Cargar el driver de MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establecer la conexión
        this.conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Conexión establecida correctamente.");
    }

    // Metodo para obtener la conexión
    public Connection getConexion() {
        return this.conexion;
    }

}




