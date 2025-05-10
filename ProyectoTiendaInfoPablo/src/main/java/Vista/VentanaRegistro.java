package Vista;

import Controlador.ControladorPersona;
import Modelo.Cliente;
import Modelo.Empleado;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {


    private JTextField txtNombre, txtEmail, txtTelefono, txtDireccion, txtContraseña;



    public VentanaRegistro() {
        setTitle("Registro de Personas");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.add("Cliente", panelCliente());
        pestañas.add("Empleado", panelEmpleado());

        add(pestañas);

    }

    private JPanel panelCliente() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        txtNombre = new JTextField();
        txtEmail = new JTextField();
        txtTelefono = new JTextField();
        txtDireccion = new JTextField();
        txtContraseña = new JTextField();

        panel.add(new JLabel("Nombre:")); panel.add(txtNombre);
        panel.add(new JLabel("Email:")); panel.add(txtEmail);
        panel.add(new JLabel("Teléfono:")); panel.add(txtTelefono);
        panel.add(new JLabel("Dirección:")); panel.add(txtDireccion);
        panel.add(new JLabel("Contraseña:")); panel.add(txtContraseña);

        JButton btnRegistrar = new JButton("Registrar Cliente");
        panel.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            Cliente cliente = new Cliente(
                    txtNombre.getText(),
                    txtEmail.getText(),
                    txtTelefono.getText(),
                    txtDireccion.getText(),
                    txtContraseña.getText()
            );

            new ControladorPersona().registrarPersona(cliente);
            JOptionPane.showMessageDialog(this, "Cliente registrado correctamente.");
            limpiarCampos();
        });

        return panel;
    }

    private JPanel panelEmpleado() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        txtNombre = new JTextField();
        txtEmail = new JTextField();
        txtTelefono = new JTextField();
        txtDireccion = new JTextField();

        panel.add(new JLabel("Nombre:")); panel.add(txtNombre);
        panel.add(new JLabel("Email:")); panel.add(txtEmail);
        panel.add(new JLabel("Teléfono:")); panel.add(txtTelefono);
        panel.add(new JLabel("Dirección:")); panel.add(txtDireccion);

        JButton btnRegistrar = new JButton("Registrar Empleado");
        panel.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            Empleado empleado = new Empleado(
                    txtNombre.getText(),
                    txtEmail.getText(),
                    txtTelefono.getText(),
                    txtDireccion.getText()
            );

            new ControladorPersona().registrarPersona(empleado);
            JOptionPane.showMessageDialog(this, "Empleado registrado correctamente.");
            limpiarCampos();
        });

        return panel;
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        if (txtContraseña != null) {
            txtContraseña.setText("");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new VentanaRegistro().setVisible(true));
    }
}
