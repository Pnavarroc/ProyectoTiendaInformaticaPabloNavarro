package Vista;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;

import javax.swing.*;
import java.awt.*;

public class VentanaLoginEmpleado extends JFrame {

    private JTextField txtId;
    private JPasswordField txtContraseña;

    public VentanaLoginEmpleado() {
        setTitle("Iniciar Sesión - Empleado");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🔷 Panel principal
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));

        txtId = new JTextField();
        txtContraseña = new JPasswordField();

        panel.add(new JLabel("ID de Empleado:"));
        panel.add(txtId);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContraseña);

        JButton btnVolver = new JButton("🔙 Volver");
        JButton btnLogin = new JButton("🔓 Iniciar Sesión");

        panel.add(btnVolver);
        panel.add(btnLogin);

        add(panel, BorderLayout.CENTER);

        // 🔘 Acciones
        btnLogin.addActionListener(e -> iniciarSesion());
        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaInicio().setVisible(true);
        });
    }

    private void iniciarSesion() {
        String idTexto = txtId.getText().trim();
        String contraseña = new String(txtContraseña.getPassword());

        if (idTexto.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            Empleado empleado = EmpleadoDAO.iniciarSesion(id, contraseña);

            if (empleado != null) {
                JOptionPane.showMessageDialog(this, "Bienvenido, " + empleado.getNombre());
                dispose();
                new VentanaEmpleadoMenu(empleado).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "ID o contraseña incorrectos.",
                        "Login fallido", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número.",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }


}
