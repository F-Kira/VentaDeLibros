package venta.libros.controlador;

import venta.libros.modelo.dao.LibroDAO;
import venta.libros.modelo.vo.Libro;
import venta.libros.vista.LibroABMC;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import static javax.swing.JOptionPane.*;

/**
 * Clase que controla los eventos de la vista LibroABMC.
 *
 * @autor Félix Pedrozo
 */
public class LibroControlador extends MouseAdapter implements ActionListener {
    private LibroABMC vista;
    private LibroDAO modelo;
    private int fila;

    public LibroControlador () {
        //Obtengo la referencia de mi LibroDAO.
        modelo = new LibroDAO();
        //Obtengo referencia de mi vista para utilizarlo en el controlador.
        vista = new LibroABMC(this);
        //Hago visible la vista.
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Obtengo el comando que identifica al botón pulsado.
        String command = e.getActionCommand();

        //Condición para detectar el botón cancelar.
        switch (command) {
            case "jbCancelar":
                LibroABMC.actualizar_eliminar = false;
                vista.activarBotones(false);
                vista.limpiarDatos();

                break;
            //Bloque de código para el botón buscar.
            case "jbBuscar":
                //Valido el dato del campo de texto buscar.
                if (vista.estaVacioBuscar())
                    vista.mostrarMensaje("No puede estar vacío el campo de busqueda.", ERROR_MESSAGE);

                else {
                    ArrayList<Libro> listLibro = buscar();

                    if (!vista.getJbMostrarTodo().isEnabled() && vista.libro.size() != listLibro.size()) {
                        vista.getJbMostrarTodo().setEnabled(true);
                        System.out.println ("Estoy dentro");
                    }

                    vista.insertarNuevasFilas(listLibro);
                }

                break;
            //Bloque de código para el botón mostrarTodo.
            case "jbMostrarTodo":
                vista.getJbMostrarTodo().setEnabled(false);
                vista.limpiarTabla();
                vista.cargarTodosLosRegistros();

                break;
            default:
                //Valido el dato del campo de texto titulo, comprobando si esta vacío.
                if (vista.estaVacioTitulo())
                    vista.mostrarMensaje("No puede estar vacío el campo de texto titulo.", ERROR_MESSAGE);

                //Condición para el botón eliminar.
                else if (command.equals("jbEliminar")) {
                    modelo.eliminarLibro(vista.libro.get(fila).getIdLibro());
                    vista.mostrarMensaje("Eliminación correcta.", INFORMATION_MESSAGE);
                    LibroABMC.actualizar_eliminar = false;
                    vista.activarBotones(false);

                //Bloque de código para el botón guardar.
                } else {
                    //Insertar nuevo registro.
                    if (!LibroABMC.actualizar_eliminar) {
                        modelo.insertarLibro(vista.getLibro());
                        vista.mostrarMensaje("Inserción correcta.", INFORMATION_MESSAGE);

                    //Actualizar registro.
                    } else {
                        modelo.actualizarLibro(vista.getLibro());
                        vista.mostrarMensaje("Actualización correcta.", INFORMATION_MESSAGE);
                        LibroABMC.actualizar_eliminar = false;
                        vista.activarBotones(false);
                    }
                }
                //Actualizar vista.
                vista.limpiarDatos();
                vista.limpiarTabla();
                vista.cargarTodosLosRegistros();
                break;
        }
    }

    /**
     * Metodo que verifica el RadioButton pulsado, asignando el query para realizar la consulta.
     *
     * @return Devuelve el resultado de la consulta.
     */
    private ArrayList<Libro> buscar () {
        String atributo = vista.getAtributoDeBusq(), where;

        switch (atributo) {
            //Selecciono autor.
            case "Autor":
                where = "WHERE au.descripcion like concat(?, '%')";
                break;

            //Selecciono categoria.
            case "Categoria":
                where = "WHERE cat.descripcion = like concat(?, '%')";
                break;

            //Selecciono titulo.
            case "Titulo":
                where = "WHERE li.titulo like concat(?, '%')";
                break;

            //Selecciono sección.
            default:
                where = "WHERE sec.descripcion like concat(?, '%')";
                break;
        }

        //Retorna el registro con los datos de la consulta.
        return modelo.obtenerUnLibro(where, vista.getAtributoDeBusqueda());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Compruebo si ha dado click dos veces.
        if (e.getClickCount() == 2) {
            JTable table = (JTable)e.getSource();
            //Obtengo el numero de la fila seleccionado.
            fila = table.getSelectedRow();
            //Actualizo la vista de la pestaña ABM.
            vista.cargarRegistro(vista.libro.get(fila));
            vista.cambiarPestania(0);
            //Habilitado para que se pueda actualizar y eliminar.
            LibroABMC.actualizar_eliminar = true;
        }
    }
}
