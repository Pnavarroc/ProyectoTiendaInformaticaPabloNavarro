package Vista;

import Modelo.Cliente;
import javax.swing.*;
import java.awt.*;

public class VentanaClienteSesion extends JFrame {

    private Cliente cliente;

    public VentanaClienteSesion(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Panel Cliente - " + cliente.getNombre());
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔙 Botón Volver
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new BotonVolver(new VentanaCliente())); // o VentanaInicio()
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central con botones
        JPanel panelCentro = new JPanel(new GridLayout(4, 1, 20, 20));

        JButton btnComprar = new JButton("🛒 Comprar productos");
        JButton btnHistorial = new JButton("📜 Ver historial de compras");
        JButton btnBaja = new JButton("❌ Darse de baja");
        JButton btnCerrar = new JButton("🔓 Cerrar sesión");

        panelCentro.add(btnComprar);
        panelCentro.add(btnHistorial);
        panelCentro.add(btnBaja);
        panelCentro.add(btnCerrar);

        add(panelCentro, BorderLayout.CENTER);

        // 👉 Acciones

        btnComprar.addActionListener(e -> {
            dispose();
            new VentanaCompraCliente(cliente).setVisible(true);
        });

        btnHistorial.addActionListener(e -> new VentanaHistorialCliente(cliente).setVisible(true));

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

        btnCerrar.addActionListener(e -> {
            dispose();
            new VentanaCliente().setVisible(true);
        });
    }
}
