package venta.libros.vista;

import venta.libros.controlador.LibroControlador;
import static javax.swing.UIManager.*;
import javax.swing.*;

/**
 * Clase que contiene el metodo main, el punto de inicio del programa.
 *
 * @autor Félix Pedrozo
 */
public class Main {
    public static void main (String [] args) {
        try {
            /*
               Le configuro el LookAndFeel para que se adapte el diseño
               del programa a la plataforama en la que se esta ejecutando.
             */
            setLookAndFeel(getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new LibroControlador());
    }
}