package Vista;

import Modelo.CompraDAO;

import javax.swing.*;
import java.util.Map;

public class VentanaContiene extends JFrame {

    public VentanaContiene(int idCompra) {
        setTitle("Detalle de la Compra #" + idCompra);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        Map<String, Integer> productos = CompraDAO.obtenerProductosPorCompra(idCompra);

        if (productos.isEmpty()) {
            area.setText("No hay productos asociados a esta compra.");
        } else {
            area.append("Productos comprados:\n");
            for (Map.Entry<String, Integer> entry : productos.entrySet()) {
                area.append("- " + entry.getKey() + " x" + entry.getValue() + "\n");
            }
        }

        add(new JScrollPane(area));
    }
}