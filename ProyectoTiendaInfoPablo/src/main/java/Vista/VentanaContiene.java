package Vista;

import Controlador.ControladorContiene;
import Modelo.Contiene;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaContiene extends JFrame {

    public VentanaContiene(int idCompra) {
        setTitle("Detalle de la compra #" + idCompra);
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        List<Contiene> productos = ControladorContiene.obtenerPorCompra(idCompra);

        if (productos.isEmpty()) {
            area.setText("No hay productos asociados a esta compra.");
        } else {
            StringBuilder sb = new StringBuilder("Productos comprados:\n\n");
            for (Contiene c : productos) {
                sb.append("- ").append(c.getProducto().getNombre())
                        .append(" x").append(c.getCantidad())
                        .append("\n");
            }
            area.setText(sb.toString());
        }

        JScrollPane scroll = new JScrollPane(area);
        add(scroll, BorderLayout.CENTER);
    }
}
