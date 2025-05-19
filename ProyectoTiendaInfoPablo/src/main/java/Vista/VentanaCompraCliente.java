package Vista;

import Modelo.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;

public class VentanaCompraCliente extends JFrame {

    private Cliente cliente;
    private Map<Producto, Integer> carrito = new LinkedHashMap<>();
    private JPanel panelCarrito;
    private JLabel lblTotal;

    public VentanaCompraCliente(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Compra - " + cliente.getNombre());
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 🔙 Panel superior con botón volver
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new BotonVolver(new VentanaClienteSesion(cliente)));
        add(panelSuperior, BorderLayout.NORTH);

        // 🟩 Panel de productos disponibles (izquierda)
        JPanel panelProductos = new JPanel();
        panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));
        JScrollPane scrollProductos = new JScrollPane(panelProductos);
        scrollProductos.setBorder(BorderFactory.createTitledBorder("Productos"));

        for (Producto p : ProductoDAO.getTodosLosProductos()) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            JTextArea info = new JTextArea(
                    p.getNombre() + "\n" +
                            "Marca: " + p.getMarca() + "\n" +
                            "Precio: " + p.getPrecio() + "€"
            );
            info.setEditable(false);

            JButton btnAdd = new JButton("Añadir al carrito");
            btnAdd.addActionListener(e -> {
                carrito.put(p, carrito.getOrDefault(p, 0) + 1);
                actualizarCarrito();
            });

            card.add(info, BorderLayout.CENTER);
            card.add(btnAdd, BorderLayout.SOUTH);
            panelProductos.add(card);
        }

        add(scrollProductos, BorderLayout.WEST);

        // 🟦 Panel del carrito (derecha)
        panelCarrito = new JPanel();
        panelCarrito.setLayout(new BoxLayout(panelCarrito, BoxLayout.Y_AXIS));
        JScrollPane scrollCarrito = new JScrollPane(panelCarrito);
        scrollCarrito.setBorder(BorderFactory.createTitledBorder("Carrito"));
        scrollCarrito.setPreferredSize(new Dimension(400, 0));
        add(scrollCarrito, BorderLayout.EAST);

        // 🟨 Panel inferior (total + botones)
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblTotal = new JLabel("Total: 0.00€");

        JButton btnVaciar = new JButton("Vaciar carrito");
        btnVaciar.addActionListener(e -> {
            carrito.clear();
            actualizarCarrito();
        });

        JButton btnComprar = new JButton("✅ Realizar compra");
        btnComprar.addActionListener(e -> {
            if (carrito.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El carrito está vacío.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "¿Deseas confirmar la compra?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    double total = calcularTotal();
                    Empleado emp = EmpleadoDAO.obtenerEmpleadoAleatorio();
                    if (emp == null) {
                        JOptionPane.showMessageDialog(this, "No hay empleados disponibles.");
                        return;
                    }
                    Compra compra = new Compra(cliente, emp, total, carrito);
                    CompraDAO.guardarCompra(compra);
                    CompraDAO.guardarProductosComprados(compra);

                    JOptionPane.showMessageDialog(this, "¡Compra realizada con éxito!");
                    carrito.clear();
                    actualizarCarrito();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al procesar la compra.");
                }
            }
        });

        panelInferior.add(lblTotal);
        panelInferior.add(btnVaciar);
        panelInferior.add(btnComprar);
        add(panelInferior, BorderLayout.SOUTH);

        actualizarCarrito();
    }

    private void actualizarCarrito() {
        panelCarrito.removeAll();

        for (Producto p : carrito.keySet()) {
            int cantidad = carrito.get(p);

            JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lbl = new JLabel(p.getNombre() + " x" + cantidad);

            JButton btnMas = new JButton("+");
            btnMas.addActionListener(e -> {
                carrito.put(p, cantidad + 1);
                actualizarCarrito();
            });

            JButton btnMenos = new JButton("-");
            btnMenos.addActionListener(e -> {
                if (cantidad > 1) {
                    carrito.put(p, cantidad - 1);
                } else {
                    carrito.remove(p);
                }
                actualizarCarrito();
            });

            JButton btnEliminar = new JButton("❌");
            btnEliminar.addActionListener(e -> {
                carrito.remove(p);
                actualizarCarrito();
            });

            item.add(lbl);
            item.add(btnMas);
            item.add(btnMenos);
            item.add(btnEliminar);
            panelCarrito.add(item);
        }

        lblTotal.setText("Total: " + String.format("%.2f", calcularTotal()) + "€");

        panelCarrito.revalidate();
        panelCarrito.repaint();
    }

    private double calcularTotal() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : carrito.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
        return total;
    }
}
