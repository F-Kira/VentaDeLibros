package venta.libros.modelo.vo;

public class ModoDePago {
    private long idModoDePago;
    private String Descripcion;

    public long getIdModoDePago() {
        return idModoDePago;
    }

    public void setIdModoDePago(long idModoDePago) {
        this.idModoDePago = idModoDePago;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
