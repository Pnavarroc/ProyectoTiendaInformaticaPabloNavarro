package Vista;

import Modelo.Cliente;
import Modelo.Compra;
import Controlador.ControladorCompra;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorialCliente extends JFrame {

    public VentanaHistorialCliente(Cliente cliente) {
        setTitle("Historial de Compras - " + cliente.getNombre());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<Compra> compras = ControladorCompra.obtenerComprasPorCliente(cliente.getId());

        if (compras == null || compras.isEmpty()) {
            panel.add(new JLabel("No hay compras registradas."));
        } else {
            for (Compra compra : compras) {
                String texto = "Compra #" + compra.getId() + " | Total: " + compra.getTotal() +
                        "€ | Atendida por: " + compra.getEmpleado().getNombre();

                JButton btn = new JButton(texto);
                btn.addActionListener(e -> new VentanaContiene(compra.getId()).setVisible(true));
                panel.add(btn);
            }
        }

        JScrollPane scroll = new JScrollPane(panel);
        add(scroll);
    }
}
