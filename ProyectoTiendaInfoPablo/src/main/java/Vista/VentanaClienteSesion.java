package Vista;

import Controlador.ControladorCliente;
import Controlador.ControladorProducto;
import Modelo.Cliente;
import Modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentanaClienteSesion extends JFrame {

    private final Cliente cliente;
    private final Map<Producto, Integer> carrito = new HashMap<>();

    public VentanaClienteSesion(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Bienvenido, " + cliente.getNombre());
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔙 Botón Volver
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new BotonVolver(new VentanaCliente()));
        add(panelSuperior, BorderLayout.NORTH);

        // 🟩 Panel de productos
        JPanel panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 2, 10, 10));

        List<Producto> productos = ControladorProducto.obtenerTodos();

        for (Producto p : productos) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createTitledBorder(p.getNombre()));

            JTextArea info = new JTextArea("Marca: " + p.getMarca() + "\nPrecio: " + p.getPrecio() + "€");
            info.setEditable(false);

            JButton btnAdd = new JButton("Añadir al carrito");
            btnAdd.addActionListener(e -> {
                String cantidadStr = JOptionPane.showInputDialog(this, "¿Cuántas unidades de " + p.getNombre() + "?");
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad <= 0) return;
                    carrito.put(p, carrito.getOrDefault(p, 0) + cantidad);
                    JOptionPane.showMessageDialog(this, "Producto añadido al carrito.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Cantidad inválida.");
                }
            });

            card.add(info, BorderLayout.CENTER);
            card.add(btnAdd, BorderLayout.SOUTH);
            panelProductos.add(card);
        }

        JScrollPane scroll = new JScrollPane(panelProductos);
        add(scroll, BorderLayout.CENTER);

        // 🟦 Botones inferiores
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnHistorial = new JButton("📜 Ver historial");
        JButton btnBaja = new JButton("❌ Darse de baja");
        JButton btnCerrar = new JButton("🔓 Cerrar sesión");
        JButton btnComprar = new JButton("🛒 Comprar productos");

        btnHistorial.addActionListener(e -> new VentanaHistorialCliente(cliente).setVisible(true));

        btnBaja.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Darse de baja?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean eliminado = ControladorCliente.eliminarCliente(cliente.getId());
                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Cuenta eliminada.");
                    dispose();
                    new VentanaCliente().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar cuenta.");
                }
            }
        });

        btnCerrar.addActionListener(e -> {
            dispose();
            new VentanaCliente().setVisible(true);
        });

        btnComprar.addActionListener(e -> {
            if (carrito.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El carrito está vacío.");
                return;
            }
            dispose();
            new VentanaCompraCliente(cliente, carrito).setVisible(true);
        });

        panelInferior.add(btnHistorial);
        panelInferior.add(btnBaja);
        panelInferior.add(btnCerrar);
        panelInferior.add(btnComprar);

        add(panelInferior, BorderLayout.SOUTH);
    }
}
