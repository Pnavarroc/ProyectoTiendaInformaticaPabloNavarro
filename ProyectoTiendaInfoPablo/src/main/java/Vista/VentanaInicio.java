package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaInicio extends JFrame {

    public VentanaInicio() {
        setTitle("Bienvenido a Tienda Informática");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel y Layout
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel lblPregunta = new JLabel("¿Eres Cliente o Empleado?", JLabel.CENTER);
        JButton btnCliente = new JButton("Cliente");
        JButton btnEmpleado = new JButton("Empleado");

        // Añadir al panel
        panel.add(lblPregunta);
        panel.add(btnCliente);
        panel.add(btnEmpleado);

        add(panel);

        // Acciones
        btnCliente.addActionListener(e -> {
            new VentanaCliente().setVisible(true);
            dispose(); // cerrar esta ventana
        });

        btnEmpleado.addActionListener(e -> {
            new VentanaEmpleado().setVisible(true);
            dispose(); // cerrar esta ventana
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaInicio().setVisible(true));
    }
}
