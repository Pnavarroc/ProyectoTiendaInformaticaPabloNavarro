package Lanzador;

import Vista.VentanaInicio;

public class Main {
    public static void main(String[] args) {
        // Asegura que la UI se construya en el hilo correcto
        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaInicio().setVisible(true);
        });
    }
}
