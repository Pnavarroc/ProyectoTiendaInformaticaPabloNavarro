package Vista;

import Controlador.ControladorCompra;
import Modelo.Compra;
import Modelo.Empleado;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorialEmpleado extends JFrame {

    public VentanaHistorialEmpleado(Empleado empleado) {
        setTitle("Historial de Ventas - " + empleado.getNombre());
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        System.out.println("Empleado actual ID: " + empleado.getId());


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<Compra> compras = ControladorCompra.obtenerComprasPorEmpleado(empleado.getId());

        if (compras == null || compras.isEmpty()) {
            panel.add(new JLabel("Este empleado no ha gestionado ninguna compra."));
        } else {
            for (Compra compra : compras) {
                String cliente = (compra.getCliente() != null)
                        ? compra.getCliente().getNombre()
                        : "Cliente eliminado";

                String texto = "Compra #" + compra.getId()
                        + " | Cliente: " + cliente
                        + " | Total: " + String.format("%.2f", compra.getTotal()) + "€";

                JButton btn = new JButton(texto);
                btn.addActionListener(e -> new VentanaContiene(compra.getId()).setVisible(true));
                panel.add(btn);
            }
        }

        JScrollPane scroll = new JScrollPane(panel);
        add(scroll, BorderLayout.CENTER);

    }
}
