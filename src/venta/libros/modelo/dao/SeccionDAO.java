package venta.libros.modelo.dao;

import venta.libros.modelo.conexion.Conexion;
import venta.libros.modelo.vo.Seccion;
import java.sql.*;
import java.util.ArrayList;
import static java.lang.System.err;

public class SeccionDAO {
    //Obtengo la conexión.
    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement = null;

    public ArrayList<Seccion> obtenerTodosLasSecciones () {
        String query = "SELECT * FROM seccion";
        ArrayList <Seccion> secciones = new ArrayList<>();

        try {
            Connection dbConnection = conexion.abrir();
            preparedStatement = dbConnection.prepareStatement(query);

            ResultSet rs = 	preparedStatement.executeQuery();
            while (rs.next()) {
                Seccion seccion = new Seccion ();
                seccion.setId(rs.getInt("id"));
                seccion.setDescripcion(rs.getString("descripcion"));

                secciones.add(seccion);
            }
        } catch (SQLException e) {
            err.println(e.getMessage());
        } finally {
            cerrarTodo();
        }
        return secciones;
    }

    private void cerrarTodo () {
        try {
            conexion.cerrar();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            }catch (SQLException ex) {
                err.println ("Error al cerrar el objeto de tipo PreparedStatement");
                ex.printStackTrace();
            }
        }
    }
}
