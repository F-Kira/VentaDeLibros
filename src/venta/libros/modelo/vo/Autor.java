package venta.libros.modelo.vo;

public class Autor {
    private int idAutor;
    private String nombre;
    private Nacionalidad nacionalidad;

    public Autor (int id, String nombre, Nacionalidad nacionalidad) {
        idAutor = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    //Constructor predeterminado.
    public Autor () {}

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    //Sobreescribo el metodo toString con la intención de indicarle que valor se mostrara en el comboBox de autor.
    @Override
    public String toString () {
        return nombre;
    }

    @Override
    public boolean equals (Object objeto) {
        if (objeto != null && objeto instanceof Autor) {
            Autor autor = (Autor)objeto;
            if (autor.getNombre().equals(nombre))
                return true;
        }
        return false;
    }
}
