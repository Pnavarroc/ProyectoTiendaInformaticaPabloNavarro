package Vista;

import Controlador.ControladorCliente;
import Controlador.ControladorCompra;
import Modelo.Cliente;
import Modelo.Compra;
import Modelo.Producto;
import Modelo.ProductoDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.*;

public class VentanaClienteSesion extends JFrame {

    private Cliente cliente;
    private Map<Producto, Integer> carrito = new LinkedHashMap<>();
    private JPanel panelCarrito;
    private JLabel lblTotal;

    public VentanaClienteSesion(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Bienvenido, " + cliente.getNombre());
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 🧾 Panel productos
        JPanel panelProductos = new JPanel();
        panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));
        JScrollPane scrollProductos = new JScrollPane(panelProductos);
        scrollProductos.setBorder(BorderFactory.createTitledBorder("Catálogo de Productos"));

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

        add(scrollProductos, BorderLayout.CENTER);

        // 🛒 Carrito lateral
        panelCarrito = new JPanel();
        panelCarrito.setLayout(new BoxLayout(panelCarrito, BoxLayout.Y_AXIS));
        JScrollPane scrollCarrito = new JScrollPane(panelCarrito);
        scrollCarrito.setBorder(BorderFactory.createTitledBorder("Carrito de Compra"));
        scrollCarrito.setPreferredSize(new Dimension(300, 0));
        add(scrollCarrito, BorderLayout.EAST);

        // 🔘 Botones inferiores
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblTotal = new JLabel("Total: 0.00€");

        JButton btnVaciar = new JButton("🗑️ Vaciar");
        btnVaciar.addActionListener(e -> {
            carrito.clear();
            actualizarCarrito();
        });

        JButton btnComprar = new JButton("✅ Realizar Compra");
        btnComprar.addActionListener(e -> procesarCompra());

        JButton btnHistorial = new JButton("📜 Historial");
        btnHistorial.addActionListener(e -> new VentanaHistorialCliente(cliente).setVisible(true));

        JButton btnBaja = new JButton("❌ Darse de baja");
        btnBaja.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar tu cuenta?",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (ControladorCliente.eliminarCliente(cliente.getId())) {
                    JOptionPane.showMessageDialog(this, "Cuenta eliminada.");
                    dispose();
                    new VentanaCliente().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar tu cuenta.");
                }
            }
        });

        JButton btnCerrar = new JButton("🔙 Cerrar sesión");
        btnCerrar.addActionListener(e -> {
            dispose();
            new VentanaCliente().setVisible(true);
        });

        panelInferior.add(lblTotal);
        panelInferior.add(btnVaciar);
        panelInferior.add(btnComprar);
        panelInferior.add(btnHistorial);
        panelInferior.add(btnBaja);
        panelInferior.add(btnCerrar);

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
        return carrito.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrecio() * e.getValue())
                .sum();
    }

    private void procesarCompra() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Confirmas la compra por " + String.format("%.2f", calcularTotal()) + "€?",
                "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = ControladorCompra.procesarCompra(cliente, carrito);
            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Compra realizada con éxito!");
                carrito.clear();
                actualizarCarrito();
            } else {
                JOptionPane.showMessageDialog(this, "Hubo un problema al procesar la compra.");
            }
        }
    }
}
