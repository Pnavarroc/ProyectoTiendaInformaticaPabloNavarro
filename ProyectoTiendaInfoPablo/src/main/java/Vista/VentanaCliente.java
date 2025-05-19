package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaCliente extends JFrame {

    public VentanaCliente() {
        setTitle("Área Cliente");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔙 Botón Volver en la parte superior izquierda
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new BotonVolver(new VentanaInicio()));
        add(panelSuperior, BorderLayout.NORTH);

        // ▶️ Contenido principal centrado
        JPanel centro = new JPanel(new GridLayout(2, 1, 10, 10));
        JButton btnIniciar = new JButton("Iniciar sesión");
        JButton btnRegistrar = new JButton("Registrarse");

        btnIniciar.addActionListener(e -> {
            dispose();
            new VentanaLoginCliente().setVisible(true);
        });

        btnRegistrar.addActionListener(e -> {
            dispose();
            new VentanaRegistroCliente().setVisible(true);
        });

        centro.add(btnIniciar);
        centro.add(btnRegistrar);
        add(centro, BorderLayout.CENTER);
    }
}

