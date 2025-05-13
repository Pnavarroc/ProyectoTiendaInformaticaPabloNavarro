package Vista;



import Modelo.Cliente;
import Modelo.ClienteDAO;

import javax.swing.*;
import java.awt.*;

public class VentanaClienteSesion extends JFrame {

    private Cliente cliente;

    public VentanaClienteSesion(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Área Cliente - " + cliente.getNombre());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel lblBienvenida = new JLabel("Sesión iniciada como: " + cliente.getNombre(), JLabel.CENTER);
        JButton btnDarseDeBaja = new JButton("Darse de baja");
        JButton btnCerrarSesion = new JButton("Cerrar sesión");

        panel.add(lblBienvenida);
        panel.add(btnDarseDeBaja);
        panel.add(btnCerrarSesion);

        add(panel);

        btnDarseDeBaja.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que quieres darte de baja?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean eliminado = ClienteDAO.eliminarCliente(cliente.getId());
                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Tu cuenta ha sido eliminada.");
                    dispose();
                    new VentanaCliente().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la cuenta.");
                }
            }
        });

        btnCerrarSesion.addActionListener(e -> {
            dispose();
            new VentanaCliente().setVisible(true);
        });
    }
}
