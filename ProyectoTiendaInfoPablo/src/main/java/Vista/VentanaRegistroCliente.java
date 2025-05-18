package Vista;

import Modelo.Cliente;
import Controlador.ControladorPersona;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroCliente extends JFrame {

    private JTextField txtNombre, txtEmail, txtTelefono, txtDireccion;
    private JPasswordField txtContraseña;

    public VentanaRegistroCliente() {
        setTitle("Registro de Cliente");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        txtNombre = new JTextField();
        txtEmail = new JTextField();
        txtTelefono = new JTextField();
        txtDireccion = new JTextField();
        txtContraseña = new JPasswordField();

        panel.add(new JLabel("Nombre:"));     panel.add(txtNombre);
        panel.add(new JLabel("Email:"));      panel.add(txtEmail);
        panel.add(new JLabel("Teléfono:"));   panel.add(txtTelefono);
        panel.add(new JLabel("Dirección:"));  panel.add(txtDireccion);
        panel.add(new JLabel("Contraseña:")); panel.add(txtContraseña);

        JButton btnRegistrar = new JButton("Registrarse");
        panel.add(btnRegistrar);

        add(panel);

        // Acción del botón
        btnRegistrar.addActionListener(e -> {
            if (camposVacios()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
                return;
            }

            Cliente cliente = new Cliente(
                    txtNombre.getText().trim(),
                    txtEmail.getText().trim(),
                    txtTelefono.getText().trim(),
                    txtDireccion.getText().trim(),
                    new String(txtContraseña.getPassword()).trim()
            );

            new ControladorPersona().registrarPersona(cliente);
            JOptionPane.showMessageDialog(this, "¡Cliente registrado con éxito!");
            volverAlMenuCliente();
        });
    }

    private boolean camposVacios() {
        return txtNombre.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty() ||
                txtTelefono.getText().trim().isEmpty() ||
                txtDireccion.getText().trim().isEmpty() ||
                txtContraseña.getPassword().length == 0;
    }

    private void volverAlMenuCliente() {
        dispose();
        new VentanaCliente().setVisible(true);
    }
}
