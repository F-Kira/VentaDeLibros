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
        String query = "SELECT * FROM libro_info";
        ArrayList <Libro> libros = new ArrayList<>();

        try {
            Connection dbConnection = conexion.abrir();
            preparedStatement = dbConnection.prepareStatement(query);

            ResultSet rs = 	preparedStatement.executeQuery();
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setIdLibro(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setFechaPublicacion(rs.getDate("fecha_publicacion"));

                Categoria categoria = new Categoria ();
                categoria.setId(rs.getInt("categoria"));
                libro.setCategoria(categoria);

                Seccion seccion = new Seccion ();
                seccion.setId(rs.getInt("seccion"));
                libro.setSeccion(seccion);

                Autor autor = new Autor();
                autor.setIdAutor(rs.getInt("autor"));
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

    public ArrayList<Libro> obtenerUnLibro (String query, String atributoDeBusqueda) {
        ArrayList <Libro> libros = new ArrayList<>();
        try {
            Connection dbConnection = conexion.abrir();
            preparedStatement = dbConnection.prepareStatement(query);

            //Realizo un comprobación para determinar si es un Integer o un String.
            boolean esNumero = true;
            int num = 0;
            try {
                num = Integer.parseInt(atributoDeBusqueda);
            } catch (NumberFormatException ex) {
                esNumero = false;
            }

            if (esNumero)
                preparedStatement.setInt(1, num);
            else
                preparedStatement.setString(1, atributoDeBusqueda);


            ResultSet rs = 	preparedStatement.executeQuery();
            //Extraigo los datos.
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setIdLibro(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setFechaPublicacion(rs.getDate("fecha_publicacion"));

                Categoria categoria = new Categoria ();
                categoria.setId(rs.getInt("categoria"));
                libro.setCategoria(categoria);

                Seccion seccion = new Seccion ();
                seccion.setId(rs.getInt("seccion"));
                libro.setSeccion(seccion);

                Autor autor = new Autor();
                autor.setIdAutor(rs.getInt("autor"));
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