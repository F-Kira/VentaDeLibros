package venta.libros.modelo.vo;

public class Nacionalidad {
    private int idNacionalidad;
    private String descripcion;

    public Nacionalidad () {
        //Constructor predeterminado.
    }

    public Nacionalidad (int id, String descripcion) {
        idNacionalidad = id;
        this.descripcion = descripcion;
    }

    public int getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(int idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
