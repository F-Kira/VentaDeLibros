package venta.libros.modelo.vo;

public class Seccion {
    private int id;
    private String descripcion;

    public Seccion (int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    //Constructor predeterminado.
    public Seccion () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Sobreescribo el metodo toString con la intención de indicarle que valor se mostrara en el comboBox de Sección.
    @Override
    public String toString () {
        return descripcion;
    }

    @Override
    public boolean equals (Object objeto) {
        if (objeto != null && objeto instanceof Seccion) {
            Seccion seccion = (Seccion)objeto;
            if (seccion.getId() == id || seccion.getDescripcion() == descripcion)
                return true;
        }
        return false;
    }
}
