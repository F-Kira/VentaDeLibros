package venta.libros.modelo.vo;

public class FacturaVenta extends Factura {
    private ModoDePago modoDePago;

    public ModoDePago getModoDePago() {
        return modoDePago;
    }

    public void setModoDePago(ModoDePago modoDePago) {
        this.modoDePago = modoDePago;
    }
}
