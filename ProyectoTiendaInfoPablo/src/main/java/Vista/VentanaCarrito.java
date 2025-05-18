package Vista;


import Modelo.Cliente;
import Modelo.Compra;
import Modelo.CompraDAO;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.Empleado;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Map;

public class VentanaCarrito extends JFrame {
    double total =0;
    public VentanaCarrito(Cliente cliente, Map<Producto, Integer> carrito) {
        setTitle("Carrito de Compra");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);

        area.setEditable(false);


        for (Map.Entry<Producto, Integer> entry : carrito.entrySet()) {
            Producto p = entry.getKey();
            int cantidad = entry.getValue();
            area.append(p.getNombre() + " x" + cantidad + " → " + (cantidad * p.getPrecio()) + "€\n");
            total += cantidad * p.getPrecio();
        }

        area.append("\nTotal: " + total + "€");

        JButton btnComprar = new JButton("Realizar compra");
        JButton btnEditar = new JButton("Editar carrito");

        btnComprar.addActionListener(e -> {
            try {
                total=0;

                for (Map.Entry<Producto, Integer> entry : carrito.entrySet()) {
                    Producto p = entry.getKey();
                    int cantidad = entry.getValue();
                    total += cantidad * p.getPrecio();
                }

                Empleado emp = EmpleadoDAO.obtenerEmpleadoAleatorio();
                if (emp == null) {
                    JOptionPane.showMessageDialog(this, "No hay empleados disponibles.");
                    return;
                }

                Compra compra = new Compra(cliente, emp, total, carrito);
                CompraDAO.guardarCompra(compra);
                CompraDAO.guardarProductosComprados(compra);

                JOptionPane.showMessageDialog(this, "Compra realizada con éxito.");
                dispose();
                new VentanaClienteSesion(cliente).setVisible(true);

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al procesar la compra.");
            }
        });

        btnEditar.addActionListener(e -> {
            dispose();
            new VentanaClienteSesion(cliente).setVisible(true);
        });

        JPanel botones = new JPanel();
        botones.add(btnEditar);
        botones.add(btnComprar);

        add(scroll, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }
}