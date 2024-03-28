
package com.mycompany.listatareasapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListaTareasApp extends JFrame {
    private JTextField tareaTextField;
    private JList<Tarea> listaTareas;
    private DefaultListModel<Tarea> listaModelo;
    private JButton btnAgregar;
    private JButton btnMarcarCompleta;
    private JButton btnEliminar;

    private List<Tarea> tareas;

    public ListaTareasApp() {
        super("Lista de Tareas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        tareaTextField = new JTextField();
        panel.add(tareaTextField, BorderLayout.NORTH);

        listaModelo = new DefaultListModel<>();
        listaTareas = new JList<>(listaModelo);
        JScrollPane scrollPane = new JScrollPane(listaTareas);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout());

        // Escalando los iconos a 16x16
        ImageIcon addIcon = new ImageIcon(new ImageIcon(getClass().getResource("/icons/add.png")).getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
        ImageIcon completeIcon = new ImageIcon(new ImageIcon(getClass().getResource("/icons/complete.png")).getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
        ImageIcon deleteIcon = new ImageIcon(new ImageIcon(getClass().getResource("/icons/delete.png")).getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));

        btnAgregar = new JButton("Agregar", addIcon);
        btnAgregar.addActionListener(e -> agregarTarea());
        btnPanel.add(btnAgregar);

        btnMarcarCompleta = new JButton("Marcar Completa", completeIcon);
        btnMarcarCompleta.addActionListener(e -> marcarCompleta());
        btnPanel.add(btnMarcarCompleta);

        btnEliminar = new JButton("Eliminar", deleteIcon);
        btnEliminar.addActionListener(e -> eliminarTarea());
        btnPanel.add(btnEliminar);

        panel.add(btnPanel, BorderLayout.SOUTH);

        add(panel);

        tareas = new ArrayList<>();
    }

    private void agregarTarea() {
        String nombreTarea = tareaTextField.getText().trim();
        if (!nombreTarea.isEmpty()) {
            Tarea nuevaTarea = new Tarea(nombreTarea);
            tareas.add(nuevaTarea);
            listaModelo.addElement(nuevaTarea);
            tareaTextField.setText("");
        }
    }

    private void marcarCompleta() {
        int indiceSeleccionado = listaTareas.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            Tarea tarea = listaModelo.getElementAt(indiceSeleccionado);
            tarea.setCompletada(true);
            listaModelo.setElementAt(tarea, indiceSeleccionado);
        }
    }

    private void eliminarTarea() {
        int indiceSeleccionado = listaTareas.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            tareas.remove(indiceSeleccionado);
            listaModelo.remove(indiceSeleccionado);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ListaTareasApp::new);
    }
}

class Tarea {
    private String nombre;
    private boolean completada;

    public Tarea(String nombre) {
        this.nombre = nombre;
        this.completada = false;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    @Override
    public String toString() {
        return nombre + (completada ? " (Completada)" : "");
    }
}

