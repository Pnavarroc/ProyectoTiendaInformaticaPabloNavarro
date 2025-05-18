package Vista;

import Modelo.CompraDAO;
import Modelo.Empleado;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VentanaInformeEmpleado extends JFrame {

    public VentanaInformeEmpleado(Empleado empleado) {
        setTitle("Clientes atendidos por: " + empleado.getNombre());
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Map<String, Double> datos = CompraDAO.obtenerClientesAtendidos(empleado.getId());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        if (datos.isEmpty()) {
            area.setText("Aún no has gestionado ninguna compra.");
        } else {
            for (Map.Entry<String, Double> entry : datos.entrySet()) {
                area.append("Cliente: " + entry.getKey() +
                        " | Total gestionado: " + entry.getValue() + "€\n");
            }
        }

        add(new JScrollPane(area));
    }
}