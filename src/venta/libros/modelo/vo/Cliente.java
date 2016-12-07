package venta.libros.modelo.vo;

import java.math.BigInteger;

public class Cliente extends Persona{
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
