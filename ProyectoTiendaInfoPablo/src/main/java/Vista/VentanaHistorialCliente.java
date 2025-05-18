package Vista;

import Modelo.Compra;
import Modelo.CompraDAO;
import Modelo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorialCliente extends JFrame {

    public VentanaHistorialCliente(Cliente cliente) {
        setTitle("Historial de Compras - " + cliente.getNombre());
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<Compra> compras = CompraDAO.obtenerComprasPorCliente(cliente.getId());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        if (compras.isEmpty()) {
            area.setText("No hay compras registradas.");
        } else {
            for (Compra c : compras) {
                JButton btn = new JButton("Ver detalles de Compra #" + c.getId() + " | Total: " + c.getTotal() + "€");
                btn.addActionListener(e -> new VentanaDetalleCompra(c.getId()).setVisible(true));
                panel.add(btn);
            }
        }

        JScrollPane scroll = new JScrollPane(area);
        add(scroll);
    }
}
