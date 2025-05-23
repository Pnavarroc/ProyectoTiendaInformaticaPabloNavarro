package Controlador;

import Modelo.Cliente;
import Modelo.Compra;
import Modelo.CompraDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ControladorCompra {

    public static boolean procesarCompra(Cliente cliente, Map<Producto, Integer> carrito) {
        if (cliente == null || carrito == null || carrito.isEmpty()) return false;

        try {
            Empleado empleado = EmpleadoDAO.obtenerEmpleadoAleatorio(); // o asignado
            if (empleado == null) return false;

            double total = calcularTotal(carrito);

            Compra compra = new Compra(cliente, empleado, total, carrito);
            CompraDAO.guardarCompra(compra);
            CompraDAO.guardarProductosComprados(compra);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static double calcularTotal(Map<Producto, Integer> carrito) {
        return carrito.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrecio() * e.getValue())
                .sum();
    }

    public static List<Compra> obtenerComprasPorCliente(int idCliente) {
        return CompraDAO.obtenerComprasPorCliente(idCliente);
    }

    public static List<Compra> obtenerComprasPorEmpleado(int idEmpleado) {
        return CompraDAO.obtenerComprasPorEmpleado(idEmpleado);
    }
}
