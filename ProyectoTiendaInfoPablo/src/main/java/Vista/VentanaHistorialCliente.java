package Vista;

import Controlador.ControladorCompra;
import Modelo.Cliente;
import Modelo.Compra;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorialCliente extends JFrame {

    public VentanaHistorialCliente(Cliente cliente) {
        setTitle("Historial de Compras - " + cliente.getNombre());
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<Compra> compras = ControladorCompra.obtenerComprasPorCliente(cliente.getId());

        if (compras == null || compras.isEmpty()) {
            panel.add(new JLabel("No se han registrado compras."));
        } else {
            for (Compra compra : compras) {
                String texto = "Compra #" + compra.getId() +
                        " | Total: " + String.format("%.2f", compra.getTotal()) + "€";

                JButton btn = new JButton(texto);
                btn.addActionListener(e ->
                        new VentanaContiene(compra.getId()).setVisible(true));

                panel.add(btn);
            }
        }

        JScrollPane scroll = new JScrollPane(panel);
        add(scroll, BorderLayout.CENTER);
    }
}
