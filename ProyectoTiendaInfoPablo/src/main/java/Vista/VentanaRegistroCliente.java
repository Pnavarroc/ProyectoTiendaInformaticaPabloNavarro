package Vista;

import Controlador.ControladorCliente;
import Modelo.Cliente;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroCliente extends JFrame {

    public VentanaRegistroCliente() {
        setTitle("Registro de Cliente");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        JTextField txtNombre = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtDireccion = new JTextField();
        JPasswordField txtContraseña = new JPasswordField();

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccion);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContraseña);

        JButton btnRegistrar = new JButton("Registrarse");

        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String email = txtEmail.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String contraseña = new String(txtContraseña.getPassword()).trim();

            if (contraseña.length() < 6) {
                JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 6 caracteres.");
                return;
            }

            Cliente cliente = new Cliente(nombre, email, telefono, direccion, contraseña);
            boolean exito = ControladorCliente.registrarCliente(cliente);

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Registro exitoso! Tu ID es: " + cliente.getId());
                dispose();
                new VentanaCliente().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Error al registrar cliente. Revisa los datos.");
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnRegistrar);

        add(panel, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
    }
}
