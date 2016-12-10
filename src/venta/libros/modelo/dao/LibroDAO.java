package venta.libros.modelo.dao;

import venta.libros.modelo.conexion.Conexion;
import venta.libros.modelo.vo.Autor;
import venta.libros.modelo.vo.Categoria;
import venta.libros.modelo.vo.Libro;
import venta.libros.modelo.vo.Seccion;

import java.sql.*;
import java.util.ArrayList;
import static java.lang.System.*;

public class LibroDAO {
    //Obtengo la conexión.
    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement = null;

    private final String consultaLibro = "SELECT li.id, li.titulo, li.descripcion, li.fecha_publicacion, cat.descripcion, " +
                "sec.descripcion, au.descripcion FROM libro_info AS li INNER JOIN categoria AS cat ON " +
                "cat.id = li.categoria INNER JOIN seccion AS sec ON sec.id = li.seccion INNER JOIN autor AS au " +
                "ON au.id = li.autor";

    public boolean insertarLibro(Libro libro) {
        String query = "INSERT INTO libro_info (titulo, descripcion, categoria, fecha_publicacion, seccion, autor) " +
                "VALUES (?,?,?,?,?,?)";

        try {
            Connection dbConnection = conexion.abrir();
            preparedStatement = dbConnection.prepareStatement(query);

            preparedStatement.setString(1, libro.getTitulo());

            if (libro.getDescripcion() != null)
                preparedStatement.setString(2, libro.getDescripcion());
            else
                preparedStatement.setNull(2, Types.CHAR);

            preparedStatement.setInt(3, libro.getCategoria().getId());
            preparedStatement.setDate(4, new java.sql.Date(libro.getFechaPublicacion().getTime()));
            preparedStatement.setInt(5, libro.getSeccion().getId());
            preparedStatement.setInt(6, libro.getAutor().getIdAutor());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarTodo();
        }
    }

    public void eliminarLibro (int idLibro) {
        String query = "DELETE FROM libro_info WHERE id = ?";
        try {
            Connection dbConnection = conexion.abrir();
            preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, idLibro);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarTodo();
        }
    }

    public void actualizarLibro (Libro libro) {
        String query = "UPDATE libro_info SET titulo = ?, descripcion = ?, categoria = ?, " +
                "seccion = ?, fecha_publicacion = ?, autor = ? WHERE id = ?";

        try {
            Connection dbConnection = conexion.abrir();
            preparedStatement = dbConnection.prepareStatement(query);
            //Inserto los argumentos a los parametros.
            preparedStatement.setString(1, libro.getTitulo());
            preparedStatement.setString(2, libro.getDescripcion());
            preparedStatement.setInt(3, libro.getCategoria().getId());
            preparedStatement.setInt(4, libro.getSeccion().getId());
            preparedStatement.setDate(5, new java.sql.Date(libro.getFechaPublicacion().getTime()));
            preparedStatement.setInt(6, libro.getAutor().getIdAutor());
            preparedStatement.setInt(7, libro.getIdLibro());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarTodo();
        }
    }

    public ArrayList <Libro> obtenerTodosLosLibros () {
        ArrayList <Libro> libros = new ArrayList<>();

        try {
            Connection dbConnection = conexion.abrir();
            preparedStatement = dbConnection.prepareStatement(consultaLibro);

            ResultSet rs = 	preparedStatement.executeQuery();
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setIdLibro(rs.getInt(1));
                libro.setTitulo(rs.getString(2));
                libro.setDescripcion(rs.getString(3));
                libro.setFechaPublicacion(rs.getDate(4));

                Categoria categoria = new Categoria ();
                categoria.setDescripcion(rs.getString(5));
                libro.setCategoria(categoria);

                Seccion seccion = new Seccion ();
                seccion.setDescripcion(rs.getString(6));
                libro.setSeccion(seccion);

                Autor autor = new Autor();
                autor.setNombre(rs.getString(7));
                libro.setAutor(autor);

                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            err.println(e.getMessage());
        } finally {
            cerrarTodo();
        }
        return libros;
    }

    public ArrayList<Libro> obtenerUnLibro (String where, String atributoDeBusqueda) {
        String query = String.format("%s %s", consultaLibro, where);

        ArrayList <Libro> libros = new ArrayList<>();
        try {
            Connection dbConnection = conexion.abrir();
            preparedStatement = dbConnection.prepareStatement(query);

            preparedStatement.setString(1, atributoDeBusqueda);

            ResultSet rs = 	preparedStatement.executeQuery();
            //Extraigo los datos.
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setIdLibro(rs.getInt(1));
                libro.setTitulo(rs.getString(2));
                libro.setDescripcion(rs.getString(3));
                libro.setFechaPublicacion(rs.getDate(4));

                Categoria categoria = new Categoria ();
                categoria.setDescripcion(rs.getString(5));
                libro.setCategoria(categoria);

                Seccion seccion = new Seccion ();
                seccion.setDescripcion(rs.getString(6));
                libro.setSeccion(seccion);

                Autor autor = new Autor();
                autor.setNombre(rs.getString(7));
                libro.setAutor(autor);

                libros.add(libro);
            }
        } catch (SQLException e) {
            err.println(e.getMessage());
        } finally {
            cerrarTodo();
        }
        return libros;
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