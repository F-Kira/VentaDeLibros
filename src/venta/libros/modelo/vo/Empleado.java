package venta.libros.modelo.vo;

import java.math.BigInteger;
import java.util.Date;

public class Empleado extends Persona {
    private BigInteger id_Empleado;
    private Date f_ingreso, f_baja;

    public BigInteger getId_Empleado() {
        return id_Empleado;
    }

    public void setId_Empleado(BigInteger id_Empleado) {
        this.id_Empleado = id_Empleado;
    }

    public Date getF_ingreso() {
        return f_ingreso;
    }

    public void setF_ingreso(Date f_ingreso) {
        this.f_ingreso = f_ingreso;
    }

    public Date getF_baja() {
        return f_baja;
    }

    public void setF_baja(Date f_baja) {
        this.f_baja = f_baja;
    }
}
