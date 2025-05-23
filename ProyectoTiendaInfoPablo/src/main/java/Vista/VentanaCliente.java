package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaCliente extends JFrame {

    public VentanaCliente() {
        setTitle("Área Cliente");
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Acceso Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        // 🔘 Botones de acción
        JButton btnRegistro = new JButton("📝 Registrarse");
        JButton btnLogin = new JButton("🔓 Iniciar sesión");
        JButton btnVolver = new JButton("🔙 Volver");

        btnRegistro.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnVolver.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel panelCentral = new JPanel(new GridLayout(2, 1, 15, 15));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
        panelCentral.add(btnRegistro);
        panelCentral.add(btnLogin);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInferior.add(btnVolver);

        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // 🔁 Acciones
        btnRegistro.addActionListener(e -> {
            dispose();
            new VentanaRegistroCliente().setVisible(true);
        });

        btnLogin.addActionListener(e -> {
            dispose();
            new VentanaLoginCliente().setVisible(true);
        });

        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaInicio().setVisible(true);
        });
    }
}

