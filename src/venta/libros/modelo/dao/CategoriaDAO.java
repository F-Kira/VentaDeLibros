package venta.libros.modelo.dao;

import venta.libros.modelo.conexion.Conexion;
import venta.libros.modelo.vo.Categoria;
import java.sql.*;
import java.util.ArrayList;

import static java.lang.System.err;

public class CategoriaDAO {
    //Obtengo la conexión.
    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement = null;

    public ArrayList<Categoria> obtenerTodosLasCategorias () {
        String query = "SELECT * FROM categoria";
        ArrayList <Categoria> categorias = new ArrayList<>();

        try {
            Connection dbConnection = conexion.abrir();
            preparedStatement = dbConnection.prepareStatement(query);

            ResultSet rs = 	preparedStatement.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria ();
                categoria.setId(rs.getInt("id"));
                categoria.setDescripcion(rs.getString("descripcion"));

                categorias.add(categoria);
            }
        } catch (SQLException e) {
            err.println(e.getMessage());
        } finally {
            cerrarTodo();
        }
        return categorias;
    }

    private void cerrarTodo () {
        try {
            conexion.cerrar();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            }catch (SQLException ex) {
                err.println ("Error al cerrar el objeto tipo PreparedStatement");
                ex.printStackTrace();
            }
        }
    }
}