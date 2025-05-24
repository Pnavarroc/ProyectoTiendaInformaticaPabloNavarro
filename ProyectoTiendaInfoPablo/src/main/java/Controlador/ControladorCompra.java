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


/**
 * Controlador que gestiona la lógica relacionada con las compras.
 * Organiza el proceso de compra, incluyendo selección de empleado,
 * cálculo del total y almacenamiento en base de datos.
 */

public class ControladorCompra {


    /**
     * Procesa una compra realizada por un cliente.
     *
     * Este método realiza toda la lógica necesaria para registrar una compra:
     * 1. Asigna un empleado aleatorio que gestionará la venta.
     * 2. Calcula el total de la compra sumando los productos del carrito.
     * 3. Crea un objeto Compra con todos los datos.
     * 4. Guarda la compra en la base de datos.
     * 5. Guarda el detalle (los productos) comprados en la tabla intermedia.
     */
    public static boolean procesarCompra(Cliente cliente, Map<Producto, Integer> carrito) {
        // Validación básica: si faltan datos, no se puede continuar
        if (cliente == null || carrito == null || carrito.isEmpty()) return false;

        try {
            //Asignamos un empleado aleatorio
            Empleado empleado = EmpleadoDAO.obtenerEmpleadoAleatorio();
            if (empleado == null) return false;
            //Calculamos el total
            double total = calcularTotal(carrito);
            // Se crea y guarda la compra completa
            Compra compra = new Compra(cliente, empleado, total, carrito);
            CompraDAO.guardarCompra(compra);
            CompraDAO.guardarProductosComprados(compra);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Calcula el total del carrito sumando el precio de cada producto multiplicado por su cantidad.
     *
     * El carrito es un Map donde:
     * - La clave (Producto) representa el producto.
     * - El valor (Integer) representa cuántas unidades de ese producto hay.
     */
    private static double calcularTotal(Map<Producto, Integer> carrito) {

        return carrito.entrySet()      // Obtenemos todos los pares producto-cantidad
                .stream()                  // Convertimos la colección en un flujo de datos
                .mapToDouble(entry ->      // Transformamos cada entrada a un número decimal (double)
                                entry.getKey().getPrecio() * entry.getValue()
                        // getKey() → Producto
                        // getPrecio() → Precio de ese producto
                        // getValue() → Cantidad de ese producto
                        // Multiplicamos precio × cantidad
                )
                .sum(); // Sumamos todos los subtotales para obtener el total final
    }

    public static List<Compra> obtenerComprasPorCliente(int idCliente) {
        return CompraDAO.obtenerComprasPorCliente(idCliente);
    }

    public static List<Compra> obtenerComprasPorEmpleado(int idEmpleado) {
        return CompraDAO.obtenerComprasPorEmpleado(idEmpleado);
    }
}
