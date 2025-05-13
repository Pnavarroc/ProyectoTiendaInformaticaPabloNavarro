package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaCliente extends JFrame {

    public VentanaCliente() {
        setTitle("Cliente - Tienda Informática");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout vertical
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel lblTitulo = new JLabel("¿Qué deseas hacer?", JLabel.CENTER);
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnRegistrarse = new JButton("Registrarse");

        panel.add(lblTitulo);
        panel.add(btnIniciarSesion);
        panel.add(btnRegistrarse);

        add(panel);

        // Acción: Iniciar sesión
        btnIniciarSesion.addActionListener(e -> {
            new VentanaLoginCliente().setVisible(true);
            dispose();
        });

        // Acción: Registrarse
        btnRegistrarse.addActionListener(e -> {
            new VentanaRegistroCliente().setVisible(true);
            dispose();
        });
    }
}
