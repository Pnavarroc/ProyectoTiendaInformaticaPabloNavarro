package Vista;

import Modelo.Empleado;

import javax.swing.*;
import java.awt.*;

public class VentanaEmpleadoMenu extends JFrame {

    private Empleado empleado;

    public VentanaEmpleadoMenu(Empleado empleado) {
        this.empleado = empleado;

        setTitle("Panel de Empleado");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel lblBienvenida = new JLabel("Bienvenido, " + empleado.getNombre(), JLabel.CENTER);
        JButton btnVerDatos = new JButton("Ver mis datos");
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        JButton btnInforme = new JButton("Clientes Atendidos");

        panel.add(lblBienvenida);
        panel.add(btnVerDatos);
        panel.add(btnCerrarSesion);

        add(panel);


        btnInforme.addActionListener(e -> {
            new VentanaInformeEmpleado(empleado).setVisible(true);
        });

        panel.add(btnInforme);

        // Mostrar información personal
        btnVerDatos.addActionListener(e -> {
            String info = String.format("""
                    ID: %d
                    Nombre: %s
                    Email: %s
                    Teléfono: %s
                    Dirección: %s
                    """, empleado.getId(), empleado.getNombre(), empleado.getEmail(),
                    empleado.getTelefono(), empleado.getDireccion());

            JOptionPane.showMessageDialog(this, info, "Información del Empleado", JOptionPane.INFORMATION_MESSAGE);
        });

        // Volver al inicio
        btnCerrarSesion.addActionListener(e -> {
            dispose();
            new VentanaInicio().setVisible(true);
        });
    }
}
