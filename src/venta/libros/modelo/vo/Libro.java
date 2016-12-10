package venta.libros.modelo.vo;

import java.util.Date;

public class Libro {
    private int idLibro;
    private String titulo, descripcion;
    private Categoria categoria;
    private Seccion seccion;
    private Autor autor;
    private Date fechaPublicacion;

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date anio_publicacion) {
        this.fechaPublicacion = anio_publicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Metodo que me devuelve los datos de la clase Libro.
     *
     * @return Devuelve los datos en forma de array.
     */
    public String [] toArray () {
        return new String [] {titulo, autor.getNombre(), categoria.getDescripcion(),
                seccion.getDescripcion(), "" + fechaPublicacion};
    }
}
