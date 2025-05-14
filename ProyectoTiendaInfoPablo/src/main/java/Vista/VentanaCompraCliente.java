package Vista;

import Modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class VentanaCompraCliente extends JFrame {

    private Cliente cliente;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    public VentanaCompraCliente(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Realizar Compra - Cliente: " + cliente.getNombre());
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnas = {"ID", "Nombre", "Marca", "Precio (€)", "Cantidad"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 4 ? Integer.class : String.class;
            }
        };

        tablaProductos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaProductos);
        cargarProductos();

        JButton btnComprar = new JButton("Realizar compra");
        btnComprar.addActionListener(e -> realizarCompra());

        add(scroll, BorderLayout.CENTER);
        add(btnComprar, BorderLayout.SOUTH);
    }

    private void cargarProductos() {
        List<Producto> productos = ProductoDAO.getTodosLosProductos();
        for (Producto p : productos) {
            modeloTabla.addRow(new Object[]{
                    p.getId(), p.getNombre(), p.getMarca(), p.getPrecio(), 0
            });
        }
    }

    private void realizarCompra() {
        Map<Producto, Integer> carrito = new HashMap<>();
        double total = 0;

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            int cantidad = (int) modeloTabla.getValueAt(i, 4);
            if (cantidad > 0) {
                Producto p = new Producto(
                        (int) modeloTabla.getValueAt(i, 0),
                        (String) modeloTabla.getValueAt(i, 1),
                        (String) modeloTabla.getValueAt(i, 2),
                        (double) modeloTabla.getValueAt(i, 3)
                );
                carrito.put(p, cantidad);
                total += cantidad * p.getPrecio();
            }
        }

        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No has seleccionado ningún producto.");
            return;
        }

        try {
            Empleado empleado = EmpleadoDAO.obtenerEmpleadoAleatorio();
            if (empleado == null) {
                JOptionPane.showMessageDialog(this, "No hay empleados disponibles para gestionar la compra.");
                return;
            }

            Compra compra = new Compra(cliente, empleado, total, carrito);
            CompraDAO.guardarCompra(compra);
            CompraDAO.guardarProductosComprados(compra);

            JOptionPane.showMessageDialog(this, "Compra realizada con éxito. Total: " + total + "€");
            dispose();
            new VentanaClienteSesion(cliente).setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la compra.");
        }
    }
}
