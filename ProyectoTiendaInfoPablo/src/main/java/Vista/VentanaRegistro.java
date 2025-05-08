package Vista;



import Modelo.Cliente;
import Modelo.Empleado;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private JTextField txtNombre, txtEmail, txtTelefono, txtDireccion, txtContraseña;
    private JButton btnCrearCliente, btnCrearEmpleado;

    public VentanaRegistro() {
        setTitle("Registro de Personas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana

        // Crear panel y layout
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        // Componentes
        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);

        panel.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panel.add(txtDireccion);

        panel.add(new JLabel("Contraseña (solo cliente):"));
        txtContraseña = new JTextField();
        panel.add(txtContraseña);

        // Botones
        btnCrearCliente = new JButton("Crear Cliente");
        btnCrearEmpleado = new JButton("Crear Empleado");
        panel.add(btnCrearCliente);
        panel.add(btnCrearEmpleado);

        add(panel); // Agregar panel a la ventana

        // Acción: Crear Cliente
        btnCrearCliente.addActionListener(e -> {
            try {
                Cliente cliente = new Cliente(
                        generarID(), // Temporal, puedes reemplazar por auto-incremento real
                        txtNombre.getText(),
                        txtEmail.getText(),
                        txtTelefono.getText(),
                        txtDireccion.getText(),
                        txtContraseña.getText()
                );
                cliente.guardarEnBD();
                JOptionPane.showMessageDialog(this, "Cliente guardado en la base de datos.");
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar cliente: " + ex.getMessage());
            }
        });

        // Acción: Crear Empleado
        btnCrearEmpleado.addActionListener(e -> {
            try {
                Empleado empleado = new Empleado(
                        generarID(),
                        txtNombre.getText(),
                        txtEmail.getText(),
                        txtTelefono.getText(),
                        txtDireccion.getText()
                );
                empleado.guardarEnBD();
                JOptionPane.showMessageDialog(this, "Empleado guardado en la base de datos.");
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar empleado: " + ex.getMessage());
            }
        });
    }

    // Método auxiliar para limpiar campos del formulario
    private void limpiarCampos() {
        txtNombre.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtContraseña.setText("");
    }

    // Método temporal para generar ID (solo para pruebas sin auto_increment)
    private int generarID() {
        return (int) (Math.random() * 10000); // ⚠️ Reemplaza por lógica real si usas AUTO_INCREMENT
    }

    // Main para ejecutar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaRegistro().setVisible(true);
        });
    }
}
