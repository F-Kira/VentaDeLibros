package venta.libros.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.lang.System.err;

/**
 * Clase que contiene la configuración para conectarme y desconectarme de la base de datos.
 *
 * @autor Félix Pedrozo
 */
public class Conexion {
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/VentaDeLibros";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "001122334455";
    private Connection conexion;

    public Connection abrir () {
        try {
            conexion = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conexion;
    }

    public void cerrar () {
        try {
            if (conexion != null) conexion.close();
        } catch (SQLException ex) {
            err.println ("Error al cerrar el objeto de tipo Connection");
            ex.printStackTrace();
        }
    }
}
