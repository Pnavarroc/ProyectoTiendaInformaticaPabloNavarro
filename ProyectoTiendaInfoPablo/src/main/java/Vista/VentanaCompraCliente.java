package Vista;

import Controlador.ControladorCompra;
import Modelo.Cliente;
import Modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VentanaCompraCliente extends JFrame {

    private final Cliente cliente;
    private final Map<Producto, Integer> carrito;
    private final JPanel panelCarrito;
    private final JLabel lblTotal;

    public VentanaCompraCliente(Cliente cliente, Map<Producto, Integer> carrito) {
        this.cliente = cliente;
        this.carrito = carrito;

        setTitle("Tu Carrito de Compra");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 🛒 Panel carrito (derecha)
        panelCarrito = new JPanel();
        panelCarrito.setLayout(new BoxLayout(panelCarrito, BoxLayout.Y_AXIS));
        JScrollPane scrollCarrito = new JScrollPane(panelCarrito);
        scrollCarrito.setBorder(BorderFactory.createTitledBorder("Productos en tu carrito"));
        add(scrollCarrito, BorderLayout.CENTER);

        // 🟨 Panel inferior (total + acciones)
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblTotal = new JLabel("Total: 0.00 €");

        JButton btnVaciar = new JButton("🧹 Vaciar carrito");
        btnVaciar.addActionListener(e -> {
            carrito.clear();
            actualizarCarrito();
        });

        JButton btnComprar = new JButton("✅ Finalizar compra");
        btnComprar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Confirmar compra?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean exito = ControladorCompra.procesarCompra(cliente, carrito);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Compra realizada con éxito.");
                    carrito.clear();
                    dispose();
                    new VentanaClienteSesion(cliente).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Error al procesar la compra.");
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
        double total = 0;

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

            total += p.getPrecio() * cantidad;
        }

        lblTotal.setText("Total: " + String.format("%.2f", total) + " €");

        panelCarrito.revalidate();
        panelCarrito.repaint();
    }
}
