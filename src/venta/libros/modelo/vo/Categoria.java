package venta.libros.modelo.vo;

public class Categoria {
    private int id;
    private String descripcion;

    public Categoria (int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Categoria () {
        //Constructor predeterminado.
    }

    public int getId() {
        return id;
    }

    public void setId(int idCategoria) {
        this.id = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Sobreescribo el metodo toString con la intención de indicarle que valor se mostrara en el comboBox de Categoria.
    @Override
    public String toString () {
        return descripcion;
    }

    @Override
    public boolean equals (Object objeto) {
        if (objeto != null && objeto instanceof Categoria) {
            Categoria categoria = (Categoria)objeto;
            if (categoria.getId() == id || categoria.getDescripcion() == descripcion)
                return true;
        }
        return false;
    }
}
