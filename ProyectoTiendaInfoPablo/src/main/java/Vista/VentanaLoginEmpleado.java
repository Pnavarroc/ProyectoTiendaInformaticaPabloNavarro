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
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        txtId = new JTextField();
        txtContraseña = new JPasswordField();

        panel.add(new JLabel("ID Empleado:"));
        panel.add(txtId);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContraseña);

        JButton btnIniciar = new JButton("Iniciar Sesión");
        panel.add(btnIniciar);

        add(panel);

        btnIniciar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String pass = new String(txtContraseña.getPassword()).trim();

                Empleado empleado = EmpleadoDAO.iniciarSesion(id, pass);

                if (empleado != null) {
                    JOptionPane.showMessageDialog(this, "Bienvenido, " + empleado.getNombre());
                    dispose();
                    new VentanaEmpleadoMenu(empleado).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "ID o contraseña incorrectos.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
            }
        });
    }
}
