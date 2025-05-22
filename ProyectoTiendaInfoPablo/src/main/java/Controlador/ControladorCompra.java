package Controlador;

import Modelo.*;

import java.util.List;
import java.util.Map;

public class ControladorCompra {

    public static boolean procesarCompra(Cliente cliente, Map<Producto, Integer> carrito) {
        if (carrito == null || carrito.isEmpty()) {
            return false;
        }

        try {
            Empleado empleado = EmpleadoDAO.obtenerEmpleadoAleatorio();
            if (empleado == null) return false;

            double total = calcularTotal(carrito);
            Compra compra = new Compra(cliente, empleado, total, carrito);

            CompraDAO.guardarCompra(compra);
            CompraDAO.guardarProductosComprados(compra);

            return true;

        } catch (Exception e) {
            System.err.println("❌ Error al procesar la compra:");
            e.printStackTrace();
            return false;
        }
    }

    private static double calcularTotal(Map<Producto, Integer> carrito) {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : carrito.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
        return total;
    }
    public static List<Compra> obtenerComprasPorCliente(int idCliente) {
        return CompraDAO.obtenerComprasPorCliente(idCliente);
    }
}
