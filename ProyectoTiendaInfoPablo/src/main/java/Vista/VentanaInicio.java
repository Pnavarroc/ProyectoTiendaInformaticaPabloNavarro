package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaInicio extends JFrame {

    public VentanaInicio() {
        setTitle("Bienvenido a Tienda Informática");
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Tienda Informática", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        // 🔘 Botones principales
        JButton btnCliente = new JButton("👤 Cliente");
        JButton btnEmpleado = new JButton("🧑‍💼 Empleado");

        btnCliente.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnEmpleado.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JPanel panelCentral = new JPanel(new GridLayout(2, 1, 15, 15));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
        panelCentral.add(btnCliente);
        panelCentral.add(btnEmpleado);

        add(panelCentral, BorderLayout.CENTER);

        // 🔁 Acciones
        btnCliente.addActionListener(e -> {
            dispose();
            new VentanaCliente().setVisible(true);
        });

        btnEmpleado.addActionListener(e -> {
            dispose();
            new VentanaLoginEmpleado().setVisible(true);
        });
    }

    // ✅ Punto de entrada del programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaInicio().setVisible(true);
        });
    }
}
