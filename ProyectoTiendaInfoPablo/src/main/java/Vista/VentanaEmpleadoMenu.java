package Vista;

import Modelo.Empleado;

import javax.swing.*;
import java.awt.*;

public class VentanaEmpleadoMenu extends JFrame {


    public VentanaEmpleadoMenu(Empleado empleado) {
        setTitle("Panel de Empleado");
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 🧾 Información del empleado
        JPanel panelInfo = new JPanel(new GridLayout(5, 1, 5, 5));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Datos del Empleado"));

        panelInfo.add(new JLabel("🧑 Nombre: " + empleado.getNombre()));
        panelInfo.add(new JLabel("📧 Email: " + empleado.getEmail()));
        panelInfo.add(new JLabel("📞 Teléfono: " + empleado.getTelefono()));
        panelInfo.add(new JLabel("🏠 Dirección: " + empleado.getDireccion()));

        add(panelInfo, BorderLayout.CENTER);

        // 🔘 Botones de acción
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));

        JButton btnHistorial = new JButton("📋 Historial de ventas");
        JButton btnCerrarSesion = new JButton("🔙 Cerrar sesión");

        panelBotones.add(btnHistorial);
        panelBotones.add(btnCerrarSesion);

        add(panelBotones, BorderLayout.SOUTH);

        // 🔁 Acciones
        btnHistorial.addActionListener(e -> {
            new VentanaHistorialEmpleado(empleado).setVisible(true);
        });

        btnCerrarSesion.addActionListener(e -> {
            dispose();
            new VentanaInicio().setVisible(true);
        });
    }
}
