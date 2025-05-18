package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaInicio extends JFrame {

    public VentanaInicio() {
        setTitle("Bienvenido a Tienda Informática");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        JLabel lblTitulo = new JLabel("¿Cómo deseas acceder?", JLabel.CENTER);
        JButton btnCliente = new JButton("Cliente");
        JButton btnEmpleado = new JButton("Empleado");


        panel.add(lblTitulo);
        panel.add(btnCliente);
        panel.add(btnEmpleado);


        add(panel);

        // Acción: acceder como cliente
        btnCliente.addActionListener(e -> {
            new VentanaCliente().setVisible(true);
            dispose();
        });

        // Acción: acceder como empleado
        btnEmpleado.addActionListener(e -> {
            new Vista.VentanaEmpleado().setVisible(true);
            dispose();
        });

       
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaInicio().setVisible(true));
    }
}
