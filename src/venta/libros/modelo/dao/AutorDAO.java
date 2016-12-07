package venta.libros.modelo.dao;

import venta.libros.modelo.conexion.Conexion;
import venta.libros.modelo.vo.Autor;
import java.sql.*;
import java.util.ArrayList;
import static java.lang.System.err;

public class AutorDAO {
    //Obtengo la conexión.
    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement = null;

    public ArrayList<Autor> obtenerTodosLosAutores () {
        String query = "SELECT * FROM autor";
        ArrayList <Autor> autores = new ArrayList<>();

        try {
            Connection dbConnection = conexion.abrir();
            preparedStatement = dbConnection.prepareStatement(query);

            ResultSet rs = 	preparedStatement.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor ();
                autor.setIdAutor(rs.getInt("id"));
                autor.setNombre(rs.getString("descripcion"));

                autores.add(autor);
            }
        } catch (SQLException e) {
            err.println(e.getMessage());
        } finally {
            cerrarTodo();
        }
        return autores;
    }

    private void cerrarTodo () {
        try {
            conexion.cerrar();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException ex) {
                err.println ("Error al cerrar el objeto de tipo PreparedStatement");
                ex.printStackTrace();
            }
        }
    }
}
