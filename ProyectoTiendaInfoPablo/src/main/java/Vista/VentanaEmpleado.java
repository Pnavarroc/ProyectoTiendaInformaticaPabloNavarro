package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaEmpleado extends JFrame {

    public VentanaEmpleado() {
        setTitle("Empleado - Tienda Informática");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel lblTitulo = new JLabel("¿Qué deseas hacer?", JLabel.CENTER);
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnRegistrar = new JButton("Registrarse como Empleado");

        panel.add(lblTitulo);
        panel.add(btnIniciarSesion);
        panel.add(btnRegistrar);

        add(panel);

        btnIniciarSesion.addActionListener(e -> {
            new VentanaLoginCliente().setVisible(true);
            dispose();
        });

        btnRegistrar.addActionListener(e -> {
            new VentanaRegistroEmpleado().setVisible(true);
            dispose();
        });
    }
}
