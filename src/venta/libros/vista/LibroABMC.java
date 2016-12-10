package venta.libros.vista;

import com.toedter.calendar.JDateChooser;
import venta.libros.controlador.LibroControlador;
import venta.libros.modelo.dao.AutorDAO;
import venta.libros.modelo.dao.CategoriaDAO;
import venta.libros.modelo.dao.LibroDAO;
import venta.libros.modelo.dao.SeccionDAO;
import venta.libros.modelo.vo.Autor;
import venta.libros.modelo.vo.Categoria;
import venta.libros.modelo.vo.Libro;
import venta.libros.modelo.vo.Seccion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que contiene la configuración de la interfaz de usuario.
 *
 * @autor Félix Pedrozo
 */
public class LibroABMC extends JFrame {
    private JTabbedPane jtpLibro;
    private JLabel jlTitulo, jlDescripcion, jlAutor, jlFiltrar, jlSeccion, jlCategoria, jlFechaPubl;
    private JButton jbGuardar, jbEliminar, jbCancelar, jbBuscar, jbMostrarTodo;
    private JRadioButton jrbTitulo, jrbAutor, jrbSeccion, jrbCategoria;
    private JTextField jtfTitulo, jtfBuscar;
    private JComboBox <Autor> jcbAutor;
    private JComboBox <Seccion> jcbSeccion;
    private JComboBox <Categoria> jcbCategoria;
    private JTextArea jtaDescripcion;
    private JScrollPane jScrollPane;
    private JTable jtLibro;
    private JDateChooser jdFechaPubl;
    public ArrayList<Libro> libro;
    private LibroControlador controlador;
    public static boolean actualizar_eliminar;

    public LibroABMC (LibroControlador controlador) {
        setSize(600, 500);
        setTitle ("Operación de libro");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Obtengo la referencia del controlador.
        this.controlador = controlador;

        //Inserto los paneles al TabbedPane.
        jtpLibro = new JTabbedPane();
        jtpLibro.addTab("ABM", addTabABMLibro());
        jtpLibro.addTab("Consulta", addTabLibroConsulta());

        //Cargo los datos para el comboBox y el table.
        cargarDatos();

        //Inserto al Frame el TabbedPane.
        add (jtpLibro);
    }

    /**
     * Contiene la configuración del panel para la pestaña ABM.
     *
     * @return Retorna el panel configurado
     */
    private JPanel addTabABMLibro () {
        JPanel jpABMLibro = new JPanel(new GridBagLayout());
        GridBagConstraints conf = new GridBagConstraints();

        //Componente de la fila 0 columna 0.
        conf.gridx = 0;
        conf.gridy = 0;
        conf.anchor = GridBagConstraints.EAST;
        conf.insets = new Insets(10, 10, 10, 10);

        jlTitulo = new JLabel ("Titulo");
        jpABMLibro.add (jlTitulo, conf);

        //Componente de la fila 1 columna 0.
        conf.gridy = 1;
        conf.anchor = GridBagConstraints.NORTHEAST;
        conf.insets = new Insets(0, 10, 10, 10);

        jlDescripcion = new JLabel ("Descripción");
        jpABMLibro.add (jlDescripcion, conf);

        //Componente de la fila 2 columna 0.
        conf.gridy = 2;
        conf.anchor = GridBagConstraints.EAST;

        jlAutor = new JLabel ("Autor");
        jpABMLibro.add (jlAutor, conf);

        //Componente de la fila 3 columna 0.
        conf.gridy = 3;

        jlCategoria = new JLabel ("Categoria");
        jpABMLibro.add (jlCategoria, conf);

        //Componente de la fila 4 columna 0.
        conf.gridy = 4;

        jlFechaPubl = new JLabel ("Fecha publicación");
        jpABMLibro.add(jlFechaPubl, conf);

        //Componente de la fila 5 columna 0.
        conf.gridy = 5;

        jlSeccion = new JLabel ("Sección");
        jpABMLibro.add (jlSeccion, conf);

        //Componente de la fila 0 columna 1.
        conf.gridx = 1;
        conf.gridy = 0;
        conf.weightx = 1.0;
        conf.fill = GridBagConstraints.HORIZONTAL;
        conf.anchor = GridBagConstraints.WEST;
        conf.insets = new Insets(10, 0, 10, 10);

        jtfTitulo = new JTextField(35);
        jpABMLibro.add (jtfTitulo, conf);

        //Componente de la fila 1 columna 1.
        conf.gridy = 1;
        conf.insets = new Insets(0, 0, 10, 10);

        jtaDescripcion = new JTextArea();
        jtaDescripcion.setLineWrap (true);
        jScrollPane = new JScrollPane(jtaDescripcion);
        jScrollPane.setMinimumSize(new Dimension(400, 100));
        jScrollPane.setPreferredSize(new Dimension(400, 100));

        jpABMLibro.add (jScrollPane, conf);

        //Componente de la fila 2 columna 1.
        conf.gridy = 2;

        jcbAutor = new JComboBox<>();
        jpABMLibro.add (jcbAutor, conf);

        //Componente de la fila 3 columna 1.
        conf.gridy = 3;

        jcbCategoria = new JComboBox<>();
        jcbCategoria.setActionCommand("jcbCategoria");
        jpABMLibro.add (jcbCategoria, conf);

        //Componente de la fila 4 columna 1.
        conf.gridy = 4;

        jdFechaPubl = new JDateChooser(new Date());
        jdFechaPubl.setDateFormatString("dd/MM/yyyy");
        jdFechaPubl.setIcon(new ImageIcon(getClass().getResource("iconos/calendario.png")));
        jpABMLibro.add (jdFechaPubl, conf);

        //Componente de la fila 5 columna 1.
        conf.gridy = 5;

        jcbSeccion = new JComboBox<>();
        jpABMLibro.add(jcbSeccion, conf);

        //Componente de la fila 6 columna 0.
        conf.gridx = 0;
        conf.gridy = 6;
        conf.gridwidth = 2;
        conf.weighty = 1.0;
        conf.insets = new Insets(0, 10, 10, 10);
        conf.anchor = GridBagConstraints.SOUTH;
        conf.fill = GridBagConstraints.HORIZONTAL;

        jbGuardar = new JButton("Guardar");
        jbGuardar.setActionCommand("jbGuardar");
        jbGuardar.addActionListener(controlador);

        jbEliminar = new JButton("Eliminar");
        jbEliminar.setActionCommand("jbEliminar");
        jbEliminar.addActionListener(controlador);

        jbCancelar = new JButton("Cancelar");
        jbCancelar.setActionCommand("jbCancelar");
        jbCancelar.addActionListener(controlador);

        //Desactivo los botones eliminar y guardar.
        activarBotones(false);

        //Inicializo el panel jpBotones y inserto los botones.
        JPanel jpBotones = new JPanel (new FlowLayout(FlowLayout.RIGHT, 10, 10));
        jpBotones.setBorder(BorderFactory.createRaisedBevelBorder());
        jpBotones.add(jbGuardar);
        jpBotones.add(jbEliminar);
        jpBotones.add(jbCancelar);

        jpABMLibro.add (jpBotones, conf);

        //Retorno el panel configurado.
        return jpABMLibro;
    }

    /**
     * Contiene la configuración del panel para la pestaña Consulta.
     *
     * @return Retorna el panel configurado
     */
    private JPanel addTabLibroConsulta () {
        //Creación del panel jpBuscar.
        JPanel jpBuscar = new JPanel(new GridBagLayout());
        GridBagConstraints conf = new GridBagConstraints();

        //Componente de la fila 0 columna 0.
        conf.gridx = 0;
        conf.gridy = 0;
        conf.gridwidth = 3;
        conf.anchor = GridBagConstraints.WEST;
        conf.insets = new Insets(10, 20, 10, 10);
        conf.fill = GridBagConstraints.HORIZONTAL;

        jtfBuscar = new JTextField(25);
        jpBuscar.add (jtfBuscar, conf);

        //Componente de la fila 0 columna 3.
        conf.gridx = 3;
        conf.gridwidth = 1;
        conf.fill = GridBagConstraints.NONE;
        conf.insets = new Insets(10, 0, 10, 10);

        jbBuscar = new JButton(new ImageIcon(getClass().getResource("iconos/buscar.gif")));
        jbBuscar.setActionCommand("jbBuscar");
        jbBuscar.setToolTipText("Buscar registros.");
        jbBuscar.addActionListener(controlador);
        jpBuscar.add (jbBuscar, conf);

        //Componente de la fila 0 columna 4.
        conf.gridx = 4;
        conf.anchor = GridBagConstraints.WEST;

        jbMostrarTodo = new JButton(new ImageIcon(getClass().getResource("iconos/todos.png")));
        jbMostrarTodo.setActionCommand("jbMostrarTodo");
        jbMostrarTodo.setToolTipText("Mostrar todos los registros");
        jbMostrarTodo.setEnabled(false);
        jbMostrarTodo.addActionListener(controlador);
        jpBuscar.add (jbMostrarTodo);

        //Componente de la fila 1 columna 0.
        conf.gridx = 0;
        conf.gridy = 1;
        conf.weightx = 1.0;
        conf.insets = new Insets(0, 20, 10, 20);

        jlFiltrar = new JLabel("Filtrar por :");
        jpBuscar.add (jlFiltrar, conf);

        ButtonGroup buttonGroup = new ButtonGroup();

        //Componente de la fila 1 columna 1.
        conf.gridx = 1;
        conf.insets = new Insets(0, 0, 10, 20);

        jrbAutor = new JRadioButton("Autor", true);
        buttonGroup.add (jrbAutor);
        jpBuscar.add (jrbAutor, conf);

        //Componente de la fila 1 columna 2.
        conf.gridx = 2;

        jrbCategoria = new JRadioButton("Categoria");
        buttonGroup.add (jrbCategoria);
        jpBuscar.add (jrbCategoria, conf);

        //Componente de la fila 1 columna 3.
        conf.gridx = 3;

        jrbTitulo = new JRadioButton("Titulo");
        buttonGroup.add (jrbTitulo);
        jpBuscar.add (jrbTitulo, conf);

        //Componente de la fila 1 columna 4.
        conf.gridx = 4;

        jrbSeccion = new JRadioButton("Sección");
        buttonGroup.add (jrbSeccion);
        jpBuscar.add (jrbSeccion, conf);

        //Configuro los componentes para el panel jpConsultaLibro.
        JPanel jpConsultaLibro = new JPanel(new GridBagLayout());

        //Componente de la fila 0 columna 0.
        conf.gridx = 0;
        conf.gridy = 0;
        conf.insets = new Insets(10, 10, 10, 10);
        conf.fill = GridBagConstraints.HORIZONTAL;

        jpConsultaLibro.add (jpBuscar, conf);

        //Componente de la fila 1 columna 0.
        conf.gridy = 1;
        conf.weighty = 1.0;
        conf.fill = GridBagConstraints.BOTH;
        conf.insets = new Insets (0, 10, 10,10);

        String [] colums = {"Titulo", "Autor", "Categoria", "Sección", "Fecha de publicación"};
        jtLibro = new JTable(new DefaultTableModel (colums, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        jtLibro.addMouseListener(controlador);
        JScrollPane jspTablaLibro = new JScrollPane(jtLibro);
        jpConsultaLibro.add (jspTablaLibro, conf);

        //Retorno el panel configurado.
        return jpConsultaLibro;
    }

    /**
     * Metodo que retorna el campo de texto de busqueda.
     *
     * @return Retorna el JTextFiel buscar.
     */
    public String getAtributoDeBusqueda () {
        return jtfBuscar.getText();
    }

    /**
     * Valida el dato del campo titulo para determinar si esta vacío.
     *
     * @return Estado del campo de texto titulo.
     */
    public boolean estaVacioTitulo () {
        return jtfTitulo.getText().trim().isEmpty();
    }

    /**
     * Limpia los componentes de la pestaña ABM.
     */
    public void limpiarDatos () {
        jtfTitulo.setText(null);
        jtaDescripcion.setText(null);
        jcbAutor.setSelectedIndex(0);
        jcbSeccion.setSelectedIndex(0);
        jcbCategoria.setSelectedIndex(0);
        jdFechaPubl.setDate(new Date());
    }

    /**
     * Muestra un cuadro de dialogo.
     *
     * @param mensaje El mensaje a ser mostrado
     * @param tipoMensaje Tipo de mensaje que sera mostrado.
     */
    public void mostrarMensaje (String mensaje, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", tipoMensaje);
    }

    /**
     * Extrae los datos de los componentes de la pestaña ABM.
     *
     * @return Retorna un objeto libro, conteniendo los datos de los componentes.
     */
    public Libro getLibro () {
        Libro libro = new Libro ();

        if (actualizar_eliminar)
            libro.setIdLibro(this.libro.get(jtLibro.getSelectedRow()).getIdLibro());

        libro.setTitulo(jtfTitulo.getText());
        libro.setDescripcion(jtaDescripcion.getText());
        libro.setSeccion((Seccion)jcbSeccion.getSelectedItem());
        libro.setCategoria((Categoria)jcbCategoria.getSelectedItem());
        libro.setAutor((Autor)jcbAutor.getSelectedItem());
        libro.setFechaPublicacion(jdFechaPubl.getDate());

        return libro;
    }

    /**
     * Determina el RadioButton seleccionado en la pestaña Consulta.
     *
     * @return Nombre de radioButton seleccionado.
     */
    public String getAtributoDeBusq () {
        if (jrbTitulo.isSelected())
            return "Titulo";
        else if (jrbAutor.isSelected())
            return "Autor";
        else if (jrbCategoria.isSelected())
            return "Categoria";

        return "Seccion";
    }

    /**
     * Valida el dato del campo buscar.
     *
     * @return Estado del campo de texto buscar.
     */
    public boolean estaVacioBuscar () {
        return jtfBuscar.getText().isEmpty();
    }

    /**
     * Inserta nuevas filas al JTable.
     *
     * @param infoLibro Objeto que contiene los datos de la filas a insertar.
     */
    public void insertarNuevasFilas (ArrayList <Libro> infoLibro) {
        limpiarTabla();
        libro = infoLibro;
        DefaultTableModel modelo = (DefaultTableModel)jtLibro.getModel();
        for (Libro registro : libro)
            modelo.addRow(registro.toArray());

        //Actualizo la tabla.
        jtLibro.updateUI();
    }

    /**
     * Borra las filas del JTable.
     */
    public void limpiarTabla () {
        DefaultTableModel modelo = (DefaultTableModel)jtLibro.getModel();
        for (int i = modelo.getRowCount() - 1; i >= 0; i --)
            modelo.removeRow(i);
    }

    /**
     * Carga los comboBox de la pestaña ABM y la tabla de la pestaña Consulta.
     */
    private void cargarDatos () {
        ArrayList<Autor> autor = new AutorDAO().obtenerTodosLosAutores();

        for (Autor autorNombre : autor)
            jcbAutor.addItem(autorNombre);

        ArrayList<Seccion> seccion = new SeccionDAO().obtenerTodosLasSecciones();
        for (Seccion seccionNombre : seccion)
            jcbSeccion.addItem(seccionNombre);

        ArrayList<Categoria> categoria = new CategoriaDAO().obtenerTodosLasCategorias();
        for (Categoria categoriaNombre : categoria)
            jcbCategoria.addItem(categoriaNombre);

        cargarTodosLosRegistros ();
    }

    /**
     * Carga el JTable con los registros que corresponden al libro.
     */
    public void cargarTodosLosRegistros () {
        libro = new LibroDAO().obtenerTodosLosLibros();
        DefaultTableModel modelo = (DefaultTableModel)jtLibro.getModel();
        for (Libro registro : libro)
            modelo.addRow(registro.toArray());

        //Actualizo la tabla.
        jtLibro.updateUI();
    }

    /**
     * Metodo que cargo los componentes de la pestaña ABM para eliminar o actualizar el registro.
     *
     * @param libro Objeto que contiene los datos del registro a insertar.
     */
    public void cargarRegistro (Libro libro) {
        jtfTitulo.setText(libro.getTitulo());
        jtaDescripcion.setText((libro.getDescripcion() != null) ? libro.getDescripcion() : null);
        jcbCategoria.setSelectedItem(libro.getCategoria());
        jcbSeccion.setSelectedItem(libro.getSeccion());
        jcbAutor.setSelectedItem(libro.getAutor());
        jdFechaPubl.setDate(libro.getFechaPublicacion());
    }

    /**
     * Cambiar la pestaña de la vista.
     *
     * @param index Indice de la pestaña a mostrar.
     */
    public void cambiarPestania (int index) {
        jtpLibro.setSelectedIndex(index);
        activarBotones (true);
    }

    public void activarBotones (boolean estado) {
        jbEliminar.setEnabled(estado);
        jbCancelar.setEnabled(estado);
    }

    public JButton getJbMostrarTodo() {
        return jbMostrarTodo;
    }
}


















