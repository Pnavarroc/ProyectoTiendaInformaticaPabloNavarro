package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaEmpleado extends JFrame {

    public VentanaEmpleado() {
        setTitle("Empleado - Iniciar Sesión");
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
