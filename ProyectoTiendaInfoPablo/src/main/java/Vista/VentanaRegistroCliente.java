package Vista;

import Controlador.ControladorCliente;
import Modelo.Cliente;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroCliente extends JFrame {

    private JTextField txtNombre, txtEmail, txtTelefono, txtDireccion;
    private JPasswordField txtContraseña;

    public VentanaRegistroCliente() {
        setTitle("Registro de Cliente");
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔷 Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        txtNombre = new JTextField();
        txtEmail = new JTextField();
        txtTelefono = new JTextField();
        txtDireccion = new JTextField();
        txtContraseña = new JPasswordField();

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Email:"));
        panelFormulario.add(txtEmail);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);
        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(txtDireccion);
        panelFormulario.add(new JLabel("Contraseña:"));
        panelFormulario.add(txtContraseña);

        JButton btnRegistrar = new JButton("✅ Registrar");
        JButton btnCancelar = new JButton("❌ Cancelar");

        panelFormulario.add(btnCancelar);
        panelFormulario.add(btnRegistrar);

        add(panelFormulario, BorderLayout.CENTER);

        // 🔘 Acciones
        btnRegistrar.addActionListener(e -> registrarCliente());
        btnCancelar.addActionListener(e -> {
            dispose();
            new VentanaCliente().setVisible(true);
        });
    }

    private void registrarCliente() {
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String contraseña = new String(txtContraseña.getPassword());

        Cliente nuevo = new Cliente(nombre, email, telefono, direccion, contraseña);

        if (ControladorCliente.validarCliente(nuevo)) {
            nuevo.guardarEnBD();
            JOptionPane.showMessageDialog(this, "¡Registro exitoso! Tu ID es: " + nuevo.getId(),
                    "Registro completado", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new VentanaCliente().setVisible(true);
        }
    }
}
