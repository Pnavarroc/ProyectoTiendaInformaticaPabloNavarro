package Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BotonVolver extends JButton {

    public BotonVolver(JFrame destino) {
        super("⬅ Volver");
        setFocusable(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setToolTipText("Volver a la ventana anterior");

        addActionListener((ActionEvent e) -> {
            SwingUtilities.getWindowAncestor(this).dispose(); // Cierra la ventana actual
            destino.setVisible(true); // Abre la ventana destino
        });
    }
}
