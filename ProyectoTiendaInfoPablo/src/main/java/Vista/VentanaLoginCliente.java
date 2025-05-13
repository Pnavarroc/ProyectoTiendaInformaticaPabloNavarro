package Vista;

import Modelo.Cliente;
import Modelo.ClienteDAO;


import javax.swing.*;
import java.awt.*;

public class VentanaLoginCliente extends JFrame {
    private JTextField txtId;
    private JPasswordField txtContraseña;

    public VentanaLoginCliente() {
        setTitle("Iniciar Sesión - Cliente");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        txtId = new JTextField();
        txtContraseña = new JPasswordField();

        panel.add(new JLabel("ID Cliente:"));
        panel.add(txtId);

        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContraseña);

        JButton btnIniciar = new JButton("Iniciar Sesión");
        panel.add(btnIniciar);

        add(panel);

        btnIniciar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String contraseña = new String(txtContraseña.getPassword()).trim();

                Cliente cliente = ClienteDAO.iniciarSesion(id, contraseña);

                if (cliente != null) {
                    JOptionPane.showMessageDialog(this, "Bienvenido, " + cliente.getNombre());
                    dispose();
                    new VentanaClienteSesion(cliente).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "ID o contraseña incorrectos.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID debe ser un número válido.");
            }
        });
    }
}
