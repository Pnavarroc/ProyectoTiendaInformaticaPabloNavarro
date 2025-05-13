package Vista;

import Modelo.Empleado;
import Modelo.EmpleadoDAO;

import javax.swing.*;
import java.awt.*;

public class VentanaEmpleado extends JFrame {

    private JTextField txtId;

    public VentanaEmpleado() {
        setTitle("Iniciar Sesión - Empleado");
        setSize(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        txtId = new JTextField();

        panel.add(new JLabel("ID Empleado:"));
        panel.add(txtId);

        JButton btnIniciar = new JButton("Iniciar Sesión");
        panel.add(btnIniciar);

        add(panel);

        btnIniciar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());

                Empleado empleado = EmpleadoDAO.buscarEmpleadoPorId(id);

                if (empleado != null) {
                    JOptionPane.showMessageDialog(this, "Bienvenido, " + empleado.getNombre());
                    dispose();
                    new VentanaEmpleadoMenu(empleado).setVisible(true); // ⬅️ Abre menú
                } else {
                    JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID debe ser un número válido.");
            }
        });
    }
}

