package Vista;

import Modelo.Cliente;
import Modelo.Producto;
import Modelo.ProductoDAO;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class VentanaClienteSesion extends JFrame {

    private Cliente cliente;
    private List<Producto> productos;
    private Map<Producto, Integer> carrito = new HashMap<>();

    public VentanaClienteSesion(Cliente cliente) {
        this.cliente = cliente;
        this.productos = ProductoDAO.getTodosLosProductos();

        setTitle("Bienvenido, " + cliente.getNombre());
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔙 Botón Volver en la parte superior izquierda
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new BotonVolver(new VentanaInicio()));
        add(panelSuperior, BorderLayout.NORTH);

        // Panel productos (centro)
        JPanel panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 2, 10, 10));

        for (Producto p : productos) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createTitledBorder(p.getNombre()));

            JTextArea info = new JTextArea(
                    "Marca: " + p.getMarca() + "\n" +
                            "Precio: " + p.getPrecio() + "€"
            );
            info.setEditable(false);
            card.add(info, BorderLayout.CENTER);

            JButton btnAdd = new JButton("Añadir al carrito");
            btnAdd.addActionListener(e -> {
                String cantidadStr = JOptionPane.showInputDialog(this,
                        "¿Cuántas unidades deseas de " + p.getNombre() + "?");
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad <= 0) return;
                    carrito.put(p, carrito.getOrDefault(p, 0) + cantidad);
                    JOptionPane.showMessageDialog(this, "Producto añadido.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Cantidad inválida.");
                }
            });

            card.add(btnAdd, BorderLayout.SOUTH);
            panelProductos.add(card);
        }

        JScrollPane scroll = new JScrollPane(panelProductos);
        add(scroll, BorderLayout.CENTER);

        // Panel de botones inferiores
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnHistorial = new JButton("Ver historial");
        JButton btnBaja = new JButton("Darse de baja");
        JButton btnCerrar = new JButton("Cerrar sesión");

        panelInferior.add(btnHistorial);
        panelInferior.add(btnBaja);
        panelInferior.add(btnCerrar);

        add(panelInferior, BorderLayout.SOUTH);



        // Acciones



        btnHistorial.addActionListener(e -> new VentanaHistorialCliente(cliente).setVisible(true));

        btnCerrar.addActionListener(e -> {
            dispose();
            new VentanaCliente().setVisible(true);
        });

        btnBaja.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Darse de baja?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean eliminado = Modelo.ClienteDAO.eliminarCliente(cliente.getId());
                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Cuenta eliminada.");
                    dispose();
                    new VentanaCliente().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar.");
                }
            }
        });
    }
}
