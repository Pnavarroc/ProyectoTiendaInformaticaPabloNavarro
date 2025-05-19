package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaEmpleado extends JFrame {

    public VentanaEmpleado() {
        setTitle("Empleado - Iniciar Sesión");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🔙 Botón Volver en la parte superior izquierda
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new BotonVolver(new VentanaInicio()));
        add(panelSuperior, BorderLayout.NORTH);


        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));

        JLabel lblTitulo = new JLabel("Bienvenido, empleado", JLabel.CENTER);
        JButton btnLogin = new JButton("Iniciar Sesión");

        panel.add(lblTitulo);
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(e -> {
            dispose();
            new VentanaLoginEmpleado().setVisible(true);
        });
    }
}
